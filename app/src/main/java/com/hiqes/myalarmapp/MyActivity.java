/*
 * Copyright (C) 2014 HIQES LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hiqes.myalarmapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class MyActivity extends Activity implements View.OnClickListener {
    private static final String         TAG = MyActivity.class.getName();

    protected static String             EXTRA_FROM_RXR = "extra_from_rxr";

    private Button              mBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Log.d(TAG, "onCreate done!");

        TextView tv = (TextView)findViewById(R.id.status);
        mBtn = (Button)findViewById(R.id.btn_ack);
        mBtn.setOnClickListener(this);
        mBtn.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        boolean fromRxr = intent.getBooleanExtra(EXTRA_FROM_RXR, false);
        if (fromRxr) {
            tv.setText(R.string.i_am_awake);

            //  Force the screen on while we are awake
            Window win = getWindow();
            win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                         WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                         WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

            MyApplication app = (MyApplication)getApplication();
            if (!app.mWake.isHeld()) {
                Log.w(TAG, "Received wake from RXR but lock is not held!");
            } else {
                mBtn.setVisibility(View.VISIBLE);

                //  Force the screen to come back on by telling the power
                //  manager that user activity has occurred.
                PowerManager pmgr = (PowerManager)getSystemService(Context.POWER_SERVICE);
                pmgr.userActivity(SystemClock.uptimeMillis(), false);
            }
        } else {
            tv.setText(R.string.manual_start);
        }
    }


    @Override
    public void onClick(View v) {
        Log.d(TAG, "User ack'd the wake, release the lock");
        MyApplication app = (MyApplication)getApplication();
        app.mWake.release();
        finish();
    }
}
