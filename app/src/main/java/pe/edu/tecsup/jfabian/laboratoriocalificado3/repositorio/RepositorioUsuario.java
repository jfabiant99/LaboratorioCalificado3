package pe.edu.tecsup.jfabian.laboratoriocalificado3.repositorio;

import com.orm.SugarRecord;

import java.util.List;

import pe.edu.tecsup.jfabian.laboratoriocalificado3.modelo.Usuario;

public class RepositorioUsuario {

    public static String nombre  = "";

    public static Boolean inicioSesion(String usuario,String contraseña){
        List<Usuario> users = SugarRecord.listAll(Usuario.class);
        for (Usuario u :  users) {
            if (u.getUsuario().equalsIgnoreCase(usuario) && u.getContraseña().equals(contraseña)){
                nombre = u.getUsuario();
                System.out.println(nombre);
                return true;
            }
        }
        return false;
    }
    public static List<Usuario> listar(){
        List<Usuario> users = SugarRecord.listAll(Usuario.class);
        return users;
    }
    public static Usuario read(Long id){
        Usuario user = SugarRecord.findById(Usuario.class, id);
        return user;
    }
    public static void create(String usuario,String nombre,String correo, String contraseña){
        Usuario user = new Usuario(usuario,nombre,correo,contraseña);
        SugarRecord.save(user);
    }



}
