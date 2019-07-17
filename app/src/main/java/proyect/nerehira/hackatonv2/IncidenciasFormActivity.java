package proyect.nerehira.hackatonv2;

import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import proyect.nerehira.hackatonv2.Negocio.NSenal;

public class IncidenciasFormActivity extends AppCompatActivity {
    private EditText et_codigo;
    private EditText et_detalle;
    private EditText et_direccion;
    private TextView tv_latitud;
    private TextView tv_longitud;
    private ImageView imgBtn;
    private double latitud;
    private double longitud;
    private NSenal negocio;


    private LocationManager mlocManager;
    private SenalFormActivity.Localizacion locListener;
    private final int PERMISSIONS_REQUEST_CAMERA = 1;
    private final int PERMISSION_REQUEST_STORAGE = 2;
    public static final int REQUEST_CODE_TAKE_PHOTO = 0 /*1*/;
    private String mCurrentPhotoPath;
    private Uri photoURI;
    private String nombreImagen;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidencias_form);
        setSupportActionBar(mToolbar);

    }

    public void clickView(View v){

    }
}
