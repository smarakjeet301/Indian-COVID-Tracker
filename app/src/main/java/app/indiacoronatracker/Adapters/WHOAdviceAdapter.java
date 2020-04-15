package app.indiacoronatracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import app.indiacoronatracker.Models.AdviceModel;
import app.indiacoronatracker.Models.StatewiseModel;
import app.indiacoronatracker.R;

public class WHOAdviceAdapter extends RecyclerView.Adapter<WHOAdviceAdapter.ViewHolder> {

private ArrayList<AdviceModel> list;
private Context context;

public WHOAdviceAdapter(ArrayList<AdviceModel> list, Context context) {
        this.list = list;
        this.context = context;

        }

@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout
        View view = inflater.inflate(R.layout.item_advice, parent, false);

        return new WHOAdviceAdapter.ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AdviceModel model = list.get(position);
        holder.question.setText(model.getQuestion());
        holder.answer.setText(model.getAnswer());

        }

@Override
public int getItemCount() {
        return list.size();
        }

class ViewHolder extends RecyclerView.ViewHolder {

    TextView question, answer;

    ViewHolder(View view) {
        super(view);

        //TextView
        question = view.findViewById(R.id.question);
        answer = view.findViewById(R.id.answer);


    }
}

}
