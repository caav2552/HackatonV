package proyect.nerehira.hackatonv2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


import java.util.ArrayList;


import proyect.nerehira.hackatonv2.Config.SingletonDatabase;
import proyect.nerehira.hackatonv2.Datos.BaseDatos;
import proyect.nerehira.hackatonv2.Datos.DSingletonSession;
import proyect.nerehira.hackatonv2.Entidad.EUser;
import proyect.nerehira.hackatonv2.Negocio.NUser;
import android.telephony.euicc.EuiccInfo;

import proyect.nerehira.hackatonv2.servicio.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.alibaba.fastjson.JSON;
public class MainActivity extends AppCompatActivity {

    private Animation animation;
    private ImageView imageView;
    private Button btnUser;
    private BaseDatos basedatos;
    private NUser nUser;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private GoogleSignInClient googleSignInClient;
    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionPagerAdapter;
    private NUser negocio;
    EUser usuario;
    Retrofit cliente;
    ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nUser = new NUser(this);
        animation= AnimationUtils.loadAnimation(this,R.anim.animation);
        imageView=(ImageView)findViewById(R.id.imageView);
        btnUser=(Button)findViewById(R.id.btnUser);
//
        setData();
        iniciarAnimacion();

     //  Toast.makeText(MainActivity.this,instancear(),Toast.LENGTH_SHORT).show();
            this.instancear();

        //Autentification GMAIL
        progressBar=(ProgressBar)findViewById(R.id.progress_circular);
        auth=FirebaseAuth.getInstance();
        GoogleSignInOptions gso=new GoogleSignInOptions.Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient= GoogleSignIn.getClient(this,gso);

        //TABS

    }
    private void setData() {
        this.negocio = new NUser(this);
    }

    public String instancear(){
        View vie ;
        long x;
        String sms1="";

        /*   x = negocio.insertarUser("carla","caav","1234","ti");

        if(x!=-1){
            return sms1="Senal insertado correctamente";
        }else{
            return sms1="Senal no insertado";
        }*/
        return sms1;



    }

    public void iniciarAnimacion(){
        imageView.startAnimation(animation);
    }
    public void btnAdm(View view){
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        View vie =getLayoutInflater().inflate(R.layout.dialog_admlogin,null);
        final EditText codigo=(EditText)vie.findViewById(R.id.codigo);
        final EditText password=(EditText)vie.findViewById(R.id.password);
        Button login=(Button)vie.findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = codigo.getText().toString();
                String pass = password.getText().toString();
                String message = "";
                if (user.isEmpty() ){
                    message ="Ingrese un usuario";
                }

                if (pass.isEmpty()){
                    message = "Ingrese una contrasenha";
                }

                if (!message.isEmpty()) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Login");
                    alert.setMessage("Ingrese los campos necesario");
                    alert.create().show();
                    return;
                }
                int y= consultarUsuario(codigo.getText().toString());
                int z= negocio.consultarPasword(codigo.getText().toString(),password.getText().toString());
                if (y>=1){
                    if (z>=1) {
                        Intent intent = new Intent(MainActivity.this, activity_Form.class);
                        startActivity(intent);
                    }
               }


                Toast.makeText(MainActivity.this,"Inicio sesion",Toast.LENGTH_SHORT).show();
//
//                cliente = new Retrofit.Builder()
//                        .baseUrl("http://" + "192.168.10.154/servicios-alcaldia/public/")
//                        .addConverterFactory(GsonConverterFactory
//                                .create()).build();
//
//                apiService = cliente.create(ApiService.class);

         //       ArrayList lisa=consultarUsuario(codigo.getText().toString(),password.getText().toString());
             //   Intent intent = new Intent(view.getContext(), activity_Form.class);
//                startActivityForResult(intent, 0);
       //       intent.putExtra("nameMail",nam);
             /**if(!lisa.isEmpty()){
                    Toast.makeText(MainActivity.this,"Datos Correctos",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), activity_Form.class);
                    String nam=lisa.get(1).toString();
                    intent.putExtra("nameMail",nam);
                    intent.putExtra("urlFoto","");
                    startActivityForResult(intent, 0);
                }else{
                    Toast.makeText(MainActivity.this,"Error al consultar",Toast.LENGTH_SHORT).show();
                }*/
            }
        });
        builder.setView(vie);
        AlertDialog dialog=builder.create();
        dialog.show();
    }
    public int consultarUsuario(String codigo){

       return negocio.consultarUser(codigo);

    }

    public void btnUser(View view) {
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(view.getContext(), Principal2.class);
                //startActivityForResult(intent, 0);

                progressBar.setVisibility(view.VISIBLE);
                Intent intent=googleSignInClient.getSignInIntent();
                startActivityForResult(intent,123);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==123){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);

            try{
                GoogleSignInAccount account=task.getResult(ApiException.class);
                if(account!=null){
                    firebaseAutWithGoogle(account);

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void firebaseAutWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential= GoogleAuthProvider.
                getCredential(account.getIdToken(),null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, task ->{
                    if(task.isSuccessful()){
                        progressBar.setVisibility(View.VISIBLE);
                        FirebaseUser user=auth.getCurrentUser();
                        update(user);
                    }else{
                        progressBar.setVisibility(View.INVISIBLE);

                    }
                });

    }
    private void update(FirebaseUser user){
        if(user!=null){

            String name=user.getDisplayName();
            String email=user.getEmail();
            String photo=user.getPhotoUrl().toString();
            Toast.makeText(MainActivity.this,""+name+email,Toast.LENGTH_SHORT).show();
            //Fire
            //imageView.setImageURI(photo);
            Intent intent = new Intent(this, Principal2.class);
            intent.putExtra("nameMail",email);
            intent.putExtra("urlFoto",photo);
            startActivityForResult(intent, 0);

        }else{
            Toast.makeText(MainActivity.this,"No user",Toast.LENGTH_SHORT).show();
        }
    }

    public static class PlaceholderFragment extends Fragment{
        private static final String ANG_SECTION_NUMBER="section_number";
        public PlaceholderFragment(){

        }
        public static PlaceholderFragment newInstance(int sectionNumber){
            PlaceholderFragment fragment=new PlaceholderFragment();
            Bundle args=new Bundle();
            args.putInt(ANG_SECTION_NUMBER,sectionNumber);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
            View rootView=inflater.inflate(R.layout.activity_principal,container,false);
            //TextView textView=(TextView)rootView.findViewById(R.id.);
            return rootView;
        }
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter{

        public SectionsPagerAdapter(FragmentManager fn){
            super(fn);
        }

        @Override
        public Fragment getItem(int i) {
            return PlaceholderFragment.newInstance(i+1);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
