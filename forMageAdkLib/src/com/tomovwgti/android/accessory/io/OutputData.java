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

public abstract class OutputData {
    static final String TAG = OutputData.class.getSimpleName();

    private ADKCommandSender mSender = null;

    public OutputData() {
        mSender = ADKCommandSender.getSender();
    }

    protected void sendCommand(byte command, byte target, int value) {
        mSender.sendCommand(command, target, value);
    }

    public abstract void sendData();
}
