package adp;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sgsits_dr.HomeActivity;
import com.example.sgsits_dr.R;


import java.util.ArrayList;


public class RecyclerViewAdp extends RecyclerView.Adapter<RecyclerViewAdp.ViewHolder> {

    private ArrayList<String> arr;
    public RecyclerViewAdp(HomeActivity homeActivity, ArrayList<String> strings) {
        arr=strings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View listItem = layoutInflater.inflate(R.layout.recycler_item, viewGroup, false);
       RecyclerViewAdp.ViewHolder viewHolder = new RecyclerViewAdp.ViewHolder(listItem);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(arr.get(i));
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text);
        }
    }
}
