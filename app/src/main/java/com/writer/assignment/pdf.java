package com.writer.assignment;

import android.graphics.Bitmap;

public class pdf {

    Bitmap filename;
    String date;
    String time;

    pdf(Bitmap filename, String date, String time)
    {
        this.filename=filename;
        this.date=date;
        this.time=time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFilename(Bitmap filename) {
        this.filename = filename;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Bitmap getFilename()
    {
        return filename;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
