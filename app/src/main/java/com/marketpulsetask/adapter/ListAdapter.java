package com.marketpulsetask.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.marketpulsetask.R;
import com.marketpulsetask.databinding.RowItemParentBinding;
import com.marketpulsetask.pojo.CriteriaItem;
import com.marketpulsetask.pojo.Response;
import com.marketpulsetask.utils.Constants;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private ArrayList<Response> responseDataList;
    private ItemClickCallback listener;

    public ListAdapter(@NotNull ArrayList<Response> responseDataList, @NotNull ItemClickCallback listener) {

        this.responseDataList = responseDataList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        RowItemParentBinding rowItemParentBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.row_item_parent, parent, false);
        return new MyViewHolder(rowItemParentBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.rowItemParentBinding.setModel(responseDataList.get(position));

        String color = responseDataList.get(position).getColor();

        if (color.equalsIgnoreCase(Constants.Companion.getCOLOR_GREEN())) {
            holder.rowItemParentBinding.type.setTextColor(Color.GREEN);
        } else if (color.equalsIgnoreCase(Constants.Companion.getCOLOR_RED())) {
            holder.rowItemParentBinding.type.setTextColor(Color.RED);
        }

        holder.rowItemParentBinding.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(responseDataList.get(position).getCriteria(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return responseDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowItemParentBinding rowItemParentBinding;

        public MyViewHolder(@NonNull RowItemParentBinding rowItemParentBinding) {
            super(rowItemParentBinding.getRoot());
            this.rowItemParentBinding = rowItemParentBinding;
        }

    }

    public interface ItemClickCallback {
        void onItemClick(List<CriteriaItem> criteriaItemList, int position);
    }
}
