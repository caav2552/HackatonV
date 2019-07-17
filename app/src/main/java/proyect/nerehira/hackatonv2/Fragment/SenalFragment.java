package proyect.nerehira.hackatonv2.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import proyect.nerehira.hackatonv2.Adapter.SenalAdapter;
import proyect.nerehira.hackatonv2.Entidad.ESenal;
import proyect.nerehira.hackatonv2.Negocio.NSenal;
import proyect.nerehira.hackatonv2.R;
import proyect.nerehira.hackatonv2.SenalFormActivity;

import static android.app.Activity.RESULT_OK;

public class SenalFragment extends Fragment {
    public static int REQUEST_LOCATION = 5;
    public static int REQUEST_SENAL = 2;
    private NSenal negocio;
    private SenalAdapter adapter;
    private RecyclerView rv_senal;
    private FloatingActionButton fab_senal;

    public SenalFragment() {

    }

    public static SenalFragment newInstance() {

        SenalFragment fragment = new SenalFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_senal, container, false);
        setData();
        setView(view);
        setEvents();
        return view;
    }

    private void setData(){
        this.negocio = new NSenal(getContext());
    }

    private void setEvents() {
        fab_senal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                        return;
                    }
                }
                startActivityForResult(new Intent(getContext(), SenalFormActivity.class), REQUEST_SENAL);
            }
        });
    }


    private void setView(View view) {
        fab_senal = view.findViewById(R.id.fab_senal);
        rv_senal = view.findViewById(R.id.rv_senals);

        ArrayList<ESenal> senals = negocio.getAllSenales();
        adapter = new SenalAdapter(senals);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
        rv_senal.setLayoutManager(lm);
        rv_senal.setHasFixedSize(true);
        rv_senal.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SENAL) {
            Toast.makeText(getContext(), "Ha regresado del formulario", Toast.LENGTH_LONG).show();
            if (resultCode == RESULT_OK){
                ArrayList<ESenal> nuevosDatos = negocio.getAllSenales();
                adapter.updateIncidencias(nuevosDatos);
                adapter.notifyDataSetChanged();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_LOCATION && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startActivityForResult(new Intent(getContext(), SenalFormActivity.class), REQUEST_SENAL);
        } else {
            Toast.makeText(getContext(), "No se han habilitado los permisos revise e intente de nuevo", Toast.LENGTH_LONG).show();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
