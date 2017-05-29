package com.andrelogs.PrinterJS;

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

public class PrinterJS extends CordovaPlugin{
    public void PrintData(){
        cordova.getActivity().runOnUiThread(new Runnable() {
            
            @Override
            public void run(){
                Toast myMessage = Toast.makeText(cordova.getActivity().getWindow().getContext(), "This message is from cordova plugin.", Toast.LENGTH_LONG);
                myMessage.show();
            }
        });
    }
    
    boolean open_flg = false;
	
   	Printer printer = null;
    

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
		   //Messagebox(this, "open success!!") ;
		   open_flg = true;  
	    }
	   else {   
		   Messagebox(this, "open fail!!") ;
		   open_flg = false; 		   
	  }		
	}

	public void printString(View view, String data)
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
		printer.setCharacterSpacing(1);
		printer.setUnderline(false);
		int ret = printer.printString(data);
		
		 if (ret== 0) {  
			 
			 //Messagebox(this, "success") ;
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
			 
			 //Messagebox(this, "success") ;
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
			 
			 //Messagebox(this, "success") ;
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
			 
			 //Messagebox(this, "success") ;
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
			 //Messagebox(this, "please open first") ;
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
			 
			 //Messagebox(this, "success") ; 
		 }
		 else
		 { 
			  Messagebox(this, "fail") ;    
		 }			
	}	
	
	public void setBold(boolean stat){
		printer.setBold(stat);
	}
	
	public void setfontSize(int size){ /*0-3*/
		printer.setFontSize(size);
	}
	
	public void printBlankLines(int n){
		printer.printBlankLines(n);
	}
	
	public void setAlignment(int tpy){ /*0- left, 1- middle, 2-right*/
		printer.setAlignment(tpy);
	}
	
	public void setFontwidthZoomIn(int n){ /*1-4*/
		printer.setFontwidthZoomIn(n);
	}
	
	public void setFontHeightZoomIn(int n){ /*1-4*/
		printer.setFontHeightZoomIn(n);
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
	
	private void Messagebox(PrinterJS printJS, String info) {
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
            
            if(action.equals("printerOpen")){
            	open(null);
                callbackContext.success("okay");
                return true;
            }
            
            if(action.equals("printerClose")){
            	close(null);
                callbackContext.success("okay");
                return true;
            }
            
            if(action.equals("printString")){
            	String userdata = args.getString(0);
            	printString(null, userdata);
                callbackContext.success("okay");
                return true;
            }
            
            if(action.equals("printerBold")){
            	boolean userdata = args.getBoolean(0);
            	setBold(userdata);
                callbackContext.success("okay");
                return true;
            }
            
            if(action.equals("printerSize")){
            	int userdata = args.getInt(0);
            	setfontSize(userdata);
                callbackContext.success("okay");
                return true;
            }
            
            if(action.equals("printBlankLines")){
            	int userdata = args.getInt(0);
            	printBlankLines(userdata);
                callbackContext.success("okay");
                return true;
            }
            
            if(action.equals("setAlignment")){
            	int userdata = args.getInt(0);
            	setAlignment(userdata);
                callbackContext.success("okay");
                return true;
            }
            
            if(action.equals("setFontwidthZoomIn")){
            	int userdata = args.getInt(0);
            	setFontwidthZoomIn(userdata);
                callbackContext.success("okay");
                return true;
            }
            
            if(action.equals("setFontHeightZoomIn")){
            	int userdata = args.getInt(0);
            	setFontHeightZoomIn(userdata);
                callbackContext.success("okay");
                return true;
            }
            
            return false;
        }
}

