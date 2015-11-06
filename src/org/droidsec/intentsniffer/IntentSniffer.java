package org.droidsec.intentsniffer;

import static de.robv.android.xposed.XposedHelpers.findClass;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class IntentSniffer implements IXposedHookLoadPackage{
			
	@Override
	public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
		
		if(lpparam.isFirstApplication && !lpparam.packageName.equals("org.droidsec.intentsniffer")){
			
		final String tag = "IntentSniffer-" + lpparam.packageName;
		
		//ContextWrapper startActivities
		final Class<?> contextActivities = findClass("android.content.ContextWrapper", lpparam.classLoader);
		XposedBridge.hookAllMethods(contextActivities, "startActivities", new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
				Intent[] intents = (Intent[])param.args[0];
				String methodName = param.method.getName();
				for(Intent i : intents){
					logMsg(i, tag, methodName);
				}
			}
		});
		
		//ContextWrapper startActivity
		final Class<?> contextActivity = findClass("android.content.ContextWrapper", lpparam.classLoader);
		XposedBridge.hookAllMethods(contextActivity, "startActivity", new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
				Intent i = (Intent)param.args[0];
				String methodName = param.method.getName();
				logMsg(i, tag, methodName);
			}
		});
		
		//ContextWrapper startActivityForResult
		final Class<?> contextActivityForResult = findClass("android.content.ContextWrapper", lpparam.classLoader);
		XposedBridge.hookAllMethods(contextActivityForResult, "startActivityForResult", new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
				Intent i = (Intent)param.args[0];
				String methodName = param.method.getName();
				logMsg(i, tag, methodName);
			}
		});
		
		//Activity startActivities
		final Class<?> appActivities = findClass("android.app.Activity", lpparam.classLoader);
		XposedBridge.hookAllMethods(appActivities, "startActivities", new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
				Intent[] intents = (Intent[])param.args[0];
				String methodName = param.method.getName();
				for(Intent i : intents){
					logMsg(i, tag, methodName);
				}
			}
		});
		
		//Activity startActivity
		final Class<?> appActivity = findClass("android.app.Activity", lpparam.classLoader);
		XposedBridge.hookAllMethods(appActivity, "startActivity", new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
				Intent i = (Intent)param.args[0];
				String methodName = param.method.getName();
				logMsg(i, tag, methodName);
			}
		});
		
		//Activity startActivityForResult
		final Class<?> appActivityForResult = findClass("android.app.Activity", lpparam.classLoader);
		XposedBridge.hookAllMethods(appActivityForResult, "startActivityForResult", new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
				Intent i = (Intent)param.args[0];
				String methodName = param.method.getName();
				logMsg(i, tag, methodName);
			}
		});
		
		//startService
		final Class<?> sService = findClass("android.content.ContextWrapper", lpparam.classLoader);
		XposedBridge.hookAllMethods(sService, "startService", new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
				Intent i = (Intent)param.args[0];
				String methodName = param.method.getName();
				logMsg(i, tag, methodName);
			}
		});
		
		//bindService
		final Class<?> bService = findClass("android.content.ContextWrapper", lpparam.classLoader);
		XposedBridge.hookAllMethods(bService, "bindService", new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
				Intent i = (Intent)param.args[0];
				String methodName = param.method.getName();
				logMsg(i, tag, methodName);
			}
		});
		
		//sendBroadcast
		final Class<?> sBroadcast = findClass("android.content.ContextWrapper", lpparam.classLoader);
		XposedBridge.hookAllMethods(sBroadcast, "sendBroadcast", new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
				Intent i = (Intent)param.args[0];
				String methodName = param.method.getName();
				logMsg(i, tag, methodName);
			}
		});
		
		//sendOrderedBroadcast
		final Class<?> soBroadcast = findClass("android.content.ContextWrapper", lpparam.classLoader);
		XposedBridge.hookAllMethods(soBroadcast, "sendOrderedBroadcast", new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
				Intent i = (Intent)param.args[0];
				String methodName = param.method.getName();
				logMsg(i, tag, methodName);
			}
		});
		
	}
		else {
			return;
		}
	}
	
	public void logMsg(Intent i, String tag, String methodName){
        Log.i(tag, methodName + " Intent: " + i.toUri(0));
		Bundle b = i.getExtras(); 
		if(b != null){
            Object[] keys = b.keySet().toArray();   
            for(int n=0; n<keys.length; n++)
            { 
                Log.i(tag, methodName + " Extra[" + n +"]");
                String key = keys[n].toString();
                Log.i(tag, methodName + " Type: " + b.get(key).getClass().getName()); 
                Log.i(tag, methodName + " Key: " + key);
                Log.i(tag, methodName + " Value: " + String.valueOf(b.get(key)));
            }
	}
	}


}
