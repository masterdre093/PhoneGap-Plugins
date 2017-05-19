package com.infologs.EchoJS;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONException;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.provider.Contacts.SettingsColumns;
import android.util.Log;
import android.widget.Toast;

public class EchoJS extends CordovaPlugin{
    public void PrintData(){
        cordova.getActivity().runOnUiThread(new Runnable() {
            
            @Override
            public void run(){
                
                Toast myMessage = Toast.makeText(cordova.getActivity().getWindow().getContext(), "This message is from cordova plugin.", Toast.LENGTH_LONG);
                myMessage.show();
            }
        });
    }
    
    @Override
    public boolean execute(String action, final CordovaArgs args, final CallbackContext callbackContext)
        throws JSONException{
            if(action.equals("printData")){
                PrintData();
                callbackContext.success("okay");
                return true;
            }
            
            return false;
        }
}