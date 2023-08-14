package com.example.lab7permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSIONS_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRequestPermission = findViewById(R.id.btnRequest);
        Button btnOpenSettingPermission = findViewById(R.id.btnSettingPermission);
        ImageButton btnChrome = findViewById(R.id.btnChrome),
                btnPhone = findViewById(R.id.btnPhone),
                btnMessage = findViewById(R.id.btnMessage);

        btnRequestPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickRequestPermission();
            }
        });

        btnOpenSettingPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickOpenSettingPermission();
            }
        });

        btnChrome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickChrome();
            }
        });

        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickPhone();
            }
        });

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMessage();
            }
        });
    }

    private void ClickRequestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            //CODE.M tuong duong Android 6. Neu version cua may hien tai < Android 6 -> return
            return;
        }

        //version hien hanh >=6
        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            String permissions = (Manifest.permission.ACCESS_FINE_LOCATION);
            requestPermissions(new String[]{permissions}, REQUEST_PERMISSIONS_CODE);
        }
    }

    private void ClickOpenSettingPermission() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

    private void ClickChrome() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
        startActivity(browserIntent);
    }

    private void ClickPhone() {
        String number = "888888888";
        Uri call = Uri.parse("tel:" + number);
        Intent surf = new Intent(Intent.ACTION_DIAL, call);
        startActivity(surf);
    }

    private void ClickMessage() {
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", "12125551212");
        smsIntent.putExtra("sms_body", "Body of Message");
        startActivity(smsIntent);
    }

    //bat ket qua
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}