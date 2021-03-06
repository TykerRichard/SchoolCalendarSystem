package com.example.tylerricardc196.UI;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tylerricardc196.Classes.Courses;
import com.example.tylerricardc196.Classes.Terms;
import com.example.tylerricardc196.Database.Repository;
import com.example.tylerricardc196.R;

import java.util.List;

public class CourseRecyclerAdapter extends RecyclerView.Adapter<CourseRecyclerAdapter.MyViewHolder> {
    private final List<Courses> allCourses;
    private int selectedPosition;
    private TextView courseTextView;
    private Context context;
    private final List<Terms> allTerms;


    public CourseRecyclerAdapter(List<Courses> course, List<Terms> terms) {
        this.allCourses = course;
        this.allTerms = terms;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseName;
        private final TextView courseStartDate;
        private final TextView courseEndDate;
        private final TextView courseTermName;
        private final TextView courseLabel;
        private final TextView courseStart;
        private final TextView courseEnd;
        private final TextView courseTerm;


        public MyViewHolder(View view) {
            super(view);
            context = view.getContext();
            courseName = view.findViewById(R.id.CourseNameRecycle);
            courseStartDate = view.findViewById(R.id.CourseStartDateRecycle);
            courseEndDate = view.findViewById(R.id.CourseEndDateRecycle);
            courseTextView = itemView.findViewById(R.id.CourseView);
            courseTermName = view.findViewById(R.id.CourseTermField);
            courseLabel=view.findViewById(R.id.CourseNameLabel);
            courseStart=view.findViewById(R.id.CourseStartDateLabel);
            courseEnd=view.findViewById(R.id.CourseEndDateLabel);
            courseTerm=view.findViewById(R.id.CourseUITermLabel);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    selectedPosition = getAdapterPosition();
                    Courses current = allCourses.get(selectedPosition);
                    Intent intent = new Intent(context, AddModifyCourses.class);
                    intent.putExtra("TermID", current.getTermID());
                    intent.putExtra("CourseID", current.getCourseID());
                    intent.putExtra("CourseTitle", current.getCourseTitle());
                    intent.putExtra("CourseStart", current.getStartDate());
                    intent.putExtra("CourseEnd", current.getEndDate());
                    intent.putExtra("Status", current.getStatus());
                    intent.putExtra("InstructorName", current.getInstructorName());
                    intent.putExtra("InstructorNumber", current.getPhoneNumber());
                    intent.putExtra("Email", current.getEmailAddress());
                    intent.putExtra("Notes", current.getNotes());
                    context.startActivity(intent);


                }

            });


        }
    }


    @NonNull
    @Override
    public CourseRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull CourseRecyclerAdapter.MyViewHolder holder, int position) {
        if (allCourses.get(position).getCourseID() == 1) {
            holder.courseTerm.setVisibility(View.GONE);
            holder.courseLabel.setVisibility(View.GONE);
            holder.courseStart.setVisibility(View.GONE);
            holder.courseEndDate.setVisibility(View.GONE);
            holder.courseStartDate.setVisibility(View.GONE);
            holder.courseEnd.setVisibility(View.GONE);
            holder.courseTermName.setVisibility(View.GONE);
            holder.courseName.setVisibility(View.GONE);
        }

        String courseTitle = allCourses.get(position).getCourseTitle();
        String courseStart = allCourses.get(position).getStartDate();
        String courseEnd = allCourses.get(position).getEndDate();
        int termID = allCourses.get(position).getTermID();
        holder.courseName.setText(courseTitle);
        holder.courseStartDate.setText(courseStart);
        holder.courseEndDate.setText(courseEnd);

            for (Terms currentTerm : allTerms) {
                if (currentTerm.getTermID() == termID && allCourses.get(position).getCourseID() != 1) {
                    holder.courseTermName.setText(currentTerm.getTermName());
                }
        }
    }
    @Override
    public int getItemCount() {
        return allCourses.size();
    }


}