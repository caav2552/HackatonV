package proyect.nerehira.hackatonv2.Datos;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class saveImage {
    private Context TheThis;
    public static String NameOfFolder = "/CREBO/lowQuality";
    private String NameOfFile = "imagen";

    public void SaveImage(Context context, Bitmap ImageToSave, String name) {
        //  String nombre="";
        TheThis = context;
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + NameOfFolder;
        String CurrentDateAndTime = getCurrentDateAndTime();
        File dir = new File(file_path);

        if (!dir.exists()) {
            dir.mkdirs();
        }
           /* Date now = new Date();
            android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
            String NameOfFile = "CREBO_";
            NameOfFile=NameOfFile+now.toString();*/
        //  nombre=NameOfFile + CurrentDateAndTime + ".jpg";
        File file = new File(dir, name);

        try {

            FileOutputStream fOut = new FileOutputStream(file);
            Bitmap imageScaled = Bitmap.createScaledBitmap(ImageToSave, 200, 150, false);
            // ImageToSave.compress(Bitmap.CompressFormat.JPEG, 20, fOut);
            imageScaled.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            MakeSureFileWasCreatedThenMakeAvabile(file);
            AbleToSave();
        } catch (FileNotFoundException e) {
            UnableToSave();
        } catch (IOException e) {
            UnableToSave();
        }
        //return nombre;
    }

    private void MakeSureFileWasCreatedThenMakeAvabile(File file) {
        MediaScannerConnection.scanFile(TheThis,
                new String[]{file.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {

                    public void onScanCompleted(String path, Uri uri) {
                    }
                });
    }

    private String getCurrentDateAndTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-­ss");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    private void UnableToSave() {
        Toast.makeText(TheThis, "¡No se ha podido guardar la imagen!", Toast.LENGTH_SHORT).show();
    }

    private void AbleToSave() {
        Toast.makeText(TheThis, "Imagen guardada en la galería.", Toast.LENGTH_SHORT).show();
    }

}
