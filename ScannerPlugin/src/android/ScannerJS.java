package com.andrelogs.ScannerJS;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONException;

import android.R;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.provider.Contacts.SettingsColumns;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;
import android.pt.scan.Scan;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.Menu;
import android.view.View;

public class ScannerJS extends CordovaPlugin{
    public String PrintData(){
        cordova.getActivity().runOnUiThread(new Runnable() {
            
            @Override
            public void run(){
                Toast myMessage = Toast.makeText(cordova.getActivity().getWindow().getContext(), "This message is from cordova plugin s canner pl.", Toast.LENGTH_LONG);
                myMessage.show();
            }
        });
        
        return "test";
    }
    
    boolean open_flg = false;
	
    Scan scan = null;
    
    EditText text = null;  
    
    public void open(View view)
	{
		
		if (open_flg) {
			
			Messagebox(this, "is opened");
			return;
		}	
		
		scan =  new Scan();  //Create Scan
		
		int ret= scan.open();   //open scan
		
        if (ret<0) {
        	
        	Messagebox(this, "open fail"); 
        	return;    
		}  
        else
        {
        	Messagebox(this, "open success");            
        }   
        
        open_flg = true;       
		
		
	}
	
	public String scan(View view)
	{
		
		if (!open_flg) {  
			
			Messagebox(this, "open scan first!!!");
			//return;    
		} 
		
		//text.setText("");      
		
		
		String string = scan.scan(3000);  //if 3 second not scan anything,stop scan and display "not scan any info!!!"           
		
		if (string==null)     
		{         
			//text.setText("not scan any info!!!"); 
			return "not scan any info!!!";
		}        
		else
		{
			//text.setText(string);  
			return string;
		}    
		
	}	 
	
	public void close(View view)
	{
		
		if (!open_flg) {  
			
			Messagebox(this, "is close");  
			return;  
		}			
		
		int ret =  scan.close();
		
        if (ret<0) {
        	
        	Messagebox(this, "close fail");
        	return; 
		}
        else
        {
        	Messagebox(this, "close success");     
        }
		 
		 scan = null; 	
		 
		 open_flg = false;  
	}		
	
	private void Messagebox(ScannerJS printJS, String info) {
		Toast myMessage = Toast.makeText(cordova.getActivity().getWindow().getContext(), info, Toast.LENGTH_LONG);
        myMessage.show();
	}

    @Override
    public boolean execute(String action, final CordovaArgs args, final CallbackContext callbackContext)
        throws JSONException{
            if(action.equals("printData")){
                String data = PrintData();
            	//printString("Hellow");
                callbackContext.success(data);
                return true;
            }
            
            if(action.equals("scannerOpen")){
            	open(null);
                callbackContext.success("okay");
                return true;
            }
            
            if(action.equals("scannerClose")){
            	close(null);
                callbackContext.success("okay");
                return true;
            }
            
            if(action.equals("scanData")){
            	String data = scan(null);
                callbackContext.success(data);
                return true;
            }
            
            return false;
        }
}

