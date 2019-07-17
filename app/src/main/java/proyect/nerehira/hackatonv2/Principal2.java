package proyect.nerehira.hackatonv2;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import proyect.nerehira.hackatonv2.Adapter.PrincipalViewTabPagerAdapter;
import proyect.nerehira.hackatonv2.Config.SingletonDatabase;
import proyect.nerehira.hackatonv2.Fragment.IncidenciasFragment;
import proyect.nerehira.hackatonv2.Fragment.SenalFragment;

public class Principal2 extends AppCompatActivity {

    private PrincipalViewTabPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TextView mail;
    private CircleImageView fotoMail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        mail= findViewById(R.id.mail);
        fotoMail= findViewById(R.id.fotoMail);
        mail.setText(getIntent().getStringExtra("nameMail"));
        String photo=getIntent().getStringExtra("urlFoto");
        Picasso.get().load(photo).into(fotoMail);

      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new PrincipalViewTabPagerAdapter(getSupportFragmentManager());
        mSectionsPagerAdapter.addFragment(IncidenciasFragment.newInstance(), "Incidencias");
        mSectionsPagerAdapter.addFragment(SenalFragment.newInstance(), "Señales");
       // mSectionsPagerAdapter.addFragment(PlaceholderFragment.newInstance(2), "Extras");
        // Set up the ViewPager with the sections adapter.
        mViewPager =  findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

//        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
    }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_principal, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;

        }
    }

    public String insertarSenal(){
        String sms="";
//      baseDatos=new BaseDatos(this,"SEMURB.db",null,1);
        SQLiteDatabase basedatos = SingletonDatabase.getInstance(this).getDB();
        if(basedatos!=null){
            return "se a conectado a la base de datos";
//            int x=nSenal.insertarSenal(baseDatos,"1","123", "carla.jpg", "1",restrictivas);
//            int x=nSenal.insertarSenal(baseDatos,"1","123","carlita.jpg","restrictivas","1","malo","1","1");
//            if(x!=-1){
//               return sms="Senal insertado correctamente";
//            }else{
//                return sms="Senal no insertado";
//            }
           // return sms="base de datos creada correctamente";

        }
        return sms="base de datos no creada";
    }

    public void refrescarListaDeSenals() {
        /*
         * ==========
         * Justo aquí obtenemos la lista de la BD
         * y se la ponemos al RecyclerView
         * ============
         *
         * */
       /* if (adaptadorMascotas == null) return;
        listaDeMascotas = mascotasController.obtenerMascotas();
        adaptadorMascotas.setListaDeMascotas(listaDeMascotas);
        adaptadorMascotas.notifyDataSetChanged();*/
    }


}
