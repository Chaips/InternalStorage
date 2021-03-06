package com.example.memory;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StatFs;
import android.os.Environment;
import android.text.format.Formatter;
import android.widget.Toast;

import java.io.File;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    DecimalFormat formato = new DecimalFormat("000");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSizeStorage();
    }

    public void getSizeStorage(){
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        double sizeapp = 8.5;
        String[] units = new String[]{"B", "KB", "MB", "GB"};
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        long bytesAvailable = statFs.getFreeBytes();
        //double MegasAvailable = ((bytesAvailable/1024.0)/1024.0);
        double bytes = (int)Math.log10(bytesAvailable) / Math.log10(1024);
        double result = bytesAvailable / Math.pow(1024,bytes);
        //double GigasAvailable = (((bytesAvailable/1024.0)/1024.0)/1024.0);
        if(bytesAvailable <= 0){
            Toast.makeText(this, "No cuenta con espacio en la memoria interna de su dispositivo" +
                    "0", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Memoria: " + decimalFormat.format(result) + " + " + bytesAvailable/*+
                    " + " + units[(int) result]*/, Toast.LENGTH_LONG).show();
        }
        /*if(MegasAvailable < 1){
            size = formato.format(MegasAvailable).concat("MB");
            Toast.makeText(this,"Memoria disponible: "+ size,Toast.LENGTH_LONG).show();
        }else if(MegasAvailable > 1){
            size = decimalFormat.format(GigasAvailable).concat("GB");
            Toast.makeText(this, "Memoria disponible: " + size
                    , Toast.LENGTH_LONG).show();
        }*/
        if(result < sizeapp){
            Mensaje();
            Toast.makeText(this,"Memoria insuficiente", Toast.LENGTH_LONG).show();
        }
    }

    public void Mensaje(){
        AlertDialog.Builder memoria = new AlertDialog.Builder(this);
        memoria.setTitle("Memoria Insuficiente");
        memoria.setMessage("porfavor libere espacio en su dispositivo");
        memoria.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(MainActivity.this,MainActivity.class);
                finish();
            }
        });
        memoria.show();
    }
}

