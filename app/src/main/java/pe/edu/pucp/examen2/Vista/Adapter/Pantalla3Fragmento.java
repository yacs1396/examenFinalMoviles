package pe.edu.pucp.examen2.Vista.Adapter;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pe.edu.pucp.examen2.Modelo.Usuario;
import pe.edu.pucp.examen2.Presentador.IUsuarioPresenter;
import pe.edu.pucp.examen2.Presentador.UsuarioPresenter;
import pe.edu.pucp.examen2.R;

public class Pantalla3Fragmento extends Fragment implements IPantalla3 {
    private SwipeRefreshLayout swipeRefresh;
    private Pantalla3Adapter adapter;
    private IUsuarioPresenter iUsuarioPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        iUsuarioPresenter = new UsuarioPresenter(this);
        //mUserId = args.getString("userId", "");
    }

    public static Pantalla3Fragmento getNewInstance() {
        Pantalla3Fragmento f = new Pantalla3Fragmento();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putString("userId", "");
        f.setArguments(args);
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.card_list, container, false);

        final RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);

        iUsuarioPresenter.obtenerUsuarios();

        adapter = new Pantalla3Adapter(new ArrayList<Usuario>(), getContext());
        rv.setAdapter(adapter);


        swipeRefresh = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeUsuario);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                iUsuarioPresenter.obtenerUsuarios();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefresh.setRefreshing(false);
                    }
                },1000);
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        return rootView;
    }

    @Override
    public void limpiar() {
        adapter.setData(new ArrayList<Usuario>());
    }

    @Override
    public void añadirUsuarios(List<Usuario> listaUsuarios) {
        adapter.setData(listaUsuarios);
    }

    @Override
    public void finalizarRefresh() {
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
    }
}
