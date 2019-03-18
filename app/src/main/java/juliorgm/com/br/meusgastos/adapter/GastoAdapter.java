package juliorgm.com.br.meusgastos.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import juliorgm.com.br.meusgastos.R;
import juliorgm.com.br.meusgastos.dao.GastoDAO;
import juliorgm.com.br.meusgastos.helper.Util;
import juliorgm.com.br.meusgastos.model.Gasto;

public class GastoAdapter extends BaseAdapter {

    private Activity activity;
    private List<Gasto> listaDeGastos;
    private static final int RESULTADO = 0;

    public GastoAdapter(Activity activity, List<Gasto> listaDeGastos) {
        this.activity = activity;
        this.listaDeGastos = listaDeGastos;
    }

    @Override
    public int getCount() {
        return listaDeGastos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDeGastos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaDeGastos.get(position).getIdGasto();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.item_gasto,parent,false);

        ImageView imageViewCategoria = view.findViewById(R.id.item_gasto_image_categoria);
        TextView textValor = view.findViewById(R.id.item_gasto_text_valor);
        TextView textData = view.findViewById(R.id.item_gasto_text_data);
        TextView textDescricao = view.findViewById(R.id.item_gasto_text_descricao);
        FloatingActionButton fabDeletar = view.findViewById(R.id.item_fab_deletar);

        final Gasto gasto = (Gasto) getItem(position);

        textValor.setText(gasto.getValorToString());
        textData.setText(gasto.getData());
        textDescricao.setText(gasto.getDescricao());

        fabDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =  new AlertDialog.Builder(activity);
                builder.setMessage("Você tem certeza que deseja realizar essa operação")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                GastoDAO dao = new GastoDAO(activity);
                                if (dao.delete(getItemId(position)) > RESULTADO) {
                                    Util.messagem(activity, "Gasto excluido com sucesso!");
                                    listaDeGastos.remove(gasto);
                                    notifyDataSetChanged();
                                }
                            }
                        }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                });
                builder.show();
            }
        });

        return view;
    }
}
