package proyect.nerehira.hackatonv2.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import proyect.nerehira.hackatonv2.Datos.saveImage;
import proyect.nerehira.hackatonv2.Entidad.ESenal;
import proyect.nerehira.hackatonv2.R;

public class SenalAdapter extends RecyclerView.Adapter<SenalAdapter.SenalHolder> {
    private ArrayList<ESenal> senals;


    public SenalAdapter(ArrayList<ESenal> senals){
        this.senals = senals;
    }

    public void updateIncidencias(ArrayList<ESenal> senals){
        this.senals = senals;
    }

    @NonNull
    @Override
    public SenalHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_senal, viewGroup, false);
        return new SenalHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SenalHolder senalHolder, int i) {
        ESenal senal = senals.get(i);

        loadImage(senalHolder.iv_foto, senal.imagen);
        senalHolder.tv_detalle.setText(senal.detalle);
        senalHolder.tv_codigo.setText(senal.codigo);
        senalHolder.tv_ubicacion.setText(senal.ubicacion.ubicacion);


    }

    private void loadImage(ImageView iv_foto, String imagen) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + saveImage.NameOfFolder + File.separator + imagen;
        File archivo = new File(path);
        if (archivo.exists()){
            Bitmap foto = BitmapFactory.decodeFile(archivo.getAbsolutePath());
            iv_foto.setImageBitmap(foto);
        }

    }


    @Override
    public int getItemCount() {
        return senals.size();
    }

    class SenalHolder extends RecyclerView.ViewHolder{
        ImageView iv_foto;
        TextView tv_detalle;
        TextView tv_ubicacion;
        TextView tv_codigo;
        ImageView iv_upload;
        public SenalHolder(@NonNull View itemView) {
            super(itemView);
            iv_foto = itemView.findViewById(R.id.iv_senal);
            tv_detalle = itemView.findViewById(R.id.tv_detalle);
            tv_ubicacion = itemView.findViewById(R.id.tv_ubicacion);
            tv_codigo = itemView.findViewById(R.id.tv_codigo);
            iv_upload = itemView.findViewById(R.id.iv_uploadInfo);
        }
    }
}
