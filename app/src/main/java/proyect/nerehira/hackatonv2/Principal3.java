package proyect.nerehira.hackatonv2;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import proyect.nerehira.hackatonv2.Adapter.PrincipalViewTabPagerAdapter;
import proyect.nerehira.hackatonv2.Fragment.IncidenciasFragment;
import proyect.nerehira.hackatonv2.Fragment.SenalFragment;

public class Principal3 extends AppCompatActivity {
    private PrincipalViewTabPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TextView mail;
    private CircleImageView fotoMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal2);
        mail = findViewById(R.id.mail);
        fotoMail = findViewById(R.id.fotoMail);
        mail.setText(getIntent().getStringExtra("nameMail"));
        String photo = getIntent().getStringExtra("urlFoto");
        Picasso.get().load(photo).into(fotoMail);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new PrincipalViewTabPagerAdapter(getSupportFragmentManager());
        mSectionsPagerAdapter.addFragment(IncidenciasFragment.newInstance(), "Senals");
        mSectionsPagerAdapter.addFragment(SenalFragment.newInstance(), "Lista");
        mSectionsPagerAdapter.addFragment(Principal3.PlaceholderFragment.newInstance(2), "Mapa");
        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
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
    }
}
*/
    }
    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static Principal3.PlaceholderFragment newInstance(int sectionNumber) {
            Principal3.PlaceholderFragment fragment = new Principal3.PlaceholderFragment();
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
}


