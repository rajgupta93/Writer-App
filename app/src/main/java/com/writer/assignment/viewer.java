package com.writer.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class viewer extends AppCompatActivity   {

    FloatingActionButton btn;
    TextView nopdf ;
    ArrayList<pdf> pdfArrayList = new ArrayList<>();
    File folder;
    Button mprofile;

    ArrayList<pdf> pdfname = new ArrayList<>();
    String pdfPath= Environment.getExternalStorageDirectory()+"/writernew/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        folder = new File(Environment.getExternalStorageDirectory()+"/writernew/");
        if (!folder.exists())
        {
            folder.mkdir();
            Toast.makeText(this, "Folder Created ",
                    Toast.LENGTH_LONG).show();
        }
        setContentView(R.layout.activity_viewer);

      int size=  displayfiles(pdfPath);

        if(size==0)
        {

            TextView textView = findViewById(R.id.nopdf);
            textView.setText("No pdf file exist let create one");
        }



        pdf_adapter pdfAdapter = new pdf_adapter(this,pdfArrayList);
        ListView   listView = findViewById(R.id.listView);
        listView.setAdapter(pdfAdapter);

         btn = findViewById(R.id.mainactivitybtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        mprofile= findViewById(R.id.profile);

        mprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });




    }

//    private void addFile(File file0) {
//        File[] files = file0.listFiles();
//
//
//        for (File file : files) {
//            if (!file.isDirectory() && file.getName().endsWith(".pdf")) {
//                inFiles.add(file.getPath());
//                Log.v("adding", file.getName());
//
//                pdfname.add(new pdf(R.drawable.ruledpage,23,file.getName()));
//
//            } else if (file.isDirectory()) {
//                addFile(file);
//            }
////
//
//        }
//
//    }
//    public void populateListView() {
//        inFiles = new ArrayList<>();
//        files = folder.listFiles();
//        if (files == null)
//            Toast.makeText(this, "no pdf exists", Toast.LENGTH_LONG).show();
//        else {
//            addFile(folder);
//            Log.e("96 viewre","vieweer");
//
//
//        }




    int  displayfiles(String path)

    {
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();

        if(files==null||files.length==0)
        {
            return 0;


        }else {
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().endsWith(".pdf")) {
                    Log.d("Files", "FileName:" + files[i].getName());
                 Bitmap bt =    pdfToBitmap(new File(pdfPath + files[i].getName()));
                    Date lastModDate = new Date(files[i].lastModified());
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String formattedDateString = formatter.format(lastModDate);

                    pdfArrayList.add(new pdf(bt, formattedDateString, files[i].getName()));
                } else
                    Log.d("else", "else");
            }
        }


return  files.length;

    }
    private Bitmap pdfToBitmap(File pdfFile) {
        Bitmap bitmap = null;
        try {
            PdfRenderer renderer = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                renderer = new PdfRenderer(ParcelFileDescriptor.open(pdfFile, ParcelFileDescriptor.MODE_READ_ONLY));

                final int pageCount = renderer.getPageCount();
                if (pageCount > 0) {
                    PdfRenderer.Page page = renderer.openPage(0);
                    int width = (int) (page.getWidth());
                    int height = (int) (page.getHeight());
                    bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

                    page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
                    page.close();
                    renderer.close();
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    @Override
    public void onResume() {
        // fetch updated data

        super.onResume();

    }


}