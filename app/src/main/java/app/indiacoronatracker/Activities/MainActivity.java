package app.indiacoronatracker.Activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import app.indiacoronatracker.Fragments.AdvicesFragment;
import app.indiacoronatracker.Fragments.HotspotsFragment;
import app.indiacoronatracker.Fragments.NewsFragment;
import app.indiacoronatracker.Fragments.StatsFragment;
import app.indiacoronatracker.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        changeFragment(new StatsFragment());

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_stat:
                    changeFragment(new StatsFragment());
                    return true;
                case R.id.navigation_advices:
                    changeFragment(new AdvicesFragment());
                    return true;
                case R.id.navigation_hotspots:
                    changeFragment(new HotspotsFragment());
                    return true;
                case R.id.navigation_news:
                    changeFragment(new NewsFragment());
                    return true;
            }
            return false;
        }
    };


    private void changeFragment(Fragment fragment) {
        FragmentTransaction transaction;
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        getSupportFragmentManager().popBackStack();
        transaction.commit();
    }

}
