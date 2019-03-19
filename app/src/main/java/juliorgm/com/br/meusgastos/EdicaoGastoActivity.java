package juliorgm.com.br.meusgastos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

import juliorgm.com.br.meusgastos.dao.GastoDAO;
import juliorgm.com.br.meusgastos.helper.Util;
import juliorgm.com.br.meusgastos.model.Gasto;

public class EdicaoGastoActivity extends AppCompatActivity {

    public static final int RESULTADO_ERRO = 0;
    private EditText campoValor;
    private EditText campoData;
    private EditText campoDescricao;
    private Spinner spinnerCategoria;
    private Button btnSalvar;
    private Gasto gasto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao_gastos);

        campoValor = findViewById(R.id.edicao_edittext_valor);
        campoData = findViewById(R.id.edicao_edittext_data);
        campoDescricao = findViewById(R.id.edicao_edittext_descricao);
        btnSalvar = findViewById(R.id.edicao_button_salvar);

        carregaSpinner();
        carregaFormulario();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double valor = Double.valueOf(campoValor.getText().toString());
                String data = campoData.getText().toString();
                String descricao = campoDescricao.getText().toString();
                String categoria = spinnerCategoria.getSelectedItem().toString();

                // E se os campos estiverem vazios?
                gasto = new Gasto(gasto.getIdGasto(), valor, data,descricao,categoria);
                GastoDAO dao = new GastoDAO(EdicaoGastoActivity.this);

                long resultado = dao.editar(gasto);

                if (resultado == RESULTADO_ERRO){
                    Util.messagemFromResource(EdicaoGastoActivity.this,R.string.mensagem_erro);
                }else {
                    Util.messagemFromResource(EdicaoGastoActivity.this,R.string.operacao_sucesso);
                    finish();
                }
            }
        });
    }

    private void carregaSpinner(){
        spinnerCategoria = findViewById(R.id.edicao_spinner_categoria);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.lista_categorias,
                R.layout.layout_spinner);

        spinnerCategoria.setAdapter(adapter);
    }

    private Gasto pegaGastoDaIntent(){
        Intent intent = getIntent();
        return (Gasto) intent.getSerializableExtra(Util.EDITAR_GASTO);
    }

    private void carregaFormulario(){
        gasto = pegaGastoDaIntent();

        if (gasto != null){
            campoValor.setText(gasto.getValorToString());
            campoData.setText(gasto.getData());
            campoDescricao.setText(gasto.getDescricao());
            campoValor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                }
            });
            setSpinnerCategoria(gasto.getCategoria());
        }else{
            Util.messagemFromResource(this,R.string.erro_objeto_edicao);
            finish();
        }
    }

    private void setSpinnerCategoria(String categoria){
        //Para setar o spinner temos que recuperar o array e idenficar qual é o index da llista
        //Obs: Seria mais facil se ao inves de cadastrar o nome da categoria cadastrassemos o codigo

        //Recuperando o array
        String [] lista = getResources().getStringArray(R.array.lista_categorias);
        List<String> list = Arrays.asList(lista);//Transforman
        int index = list.indexOf(categoria);
        spinnerCategoria.setSelection(index);
    }
}
