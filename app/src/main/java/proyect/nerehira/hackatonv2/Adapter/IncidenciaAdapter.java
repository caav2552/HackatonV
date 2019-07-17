package proyect.nerehira.hackatonv2.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import proyect.nerehira.hackatonv2.Entidad.EIncidencia;
import proyect.nerehira.hackatonv2.R;

public class IncidenciaAdapter extends RecyclerView.Adapter<IncidenciaAdapter.IncidenciaHolder> {
    private ArrayList<EIncidencia> incidencias;


    public IncidenciaAdapter(ArrayList<EIncidencia> incidencias){
        this.incidencias = incidencias;
    }

    @NonNull
    @Override
    public IncidenciaHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_incidencia, viewGroup, false);
        return new IncidenciaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncidenciaHolder incidenciaHolder, int i) {
        EIncidencia incidencia = incidencias.get(i);
      //  incidenciaHolder.iv_incidencia.setImageBitmap();
        // incidencia.imagen;
        incidenciaHolder.tv_detalle.setText(incidencia.observacion);
        incidenciaHolder.tv_fecha.setText(incidencia.fecha);

    }



    @Override
    public int getItemCount() {
        return incidencias.size();
    }

    class IncidenciaHolder extends RecyclerView.ViewHolder{
        ImageView iv_incidencia;
        TextView tv_detalle;
        TextView tv_fecha;
        public IncidenciaHolder(@NonNull View itemView) {
            super(itemView);
            iv_incidencia = itemView.findViewById(R.id.iv_imagen);
            tv_detalle = itemView.findViewById(R.id.tv_detalle);
            tv_fecha = itemView.findViewById(R.id.tv_fecha);
        }
    }
}
