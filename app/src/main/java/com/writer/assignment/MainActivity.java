 package com.writer.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import android.graphics.fonts.Font;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;


import android.graphics.Bitmap;

import android.graphics.drawable.BitmapDrawable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.io.util.StreamUtil;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;


import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.AreaBreakType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;

import static com.itextpdf.kernel.pdf.PdfName.BaseFont;


 public class MainActivity extends AppCompatActivity {
     private static final int readStorage = 101;
     private static final int writeStorage = 102;
     Button btn ;
     EditText text;
     String s;
     TextView nopdf;
     SeekBar fontsize;
     int progressvalue;
     float letterspacingvalue;
     LinearLayout constrain;
     Button addnewpage;
     int index=0;
     TextSwitcher mtextswitcher;
     View viewRow;
     EditText editText;
     SeekBar mletterspacing;

     FloatingActionButton mblackcolor;
     FloatingActionButton mbluecolor;
     FloatingActionButton mgreencolor;
     FloatingActionButton mdarkbluecolor;
     FloatingActionButton mredcolor;
     FloatingActionButton mgreycolor;

     Button mhandwritting1;
     Button mhandwritting2;
     Button mhandwritting3;
     Button mhandwritting4;
     Button mhandwritting5;
     Button mhandwritting6;
     Button mhandwritting7;
     Button mhandwritting8;
     Button mhandwritting9;
     Button mhandwritting10;
     Button mhandwritting11;








     private List<View> rowViewList = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.display);

        btn = findViewById(R.id.createPdf);
        fontsize=findViewById(R.id.fontsize);





        Button navpagebtn = findViewById(R.id.bottomnavigationimg1);
        LinearLayout navpagelayout = findViewById(R.id.linearlayoutbackground);
        Button navalignbtn = findViewById(R.id.bottomnavigationimg2);
        LinearLayout alignlayout =findViewById(R.id.linearlayout2);

        Button navfontbtn = findViewById(R.id.bottomnavigationimg3);

        LinearLayout fontlayout = findViewById(R.id.linearlayout3);

        navpagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(alignlayout.getVisibility()==View.VISIBLE) {
                    alignlayout.setVisibility(View.GONE);
                    navalignbtn.setBackgroundResource(R.drawable.ic_baseline_format_align_center_nothover);
                }
                if(fontlayout.getVisibility()==View.VISIBLE)
                    fontlayout.setVisibility(View.GONE);

                if(navpagelayout.getVisibility()==View.GONE) {
                    navpagelayout.setVisibility(View.VISIBLE);
                    navpagebtn.setBackgroundResource(R.drawable.ic_baseline_insert_drive_file_hover);
                }
                else if(navpagelayout.getVisibility()==View.VISIBLE)
                    navpagelayout.setVisibility(View.GONE);
                navpagebtn.setBackgroundResource(R.drawable.ic_baseline_insert_drive_file_24);

            }
        });

        navalignbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {

                if(navpagelayout.getVisibility()==View.VISIBLE)
                    navpagelayout.setVisibility(View.GONE);
                if(fontlayout.getVisibility()==View.VISIBLE) {
                    fontlayout.setVisibility(View.GONE);


                }

                if(alignlayout.getVisibility()==View.GONE) {
                    alignlayout.setVisibility(View.VISIBLE);
                    navalignbtn.setBackgroundResource(R.drawable.ic_baseline_format_align_center_hover);


                }
                else if(alignlayout.getVisibility()==View.VISIBLE) {
                    alignlayout.setVisibility(View.GONE);
                    navalignbtn.setBackgroundResource(R.drawable.ic_baseline_format_align_center_nothover);
                }
            }
        });

        navfontbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(alignlayout.getVisibility()==View.VISIBLE)
                    alignlayout.setVisibility(View.GONE);
                if(navpagelayout.getVisibility()==View.VISIBLE)
                    navpagelayout.setVisibility(View.GONE);

                if(fontlayout.getVisibility()==View.GONE) {
                    fontlayout.setVisibility(View.VISIBLE);
//                    navpagebtn.setBackgroundResource(R.drawable.ic_baseline_share_24);
                }
                else if(fontlayout.getVisibility()==View.VISIBLE)
                    fontlayout.setVisibility(View.GONE);
            }
        });
        constrain=findViewById(R.id.constrain1);
        addnewpage = findViewById(R.id.addnewpage);


        addnewpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView=inflater.inflate(R.layout.pageview, null);
                EditText myEditText = rowView.findViewById(R.id.write);
                constrain.addView(rowView, constrain.getChildCount());
                rowViewList.add(rowView);
                Log.e("edit ",""+ myEditText.getText()+"hello");





            }
        });


        fontsize.setMax(44);
        fontsize.setProgress(18);

        fontsize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(progress<7)
                {
                    progressvalue =7;
                }else
                    progressvalue =progress;

                for (int i = 0; i<rowViewList.size(); i++){
                    viewRow = rowViewList.get(i);
                    editText = viewRow.findViewById(R.id.write);
                editText.setTextSize(progressvalue);}

            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mletterspacing=findViewById(R.id.letterspacing);

        mletterspacing.setMax(17);
        mletterspacing.setProgress(5);

        mletterspacing.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(progress<1)
                {
                    letterspacingvalue = (float) -0.5;
                }else
                    letterspacingvalue =(float) (((progress-5)/10));

                for (int i = 0; i<rowViewList.size(); i++){
                    viewRow = rowViewList.get(i);
                    editText = viewRow.findViewById(R.id.write);
                editText.setLetterSpacing(letterspacingvalue);
                Log.e("character spacing", " "+ letterspacingvalue);}

            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//
