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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyWakeReceiver extends BroadcastReceiver {
    private static final String         TAG = MyWakeReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {

        //  Hold a wakelock so our activity has time to get started
        MyApplication app = (MyApplication)context.getApplicationContext();
        app.mWake.acquire();

        Log.d(TAG, "Starting activity");
        Intent actIntent = new Intent(context, MyActivity.class);
        actIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        actIntent.putExtra(MyActivity.EXTRA_FROM_RXR, true);
        context.startActivity(actIntent);
    }
}
