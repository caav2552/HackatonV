package proyect.nerehira.hackatonv2;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import proyect.nerehira.hackatonv2.Datos.DSingletonSession;
import proyect.nerehira.hackatonv2.Datos.saveImage;
import proyect.nerehira.hackatonv2.Entidad.ESenalUbicacion;
import proyect.nerehira.hackatonv2.Negocio.NSenal;

public class SenalFormActivity extends AppCompatActivity {
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
    private Localizacion locListener;
    private final int PERMISSIONS_REQUEST_CAMERA = 1;
    private final int PERMISSION_REQUEST_STORAGE = 2;
    public static final int REQUEST_CODE_TAKE_PHOTO = 0 /*1*/;
    private String mCurrentPhotoPath;
    private Uri photoURI;
    private String nombreImagen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senal_form);
        setData();
        setViews();
        setEvents();

        mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Permiso");
            builder.setMessage("Se requiere el GPS para poder obtener la ubicacion actual");
            builder.setPositiveButton("Ir a Config", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(settingsIntent);
                }
            });
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.create().show();
        }

        locListener = new Localizacion();

        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0,
                 locListener);
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 0,
                 locListener);
        checkExternalStoragePermission();
    }

    private void setEvents() {

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void setViews() {
        et_codigo = findViewById(R.id.et_codigo);
        et_detalle = findViewById(R.id.et_detalle);
        et_direccion = findViewById(R.id.et_ubicacion);
        tv_latitud = findViewById(R.id.latitud);
        tv_longitud = findViewById(R.id.longitud);
        imgBtn = findViewById(R.id.iv_foto);
    }

    private void setData() {
        this.negocio = new NSenal(this);
    }


    public void registrarDatos(View view) {
            if (validarCampos()){
                String codigo = et_codigo.getText().toString();
                String direccion = et_direccion.getText().toString();
                String detalle = et_detalle.getText().toString();
                String lat = String.valueOf(latitud);
                String lng = String.valueOf(longitud);
                ESenalUbicacion ubicacion = new ESenalUbicacion(lat, lng, direccion);
                int userId = DSingletonSession.getInstance().getId();
                long result = negocio.insertarSenal(detalle, codigo, nombreImagen, "horizontal",
                                                    "T",  "A", userId, ubicacion);
                if (result > 0){
                    Toast.makeText(this, "Se ha registrado correctamente la informacion", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                }
                else {
                    Toast.makeText(this, "No se ha registrado correctamente la informacion, revise", Toast.LENGTH_SHORT).show();

                }
            }
    }


    public boolean validarCampos() {
        return validarImagen() && validarLatLon() && validarObservacion() && validarDireccionCodigo();
    }

    public boolean validarObservacion() {
        if (!et_detalle.getText().toString().equals(""))
            return true;
        else {
            Toast.makeText(getApplicationContext(), "ingrese una observacion", Toast.LENGTH_SHORT).show();

            return false;
        }
    }

    public boolean validarLatLon() {
        if (latitud != 0 && longitud != 0)
            return true;
        else {
            Toast.makeText(getApplicationContext(), "esperando Latitud y longitud", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean validarImagen() {

        if (!nombreImagen.equals(""))
            return true;
        else {
            Toast.makeText(getApplicationContext(), "ingrese una imagen", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean validarDireccionCodigo(){
        if(!et_direccion.getText().toString().isEmpty() && !et_codigo.getText().toString().isEmpty()){
            return true;
        }
        else{
            Toast.makeText(this, "Existen algunos campos invalidos revise e intente de nuevo", Toast.LENGTH_SHORT).show();
            return  false;
        }
    }


    public class Localizacion implements LocationListener {


        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion

            latitud = loc.getLatitude();
            longitud = loc.getLongitude();
          /*  String latitud=String.valueOf(lat);
            String longitud=String.valueOf(lng);

            String Text = "Mi ubicacion actual es: " + "\n Lat = "
                    + loc.getLatitude() + "\n Long = " + loc.getLongitude();
            Toast.makeText(getBaseContext(),Text,Toast.LENGTH_LONG).show();*/

            tv_latitud.setText("Latitud " + String.valueOf(latitud));
            tv_longitud.setText("Longitud " + String.valueOf(longitud));

            if (loc.getAccuracy() < 30){
                mlocManager.removeUpdates(locListener);
                Toast.makeText(SenalFormActivity.this, "Se ha capturado coordenadas", Toast.LENGTH_LONG).show();
            }


        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            //mensaje1.setText("GPS Desactivado");
            Toast.makeText(getApplicationContext(), "GPS Desactivado", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            //mensaje1.setText("GPS Activado");
            Toast.makeText(getApplicationContext(), "GPS Activado", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
    }


    private void checkExternalStoragePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso para leer.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 225);
        } else {
            Log.i("Mensaje", "Se tiene permiso para leer!");
        }
    }

    public void capturarFoto(View view) {

      /*  Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, COD_FOTO);
        }*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)) {


            boolean needCameraPermission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED;

            boolean needStoragePermission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED;

            int code = 0;
            String[] permission;
            if (needCameraPermission) {
                code += PERMISSIONS_REQUEST_CAMERA;
            }

            if (needStoragePermission) {
                code += PERMISSION_REQUEST_STORAGE;
            }

            if (code > 0){
                switch (code){
                    case 1:
                        ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        PERMISSIONS_REQUEST_CAMERA);
                        break;
                    case 2:
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                PERMISSION_REQUEST_STORAGE);
                        break;
                    case 3:
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                3);
                        break;
                    default:
                        break;
                }
            }
            else {
                dispatchTakePictureIntent();
            }


//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//
//            } else {
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                        225);
//            }
//
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.CAMERA)) {
//
//            } else {
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.CAMERA},
//                        226);
//            }
        } else {
            dispatchTakePictureIntent();
        }


    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_CODE_TAKE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        String NameOfFile = "imagen";
        String NameOfFolder = "/CREBO/highQuality";
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + NameOfFolder;
        String CurrentDateAndTime = getCurrentDateAndTime();
        File dir = new File(file_path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        nombreImagen = NameOfFile + CurrentDateAndTime + ".jpg";
        File image = new File(dir, nombreImagen);


        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private String getCurrentDateAndTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-­ss");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0){
            for (int permission :
                    grantResults) {
                if (permission == PackageManager.PERMISSION_DENIED){
                    Toast.makeText(this, "No se han establecido los permisos necesarios", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            dispatchTakePictureIntent();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        /*if (requestCode == COD_FOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            saveImage save=new saveImage();
            //imageView.buildDrawingCache();
            nombreImagen= save.SaveImage(this,imageBitmap);
            imageView.setImageBitmap(imageBitmap);


        }*/
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_TAKE_PHOTO && resultCode == RESULT_OK) {

            Bitmap bitmap;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), photoURI);
                imgBtn.setImageBitmap(bitmap);
                saveImage save = new saveImage();
                //imageView.buildDrawingCache();
                save.SaveImage(this, bitmap, nombreImagen);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            /*if (requestCode == REQUEST_CODE_TAKE_PHOTO && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras(); // Aquí es null
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                mPhotoImageView.setImageBitmap(imageBitmap);
            }*/

        }

    }

}
