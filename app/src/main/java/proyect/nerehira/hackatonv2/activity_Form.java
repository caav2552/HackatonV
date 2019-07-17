package proyect.nerehira.hackatonv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_Form extends AppCompatActivity {
    private Button Incidencia;
    private  Button Senals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__form);
        Incidencia=(Button)findViewById(R.id.Incidencia);
        Senals=(Button)findViewById(R.id.Senals);
    }

    public void Incidencia(View view) {
        Incidencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Principal2.class);
                startActivityForResult(intent, 0);

            }
        });
    }

}
