package com.terobyte.appmedicamentos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class EditUsuarios extends AppCompatActivity {
    public static final String EXTRA_IDU="com.terobyte.appmedicamentos.idUser";
    public static final String EXTRA_NOMAP="com.terobyte.appmedicamentos.nomAp";
    public static final String EXTRA_PESO="com.terobyte.appmedicamentos.peso";
    public static final String EXTRA_ALTURA="com.terobyte.appmedicamentos.Altura";
    public static final String EXTRA_PRESION="com.terobyte.appmedicamentos.Presion";

    private EditText editNomAp;
    private EditText editPeso;
    private EditText editAltura;
    private EditText editPresion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_usuarios);
        editNomAp=findViewById(R.id.editTextNombreApellidoUsuario);
        editPeso=findViewById(R.id.editTextPesoUsuario);
        editAltura=findViewById(R.id.editTextAlturaUsuario);
        editPresion=findViewById(R.id.editTextTextPresionUsuario);

        final Button button = findViewById(R.id.buttonGuardaUsuario);
        button.setOnClickListener(view ->{
            Intent replyIntent= new Intent();
            if (TextUtils.isEmpty(editNomAp.getText())){
                setResult(RESULT_CANCELED,replyIntent);
            } else{
                String nomap= editNomAp.getText().toString();
                String pesotext= editPeso.getText().toString();
                String alturatext= editAltura.getText().toString();
                String presion= editPresion.getText().toString();
                Double altura=Double.parseDouble(alturatext);
                Integer peso= Integer.parseInt(pesotext);
                replyIntent.putExtra(EXTRA_NOMAP,nomap);
                replyIntent.putExtra(EXTRA_PESO,peso);
                replyIntent.putExtra(EXTRA_ALTURA,altura);
                replyIntent.putExtra(EXTRA_PRESION,presion);
                int id= getIntent().getIntExtra(EXTRA_IDU,-1);
                if (id != -1){
                    replyIntent.putExtra(EXTRA_IDU,id);
                }
                setResult(RESULT_OK,replyIntent);
            }
            finish();
        });

    }
}