package com.example.bighomework.teacher;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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


public class TeaCheckAdapter extends BaseAdapter {

    private List<String>list1;
    private List<String>list2;
    private Context context;
    private LayoutInflater mInflater;
    private String account;
    private ExamData ED=new ExamData();

    public TeaCheckAdapter(Context context, List<String> list1,List<String> list2,String account) {
        this.list1 = list1;
        this.list2 = list2;
        this.context = context;
        this.account=account;
        mInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return list1.size();
    }

    @Override
    public String getItem(int position) {
        return list1.get(position);
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
            item = mInflater.inflate(R.layout.item_tea_check,null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) item.findViewById(R.id.name);
            viewHolder.time = (TextView) item.findViewById(R.id.time);
            item.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) item.getTag();
        }
        try {
            if (!list1.get(position).equals("")) {
                viewHolder.name.setText(list1.get(position));
            } else {
                viewHolder.name.setText("签到不详");
            }
            if (!list2.get(position).equals("")) {
                viewHolder.time.setText(list2.get(position));
            } else {
                viewHolder.time.setText("时间不详");
            }
            viewHolder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        Intent intent=new Intent(view.getContext(), HaveCheck.class);
                        intent.putExtra("sign",list1.get(position));
                        intent.putExtra("account",account);
                        view.getContext().startActivity(intent);
                    } catch (RuntimeException e) {
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
        public TextView name;
        public TextView time;
    }
}
