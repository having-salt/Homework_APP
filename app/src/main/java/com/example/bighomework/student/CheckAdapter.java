package com.example.bighomework.student;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.bighomework.R;
import com.example.bighomework.dao.AccountData;
import com.example.bighomework.dao.ExamData;
import com.example.bighomework.model.Exam;
import com.example.bighomework.service.SignInService;

import java.util.List;


public class CheckAdapter extends BaseAdapter {

    private List<String>list;
    private Context context;
    private LayoutInflater mInflater;
    private String account;
    private AccountData AD;

    public CheckAdapter(Context context, List<String> list,String account) {
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
    public String getItem(int position) {
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
            item = mInflater.inflate(R.layout.item_check, null);
            viewHolder = new ViewHolder();
            viewHolder.input_check_name = (TextView) item.findViewById(R.id.input_check_name);
            viewHolder.check = (Button) item.findViewById(R.id.check);
            item.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) item.getTag();
        }
        try {
            Log.d("tag","1."+list.get(position));
            Log.d("tag","2."+AD.getNameByAccount(account));
            Log.d("tag","3."+account);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            if (!list.get(position).equals("")) {
                viewHolder.input_check_name.setText(list.get(position));
            } else {
                viewHolder.input_check_name.setText("签到不详");
            }
            viewHolder.check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        Log.d("tag",list.get(position)+AD.getNameByAccount(account)+account);
                        SignInService.signIn(list.get(position),AD.getNameByAccount(account),account);
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
        public TextView input_check_name;
        public Button check;
    }
}