//        LinearLayout parent = new LinearLayout(this);
//
//        parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//        parent.setOrientation(LinearLayout.HORIZONTAL);
//        ImageView iv = new ImageView(this);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            iv.setImageDrawable(getDrawable(R.drawable.ruled1));
//        }
//        constrain = findViewById(R.id.constrain1);
//        if(text.getParent() != null) {
//            ((ViewGroup)text.getParent()).removeView(text); // <- fix
//        }
//
//
//        constrain.addView(text);


        mhandwritting1=findViewById(R.id.handwritting1);
        mhandwritting2=findViewById(R.id.handwritting2);
        mhandwritting3=findViewById(R.id.handwritting3);
        mhandwritting4=findViewById(R.id.handwritting4);
        mhandwritting5=findViewById(R.id.handwritting5);
        mhandwritting6=findViewById(R.id.handwritting6);
        mhandwritting7=findViewById(R.id.handwritting7);
        mhandwritting8=findViewById(R.id.handwritting8);
        mhandwritting9=findViewById(R.id.handwritting9);
//        mhandwritting10=findViewById(R.id.handwritting10);
//        mhandwritting11=findViewById(R.id.handwritting11);

        mhandwritting1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final Typeface typeface= ResourcesCompat.getFont(getApplicationContext(), R.font.aedigarr1);

                for (int i = 0; i<rowViewList.size(); i++){
                    viewRow = rowViewList.get(i);
                    editText = viewRow.findViewById(R.id.write);
//                    editText.setTypeface(typeface);
                    Log.e("font ", " "+ "font changed");}

                Log.e("Font","font changed");
            }
        });
        mhandwritting2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Typeface typeface= ResourcesCompat.getFont(getApplicationContext(), R.font.alex1);

                for (int i = 0; i<rowViewList.size(); i++){
                    viewRow = rowViewList.get(i);
                    editText = viewRow.findViewById(R.id.write);
                    editText.setTypeface(typeface);
                    Log.e("font ", " "+ "font changed");}

                Log.e("Font","font changed");
            }
        });
        mhandwritting3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Typeface typeface= ResourcesCompat.getFont(getApplicationContext(), R.font.delta1);

                for (int i = 0; i<rowViewList.size(); i++){
                    viewRow = rowViewList.get(i);
                    editText = viewRow.findViewById(R.id.write);
                    editText.setTypeface(typeface);
                    Log.e("font ", " "+ "font changed");}

                Log.e("Font","font changed");
            }
        });
        mhandwritting4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Typeface typeface= ResourcesCompat.getFont(getApplicationContext(), R.font.dorinius1);

                for (int i = 0; i<rowViewList.size(); i++){
                    viewRow = rowViewList.get(i);
                    editText = viewRow.findViewById(R.id.write);
                    editText.setTypeface(typeface);
                    Log.e("font ", " "+ "font changed");}

                Log.e("Font","font changed");
            }
        });
        mhandwritting5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Typeface typeface= ResourcesCompat.getFont(getApplicationContext(), R.font.estoutic1);

                for (int i = 0; i<rowViewList.size(); i++){
                    viewRow = rowViewList.get(i);
                    editText = viewRow.findViewById(R.id.write);
                    editText.setTypeface(typeface);
                    Log.e("font ", " "+ "font changed");}

                Log.e("Font","font changed");
            }
        });
        mhandwritting6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Typeface typeface= ResourcesCompat.getFont(getApplicationContext(), R.font.forichok1);

                for (int i = 0; i<rowViewList.size(); i++){
                    viewRow = rowViewList.get(i);
                    editText = viewRow.findViewById(R.id.write);
                    editText.setTypeface(typeface);
                    Log.e("font ", " "+ "font changed");}

                Log.e("Font","font changed");
            }
        });
        mhandwritting7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Typeface typeface= ResourcesCompat.getFont(getApplicationContext(), R.font.hindi1);

                for (int i = 0; i<rowViewList.size(); i++){
                    viewRow = rowViewList.get(i);
                    editText = viewRow.findViewById(R.id.write);
                    editText.setTypeface(typeface);
                    Log.e("font ", " "+ "font changed");}

                Log.e("Font","font changed");
            }
        });
        mhandwritting8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Typeface typeface= ResourcesCompat.getFont(getApplicationContext(), R.font.laconical1);

                for (int i = 0; i<rowViewList.size(); i++){
                    viewRow = rowViewList.get(i);
                    editText = viewRow.findViewById(R.id.write);
                    editText.setTypeface(typeface);
                    Log.e("font ", " "+ "font changed");}

                Log.e("Font","font changed");
            }
        });
        mhandwritting9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Typeface typeface= ResourcesCompat.getFont(getApplicationContext(), R.font.steship1);

                for (int i = 0; i<rowViewList.size(); i++){
                    viewRow = rowViewList.get(i);
                    editText = viewRow.findViewById(R.id.write);
                    editText.setTypeface(typeface);
                    Log.e("font ", " "+ "font changed");}

                Log.e("Font","font changed");
            }
        });
