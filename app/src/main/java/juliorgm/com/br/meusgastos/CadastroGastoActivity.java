package juliorgm.com.br.meusgastos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import juliorgm.com.br.meusgastos.dao.GastoDAO;
import juliorgm.com.br.meusgastos.helper.Util;
import juliorgm.com.br.meusgastos.model.Gasto;

public class CadastroGastoActivity extends AppCompatActivity {

    public static final int RESULTADO_ERRO = -1;
    private EditText campoValor;
    private EditText campoData;
    private EditText campoDescricao;
    private Spinner  spinnerCategoria;
    private Button   btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_gasto);

        campoValor = findViewById(R.id.cadastro_edittext_valor);
        campoData = findViewById(R.id.cadastro_edittext_data);
        campoDescricao = findViewById(R.id.cadastro_edittext_descricao);
        btnSalvar = findViewById(R.id.cadastro_button_salvar);

        carregaSpinner();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double valor = Double.valueOf(campoValor.getText().toString());
                String data = campoData.getText().toString();
                String descricao = campoDescricao.getText().toString();
                String categoria = spinnerCategoria.getSelectedItem().toString();

                // E se os campos estiverem vazios?
                Gasto gasto = new Gasto(valor, data,descricao,categoria);
                GastoDAO dao = new GastoDAO(CadastroGastoActivity.this);

                long resultado = dao.inserir(gasto);

                if (resultado == RESULTADO_ERRO){
                    Util.messagemFromResource(CadastroGastoActivity.this,R.string.mensagem_erro);
                }else {
                    Util.messagem(CadastroGastoActivity.this,"Gasto inserido com sucesso!");
                    finish();
                }
            }
        });
    }

    private void carregaSpinner(){
        spinnerCategoria = findViewById(R.id.cadastro_spinner_categoria);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.lista_categorias,
                R.layout.layout_spinner);

        spinnerCategoria.setAdapter(adapter);
    }
}
