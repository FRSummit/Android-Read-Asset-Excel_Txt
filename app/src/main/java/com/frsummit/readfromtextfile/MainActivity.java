package com.frsummit.readfromtextfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class MainActivity extends AppCompatActivity {
    TextView textView, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);
        textView2 = findViewById(R.id.text2);
        readFromTxtFile();
        readExcel();
    }

    public void readExcel() {
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("name.xls");
            Workbook workbook = Workbook.getWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(0);
            int rows = sheet.getRows();
            int columns = sheet.getColumns();
            String text = "";
            for(int r=0; r< rows; r++) {
                for(int col=-0; col< columns; col++) {
                    Cell cell = sheet.getCell(col, r);
                    text = text + cell.getContents();
                    text = text + " ";
                }
                text = text + "\n";
            }
            textView2.setText(text);
        } catch(Exception e) {
            Toast.makeText(this, "Exception : " + e, Toast.LENGTH_LONG).show();
        }
    }

    public void readFromTxtFile() {
        String text = "";
//        Typeface tf = Typeface.createFromAsset(getAssets(), "font/vrinda.ttf");
        try {
            InputStream in = getAssets().open("bangla.txt");
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            text = new String(buffer);
        }catch (IOException e) {
            e.printStackTrace();
        }
//        textView.setTypeface(tf);
        textView.setText(text);
        System.out.println(text);
    }
}
