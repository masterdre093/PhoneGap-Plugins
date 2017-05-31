function ScannerJS() {}

ScannerJS.prototype.PluginName = "ScannerJS";

ScannerJS.prototype.pInvoke = function(method, data, callbackOK, callbackError){
    if(data==null || data===undefined){
        data=[];
    }
    else if(!Array.isArray(data)){
        data = [data];
    }
    
    cordova.exec(callbackOK, callbackError, this.PluginName, method, data);
};

ScannerJS.prototype.printData = function(data, callbackOK, callbackError){
   this.pInvoke("printData", data, callbackOK, callbackError);
};

ScannerJS.prototype.scannerOpen = function(data, callbackOK, callbackError){
    this.pInvoke("scannerOpen", data, callbackOK, callbackError);
};

ScannerJS.prototype.scanData = function(data, callbackOK, callbackError){
    this.pInvoke("scanData", data, callbackOK, callbackError);
};

ScannerJS.prototype.scannerClose = function(data, callbackOK, callbackError){
    this.pInvoke("scannerClose", data, callbackOK, callbackError);
};


ScannerJS.install = function(){
    if(!window.plugins){
        window.plugins = {};
    }  
    
    window.plugins.scannerjs = new ScannerJS();
    return window.plugins.scannerjs;
};

cordova.addConstructor(ScannerJS.install);