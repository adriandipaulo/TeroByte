package com.terobyte.appmedicamentos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class EditMedicamento extends AppCompatActivity {
    public static final String EXTRA_ID = "com.terobyte.appmedicamentos.id";
    public static final String EXTRA_NOM = "com.terobyte.appmedicamentos.nombre";
    public static final String EXTRA_HOR = "com.terobyte.appmedicamentos.hora";
    public static final String EXTRA_DOS = "com.terobyte.appmedicamentos.dosis";
    public static final String EXTRA_USU = "com.terobyte.appmedicamentos.usuario";

    private EditText editNombre;
    private EditText editHora;
    private EditText editDosis;
    private EditText editUsuario;
    private RadioButton radioHora;
    private RadioButton radioMinuto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_medicamento);
        editNombre=findViewById(R.id.editNomMedic);
        editHora=findViewById(R.id.editHora);
        editDosis=findViewById(R.id.editDosis);
        editUsuario=findViewById(R.id.editUsuario);
        radioMinuto=findViewById(R.id.radioMin);
        radioMinuto.setChecked(true);
        
        final Button button = findViewById(R.id.BtnGuardar);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(editNombre.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String enm = editNombre.getText().toString();
                String eho1 = editHora.getText().toString();
                String edo = editDosis.getText().toString();
                String eus = editUsuario.getText().toString();
                Double eho= Double.parseDouble(eho1);
                replyIntent.putExtra(EXTRA_NOM, enm);
                replyIntent.putExtra(EXTRA_HOR, eho);
                replyIntent.putExtra(EXTRA_DOS, edo);
                replyIntent.putExtra(EXTRA_USU, eus);
                int id= getIntent().getIntExtra(EXTRA_ID,-1);
                if (id != -1) {
                    replyIntent.putExtra(EXTRA_ID,id);
                }
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });

        Intent intent= getIntent();
        if (intent.hasExtra(EXTRA_ID)){
            Double ehoEdit=intent.getDoubleExtra(EXTRA_HOR,0.0);
            editNombre.setText(intent.getStringExtra(EXTRA_NOM));
            editHora.setText(ehoEdit.toString());
            editDosis.setText(intent.getStringExtra(EXTRA_DOS));
            editUsuario.setText(intent.getStringExtra(EXTRA_USU));
        }
    }
}