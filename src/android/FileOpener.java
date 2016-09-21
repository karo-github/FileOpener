/*
 * PhoneGap is available under *either* the terms of the modified BSD license *or* the
 * MIT License (2008). See http://opensource.org/licenses/alphabetical for full text.
 *
 * Copyright (c) 2005-2010, Nitobi Software Inc.
 * Copyright (c) 2011, IBM Corporation
 */

package com.phonegap.plugins.fileopener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;


public class FileOpener extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args,
                    CallbackContext callbackContext) throws JSONException {

                PluginResult.Status status = PluginResult.Status.OK;
            String result = "";

            try {
                if (action.equals("openFile")) {
                    openFile(args.getString(0), args.getString(1));
                }
                else {
                    status = PluginResult.Status.INVALID_ACTION;
                }

                return true;
            } catch (JSONException e) {
                return false;
            } catch (IOException e) {
                return false;
            }

    }



    private void openFile(String url, String type) throws IOException {
        // Create URI
        Uri uri = Uri.parse(url);

        Intent intent = null;
        // Check what kind of file you are trying to open, by comparing the url with extensions.
        // When the if condition is matched, plugin sets the correct intent (mime) type,
        // so Android knew what application to use to open the file
        intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, type);

        //if you want you can also define the intent type for any other file

        //additionally use else clause below, to manage other unknown extensions
        //in this case, Android will show all applications installed on the device
        //so you can choose which application to use

        this.cordova.getActivity().startActivity(intent);
    }

}

