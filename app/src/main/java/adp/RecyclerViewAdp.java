package adp;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sgsits_dr.Editprofile;
import com.example.sgsits_dr.HomeActivity;
import com.example.sgsits_dr.NotificationActivity;
import com.example.sgsits_dr.NotifictionDisplay;
import com.example.sgsits_dr.R;
import com.example.sgsits_dr.models.NotificationModel;


import java.util.ArrayList;

import database.NotificationTable;


public class RecyclerViewAdp extends RecyclerView.Adapter<RecyclerViewAdp.ViewHolder> {
    public static NotificationModel NOTIFICATION_MODEL;
    private ArrayList<NotificationModel> arr;
    NotificationTable nt;
    private Context context;
    public RecyclerViewAdp(Context context, ArrayList<NotificationModel> strings) {
        arr=strings;
        this.context=context;

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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {
        nt=new NotificationTable();
        viewHolder.textView.setText(arr.get(i).getTitle());
        viewHolder.summary.setText(arr.get(i).getSummary());
        viewHolder.domain.setText(arr.get(i).getDomain());
        viewHolder.stdate.setText(arr.get(i).getStartdate());
        viewHolder.enddate.setText(arr.get(i).getEnddate());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NOTIFICATION_MODEL=arr.get(i);
                Intent intent= new Intent(context, NotifictionDisplay.class);
                intent.putExtra("description",arr.get(i).getDes()
                );
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView summary;
        TextView domain;
        TextView stdate;
        TextView enddate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text);
            summary=itemView.findViewById(R.id.summary);
            domain=itemView.findViewById(R.id.domain);
            stdate=itemView.findViewById(R.id.startdate);
            enddate=itemView.findViewById(R.id.enddate);
        }
    }
}
