package juliorgm.com.br.meusgastos.dao;

import android.provider.BaseColumns;

public final class GastoContrato {

    private GastoContrato() {}

    public static class GastoEntrada implements BaseColumns {
        public static final String DATABASE_NAME = "database_gastos.db";
        public static final String TABLE_NAME = "gastos";
        public static final int DATABASE_VERSION = 1;

        public static final String COLUMN_ID = "id_gastos";
        public static final String COLUMN_VALOR = "valor";
        public static final String COLUMN_DATA = "data";
        public static final String COLUMN_DESCRICAO = "descricao";
        public static final String COLUMN_CATEGORIA = "categoria";
    }



}