//        mhandwritting10.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Typeface typeface= ResourcesCompat.getFont(getApplicationContext(), R.font.woipot1);
//
//                for (int i = 0; i<rowViewList.size(); i++){
//                    viewRow = rowViewList.get(i);
//                    editText = viewRow.findViewById(R.id.write);
//                    editText.setTypeface(typeface);
//                    Log.e("font ", " "+ "font changed");}
//
//                Log.e("Font","font changed");
//            }
//        });

        mblackcolor=findViewById(R.id.black);
        mredcolor= findViewById(R.id.red);
        mdarkbluecolor=findViewById(R.id.dark);
        mbluecolor=findViewById(R.id.blue);
        mgreencolor=findViewById(R.id.green);
        mgreycolor=findViewById(R.id.grey);

       mblackcolor.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               for (int i = 0; i<rowViewList.size(); i++){
                   viewRow = rowViewList.get(i);
                   editText = viewRow.findViewById(R.id.write);
                   editText.setTextColor(getColorStateList(R.color.black));
                   Log.e("font ", " "+ "font changed");}

               Log.e("Font","font changed");
           }
       });

        mredcolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i<rowViewList.size(); i++){
                    viewRow = rowViewList.get(i);
                    editText = viewRow.findViewById(R.id.write);
                    editText.setTextColor(getColorStateList(R.color.Red));
                    Log.e("font ", " "+ "font changed");}

                Log.e("Font","font changed");
            }
        });

        mbluecolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i<rowViewList.size(); i++){
                    viewRow = rowViewList.get(i);
                    editText = viewRow.findViewById(R.id.write);
                    editText.setTextColor(getColorStateList(R.color.darkskyblue));
                    Log.e("font ", " "+ "font changed");}

                Log.e("Font","font changed");
            }
        });
        mgreycolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i<rowViewList.size(); i++){
                    viewRow = rowViewList.get(i);
                    editText = viewRow.findViewById(R.id.write);
                    editText.setTextColor(getColorStateList(R.color.Gray));
                    Log.e("font ", " "+ "font changed");}

                Log.e("Font","font changed");
            }
        });

        mgreencolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i<rowViewList.size(); i++){
                    viewRow = rowViewList.get(i);
                    editText = viewRow.findViewById(R.id.write);
                    editText.setTextColor(getColorStateList(R.color.green));
                    Log.e("font ", " "+ "font changed");}

                Log.e("Font","font changed");
            }
        });

        mbluecolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i<rowViewList.size(); i++){
                    viewRow = rowViewList.get(i);
                    editText = viewRow.findViewById(R.id.write);
                    editText.setTextColor(getColorStateList(R.color.Blue));
                    Log.e("font ", " "+ "font changed");}

                Log.e("Font","font changed");
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,readStorage);
                checkPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,writeStorage);
                try {

                    s=text.getText().toString();
                    Log.e("string " ," "+ s);

                    File folder = new File(Environment.getExternalStorageDirectory()+"/writernew/");
                    if (!folder.exists())
                    {
                        folder.mkdir();
                        Toast.makeText(getApplicationContext(), "Folder Created ",
                                Toast.LENGTH_LONG).show();
                    }
                     String pdfPath= Environment.getExternalStorageDirectory()+"/writernew/";
                    File file = new File(pdfPath,"hello.pdf");
                    Log.e("68","68");
//                    URL font_path = Thread.currentThread().getContextClassLoader().getResource("font/font1.ttf");

                URL font_path = Thread.currentThread().getContextClassLoader().getResource("font/font1.ttf");
//                    String in = String.valueOf(this.getClass().getClassLoader().getResourceAsStream("font/font1.ttf"));
//                    String pathToFontInAssets = "fonts/font1.ttf";
//                  InputStream inputStream = getAssets().open();
                    

//                    byte[] bytes;
//                    try (InputStream is = font_path.openStream()) {
//                    if(inputStream== null)
//                    {
//                        Log.e("TAG","in is null ");
//
//                    }
//                        bytes = StreamUtil.inputStreamToArray(inputStream);
//                    }
//                    PdfFont font = PdfFontFactory.createFont(bytes, PdfEncodings.WINANSI, true);

                    URL fontURL = MainActivity.class.getResource("/aedigarr1.otf");

                    Log.e("url", String.valueOf(fontURL));
                    Log.e("font path url", String.valueOf(font_path));

                    InputStream fontStream = fontURL.openStream();

//                    try (InputStream is = fontURL.openStream()) {
//                        byte[] bytes = StreamUtil.inputStreamToArray(is);
//                        PdfFont font = PdfFontFactory.createFont(bytes, PdfEncodings.WINANSI, true);
//                    }

//                    byte[] b = PdfEncodings.convertToBytes(String.valueOf(fontURL), PdfEncodings.WINANSI);
//                    FontProgram fontProgram = FontProgramFactory.createFont(b);
//
//                    PdfFont font = PdfFontFactory.createFont(b, PdfEncodings.WINANSI, true);
                    PdfWriter writer=new PdfWriter(file);
//                    BaseFont customfont = BaseFont.CreateFont(fontpath + "myspecial.ttf", BaseFont.CP1252, BaseFont.EMBEDDED);
//                    PdfFont fontnew= PdfFontFactory.createFont(String.valueOf(font_path),BaseFont.,true);

                    Log.e("71","71");
                    PdfDocument pdfDocument = new PdfDocument(writer);
                    PageSize pageSize = PageSize.A4;
                    Log.e("size",pageSize.toString());
                    Document document=new Document(pdfDocument ,pageSize);
//                    Font font = null;
//
//                    try{
//                        URL fontURL = MainActivity.class.getResource("/aedigarr1.otf");
//                        InputStream fontStream = fontURL.openStream();
//                        font = new Font(Font.createFont(Font.TRUETYPE_FONT, fontStream).getFamily(), Font.PLAIN, 12);
//                    }catch(IOException) {
//                        font = new Font("Arial", Font.PLAIN, fontSize);
//                        ief.printStackTrace();
//                    }
//                 document.setFont(font);
                    document.setFontSize(progressvalue).setMargins(0,0,0,0);
//try {
// document.setFont(path)
//   ;
//} catch (Exception e) {
//    e.printStackTrace();
//}


//                    BaseFont urName = BaseFont.createFont("assets/font/fontName.TTF", "UTF-8",BaseFont.EMBEDDED);
//                    Font urFontName = new Font(urName, 12);

                    Drawable d= null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        d = getDrawable(R.drawable.ruled1);
                    }
