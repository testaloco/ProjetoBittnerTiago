package br.com.saugo;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import database.DataBaseHelper;

public class MainActivity extends AppCompatActivity {
    String nome, cpf;
    Date dataCadastro;
    Double saldo;
    DataBaseHelper helper;
    private Button btnCadastrar;
    private EditText campoNome ,campoCpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //cadastrar usuário

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etNome =  campoNome.getText().toString();
                String etCpf = campoCpf.getText().toString();
            }
        });


           //inicialização de componentes

        inicializarComponentes();
        helper = new DataBaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        nome = "Edson Blum";
        dataCadastro = new Date();
        saldo = 25000.34;
        cpf = "123.456.789-99";
        values.put("nome", nome);
        values.put("data_cadastro", dataCadastro.toString());
        values.put("cpf", cpf);
        values.put("saldo", saldo);
        long resultado = db.insert("cliente", null, values);
        if(resultado != -1)
            Toast.makeText(this, "Registro Salvo", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Falha ao salvar", Toast.LENGTH_LONG).show();
        //Listar Registros após insert
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id, nome, cpf FROM cliente", null);
        if(cursor != null) {
            cursor.moveToFirst();
            for(int i = 0; i < cursor.getCount(); i++){
                System.out.println("ID: " + cursor.getInt(0));
                System.out.println("Nome: " + cursor.getString(1));
                System.out.println("Cpf: " + cursor.getString(2));
                cursor.moveToNext();
            }

        }



    }
    private void inicializarComponentes() {
        campoCpf = findViewById(R.id.etCpf);
        campoNome = findViewById(R.id.etNome);
    }




    public void  gerarSair(View view){
        finish();
    }
}