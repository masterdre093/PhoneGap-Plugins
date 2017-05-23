function PrinterJS() {}

PrinterJS.prototype.PluginName = "PrinterJS";

PrinterJS.prototype.pInvoke = function(method, data, callbackOK, callbackError){
    if(data==null || data===undefined){
        data=[];
    }
    else if(!Array.isArray(data)){
        data = [data];
    }
    
    cordova.exec(callbackOK, callbackError, this.PluginName, method, data);
};

PrinterJS.prototype.printData = function(data, callbackOK, callbackError){
   this.pInvoke("printData", data, callbackOK, callbackError);
};

PrinterJS.prototype.printerOpen = function(data, callbackOK, callbackError){
	   this.pInvoke("printerOpen", data, callbackOK, callbackError);
};

PrinterJS.prototype.printerClose = function(data, callbackOK, callbackError){
	   this.pInvoke("printerClose", data, callbackOK, callbackError);
};

PrinterJS.prototype.printString = function(data, callbackOK, callbackError){
	   this.pInvoke("printString", data, callbackOK, callbackError);
};

PrinterJS.prototype.printerBold = function(data, callbackOK, callbackError){
	   this.pInvoke("printerBold", data, callbackOK, callbackError); /*true or false*/
};

PrinterJS.prototype.printerSize = function(data, callbackOK, callbackError){ /*0-2*/
	   this.pInvoke("printerSize", data, callbackOK, callbackError);
};

PrinterJS.prototype.printBlankLines = function(data, callbackOK, callbackError){
	   this.pInvoke("printBlankLines", data, callbackOK, callbackError);
};

PrinterJS.prototype.setAlignment = function(data, callbackOK, callbackError){ /*0- left, 1- middle, 2-right*/
	   this.pInvoke("setAlignment", data, callbackOK, callbackError);
};

PrinterJS.prototype.setFontwidthZoomIn = function(data, callbackOK, callbackError){ /*1-4*/
	   this.pInvoke("setFontwidthZoomIn", data, callbackOK, callbackError);
};

PrinterJS.prototype.setFontHeightZoomIn = function(data, callbackOK, callbackError){ /*1-4*/
	   this.pInvoke("setFontHeightZoomIn", data, callbackOK, callbackError);
};

PrinterJS.install = function(){
    if(!window.plugins){
        window.plugins = {};
    }  
    
    window.plugins.printjs = new PrinterJS();
    return window.plugins.printjs;
};

cordova.addConstructor(PrinterJS.install);