//                    URL font_path = Thread.currentThread().getContextClassLoader().getResource("font/alex1.otf");
//                    FontProgram fontProgram =
//                            FontProgramFactory.createFont(String.valueOf(font_path));
//                    PdfFont font1 = PdfFontFactory.createFont(
//                            fontProgram, PdfEncodings.WINANSI, true);




               
//
//                        for (int i = 1; i <= index; i++) {
//                            pdfDocument.addNewPage();
//                            View et2= constrain.getChildAt(constrain.getChildCount());
//                            EditText myEditText1 = et2.findViewById(R.id.write);
//                            Log.e("edit", " " + myEditText1.getText());
//                            s = String.valueOf(myEditText1.getText());
//                            Paragraph para = new Paragraph(s).setFontSize(16);
//                            document.add(para);
//                        }



                    String allEditText = "";
                    for (int i = 0; i<rowViewList.size(); i++){

                        viewRow = rowViewList.get(i);
                        editText = viewRow.findViewById(R.id.write);
                        allEditText =  editText.getText().toString();
                        Text txt = new Text(allEditText);
//                        txt.setFont(PdfFontFactory.createFont(font_path,));
                        Log.e("fontsize"," "+progressvalue);

                        document.add(new Paragraph(allEditText));
                        if(i!=rowViewList.size()-1)
                        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                        Bitmap bitmap=((BitmapDrawable)d).getBitmap();
                        ByteArrayOutputStream stream=new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                        byte[] bitmapData=stream.toByteArray();
                        ImageData imageData= ImageDataFactory.create(bitmapData);
                        Image image=new Image(imageData);
                        ImageEventHandler handler = new ImageEventHandler(image);
                        pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, handler);
                        Log.e("run","1"+i);

                    }


