package com.terobyte.appmedicamentos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class EditMedicamento extends AppCompatActivity {
    public static final String EXTRA_ID = "com.terobyte.appmedicamentos.id";
    public static final String EXTRA_NOM = "com.terobyte.appmedicamentos.nombre";
    public static final String EXTRA_HOR = "com.terobyte.appmedicamentos.hora";
    public static final String EXTRA_DOS = "com.terobyte.appmedicamentos.dosis";
    public static final String EXTRA_USU = "com.terobyte.appmedicamentos.usuario";
    public static final String EXTRA_FORMATOHORA = "com.terobyte.appmedicamentos.formatohora";

    private EditText editNombre;
    private EditText editHora;
    private EditText editDosis;
    private EditText editUsuario;
    private RadioButton radioHora;
    private RadioButton radioMinuto;
    private String FH="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_medicamento);
        editNombre=findViewById(R.id.editusuario4);
        editHora=findViewById(R.id.editHora);
        editDosis=findViewById(R.id.editDosis);
        editUsuario=findViewById(R.id.editUsuario);
        radioMinuto=findViewById(R.id.radioMin);
        radioHora=findViewById(R.id.radioHoras);

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
                String fho="Horas";
                if (radioMinuto.isChecked()==true){
                    fho="Minutos";
                    replyIntent.putExtra(EXTRA_FORMATOHORA,fho);
                }else{
                    replyIntent.putExtra(EXTRA_FORMATOHORA,fho);
                }
                Integer eho= Integer.parseInt(eho1);
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
            Integer ehoEdit=intent.getIntExtra(EXTRA_HOR,0);
            editNombre.setText(intent.getStringExtra(EXTRA_NOM));
            editHora.setText(ehoEdit.toString());
            editDosis.setText(intent.getStringExtra(EXTRA_DOS));
            editUsuario.setText(intent.getStringExtra(EXTRA_USU));
            FH=intent.getStringExtra(EXTRA_FORMATOHORA);
            Toast.makeText(this,FH.toString(),Toast.LENGTH_LONG).show();
            if(FH.equals("Horas")){
                radioHora.setChecked(true);
            }else{
                radioMinuto.setChecked(true);
            }
            }

        }
        private void checkR(String dato){
            if(dato == "1"){
                radioHora.setChecked(true);
            }else{
                radioMinuto.setChecked(true);

        }
    }
}