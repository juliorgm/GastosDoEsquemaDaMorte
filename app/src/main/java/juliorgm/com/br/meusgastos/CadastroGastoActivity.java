package juliorgm.com.br.meusgastos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import juliorgm.com.br.meusgastos.model.Gasto;

public class CadastroGastoActivity extends AppCompatActivity {

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

                Gasto gasto = new Gasto(valor, data,descricao,categoria );
            }
        });
    }

    private void carregaSpinner(){
        spinnerCategoria = findViewById(R.id.cadastro_spinner_categoria);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.lista_categorias,
                android.R.layout.simple_spinner_item);

        spinnerCategoria.setAdapter(adapter);
    }
}
