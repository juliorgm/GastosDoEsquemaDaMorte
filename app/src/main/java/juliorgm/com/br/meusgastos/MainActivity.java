package juliorgm.com.br.meusgastos;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView listViewGastos;
    private FloatingActionButton fabAdicionarGasto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewGastos = findViewById(R.id.listview_gastos);
        fabAdicionarGasto = findViewById(R.id.fab_cadastrar_gasto);

        String [] listaTeste = {"João","Pedro","Maria"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listaTeste);

        listViewGastos.setAdapter(adapter);

        fabAdicionarGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CadastroGastoActivity.class);
                startActivity(intent);
            }
        });
    }
}
