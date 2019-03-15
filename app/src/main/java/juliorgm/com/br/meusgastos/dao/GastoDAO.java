package juliorgm.com.br.meusgastos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import juliorgm.com.br.meusgastos.model.Gasto;

import static juliorgm.com.br.meusgastos.dao.GastoContrato.GastoEntrada.DATABASE_NAME;
import static juliorgm.com.br.meusgastos.dao.GastoContrato.GastoEntrada.DATABASE_VERSION;
import static juliorgm.com.br.meusgastos.dao.GastoContrato.GastoEntrada;

public class GastoDAO extends SQLiteOpenHelper {

    private static final String SQL_CREATE_TABLE_GASTO =
            "CREATE TABLE "+ GastoEntrada.TABLE_NAME
                    + "(" + GastoEntrada.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT"
                    + "," + GastoEntrada.COLUMN_VALOR + " DOUBLE NOT NULL"
                    + "," + GastoEntrada.COLUMN_DATA + " TEXT NOT NULL"
                    + "," + GastoEntrada.COLUMN_DESCRICAO + "TEXT NOT NULL"
                    + "," + GastoEntrada.COLUMN_CATEGORIA + ")";

    private static final String SQL_DROP_TABLE_GASTO = "DROP TABLE " + GastoEntrada.TABLE_NAME;

    public GastoDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_GASTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TABLE_GASTO);
    }

    public long inserir(Gasto gasto){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put(GastoEntrada.COLUMN_VALOR,gasto.getValor());
        dados.put(GastoEntrada.COLUMN_DATA,gasto.getData());
        dados.put(GastoEntrada.COLUMN_DESCRICAO,gasto.getDescricao());
        dados.put(GastoEntrada.COLUMN_CATEGORIA,gasto.getCategoria());

        long resultado = db.insert(GastoEntrada.TABLE_NAME,null, dados);
        db.close();

        return resultado;
    }

    public List<Gasto> listarTodosOsGastos(){
        SQLiteDatabase db = getReadableDatabase();
        List<Gasto> listaDeGastos = new ArrayList<>();

        String sql = "SELECT * FROM " + GastoEntrada.TABLE_NAME;
        Cursor cursor = db.rawQuery(sql,null);

        while (cursor.moveToNext()){
            double valor =  cursor.getDouble(cursor.getColumnIndex(GastoEntrada.COLUMN_VALOR));
            String data =  cursor.getString(cursor.getColumnIndex(GastoEntrada.COLUMN_DATA));
            String descricao =  cursor.getString(cursor.getColumnIndex(GastoEntrada.COLUMN_CATEGORIA));
            String categoria =  cursor.getString(cursor.getColumnIndex(GastoEntrada.COLUMN_CATEGORIA));

            listaDeGastos.add(new Gasto(valor,data,descricao,categoria));
        }

        return listaDeGastos;
    }
}
