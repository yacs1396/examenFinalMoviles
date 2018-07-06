package pe.edu.pucp.examen2.Presentador;

import android.content.Context;

public interface IUsuarioPresenter {
    void obtenerUsuarios();
    void enviarDatosUsuario(Context context, Double x, Double y);
}
