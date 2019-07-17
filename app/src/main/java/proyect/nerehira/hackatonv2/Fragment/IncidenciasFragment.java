package proyect.nerehira.hackatonv2.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import proyect.nerehira.hackatonv2.Adapter.IncidenciaAdapter;
import proyect.nerehira.hackatonv2.Entidad.EIncidencia;
import proyect.nerehira.hackatonv2.IncidenciasFormActivity;
import proyect.nerehira.hackatonv2.Negocio.NIncidencia;
import proyect.nerehira.hackatonv2.R;

public class IncidenciasFragment extends Fragment {
    private RecyclerView rv_incidencias;
    private IncidenciaAdapter adapter;
    private FloatingActionButton fab_incidencias;

    public IncidenciasFragment(){

    }

    public static IncidenciasFragment newInstance() {
        IncidenciasFragment fragment = new IncidenciasFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_incidencias, container, false);
        rv_incidencias = view.findViewById(R.id.rv_incidencia);
        fab_incidencias = view.findViewById(R.id.fab_incidencias);

        setView();
        setEvents();
        return view;
    }
    private void setEvents(){
        fab_incidencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), IncidenciasFormActivity.class));
            }
        });
    }

    private void setView(){
        ArrayList<EIncidencia> incidencias;
        NIncidencia negocio = new NIncidencia(getContext());
        incidencias = negocio.obtenerIncidencias();
        adapter = new IncidenciaAdapter(incidencias);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rv_incidencias.setHasFixedSize(true);
        rv_incidencias.setLayoutManager(manager);
        rv_incidencias.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
