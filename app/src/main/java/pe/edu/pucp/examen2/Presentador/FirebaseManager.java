package pe.edu.pucp.examen2.Presentador;

import android.arch.core.util.Function;
import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import pe.edu.pucp.examen2.Modelo.Usuario;

public class FirebaseManager {

    public static final String USUARIO_REFERENCE = "usuarioss";

    private static FirebaseManager instance = new FirebaseManager();
    private Context context;
    private FirebaseManager(){}

    public static FirebaseManager getInstance(Context context) {
        if(instance.context == null) {
            instance.context = context;
        }
        return instance;
    }


    private Usuario obtenerUsuario(Double x, Double y){
        Usuario usuario = new Usuario("User Prueba","UP",x,y);
        return usuario;
    }

    public void EnviarDatosUsuario(Double x, Double y,String key){
        Usuario usuario = obtenerUsuario(x,y);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        //String key = ref.child(USUARIO_REFERENCE).push().getKey();
        ref.child(USUARIO_REFERENCE).child(key).setValue(usuario);
    }

    public void ObtenerListaUsuarios(final Function<List<Usuario>,Boolean> funcionLista){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child(USUARIO_REFERENCE).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                    List<Usuario> listaUsuario = new ArrayList<>();
                    for(DataSnapshot tarjetaSnapshot : dataSnapshot.getChildren()){
                        System.out.println(tarjetaSnapshot.toString());
                        String key = tarjetaSnapshot.getKey();
                        System.out.println("KEY:"+key);
                        Usuario usuario = tarjetaSnapshot.getValue(Usuario.class);
                        usuario.setKey(key);
                        listaUsuario.add(usuario);
                    }
                    funcionLista.apply(listaUsuario);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
