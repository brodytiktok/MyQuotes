package com.if4b.learn.myquotes.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.if4b.learn.myquotes.Model.QuoteModel;
import com.if4b.learn.myquotes.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterQuote extends RecyclerView.Adapter<AdapterQuote.HolderData> {

    private List<QuoteModel> listQuote = new ArrayList<>();

    public AdapterQuote(List<QuoteModel> listQuote) {
        this.listQuote = listQuote;
    }


    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_quote, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        QuoteModel QM = listQuote.get(position);

        holder.tvText.setText(QM.getText());
        holder.tvAuthor.setText(QM.getAuthor());


    }

    @Override
    public int getItemCount() {
        return listQuote.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvText, tvAuthor; //Didalam Card View
        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tv_text); // harus izin didalam view holder
            tvAuthor = itemView.findViewById(R.id.tv_author);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "Author : "+ tvText.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
