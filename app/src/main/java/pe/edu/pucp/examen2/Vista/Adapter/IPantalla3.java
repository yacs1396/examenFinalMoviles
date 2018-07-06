package pe.edu.pucp.examen2.Vista.Adapter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import pe.edu.pucp.examen2.Modelo.Usuario;

public interface IPantalla3 {

    Context getContext();
    public void limpiar();


    public void añadirUsuarios(List<Usuario> listaUsuarios);


    public void finalizarRefresh();

}
