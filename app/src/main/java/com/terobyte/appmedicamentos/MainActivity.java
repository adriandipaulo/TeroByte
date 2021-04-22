package com.terobyte.appmedicamentos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.appbar.MaterialToolbar;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.terobyte.appmedicamentos.adapters.MedicamentoAdapter;
import com.terobyte.appmedicamentos.entidades.Medicamentos;
import com.terobyte.appmedicamentos.models.MedicamentosViewModel;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private MedicamentosViewModel medicamentosViewModel;
    public static final int CODE_MEDICAMETO = 1;
    public static final int UPDATE_CODE_MEDICAMETO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView= findViewById(R.id.recyclerViewMed);
        final MedicamentoAdapter adapter= new MedicamentoAdapter(new MedicamentoAdapter.medicamentoDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        medicamentosViewModel = new ViewModelProvider(this,new MedicamentoFactory(getApplication())).get(MedicamentosViewModel.class);
        medicamentosViewModel.verMedicamentos().observe(this,Medicamentos ->{adapter.submitList(Medicamentos);});

        FloatingActionButton fab= findViewById(R.id.AgregarBotonFloat);

        adapter.setMeicamentoListener(new MedicamentoAdapter.OnItemClickListener() {
            @Override
            public void onItemDelete(Medicamentos medicamento) {
                medicamentosViewModel.delete(medicamento);
            }

            @Override
            public void onEditMedicamento(Medicamentos medicamento) {
                Intent intent= new Intent(MainActivity.this,EditMedicamento.class);
                intent.putExtra(EditMedicamento.EXTRA_ID,medicamento.getId_medicamento());
                intent.putExtra(EditMedicamento.EXTRA_NOM,medicamento.getNombreMedicamento());
                intent.putExtra(EditMedicamento.EXTRA_HOR,medicamento.getTomar_cada());
                intent.putExtra(EditMedicamento.EXTRA_DOS,medicamento.getPresentacion());
                intent.putExtra(EditMedicamento.EXTRA_USU,medicamento.getUsuario());
                intent.putExtra(EditMedicamento.EXTRA_FORMATOHORA,"1");
                startActivityForResult(intent, UPDATE_CODE_MEDICAMETO);
            }
        });

        fab.setOnClickListener(view ->{
            Intent intent = new Intent(MainActivity.this, EditMedicamento.class);
            startActivityForResult(intent, CODE_MEDICAMETO);
        });
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setSubtitle(R.string.app_subtitle);

        DrawerLayout drawerLayout =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode== CODE_MEDICAMETO && resultCode == RESULT_OK){
            Medicamentos xm= new Medicamentos();
            xm.setNombreMedicamento(data.getStringExtra(EditMedicamento.EXTRA_NOM));
            xm.setPresentacion(data.getStringExtra(EditMedicamento.EXTRA_DOS));
            xm.setTomar_cada(data.getDoubleExtra(EditMedicamento.EXTRA_HOR,0.0));
            xm.setUsuario(data.getStringExtra(EditMedicamento.EXTRA_USU));
            medicamentosViewModel.insert(xm);

        } else if (requestCode== UPDATE_CODE_MEDICAMETO && resultCode==RESULT_OK){
            int id= data.getIntExtra(EditMedicamento.EXTRA_ID,-1);
            if (id ==-1){
                Toast.makeText(getApplicationContext(),"Error al Editar",Toast.LENGTH_SHORT).show();
            }
            Medicamentos xm= new Medicamentos();
            xm.setNombreMedicamento(data.getStringExtra(EditMedicamento.EXTRA_NOM));
            xm.setPresentacion(data.getStringExtra(EditMedicamento.EXTRA_DOS));
            xm.setTomar_cada(data.getDoubleExtra(EditMedicamento.EXTRA_HOR,0.0));
            xm.setUsuario(data.getStringExtra(EditMedicamento.EXTRA_USU));
            xm.setId_medicamento(id);
            medicamentosViewModel.update(xm);

        }else {
            Toast.makeText(getApplicationContext(), "Medicamento no guardado", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.usuarios:
                Intent i = new Intent(MainActivity.this, UsuariosActivity.class);
                startActivity(i);
                break;
            case R.id.medicamentos:
                Intent ii = new Intent(MainActivity.this, MainActivity.class);
                startActivity(ii);
                break;
            default:
                throw new IllegalArgumentException("Opcion  incorrecta");
        }
        return true;
    }
}