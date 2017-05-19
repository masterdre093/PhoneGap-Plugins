package com.infologs.EchoJS;

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
import android.widget.Toast;

import android.os.Bundle;
import android.pt.printer.Printer;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.Menu;
import android.view.View;

public class EchoJS extends CordovaPlugin{
    public void PrintData(){
        cordova.getActivity().runOnUiThread(new Runnable() {
            
            @Override
            public void run(){
            	andreTest();
                Toast myMessage = Toast.makeText(cordova.getActivity().getWindow().getContext(), "This message is from cordova plugin.", Toast.LENGTH_LONG);
                myMessage.show();
            }
        });
    }
    
    boolean open_flg = false;
	
   	Printer printer = null;
    
   
   	public void andreTest(){
        cordova.getActivity().runOnUiThread(new Runnable() {
            
            @Override
            public void run(){
            	open(null);
            	printString(null);
                Toast myMessage = Toast.makeText(cordova.getActivity().getWindow().getContext(), "Andre Test ", Toast.LENGTH_LONG);
                myMessage.show();
            }
        });
    }
   
	
	
	public void open(View view)
	{
		
		  
		if (open_flg) 
		{
			 Messagebox(this, "is opend") ;
			 return;  
		} 
		
		printer = new Printer(); 
	   
	   
	   int ret =  printer.open();
	   
	   if (ret == 0) {
		   
		   
		   Messagebox(this, "open success!!") ;
		   open_flg = true; 
		   
	    }
	   else {
		   
		   
		   Messagebox(this, "open fail!!") ;
		   open_flg = false; 		   
		
	  }	
		
		
		
	}
	
	 
	
	
	public void printString(View view)
	{
		
		if (!open_flg)
		{
			 Messagebox(this, "please open first") ;
			 return; 
		}			
		
		int no_paper_flg = printer.queState();
		
	    if (no_paper_flg == 1)
	    {
			 Messagebox(this, "no_paper") ;
			 return; 	    	
	    }
		
		
		
		int ret = printer.printString("Hell,wolrd!!!");
		
		
		 if (ret== 0) {  
			 
			 Messagebox(this, "success") ;
		 }
		 else
		 {
			  Messagebox(this, "fail") ;   
		 }		
		
		
	} 	
	
	public void printCode128(View view)
	{
		
		if (!open_flg)
		{
			 Messagebox(this, "please open first") ;
			 return; 
		}			
		
		int no_paper_flg = printer.queState();
		
	    if (no_paper_flg == 1)
	    {
			 Messagebox(this, "no_paper") ;
			 return; 	    	
	    }		
		
		
		
		int ret = printer.printCODE128("20160601");
		
		
		 if (ret== 0) {  
			 
			 Messagebox(this, "success") ;
		 }
		 else
		 {
			  Messagebox(this, "fail") ;   
		 }			
		
		
	} 		
	
	public void printQR(View view)
	{
		
		if (!open_flg)
		{
			 Messagebox(this, "please open first") ;
			 return; 
		}			
		
		int no_paper_flg = printer.queState();
		
	    if (no_paper_flg == 1)
	    {
			 Messagebox(this, "no_paper") ;
			 return; 	    	
	    }		
		
		
		
		int ret = printer.printQR("Hello,world", 5);
		
		
		 if (ret== 0) {  
			 
			 Messagebox(this, "success") ;
		 }
		 else
		 {
			  Messagebox(this, "fail") ;   
		 }			
		
		
	}
	
	public void printDataMatrix(View view)
	{
		
		if (!open_flg)
		{
			 Messagebox(this, "please open first") ;
			 return; 
		}	
		
		
		int no_paper_flg = printer.queState();
		
	    if (no_paper_flg == 1)
	    {
			 Messagebox(this, "no_paper") ;
			 return; 	    	
	    }		
		
		
		
		int ret = printer.printDataMatrix("Hello,world", 5);
		
		
		 if (ret== 0) {   
			 
			 Messagebox(this, "success") ;
		 }
		 else
		 { 
			  Messagebox(this, "fail") ;    
		 }			
		
		
	}
	
	
	public void printPictrue(View view)
	{
		
		if (!open_flg)
		{
			 Messagebox(this, "please open first") ;
			 return; 
		}
		
		int no_paper_flg = printer.queState();
		
	    if (no_paper_flg == 1)
	    {
			 Messagebox(this, "no_paper") ; 
			 return; 	     	
	    }		  
		
		
		
		int ret = printer.printPictureByRelativePath("/res/drawable-hdpi/ic_launcher.png", 200, 200);
		
		 
		 if (ret== 0) {     
			 
			 Messagebox(this, "success") ; 
		 }
		 else
		 { 
			  Messagebox(this, "fail") ;    
		 }			
				
		
		
	}	
	
	
	
	public void close(View view)
	{
		
		
		if (!open_flg)
		{
			 Messagebox(this, "is closed") ;
			 return;  
		}
		
	
	   
	   int ret =  printer.close();
	   
	   if (ret == 0) {
		   
		   
		   Messagebox(this, "close success!!") ;
		   open_flg = false; 
		   
	    }
	   else {
		   
		   
		   Messagebox(this, "close fail!!") ;
		   open_flg = true;  		   
		     
	   }		
			
	}	
	
	private void Messagebox(EchoJS echoJS, String info) {
		Toast myMessage = Toast.makeText(cordova.getActivity().getWindow().getContext(), info, Toast.LENGTH_LONG);
        myMessage.show();
		
	}


	/*public static void Messagebox(Context context,String info)
	{
		Builder builder = new Builder(context);    
		builder.setTitle("title");   
		builder.setMessage(info);     
		builder.setPositiveButton("yes", null);     
		builder.show();              
	}	*/
    
    @Override
    public boolean execute(String action, final CordovaArgs args, final CallbackContext callbackContext)
        throws JSONException{
            if(action.equals("printData")){
                PrintData();
            	//printString("Hellow");
                callbackContext.success("okay");
                return true;
            }
            
            if(action.equals("andreTest")){
                andreTest();
                callbackContext.success("okay");
                return true;
            }
            
            return false;
        }
}

