package com.example.mit397.getinstallapps;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tx=(TextView)findViewById(R.id.txt);
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<PackageInfo> packList = getPackageManager().getInstalledPackages(0);
                for (int i=0; i < packList.size(); i++)
                {
                    PackageInfo packInfo = packList.get(i);
                    if (  (packInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
                    {
                        String appName = packInfo.applicationInfo.loadLabel(getPackageManager()).toString();
                        Log.e("App № " + Integer.toString(i), appName+" Package name : "+packInfo.packageName);
                    }
                    if (packInfo.packageName.equals("com.facebook.katana")){
                        Intent launchApp = getPackageManager().getLaunchIntentForPackage(packInfo.packageName);
                        startActivity(launchApp);
                    }

                }
            }
        });
    }
}
