/*
 * Copyright (C) 2011 PIGMAL LLC
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

package com.tomovwgti.android.accessory;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.ParcelFileDescriptor;
import android.util.Log;

import com.android.future.usb.UsbAccessory;
import com.android.future.usb.UsbManager;

/**
 * The class that provide basic functionality communicate Open Accessory devices
 * such as open, close, read(listen), write
 * 
 * @author itog
 */
public class Accessory implements Runnable {
    static final String TAG = Accessory.class.getSimpleName();

    private FileInputStream mInputStream;
    private FileOutputStream mOutputStream;
    private ParcelFileDescriptor mFileDescriptor;
    private UsbManager mUsbManager;
    private AccessoryListener mListener;
    private boolean mThreadRunning;

    public Accessory(UsbManager usbManager) {
        mUsbManager = usbManager;
    }

    /**
     * open Open Accessory Device
     * 
     * @param accessory UsbAccessory
     * @return
     */
    boolean open(UsbAccessory accessory) {
        boolean ret = false;
        mFileDescriptor = mUsbManager.openAccessory(accessory);
        if (mFileDescriptor != null) {
            // mAccessory = accessory;
            FileDescriptor fd = mFileDescriptor.getFileDescriptor();
            mInputStream = new FileInputStream(fd);
            mOutputStream = new FileOutputStream(fd);
            Thread thread = new Thread(null, this, "DemoKit");
            thread.start();
            Log.d(TAG, "accessory opened");
            // enableControls(true);
            ret = true;
        } else {
            Log.d(TAG, "accessory open fail");
        }
        return ret;
    }

    /**
     * close Open Accessory Device
     */
    void close() {
        Log.d(TAG, "accessory close");
        try {
            if (mFileDescriptor != null) {
                mFileDescriptor.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            mFileDescriptor = null;
            mOutputStream = null;
            mThreadRunning = false;
        }
    }

    public boolean isConnected() {
        return mInputStream != null && mOutputStream != null;
    }

    /**
     * set a listener to listen from ADK
     * 
     * @param l
     */
    public void setListener(AccessoryListener l) {
        mListener = l;
    }

    public void removeListener() {
        mThreadRunning = false;
    }

    /**
     * thread to receive messages from ADK
     */
    public void run() {
        int length = 0;
        byte[] buffer = new byte[16384];
        byte[] pass;

        mThreadRunning = true;
        while (length >= 0 && mThreadRunning) {
            try {
                length = mInputStream.read(buffer);
            } catch (IOException e) {
                break;
            }

            pass = new byte[length];
            System.arraycopy(buffer, 0, pass, 0, length);
            if (mListener != null) {
                mListener.onAccessoryMessage(pass);
            }
        }
        mListener = null;
        mInputStream = null;
        mThreadRunning = false;
    }

    public void write(byte... data) {
        if (mOutputStream != null) {
            try {
                Log.i(TAG, "data write");
                mOutputStream.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
