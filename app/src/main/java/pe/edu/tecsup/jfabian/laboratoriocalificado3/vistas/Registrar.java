package pe.edu.tecsup.jfabian.laboratoriocalificado3.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pe.edu.tecsup.jfabian.laboratoriocalificado3.R;
import pe.edu.tecsup.jfabian.laboratoriocalificado3.repositorio.RepositorioUsuario;

public class Registrar extends AppCompatActivity {

    EditText usuario;
    EditText nombre;
    EditText correo;
    EditText contra;
    Button registrar;
    Button atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        usuario = (EditText)findViewById(R.id.registro_usuario);
        nombre = (EditText)findViewById(R.id.registro_nombre);
        correo = (EditText)findViewById(R.id.registro_correo);
        contra = (EditText)findViewById(R.id.registro_contraseña);
    }

    public void Accion(View view) {
        switch (view.getId()){
            case R.id.regitro_btn_registrar:
                RegistrarUsuario();
                break;

            case R.id.registro_btn_atras:
                atrasInicioSesion();
                break;
        }
    }
    private void RegistrarUsuario() {
        String usu =  usuario.getText().toString();
        String nom =  nombre.getText().toString();
        String cor = correo.getText().toString();
        String cont = contra.getText().toString();
        if(usu.isEmpty() || nom.isEmpty() || cor.isEmpty() || cont.isEmpty() ){
            Toast.makeText(this,"Los campos son requeridos", Toast.LENGTH_LONG).show();
            return;
        }else{
            RepositorioUsuario.create(usu,nom,cor,cont);
            Toast.makeText(this,"Registro Satisfactorio",Toast.LENGTH_SHORT).show();
        }
        limpiar();
    }

    private void limpiar() {
        usuario.setText("");
        nombre.setText("");
        correo.setText("");
        contra.setText("");
    }

    private void atrasInicioSesion() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
