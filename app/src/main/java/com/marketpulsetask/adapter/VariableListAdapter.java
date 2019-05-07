package com.marketpulsetask.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.marketpulsetask.R;
import com.marketpulsetask.databinding.RowItemIndecatorVariableBinding;
import com.marketpulsetask.databinding.RowItemValueVariableBinding;
import com.marketpulsetask.pojo.VariableItems;
import com.marketpulsetask.ui.global.RecyclerViewItemClickListener;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class VariableListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private HashMap<String, VariableItems> variableItemDataList;
    private RecyclerViewItemClickListener listener;
    private boolean isIndicatorType = false;
    private int currentPossition = 0;

    public VariableListAdapter(@NotNull HashMap<String, VariableItems> variable, @NotNull RecyclerViewItemClickListener listener) {
        this.variableItemDataList = variable;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        // create a new view
        switch (viewType) {
            case 0:
                RowItemIndecatorVariableBinding rowItemIndecatorVariableBinding =
                        DataBindingUtil.inflate(layoutInflater, R.layout.row_item_indecator_variable, parent, false);
                vh = new IndicatorViewHolder(rowItemIndecatorVariableBinding);
                isIndicatorType = true;
                return vh;
            default:
                RowItemValueVariableBinding rowItemValueVariableBinding =
                        DataBindingUtil.inflate(layoutInflater, R.layout.row_item_value_variable, parent, false);
                vh = new ValueViewHolder(rowItemValueVariableBinding);
                isIndicatorType = false;
                return vh;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        currentPossition = position;
        switch (holder.getItemViewType()) {
            case 0:
                isIndicatorType = true;
                IndicatorViewHolder indicatorViewHolder = (IndicatorViewHolder) holder;
                indicatorViewHolder.rowItemIndecatorVariableBinding.setModel(variableItemDataList.get(position));
                break;
            default:
                isIndicatorType = false;
                ValueViewHolder valueViewHolder = (ValueViewHolder) holder;
                valueViewHolder.rowItemValueVariableBinding.setValue(variableItemDataList.get(position).getValues().get(position));
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 1; //Default is 1
        if (position == 0) viewType = 0; //if zero, it will be a Indicator view
        return viewType;
    }


    @Override
    public int getItemCount() {
        return variableItemDataList.values().size();
        // return isIndicatorType ? variableItemDataList.size() : variableItemDataList.get(currentPossition).getValues().size();
    }

    public class IndicatorViewHolder extends RecyclerView.ViewHolder {

        private RowItemIndecatorVariableBinding rowItemIndecatorVariableBinding;

        public IndicatorViewHolder(@NonNull RowItemIndecatorVariableBinding binding) {
            super(binding.getRoot());
            this.rowItemIndecatorVariableBinding = binding;
        }
    }

    public class ValueViewHolder extends RecyclerView.ViewHolder {

        private RowItemValueVariableBinding rowItemValueVariableBinding;

        public ValueViewHolder(RowItemValueVariableBinding binding) {
            super(binding.getRoot());
            this.rowItemValueVariableBinding = binding;
        }
    }
}
