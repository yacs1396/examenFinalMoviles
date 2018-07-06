package pe.edu.pucp.examen2.Vista.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.edu.pucp.examen2.Modelo.Usuario;
import pe.edu.pucp.examen2.R;

public class Pantalla3Adapter extends RecyclerView.Adapter<Pantalla3Adapter.MyViewHolder> {

    private List<Usuario> listaUsuarios;
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView textViewNombreTarjeta;
        public TextView textViewDatoTarjeta;

        public MyViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.card_view);
            textViewNombreTarjeta = (TextView) v.findViewById(R.id.card_nombre);
            textViewDatoTarjeta = (TextView) v.findViewById(R.id.card_dato);
        }
    }

    public Pantalla3Adapter(List<Usuario> listaUsuarios, Context context) {
        this.listaUsuarios = listaUsuarios;
        this.context = context;
    }


    public void setData(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public Pantalla3Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Pantalla3Adapter.MyViewHolder holder, int position) {
        final Usuario usuario = listaUsuarios.get(position);
        holder.textViewNombreTarjeta.setText(usuario.getName());
        holder.textViewDatoTarjeta.setText(usuario.getAlias());
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }


}
