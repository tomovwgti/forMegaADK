
package com.tomovwgti.android.accessory.io;

import android.os.Handler;
import android.os.Message;

import com.tomovwgti.android.accessory.AccessoryListener;

public abstract class ADKCommandAbstractReciever implements AccessoryListener {

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            InputDataListener input = (InputDataListener) msg.obj;
            input.handleMassage();
        }
    };
}
