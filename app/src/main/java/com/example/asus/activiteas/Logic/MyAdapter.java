package com.example.asus.activiteas.Logic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.activiteas.R;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter<File> {
    private LayoutInflater inflater;
    private int layout;
    private List<String> myList;
    private PDFHelper pdfHelper;
    private boolean isStoragePermission;

    public MyAdapter(Context context, int resource, File[] files, boolean isStoragePermission) {
        super(context, resource, files);
        myList = new ArrayList<String>();
        pdfHelper = new PDFHelper();
        this.isStoragePermission = isStoragePermission;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        for(File file : files){
            myList.add(file.getName());
        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final String file = myList.get(position);

        viewHolder.nameView.setText(file);

        viewHolder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pdfHelper.deletePdf(file, isStoragePermission);
            }
        });

        return convertView;
    }

    private class ViewHolder {
        final Button removeButton;
        final TextView nameView;
        ViewHolder(View view){
            removeButton = (Button) view.findViewById(R.id.removeButton);
            nameView = (TextView) view.findViewById(R.id.nameView);
        }
    }
}

