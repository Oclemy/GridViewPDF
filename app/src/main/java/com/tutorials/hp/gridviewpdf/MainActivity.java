package com.tutorials.hp.gridviewpdf;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final GridView gv= (GridView) findViewById(R.id.gv);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gv.setAdapter(new CustomAdapter(MainActivity.this,getPDFs()));

            }
        });
    }

    private ArrayList<PDFDoc> getPDFs()

    {
        ArrayList<PDFDoc> pdfDocs=new ArrayList<>();
        //TARGET FOLDER
        File downloadsFolder= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        PDFDoc pdfDoc;

        if(downloadsFolder.exists())
        {
            //GET ALL FILES IN DOWNLOAD FOLDER
            File[] files=downloadsFolder.listFiles();

            //LOOP THRU THOSE FILES GETTING NAME AND URI
            for (int i=0;i<files.length;i++)
            {
                File file=files[i];

                if(file.getPath().endsWith("pdf"))
                {
                    pdfDoc=new PDFDoc();
                    pdfDoc.setName(file.getName());
                    pdfDoc.setPath(file.getAbsolutePath());

                    pdfDocs.add(pdfDoc);
                }

            }
        }

        return pdfDocs;
    }


}
