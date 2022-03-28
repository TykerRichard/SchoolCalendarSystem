package com.example.tylerricardc196.UI;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tylerricardc196.Classes.Courses;
import com.example.tylerricardc196.Classes.Terms;
import com.example.tylerricardc196.Database.Repository;
import com.example.tylerricardc196.R;

import java.util.List;

public class TermRecyclerAdapter extends RecyclerView.Adapter<TermRecyclerAdapter.MyViewHolder> {
    private List<Terms> allTerms;
    private int selectedPosition=-1;
    private TextView termTextView;
    private Context context;

    public TermRecyclerAdapter(List<Terms> terms) {
        this.allTerms = terms;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView termName;
        private TextView termStartDate;
        private TextView termEndDate;

        public MyViewHolder(View view) {
            super(view);
            context=view.getContext();
            termName = view.findViewById(R.id.TermName);
            termStartDate=view.findViewById(R.id.TermStateDateField);
            termEndDate=view.findViewById(R.id.TermEndDateField);
            termTextView = itemView.findViewById(R.id.TermName);



            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    selectedPosition=getAdapterPosition();
                    final Terms current=allTerms.get(selectedPosition);
                    Intent intent=new Intent(context,AddModifyTerm.class);
                    intent.putExtra("TermID", current.getTermID());
                    intent.putExtra("TermNameField",current.getTermName());
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
            String termName = allTerms.get(position).getTermName();
            String termStart=allTerms.get(position).getStartDate();
            String termEnd= allTerms.get(position).getEndDate();
            holder.termName.setText(termName);
            holder.termStartDate.setText(termStart);
            holder.termEndDate.setText(termEnd);
        }

        @Override
        public int getItemCount() {
            return allTerms.size();
        }




}