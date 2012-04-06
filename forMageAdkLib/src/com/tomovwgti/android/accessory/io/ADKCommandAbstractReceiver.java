/*
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

package com.tomovwgti.android.accessory.io;

import android.os.Handler;
import android.os.Message;

import com.tomovwgti.android.accessory.AccessoryListener;
import com.tomovwgti.android.accessory.UsbAccessoryAbstractActivity;

public abstract class ADKCommandAbstractReceiver implements AccessoryListener {
    static final String TAG = UsbAccessoryAbstractActivity.class.getSimpleName();

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            InputDataListener input = (InputDataListener) msg.obj;
            input.handleMassage();
        }
    };
}
