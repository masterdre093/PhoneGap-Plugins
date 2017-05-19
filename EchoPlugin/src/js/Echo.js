function EchoJS() {}

EchoJS.prototype.PluginName = "EchoJS";

EchoJS.prototype.pInvoke = function(method, data, callbackOK, callbackError){
    if(data==null || data===undefined){
        data=[];
    }
    else if(!Array.isArray(data)){
        data = [data];
    }
    
    cordova.exec(callbackOK, callbackError, this.PluginName, method, data);
};

EchoJS.prototype.printData = function(data, callbackOK, callbackError){
    this.pInvoke("printData", data, callbackOK, callbackError);
};

EchoJS.install = function(){
    if(!window.plugins){
        window.plugins = {};
    }  
    
    window.plugins.echojs = new EchoJS();
    return window.plugins.echojs;
};

cordova.addConstructor(EchoJS.install);