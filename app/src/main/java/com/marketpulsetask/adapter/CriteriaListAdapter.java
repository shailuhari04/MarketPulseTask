package com.marketpulsetask.adapter;

import android.graphics.Color;
import android.text.*;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.marketpulsetask.R;
import com.marketpulsetask.databinding.RowItemCriteriaBinding;
import com.marketpulsetask.pojo.CriteriaItem;
import com.marketpulsetask.pojo.VariableItems;
import com.marketpulsetask.ui.global.RecyclerViewItemClickListener;
import com.marketpulsetask.utils.Constants;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CriteriaListAdapter extends RecyclerView.Adapter<CriteriaListAdapter.MyViewHolder> {

    private List<CriteriaItem> responseDataList;
    private RecyclerViewItemClickListener listener;

    public CriteriaListAdapter(@NotNull List<CriteriaItem> data, @NotNull RecyclerViewItemClickListener listener) {
        this.responseDataList = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        RowItemCriteriaBinding rowItemCriteriaBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.row_item_criteria, parent, false);
        return new MyViewHolder(rowItemCriteriaBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.setModel(responseDataList.get(position));

        String text = responseDataList.get(position).getText();
        SpannableString spanString = new SpannableString(text.replace("$", "#"));
        Matcher matcher = Pattern.compile("#\\d+").matcher(spanString);
        DecimalFormat format = new DecimalFormat();
        format.setDecimalSeparatorAlwaysShown(false);

        while (matcher.find()) {
            spanString.setSpan(new ForegroundColorSpan(Color.parseColor("#0000FF")), matcher.start(), matcher.end(), 0); //to highlight word havgin '@'
            final String tag = matcher.group(0).replace("#", "$");

            //setTextViewHTML(holder.binding.tvText, tag);

            HashMap<String, VariableItems> variableItems = responseDataList.get(position).getVariable();
            VariableItems valueItems = null;
            assert variableItems != null;
            for (String key : variableItems.keySet()) {
                if (key.equals(tag)) {
                    valueItems = variableItems.get(key);
                    break;
                }
            }
            assert valueItems != null;
            String value = valueItems.getType().equalsIgnoreCase(Constants.Companion.getVARIABLE_TYPE_INDICATOR()) ? String.valueOf(valueItems.getDefault_value()) : format.format(valueItems.getValues().get(0));
            text = text.replace(tag, value);


            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View textView) {
                    Log.e("click", "click " + tag);
                    String searchText = tag.replace("@", ""); //replace '@' with blank character to search on google.
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);

                }
            };
            spanString.setSpan(clickableSpan, matcher.start(), matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        holder.binding.tvText.setText(text);
        /*holder.binding.tvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (responseDataList.get(position).getVariable() != null) {
                    listener.criteriaItemClicked(Objects.requireNonNull(responseDataList.get(position).getVariable()), position);
                }
            }
        });*/
    }

    protected void setTextViewHTML(TextView text, String html) {
        CharSequence sequence = Html.fromHtml(html);
        SpannableStringBuilder strBuilder = new SpannableStringBuilder(sequence);
        URLSpan[] urls = strBuilder.getSpans(0, sequence.length(), URLSpan.class);
        for (URLSpan span : urls) {
            makeLinkClickable(strBuilder, span);
        }
        text.setText(strBuilder);
        text.setMovementMethod(LinkMovementMethod.getInstance());
    }


    protected void makeLinkClickable(SpannableStringBuilder strBuilder, final URLSpan span) {
        int start = strBuilder.getSpanStart(span);
        int end = strBuilder.getSpanEnd(span);
        int flags = strBuilder.getSpanFlags(span);


        ClickableSpan clickable = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Log.d("LipAdapter", span.getURL());
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
            }
        };

        strBuilder.setSpan(clickable, start, end, flags);
        strBuilder.removeSpan(span);
    }

    @Override
    public int getItemCount() {
        return responseDataList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowItemCriteriaBinding binding;

        public MyViewHolder(@NonNull RowItemCriteriaBinding rowItemCriteriaBinding) {
            super(rowItemCriteriaBinding.getRoot());
            binding = rowItemCriteriaBinding;
        }

    }
}
