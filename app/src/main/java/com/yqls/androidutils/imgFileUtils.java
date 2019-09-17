package com.yqls.androidutils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class imgFileUtils {

    /**
     *
     * @param context
     * @param fileUrl 文件加名称
     * @param bmp 图片资源文件
     * @return
     */
    public static String imgToGallery(Context context, String fileUrl, Bitmap bmp) {
        String imgpath = Environment.getExternalStorageDirectory().toString() + "/"+fileUrl+"/";
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory().toString(), fileUrl);
        if (!appDir.exists()) {
            appDir.mkdir();
        }

        String fileName = ms2Date(System.currentTimeMillis()) + ".jpg" ;
        imgpath += fileName;
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + imgpath)));
        Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
        return imgpath;
    }

    public static String ms2Date(long _ms){
        Date date = new Date(_ms);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault());
        return format.format(date);
    }


}
