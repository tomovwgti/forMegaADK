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

import com.tomovwgti.android.accessory.Accessory;

/**
 * Send command to MEGA ADK hardware
 */
public class ADKCommandSender {
    static final String TAG = ADKCommandSender.class.getSimpleName();

    private static ADKCommandSender sSender = null;
    private Accessory openAccessory;

    public ADKCommandSender(Accessory acc) {
        openAccessory = acc;
        sSender = this;
    }

    public static ADKCommandSender getSender() {
        return sSender;
    }

    public void sendData(OutputData out) {
        out.sendData();
    }

    /**
     * Send a command to ADK
     * 
     * @param command
     * @param target
     * @param value
     */
    public void sendCommand(byte command, byte target, int value) {
        if (target != -1) {
            openAccessory.write(command, target, (byte) value);
        }
    }

    /**
     * Send a command to ADK (Async)
     * 
     * @param command
     * @param target
     * @param value
     */
    public void sendCommandAsync(final byte command, final byte target, final int value) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                sendCommand(command, target, value);
            }
        }).start();
    }

}
