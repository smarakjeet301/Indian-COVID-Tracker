package app.indiacoronatracker.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.indiacoronatracker.Activities.DetailNewsWebviewActivity;
import app.indiacoronatracker.Adapters.StatewiseDataAdapter;
import app.indiacoronatracker.Apis.GetRequests;
import app.indiacoronatracker.Models.StatewiseModel;
import app.indiacoronatracker.R;
import app.indiacoronatracker.databinding.FragmentStatsBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatsFragment extends Fragment {

    private FragmentStatsBinding fragmentStatsBinding;
    private ArrayList<StatewiseModel> list=new ArrayList<>();
    private StatewiseDataAdapter statewiseDataAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentStatsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_stats,container, false);
        return fragmentStatsBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getStats();
        getStatewiseData();


        fragmentStatsBinding.searchState.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return false;
            }
        });


        fragmentStatsBinding.searchState.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                statewiseDataAdapter.filterData(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                statewiseDataAdapter.filterData(newText);
                return false;
            }
        });


        fragmentStatsBinding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getStats();
                getStatewiseData();
            }
        });


        fragmentStatsBinding.urlApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), DetailNewsWebviewActivity.class);
                i.putExtra("url", "https://api.covid19india.org");
                startActivity(i);
            }
        });

    }


    private void getStatewiseData() {



        AndroidNetworking.get(GetRequests.getStats)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject response) {
                        fragmentStatsBinding.swipeRefresh.setRefreshing(false);
                        try {
                            JSONArray statewise = response.getJSONArray("statewise");

                            for (int i=1;i<statewise.length();i++){
                                JSONObject statewiseJSONObject = statewise.getJSONObject(i);
                                StatewiseModel model = new StatewiseModel();
                                model.setActive(statewiseJSONObject.getString("active"));
                                model.setConfirmed(statewiseJSONObject.getString("confirmed"));
                                model.setDeath(statewiseJSONObject.getString("deaths"));
                                model.setState(statewiseJSONObject.getString("state"));
                                model.setRecovered(statewiseJSONObject.getString("recovered"));


                                list.add(model);
                            }

                            fragmentStatsBinding.statsRecyclerview.setHasFixedSize(true);
                            LinearLayoutManager linearLayoutManager
                                    = new LinearLayoutManager(getActivity());
                            fragmentStatsBinding.statsRecyclerview.setLayoutManager(linearLayoutManager);
                            statewiseDataAdapter = new StatewiseDataAdapter(list,getContext());
                            fragmentStatsBinding.statsRecyclerview.setAdapter(statewiseDataAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    private void getStats() {

        AndroidNetworking.get(GetRequests.getStats)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject response) {
                        fragmentStatsBinding.swipeRefresh.setRefreshing(false);
                        try {
                            JSONArray statewise = response.getJSONArray("statewise");
                                JSONObject data = statewise.getJSONObject(0);
                                fragmentStatsBinding.totalConfirmedCases.setText(data.getString("confirmed"));
                                fragmentStatsBinding.totalDeathCases.setText(data.getString("deaths"));
                                fragmentStatsBinding.totalRecoveredCases.setText(data.getString("recovered"));
                                String[] time = data.getString("lastupdatedtime").split(" ");
                                fragmentStatsBinding.updateTime.setText("Updated:"+time[0]+" "+time[1]);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }





}
