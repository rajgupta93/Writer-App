package com.writer.assignment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class pdf_adapter extends ArrayAdapter<pdf> {
    ArrayList<pdf> items ;
    Activity context;
    public pdf_adapter(@NonNull Context context, @NonNull ArrayList<pdf> objects) {
        super(context, 0, objects);
        items=objects;
        this.context= (Activity) context;


    }

    String pdfPath= Environment.getExternalStorageDirectory()+"/writernew/";


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentItemView = convertView;


        if (currentItemView == null)
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_view, parent, false);
        pdf currentNumberPosition = getItem(position);
        ImageView pdfimage = currentItemView.findViewById(R.id.image);
        pdfimage.setImageBitmap(currentNumberPosition.getFilename());
        TextView date = currentItemView.findViewById(R.id.date);
         date.setText( (currentNumberPosition.getDate()));

        TextView time = currentItemView.findViewById(R.id.time);
        time.setText(currentNumberPosition.getTime());

        Button edit = currentItemView.findViewById(R.id.edit);
        Button share = currentItemView.findViewById(R.id.share);
        Button delete =currentItemView.findViewById(R.id.delete);



        Animation animation = null;
        animation = new TranslateAnimation(360, 0, 0, 0);
        //animation = AnimationUtils.loadAnimation(activity, R.anim.your_animation);
        animation.setDuration(300);
        currentItemView.startAnimation(animation);
        animation = null;

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Change name to");

// Set up the input
                final EditText input = new EditText(getContext());
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        File file = new File(pdfPath+ currentNumberPosition.getTime());
                        file.renameTo(new File(pdfPath+input.getText().toString()+".pdf"));

                        currentNumberPosition.setTime(input.getText().toString()+".pdf");
                        notifyDataSetChanged();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 File outputFile = new File(pdfPath+ currentNumberPosition.getTime());
                 Uri pdfUri = FileProvider.getUriForFile(v.getContext(), v.getContext().getPackageName() + ".provider", outputFile);
                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
                share.setType("application/pdf");
                share.putExtra(Intent.EXTRA_STREAM, pdfUri);
//                v.getContext().startActivity(Intent.createChooser(share, "Share"));
                v.getContext().startActivity(share);

            }
        });


       delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               File file = new File(pdfPath+ currentNumberPosition.getTime());

               alertMessage(file,currentNumberPosition);


           }
       });

       pdfimage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               File file=new File(pdfPath+ currentNumberPosition.getTime());
               Uri uri = FileProvider.getUriForFile(getContext(), getContext().getPackageName() + ".provider", file);
              Intent intent = new Intent(Intent.ACTION_VIEW);
               intent.setData(uri);
               intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
               v.getContext().startActivity(intent);


           }
       });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file=new File(pdfPath+ currentNumberPosition.getTime());
                Uri uri = FileProvider.getUriForFile(getContext(), getContext().getPackageName() + ".provider", file);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                v.getContext().startActivity(intent);


            }
        });



        return currentItemView;


    }
    public void alertMessage(File file, pdf pos) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        // Yes button clicked
                        file.delete();
                        if(file.exists()) {
                            try {
                                file.getCanonicalFile().delete();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (file.exists()) {
                                getContext().deleteFile(file.getName());
                            }
                        }

                        Toast.makeText(getContext(), "Pdf deleted Successfully",
                                Toast.LENGTH_LONG).show();
                        items.remove(pos);
                        notifyDataSetChanged();

                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        // No button clicked
                        // do nothing
//                        Toast.makeText(getContext(), "No Clicked",
//                                Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Are you sure to delete?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }



}
