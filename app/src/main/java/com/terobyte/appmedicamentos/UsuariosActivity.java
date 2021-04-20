package com.terobyte.appmedicamentos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.terobyte.appmedicamentos.adapters.UsuarioAdapter;
import com.terobyte.appmedicamentos.entidades.Usuarios;
import com.terobyte.appmedicamentos.models.UsuarioViewModel;
import com.terobyte.appmedicamentos.repsitories.UsuariosFactory;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class UsuariosActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private UsuarioViewModel usuarioViewModel;
    public static final int CODE_USUARIOS=1;
    public static final int UPDATE_CODE_USUARIOS=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        RecyclerView recyclerView= findViewById(R.id.recyclerViewUsuarios);
        final UsuarioAdapter adapter= new UsuarioAdapter(new UsuarioAdapter.usuarioDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        usuarioViewModel = new ViewModelProvider(this,new UsuariosFactory(getApplication())).get(UsuarioViewModel.class);
        usuarioViewModel.verUsuarios().observe(this, Usuarios->{adapter.submitList(Usuarios);});
        FloatingActionButton fab= findViewById(R.id.AgregarBotonFloatUsuarios);
        adapter.setUsuarioListener(new UsuarioAdapter.OnItemClickListener() {
            @Override
            public void onItemDelete(Usuarios usuarios) {
                usuarioViewModel.delete(usuarios);
            }

            @Override
            public void onEditUsuario(Usuarios usuarios) {
                //Intent intent= new Intent(UsuariosActivity.this,EditUsuarios.class);
                //intent.putExtra(EditUsuarios.EXTRA_ID,usuarios.getId());
                //intent.putExtra(EditUsuarios.EXTRA_NOMAPELL,usuarios.getNombreApellido());

            }
        });

        fab.setOnClickListener(view ->{
            Intent intent= new Intent(UsuariosActivity.this,EditUsuarios.class);
            startActivityForResult(intent,CODE_USUARIOS);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.usuarios:
                Intent i = new Intent(this, UsuariosActivity.class);
                startActivity(i);
                break;
            case R.id.medicamentos:
                Intent ii = new Intent(this, MainActivity.class);
                startActivity(ii);
                break;
            default:
                throw new IllegalArgumentException("Opcion  incorrecta");
        }
        return true;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode== CODE_USUARIOS && resultCode == RESULT_OK){
            Usuarios nuser= new Usuarios();
            nuser.setNombreApellido(data.getStringExtra(EditUsuarios.EXTRA_NOMAP));
            nuser.setPeso(data.getIntExtra(EditUsuarios.EXTRA_PESO,0));
            nuser.setAltura(data.getDoubleExtra(EditUsuarios.EXTRA_ALTURA,0.0));
            nuser.setPresion(data.getStringExtra(EditUsuarios.EXTRA_PRESION));
            usuarioViewModel.insert(nuser);
        } else if(requestCode==UPDATE_CODE_USUARIOS && resultCode==RESULT_OK){
            int id= data.getIntExtra(EditUsuarios.EXTRA_IDU,-1);
            if (id == -1){
                Toast.makeText(getApplicationContext(),"Error al Editar",Toast.LENGTH_SHORT).show();
            }
            Usuarios nuser= new Usuarios();
            nuser.setNombreApellido(data.getStringExtra(EditUsuarios.EXTRA_NOMAP));
            nuser.setPeso(data.getIntExtra(EditUsuarios.EXTRA_PESO,0));
            nuser.setAltura(data.getDoubleExtra(EditUsuarios.EXTRA_ALTURA,0.0));
            nuser.setPresion(data.getStringExtra(EditUsuarios.EXTRA_PRESION));
            nuser.setId(id);
            usuarioViewModel.update(nuser);


        }else{
            Toast.makeText(getApplicationContext(), "Usiario no guardado", Toast.LENGTH_LONG).show();
        }

    }
}