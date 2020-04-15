package app.indiacoronatracker.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import app.indiacoronatracker.Adapters.StatewiseDataAdapter;
import app.indiacoronatracker.Adapters.WHOAdviceAdapter;
import app.indiacoronatracker.Models.AdviceModel;
import app.indiacoronatracker.R;
import app.indiacoronatracker.databinding.FragmentAdvicesBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdvicesFragment extends Fragment {

    private FragmentAdvicesBinding fragmentAdvicesBinding;
    private ArrayList<AdviceModel> list = new ArrayList<>();
    private WHOAdviceAdapter adviceAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentAdvicesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_advices, container, false);
        return fragmentAdvicesBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            String q = obj.getString("title");
            String a = obj.getString("subtitle");
            JSONArray array = obj.getJSONArray("basics");
            Log.d("tmz", array.toString() + "");

            for (int i=0; i<array.length();i++){
                JSONObject object = array.getJSONObject(i);
                AdviceModel model = new AdviceModel();

                model.setQuestion(object.getString("title"));
                model.setAnswer(object.getString("subtitle"));

                list.add(new AdviceModel(q,a));
                list.add(model);

            }
            fragmentAdvicesBinding.adviceRecyclerView.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager
                    = new LinearLayoutManager(getActivity());
            fragmentAdvicesBinding.adviceRecyclerView.setLayoutManager(linearLayoutManager);
            adviceAdapter = new WHOAdviceAdapter(list,getContext());
            fragmentAdvicesBinding.adviceRecyclerView.setAdapter(adviceAdapter);



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("who_corona_advice.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
