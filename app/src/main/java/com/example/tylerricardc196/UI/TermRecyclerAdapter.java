package com.example.tylerricardc196.UI;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tylerricardc196.Classes.Terms;
import com.example.tylerricardc196.R;

import java.util.List;

public class TermRecyclerAdapter extends RecyclerView.Adapter<TermRecyclerAdapter.MyViewHolder> {
    private final List<Terms> allTerms;
    private int selectedPosition = 1;
    private TextView termTextView;
    private Context context;

    public TermRecyclerAdapter(List<Terms> terms) {
        this.allTerms = terms;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView termName;
        private final TextView termStartDate;
        private final TextView termEndDate;
        private final TextView termLabel;
        private final TextView startLabel;
        private final TextView endLabel;
        public MyViewHolder(View view) {
            super(view);
            context = view.getContext();
            termName = view.findViewById(R.id.TermName);
            termStartDate = view.findViewById(R.id.TermStateDateField);
            termEndDate = view.findViewById(R.id.TermEndDateField);
            termLabel=view.findViewById(R.id.TermButton);
            startLabel=view.findViewById(R.id.TermStartDate);
            endLabel=view.findViewById(R.id.TermEndDate);
            termTextView = itemView.findViewById(R.id.TermName);




            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    selectedPosition = getAdapterPosition();
                    final Terms current = allTerms.get(selectedPosition);
                    Intent intent = new Intent(context, AddModifyTerm.class);
                    intent.putExtra("TermID", current.getTermID());
                    intent.putExtra("TermNameField", current.getTermName());
                    intent.putExtra("StartDateField", current.getStartDate());
                    intent.putExtra("EndDateField", current.getEndDate());
                    context.startActivity(intent);




                }

            });


        }
    }


    @NonNull
    @Override
    public TermRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.term_list, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull TermRecyclerAdapter.MyViewHolder holder, int position) {
        if(allTerms.get(position).getTermID()==1){
            holder.termName.setVisibility(View.GONE);
            holder.termStartDate.setVisibility(View.GONE);
            holder.termEndDate.setVisibility(View.GONE);
            holder.termLabel.setVisibility(View.GONE);
            holder.startLabel.setVisibility(View.GONE);
            holder.endLabel.setVisibility(View.GONE);

        }
        String termName = allTerms.get(position).getTermName();
        String termStart = allTerms.get(position).getStartDate();
        String termEnd = allTerms.get(position).getEndDate();
        holder.termName.setText(termName);
        holder.termStartDate.setText(termStart);
        holder.termEndDate.setText(termEnd);
    }

    @Override
    public int getItemCount() {
        return allTerms.size();
    }


}