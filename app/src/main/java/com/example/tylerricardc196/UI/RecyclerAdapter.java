package com.example.tylerricardc196.UI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tylerricardc196.Classes.Courses;
import com.example.tylerricardc196.Classes.Terms;
import com.example.tylerricardc196.R;

import java.util.List;

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private List<Terms> allTerms;
    private List<Courses> allCourses;

    public RecyclerAdapter(List<Terms> terms){
        this.allTerms=terms;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView termName;

        public MyViewHolder(final View view){
            super(view);
            termName=view.findViewById(R.id.TermName);
        }

    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.term_list,parent,false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String termName= allTerms.get(position).getTermName();
        holder.termName.setText(termName);
    }

    @Override
    public int getItemCount() {
        return allTerms.size();
    }
}
