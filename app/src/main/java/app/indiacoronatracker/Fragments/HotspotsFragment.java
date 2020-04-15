package app.indiacoronatracker.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.indiacoronatracker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotspotsFragment extends Fragment {

    public HotspotsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hotspots, container, false);
    }
}
