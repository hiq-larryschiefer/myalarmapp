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

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

public class MyBootReceiver extends BroadcastReceiver {
    private static final String         TAG = MyBootReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Setting PendingIntent for our RXR...");

        Intent alarmIntent = new Intent(context, MyWakeReceiver.class);
        PendingIntent pend =
            PendingIntent.getBroadcast(context,
                                       0,
                                       alarmIntent,
                                       PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        long wakeTime = SystemClock.elapsedRealtime() + 30000;
        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, wakeTime, pend);

    }
}
