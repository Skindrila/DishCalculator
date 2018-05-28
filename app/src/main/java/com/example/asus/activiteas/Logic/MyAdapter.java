package com.example.asus.activiteas.Logic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.asus.activiteas.R;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter<File> {
    private LayoutInflater inflater;
    private int layout;
    private List<String> myList;
    private PDFHelper pdfHelper;
    private Context context;
    private boolean isStoragePermission;
    private Activity activity;

    public MyAdapter(Context context, int resource, File[] files, boolean isStoragePermission, Activity activity) {
        super(context, resource, files);
        myList = new ArrayList<String>();
        pdfHelper = new PDFHelper();
        this.context = context;
        this.activity = activity;
        this.isStoragePermission = isStoragePermission;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        for(File file : files){
            myList.add(file.getName());
        }
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {
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
                myList.remove(position);
                activity.recreate();
            }
        });

        viewHolder.openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = pdfHelper.viewFile(file, isStoragePermission);
                try {
                    context.startActivity(intent);
                }catch (Exception e)
                {
                    Toast.makeText(context,R.string.toast8,Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.adobe.reader&hl=ru")));
                }
            }
        });

        return convertView;
    }

    private class ViewHolder {
        final Button removeButton;
        final Button openButton;
        final TextView nameView;
        ViewHolder(View view){
            removeButton = (Button) view.findViewById(R.id.removeButton);
            openButton = (Button) view.findViewById(R.id.openDDFButton);
            nameView = (TextView) view.findViewById(R.id.nameView);
        }
    }
}

