package com.example.asus.activiteas.Logic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PDFHelper {
    private Double mode;
    private Double numOfPerson;
    private String nameOfFile;

    public PDFHelper(){
    }

    public void setMode(Double mode) {
        this.mode = mode;
    }

    public void setNameOfFile(String nameOfFile) {
        this.nameOfFile = nameOfFile;
    }

    public void setNumOfPerson(Double numOfPerson) {
        this.numOfPerson = numOfPerson;
    }

    public void createPDF(FullList fullList, boolean isStoragePermissionGranted) throws DocumentException, FileNotFoundException {
        List<String> products = prepareForPDF(fullList);
        if(isStoragePermissionGranted) {
            File sdPath = Environment.getExternalStorageDirectory();
            sdPath = new File(sdPath + File.separator + "PdfFiles");
            if (!sdPath.exists())
                sdPath.mkdirs();
            File file = new File(sdPath.getAbsolutePath(), nameOfFile+".pdf");
            Document document = new Document();
            OutputStream os = new FileOutputStream(file);
            PdfWriter.getInstance(document, os);
            document.open();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            String currentDateAndTime = sdf.format(new Date());
            Paragraph paragraph = new Paragraph(currentDateAndTime+"\n");
            document.add(paragraph);
            paragraph.clear();
            for (String product : products){
                paragraph = new Paragraph(product);
                document.add(paragraph);
                paragraph.clear();
            }
            document.close();
        }
    }

    private List<String> prepareForPDF(FullList fullList){
        List<String> rows = new ArrayList<String>();
        List<Products> list = fullList.getProducts();

        int len = list.size();
        Products nextItem;
        Products currentItem;
        int position;
        for(int i = 0; i<len;i++){
            currentItem = list.get(i);
            position = i;
            position++;
            if(position == len)
                break;
            do{
                nextItem = list.get(position);
                if(currentItem.getName().equals(nextItem.getName())){
                    currentItem.plusPortion(nextItem);
                    list.remove(nextItem);
                    len--;
                    continue;
                }
                position++;
            }while (position<len);
        }
        int k = 1;
        for(Products product : list){
            Double num = product.getPortion()*numOfPerson*mode;
            rows.add(k +". "+product.getName()+"-> Portion: "+Math.round(num) +"gr.");
            k++;
        }

        return rows;
    }

    public Intent viewFile(String fileName, boolean isStoragePermission){
        if(isStoragePermission) {
            File sdPath = Environment.getExternalStorageDirectory();
            sdPath = new File(sdPath + File.separator + "PdfFiles" + File.separator + fileName);
            Uri uri = Uri.fromFile(sdPath);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "application/pdf");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            return intent;
        }
        else
            return null;
    }

    public void deletePdf(String fileName, boolean isStoragePermission){
        if(isStoragePermission) {
            File sdPath = Environment.getExternalStorageDirectory();
            sdPath = new File(sdPath + File.separator + "PdfFiles" + File.separator + fileName);
            sdPath.delete();
        }
    }
}
