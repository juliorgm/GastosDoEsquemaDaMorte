package juliorgm.com.br.meusgastos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import juliorgm.com.br.meusgastos.dao.GastoDAO;
import juliorgm.com.br.meusgastos.helper.Util;
import juliorgm.com.br.meusgastos.model.Gasto;

public class CadastroGastoActivity extends AppCompatActivity {

    public static final int RESULTADO_ERRO = -1;
    private EditText campoValor;
    private EditText campoData;
    private EditText campoDescricao;
    private Spinner spinnerCategoria;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_gasto);

        campoValor = findViewById(R.id.cadastro_edittext_valor);
        campoData = findViewById(R.id.cadastro_edittext_data);
        campoDescricao = findViewById(R.id.cadastro_edittext_descricao);
        btnSalvar = findViewById(R.id.cadastro_button_salvar);

        carregaMascaraEditText();
        carregaSpinner();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaCampos()) {
                    inserirGasto();
                }
            }
        });
    }

    private void inserirGasto() {
        Gasto gasto = getGasto();
        GastoDAO dao = new GastoDAO(CadastroGastoActivity.this);

        long resultado = dao.inserir(gasto);

        if (resultado == RESULTADO_ERRO) {
            Util.messagemFromResource(CadastroGastoActivity.this, R.string.mensagem_erro);
        } else {
            Util.messagem(CadastroGastoActivity.this, "Gasto inserido com sucesso!");
            finish();
        }
    }

    private Gasto getGasto() {
        double valor = Double.valueOf(campoValor.getText().toString());
        String data = campoData.getText().toString();
        data = data.replace("/","");

        //data = data.replaceAll("([0-9]{2})([0-9]{2})([0-9]{4})","$1/$2/$3");
        String descricao = campoDescricao.getText().toString();
        String categoria = spinnerCategoria.getSelectedItem().toString();

        // E se os campos estiverem vazios?
        return new Gasto(valor, data, descricao, categoria);
    }

    private void carregaSpinner() {
        spinnerCategoria = findViewById(R.id.cadastro_spinner_categoria);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.lista_categorias,
                R.layout.layout_spinner);

        spinnerCategoria.setAdapter(adapter);
    }

    private boolean validaCampos() {
        String campoVazio = getResources().getString(R.string.campo_vazio);

        if (campoData.getText().toString().isEmpty()) {
            campoData.setError(campoVazio);
            return false;
        }

        if (campoDescricao.getText().toString().isEmpty()) {
            campoDescricao.setError(campoVazio);
            return false;
        }

        if (campoValor.getText().toString().isEmpty()) {
            campoDescricao.setError(campoVazio);
            return false;
        }
        return true;
    }

    private void carregaMascaraEditText(){
        SimpleMaskFormatter smf = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(campoData, smf);
        campoData.addTextChangedListener(mtw);
    }

}
