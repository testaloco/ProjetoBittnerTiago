package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private final static String BANCO_DE_DADOS = "santander";
    private static int VERSAO = 1;



    public DataBaseHelper(Context context) {
        super(context, BANCO_DE_DADOS, null, VERSAO);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE cliente (_id INTEGER PRIMARY KEY, nome TEXT, data_cadastro DATE, saldo DOUBLE, cpf TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
