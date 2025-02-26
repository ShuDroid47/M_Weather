package com.example.m_weather.helpers;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.example.m_weather.MainViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ShareUtils {

    public static void takeScreenshot(View view, String fileName) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(bitmap));

        File screenshotFile = getScreenshotFile(fileName);
        saveScreenshot(bitmap, screenshotFile);
        scanScreenshotFile(view.getContext(), screenshotFile);
    }

    private static File getScreenshotFile(String fileName) {
        File screenshotsDir = new File("Screenshots");
        if (!screenshotsDir.exists()) {
            screenshotsDir.mkdirs();
        }
        return new File(screenshotsDir, fileName + ".png");
    }

    private static void saveScreenshot(Bitmap bitmap, File file) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void scanScreenshotFile(Context context, File file) {
        MediaScannerConnection.scanFile(context, new String[]{file.getAbsolutePath()}, null, null);
    }

    public static void store(Context cx,Bitmap bm, String fileName){
        final String dirPath = cx.getCacheDir()+"images";
        File dir = new File(cx.getCacheDir(),"images");
        if(!dir.exists())
            dir.mkdirs();
        File file = new File(dirPath, fileName);
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG, 85, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void shareImage(Context cx,File file){
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");

        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        try {
            cx.startActivity(Intent.createChooser(intent, "Share Screenshot"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(cx, "No App Available", Toast.LENGTH_SHORT).show();
        }
    }
}
