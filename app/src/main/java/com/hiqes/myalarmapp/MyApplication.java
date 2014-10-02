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

import android.app.Application;
import android.content.Context;
import android.os.PowerManager;

public class MyApplication extends Application {
    private static final String         TAG = MyApplication.class.getName();
    private static final String         WAKELOCK_NAME = "MyAlarmApp_WL";

    protected PowerManager.WakeLock     mWake;

    public void onCreate() {
        PowerManager pmgr = (PowerManager)getSystemService(Context.POWER_SERVICE);
        mWake = pmgr.newWakeLock((PowerManager.FULL_WAKE_LOCK |
                                  PowerManager.ACQUIRE_CAUSES_WAKEUP |
                                  PowerManager.ON_AFTER_RELEASE),
                                 WAKELOCK_NAME);
    }
}
