package com.marketpulsetask.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.marketpulsetask.R;
import com.marketpulsetask.pojo.Response;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CriteriaListAdapter extends RecyclerView.Adapter<CriteriaListAdapter.MyViewHolder> {

    private ArrayList<Response> responseDataList;
    private ItemClickCallback listener;

    public CriteriaListAdapter(@NotNull ArrayList<Response> responseDataList, @NotNull ItemClickCallback listener) {

        this.responseDataList = responseDataList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_parent, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvName.setText(responseDataList.get(position).getName());
        holder.tvType.setText(responseDataList.get(position).getCriteria().get(0).getType());

        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(responseDataList.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return responseDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvType;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.name);
            tvType = itemView.findViewById(R.id.type);
        }

    }

    public interface ItemClickCallback {
        void onItemClick(Response response, int position);
    }
}
