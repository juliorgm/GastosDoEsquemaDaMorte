package juliorgm.com.br.meusgastos.helper;

import android.content.Context;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Util {

    public static String EDITAR_GASTO ="EDITAR";

    public static String formataData(String data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(data);
    }

    public static String formataData(Date data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(data);
    }

    public static void messagem(Context context, String mensagem) {
        Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
    }

    public static void messagemFromResource(Context context, int resource) {
        Toast.makeText(context, resource, Toast.LENGTH_SHORT).show();
    }

    public static String formataDataBR(String data) {
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date;
        try {
            String inputText = data;
            date = inputFormat.parse(data);
        } catch (Exception e) {

        } finally {
            date = null;
        }

        return outputFormat.format(date);
    }

    public static int pegaIndexSpinner(Context context,int resource,String textoIndex){
        String [] arrayDeString = context.getResources().getStringArray(resource);
        List<String> stringList = Arrays.asList(arrayDeString);
        return stringList.indexOf(textoIndex);
    }
}
