package com.example.bighomework.teacher;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.bighomework.R;
import com.example.bighomework.dao.ExamData;
import com.example.bighomework.model.Exam;

import java.util.List;

/**
 * Created by Administrator on 2017/3/13 0013.
 * E-Mail：543441727@qq.com
 */

public class ExamAdapter extends BaseAdapter {

    private List<Exam>list;
    private Context context;
    private LayoutInflater mInflater;
    private String account;
    private ExamData ED=new ExamData();

    public ExamAdapter(Context context, List<Exam> list,String account) {
        this.list = list;
        this.context = context;
        this.account=account;
        mInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Exam getItem(int position) {
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
            item = mInflater.inflate(R.layout.item_exam, null);
            viewHolder = new ViewHolder();
            viewHolder.input_exam_name = (TextView) item.findViewById(R.id.input_exam_name);
            viewHolder.delete = (ImageButton) item.findViewById(R.id.delete);
            item.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) item.getTag();
        }
        try {
            if (!list.get(position).getExamName().equals("")) {
                viewHolder.input_exam_name.setText(list.get(position).getExamName());
            } else {
                viewHolder.input_exam_name.setText("考试不详");
            }
            viewHolder.input_exam_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        Intent intent=new Intent(view.getContext(), TeaGradeActivity.class);
                        intent.putExtra("exam",list.get(position).getExamName());
                        intent.putExtra("account",account);
                        view.getContext().startActivity(intent);
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }
                }
            });
            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        ED.deleteExam(list.get(position).getExamName());
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
        public TextView input_exam_name;
        public ImageButton delete;
    }
}
