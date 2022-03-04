package com.mountreachsolution.hospitalmanagementsystemblooddonorapp.MyQRCode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.R;

public class MyQRCodeActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String username;
    ImageView img_qr_code;

    Bitmap bitmap;
    public final static int QRWidth = 500;
    public final static int QRHeight = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_q_r_code);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        setTitle("My QR Code");

        img_qr_code = findViewById(R.id.img_my_qr_code);
        username = preferences.getString("username", "");
        Toast.makeText(this, "" + username, Toast.LENGTH_SHORT).show();

        try {
            bitmap = textToImageEncode(username);
            img_qr_code.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }


    }

    Bitmap textToImageEncode(String value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(value, BarcodeFormat.QR_CODE, QRWidth, QRHeight, null);
        } catch (IllegalArgumentException illegalArgumentException) {
            return null;
        }

        int bitMatrixWidth = bitMatrix.getWidth();
        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixHeight;

            for (int x = 0; x < bitMatrixWidth; x++) {
                pixels[offset + x] = bitMatrix.get(x, y) ? getResources().getColor(R.color.black) :
                        getResources().getColor(R.color.white);
            }
        }

            Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);
            bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
            return bitmap;
    }
}