//                    Bitmap bitmap=((BitmapDrawable)d).getBitmap();
//                    ByteArrayOutputStream stream=new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
//                    byte[] bitmapData=stream.toByteArray();
//                    ImageData imageData= ImageDataFactory.create(bitmapData);
//                    Image image=new Image(imageData);
////                    String s= String.valueOf(myEditText1.getText());
//
//                    document.setLeftMargin(0);
//                    document.setRightMargin(0);
//                    document.setTopMargin(0);
//                    document.setBottomMargin(0);

//      document.add(new Paragraph("Berlin!");
//                    ImageEventHandler handler = new ImageEventHandler(image);
//                    pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, handler);

                    document.close();

                    Toast.makeText(getApplicationContext(),"Pdf Saved in Downloads",Toast.LENGTH_LONG).show();






                }  catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });






//
    }
//     Font getCalibriLightFont(int fontSize){
//         Font font = null;
//         try{
//             URL fontURL = MainActivity.class.getResource("/aedigarr1.otf");
//             InputStream fontStream = fontURL.openStream();
//             font = new Font(Font.createFont(Font.TRUETYPE_FONT, fontStream).getFamily(), Font.PLAIN, fontSize);
//             fontStream.close();
//         }catch(IOException | FontFormatException ief){
//             font = new Font("Arial", Font.PLAIN, fontSize);
//             ief.printStackTrace();
//         }
//         return font;
//     }


    public  void createpdf(String s,float leftmargin) throws IOException {
      File folder = new File(Environment.getExternalStorageDirectory()+"/writernew/");
        if (!folder.exists())
        {
            folder.mkdir();
            Toast.makeText(this, "Folder Created ",
                    Toast.LENGTH_LONG).show();
        }







        String pdfPath= Environment.getExternalStorageDirectory()+"/writernew/";
        File file = new File(pdfPath,"hello.pdf");
        Log.e("68","68");
        PdfFont font = PdfFontFactory.createFont(FontConstants.COURIER, PdfEncodings.WINANSI, false);
        PdfWriter writer=new PdfWriter(file);
        Log.e("71","71");
        PdfDocument pdfDocument = new PdfDocument(writer);
        PageSize pageSize = PageSize.A4;
        Log.e("size",pageSize.toString());
        Document document=new Document(pdfDocument ,pageSize);
        Drawable d= null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            d = getDrawable(R.drawable.ruled1);
        }


        Bitmap bitmap=((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] bitmapData=stream.toByteArray();
        ImageData imageData= ImageDataFactory.create(bitmapData);
        Image image=new Image(imageData);
        Paragraph para = new Paragraph(s).setFontSize(16);
      document.setLeftMargin(leftmargin);
        document.setRightMargin(0);
        document.setTopMargin(0);
        document.setBottomMargin(0);
      document.add(para);
//      document.add(new Paragraph("Berlin!");
        ImageEventHandler handler = new ImageEventHandler(image);
        pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, handler);
        pdfDocument.addNewPage();
        pdfDocument.addNewPage();
        pdfDocument.addNewPage();
        pdfDocument.addNewPage();
        pdfDocument.addNewPage();
        document.close();

        Toast.makeText(this,"Pdf Saved in Downloads",Toast.LENGTH_LONG).show();


    }

     private void checkPermissions(String permission, int requestCode) {
         if (ContextCompat.checkSelfPermission(MainActivity.this, permission)
                 == PackageManager.PERMISSION_DENIED) {
             // Requesting the permission
             ActivityCompat.requestPermissions(MainActivity.this,
                     new String[] { permission },
                     requestCode);
         }else {

         }
     }


     public class ImageEventHandler implements IEventHandler {
         protected Image img;

         public ImageEventHandler(Image img) {
             this.img =img;
         }
         @Override
         public void handleEvent(Event event) {
             PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
             PdfDocument pdfDoc = docEvent.getDocument();
             PdfPage page = docEvent.getPage();
             PdfCanvas aboveCanvas = new PdfCanvas(page.newContentStreamBefore(),
                     page.getResources(), pdfDoc);
             Rectangle area = page.getPageSize();
             new Canvas(aboveCanvas, pdfDoc, area)
                     .add(img);
         }
     }
 }

