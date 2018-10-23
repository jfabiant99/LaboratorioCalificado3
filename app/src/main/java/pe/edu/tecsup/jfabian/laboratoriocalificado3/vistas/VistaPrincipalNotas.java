package pe.edu.tecsup.jfabian.laboratoriocalificado3.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pe.edu.tecsup.jfabian.laboratoriocalificado3.R;
import pe.edu.tecsup.jfabian.laboratoriocalificado3.adaptador.NotaAdaptador;
import pe.edu.tecsup.jfabian.laboratoriocalificado3.modelo.Nota;
import pe.edu.tecsup.jfabian.laboratoriocalificado3.repositorio.RepositorioNota;
import pe.edu.tecsup.jfabian.laboratoriocalificado3.repositorio.RepositorioUsuario;

public class VistaPrincipalNotas extends AppCompatActivity {

    private static final String TAG = VistaPrincipalNotas.class.getSimpleName();
    private static final int REGISTER_FORM_REQUEST = 100;
    private RecyclerView recyclerView;
    //TextView nombre_del_usuario;
    private CheckBox detalle_favorito;
    //private CheckBox detalle_archivado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_principal_notas);
        /*
         * Se esta agregando esto manito para el favorito y archivado
         * */
        detalle_favorito = (CheckBox)findViewById(R.id.detalle_favorito);
        //detalle_archivado = (CheckBox)findViewById(R.id.detalle_archivado);

        /**
         * FIN DE LO DE AGREGADO
         * */

        //nombre_del_usuario = (TextView)findViewById(R.id.nombre_del_usuario);
        recyclerView = (RecyclerView)findViewById(R.id.mostrar_notas_agregadas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Nota> notas = RepositorioNota.listar();
        recyclerView.setAdapter(new NotaAdaptador(notas));
        String nombre = RepositorioUsuario.nombre;
        //nombre_del_usuario.setText("Bienvenido: "+ nombre.toUpperCase());
        BottomNavigationView b = (BottomNavigationView)findViewById(R.id.bootom_navigation);
        b.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:
                        Toast.makeText(getApplicationContext(),"HOME",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_favorito:
                        Toast.makeText(getApplicationContext(),"FAVORITOS",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_archivados:
                        Toast.makeText(getApplicationContext(),"ARCHIVADOS",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

    }

    public void agregarNotas(View view) {
        startActivityForResult(new Intent(this,RegistrarNotas.class),REGISTER_FORM_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        NotaAdaptador notaAdaptador = (NotaAdaptador)recyclerView.getAdapter();
        List<Nota> notas = RepositorioNota.listar();
        notaAdaptador.setNotas(notas);
        notaAdaptador.notifyDataSetChanged();
    }
}