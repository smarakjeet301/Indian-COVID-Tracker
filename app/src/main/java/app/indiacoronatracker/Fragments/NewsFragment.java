package app.indiacoronatracker.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.indiacoronatracker.Adapters.NewsAdapter;
import app.indiacoronatracker.Adapters.StatewiseDataAdapter;
import app.indiacoronatracker.Apis.GetRequests;
import app.indiacoronatracker.Models.NewsModel;
import app.indiacoronatracker.R;
import app.indiacoronatracker.Utils.Constant;
import app.indiacoronatracker.databinding.FragmentNewsBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    private FragmentNewsBinding fragmentNewsBinding;
    private ArrayList<NewsModel> list = new ArrayList<>();
    private NewsAdapter newsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentNewsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);
        return fragmentNewsBinding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getNews();

    }

    private void getNews() {

        AndroidNetworking.get("https://newsapi.org/v2/everything?q=coronavirus-india&apiKey="+ Constant.NEWS_API_KEY)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray array = response.getJSONArray("articles");
                            for (int i=0; i<array.length();i++){
                                JSONObject object = array.getJSONObject(i);
                                NewsModel model = new NewsModel();

                                model.setImg_url(object.getString("urlToImage"));
                                model.setNews_title(object.getString("title"));
                                model.setNews_description(object.getString("description"));
                                model.setNews_url(object.getString("url"));


                                list.add(model);

                            }

                            fragmentNewsBinding.newRecyclerview.setHasFixedSize(true);
                            LinearLayoutManager linearLayoutManager
                                    = new LinearLayoutManager(getActivity());
                            fragmentNewsBinding.newRecyclerview.setLayoutManager(linearLayoutManager);
                            newsAdapter = new NewsAdapter(list,getContext());
                            fragmentNewsBinding.newRecyclerview.setAdapter(newsAdapter);


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
