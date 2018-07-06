package pe.edu.pucp.examen2.Presentador;

import android.arch.core.util.Function;
import android.content.Context;
import android.security.keystore.UserNotAuthenticatedException;

import java.util.ArrayList;
import java.util.List;

import pe.edu.pucp.examen2.Modelo.Usuario;
import pe.edu.pucp.examen2.Vista.Adapter.IPantalla3;

public class UsuarioPresenter implements IUsuarioPresenter {


    //private List<Usuario> listaUsuarios = new ArrayList<Usuario>();
    private IPantalla3 iPantalla3;
    public UsuarioPresenter(IPantalla3 iPantalla3){
        this.iPantalla3 = iPantalla3;
    }

    public void obtenerUsuarios(){
        final List<Usuario> listaUsuaios = new ArrayList<Usuario>();
        FirebaseManager.getInstance(iPantalla3.getContext()).ObtenerListaUsuarios(new Function<List<Usuario>, Boolean>() {
            @Override
            public Boolean apply(List<Usuario> input) {
                iPantalla3.limpiar();
                for(Usuario usuario: input){
                    listaUsuaios.add(usuario);
                }
                System.out.println("SIZE: "+listaUsuaios.size());
                iPantalla3.añadirUsuarios(listaUsuaios);
                iPantalla3.finalizarRefresh();
                return true;
            }
        });
    }

    @Override
    public void enviarDatosUsuario(Context context, Double x, Double y,String key) {
        FirebaseManager.getInstance(context).EnviarDatosUsuario(x,y,key);
    }

}
