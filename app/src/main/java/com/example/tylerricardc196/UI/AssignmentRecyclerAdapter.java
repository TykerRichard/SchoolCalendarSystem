package com.example.tylerricardc196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tylerricardc196.Classes.Assignments;
import com.example.tylerricardc196.Classes.Courses;
import com.example.tylerricardc196.Database.Repository;
import com.example.tylerricardc196.R;

import java.util.List;

public class AssignmentRecyclerAdapter extends RecyclerView.Adapter<AssignmentRecyclerAdapter.MyViewHolder>{
    private List<Assignments> allAssignments;
    private List<Courses> allCourses;
    private Context context;
    private int selectedPosition;

    public AssignmentRecyclerAdapter(List<Assignments>assignments, List<Courses> courses){
        this.allAssignments=assignments;
        this.allCourses=courses;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView assignmentName;
        TextView assignmentStartDate;
        TextView assignmentEndDate;
        TextView assignmentType;
        TextView assignmentCourse;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            assignmentName= itemView.findViewById(R.id.AssignmentRNameField);
            assignmentStartDate=itemView.findViewById(R.id.AssignmentRStartField);
            assignmentEndDate=itemView.findViewById(R.id.AssignmentREndField);
            assignmentType=itemView.findViewById(R.id.AssignmentRTypeField);
            assignmentCourse=itemView.findViewById(R.id.AssignmentRCourseField);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedPosition=getAdapterPosition();
                    Assignments current= allAssignments.get(selectedPosition);
                    Intent intent=new Intent(context,AddModifyAssignments.class);
                    intent.putExtra("AssignmentID",current.getAssignmentID());
                    intent.putExtra("AssignmentName",current.getAssignmentName());
                    intent.putExtra("StartDate",current.getStartDate());
                    intent.putExtra("EndDate",current.getEndDate());
                    intent.putExtra("Type",current.getType());
                    intent.putExtra("AssignedCourse",current.getAssignedCourse());
                    context.startActivity(intent);



                }
            });

        }
    }

    @NonNull
    @Override
    public AssignmentRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_list,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentRecyclerAdapter.MyViewHolder holder, int position) {
        String assignmentTitle=allAssignments.get(position).getAssignmentName();
        String startDate=allAssignments.get(position).getStartDate();
        String endDate=allAssignments.get(position).getEndDate();
        String type=allAssignments.get(position).getType();
        int courseID=allAssignments.get(position).getAssignedCourse();

        holder.assignmentName.setText(assignmentTitle);
        holder.assignmentStartDate.setText(startDate);
        holder.assignmentEndDate.setText(endDate);
        holder.assignmentType.setText(type);

        for(Courses current : allCourses){
            if(current.getCourseID()==courseID){
                holder.assignmentCourse.setText(current.getCourseTitle());
            }
        }
        if(holder.assignmentCourse.getText().toString().equalsIgnoreCase("textview")){
            holder.assignmentCourse.setText("Unassigned");
        }

    }

    @Override
    public int getItemCount() {
        return allAssignments.size();
    }



}
