package com.example.bighomework.teacher;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.bighomework.R;
import com.example.bighomework.dao.ExamData;
import com.example.bighomework.model.Grade;

import java.util.List;

/**
 * Created by Administrator on 2017/3/13 0013.
 * E-Mail：543441727@qq.com
 */

public class GradeAdapter extends BaseAdapter {

    private List<Grade> list;
    private Context context;
    private LayoutInflater mInflater;
    private ExamData ED=new ExamData();
    private String exam;

    public GradeAdapter(Context context, List<Grade> list,String exam) {
        this.list = list;
        this.context = context;
        this.exam = exam;
        mInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Grade getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    @SuppressLint("InflateParams")
    public View getView(final int position, View convertView, ViewGroup parent) {
        View item = convertView;
        ViewHolder viewHolder;
        if (item == null) {
            item = mInflater.inflate(R.layout.item_grade, null);
            viewHolder = new ViewHolder();
            viewHolder.input_stu_name = (TextView) item.findViewById(R.id.input_stu_name);
            viewHolder.input_stu_grade = (TextView) item.findViewById(R.id.input_stu_grade);
            viewHolder.delete = (ImageButton) item.findViewById(R.id.delete);
            item.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) item.getTag();
        }
        try {
            if (!list.get(position).getStuName().equals("")) {
                viewHolder.input_stu_name.setText(list.get(position).getStuName());
            } else {
                viewHolder.input_stu_name.setText("姓名不详");
            }
            viewHolder.input_stu_grade.setText(String.valueOf(list.get(position).getGrade()));
            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        ED.deleteGrade(exam,list.get(position).getStuName());
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    public final class ViewHolder {
        public TextView input_stu_name;
        public TextView input_stu_grade;
        public ImageButton delete;
    }
}