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

package com.tomovwgti.megaadk;

import com.tomovwgti.android.accessory.io.OutputData;

public class Led extends OutputData {
    private static final String TAG = Led.class.getSimpleName();

    // ADK device command
    private static final byte LED_COMMAND = 3;

    public byte light = 0;

    @Override
    public void sendData() {
        sendCommand(LED_COMMAND, (byte) 0, light);
    }
}
