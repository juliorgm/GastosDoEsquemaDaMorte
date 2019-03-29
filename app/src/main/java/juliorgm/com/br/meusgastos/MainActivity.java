package juliorgm.com.br.meusgastos;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import juliorgm.com.br.meusgastos.adapter.GastoAdapter;
import juliorgm.com.br.meusgastos.dao.GastoDAO;
import juliorgm.com.br.meusgastos.helper.Util;
import juliorgm.com.br.meusgastos.model.Gasto;

public class MainActivity extends AppCompatActivity {

    private ListView listViewGastos;
    private FloatingActionButton fabAdicionarGasto;
    private TextView textSomaTotal, textPrimeiroGasto;
    private GastoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewGastos = findViewById(R.id.listview_gastos);
        fabAdicionarGasto = findViewById(R.id.fab_cadastrar_gasto);
        textSomaTotal = findViewById(R.id.textSomaTotal);
        textPrimeiroGasto = findViewById(R.id.textPrimeiroGasto);

        dao = new GastoDAO(this);

        fabAdicionarGasto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,CadastroGastoActivity.class);
                    startActivity(intent);
                }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Gasto> listaDeGastos = dao.listarTodosOsGastos();
        double totalDeGastos = dao.getValorTotalDeGastos();

        if(listaDeGastos.size() != 0){
            GastoAdapter adapter = new GastoAdapter(this,listaDeGastos);
            listViewGastos.setAdapter(adapter);

            listViewGastos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Util.messagem(MainActivity.this, "teste");
                }
            });

            atualizaTotal();
        }
    }

    private void atualizaTotal(){
        double total = dao.getValorTotalDeGastos();

        textSomaTotal.setText(String.valueOf(total));
    }

    private double atualizaTotalVersao2(List<Gasto> listaDeGastos){
        double valor = 0;
        for (Gasto gasto:listaDeGastos) {
            valor += gasto.getValor();
        }
        return valor;
    }
}
