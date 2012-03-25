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

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

import com.tomovwgti.android.accessory.AccessoryBaseActivity;

public class MegaAdkLedActivity extends AccessoryBaseActivity {

    @Override
    protected void showControls() {
        setContentView(R.layout.main);

        final Led led = new Led();

        // Initialize
        led.light = 0;
        led.sendData();

        ToggleButton button = (ToggleButton) findViewById(R.id.led_button);
        button.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    led.light = 1;
                } else {
                    led.light = 0;
                }
                led.sendData();
            }
        });
    }
}
