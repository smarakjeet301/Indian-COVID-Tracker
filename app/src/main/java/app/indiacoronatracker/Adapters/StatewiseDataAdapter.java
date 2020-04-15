package app.indiacoronatracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import app.indiacoronatracker.Models.StatewiseModel;
import app.indiacoronatracker.R;

public class StatewiseDataAdapter extends RecyclerView.Adapter<StatewiseDataAdapter.ViewHolder> {

    private ArrayList<StatewiseModel> list;
    private Context context;
    private ArrayList<StatewiseModel> arraylist;

    public StatewiseDataAdapter(ArrayList<StatewiseModel> list, Context context) {
        this.list = list;
        this.context = context;
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout
        View view = inflater.inflate(R.layout.item_stat, parent, false);

        return new StatewiseDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        StatewiseModel model = list.get(position);
        holder.name.setText(model.getState());
        holder.active.setText(model.getActive());
        holder.confirmed.setText(model.getConfirmed());
        holder.death.setText(model.getDeath());
        holder.recovered.setText(model.getRecovered());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, active, confirmed, death, recovered;

        ViewHolder(View view) {
            super(view);

            //TextView
            name = view.findViewById(R.id.state_name);
            active = view.findViewById(R.id.actv_case);
            confirmed = view.findViewById(R.id.cnfm_case);
            death = view.findViewById(R.id.dcsd_case);
            recovered = view.findViewById(R.id.rcvrd_case);


        }
    }

    public void filterData(String query) {

        query = query.toLowerCase(Locale.getDefault());
        list.clear();
        if (query.length() == 0) {
            list.addAll(arraylist);
        } else {
            for (StatewiseModel wp : arraylist) {
                if (wp.getState().toLowerCase(Locale.getDefault()).contains(query)) {
                    list.add(wp);
                }
            }
        }
        notifyDataSetChanged();

    }
}
