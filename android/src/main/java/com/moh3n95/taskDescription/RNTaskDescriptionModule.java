package com.moh3n95.taskDescription;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;

import android.app.ActivityManager;
import android.os.Build;
import android.graphics.Color;
import android.content.Context;

public class RNTaskDescriptionModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNTaskDescriptionModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNTaskDescription";
  }

  @ReactMethod
  public void setHeader(String label, String color) {  
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			ActivityManager activityManager = (ActivityManager) getCurrentActivity().getSystemService(Context.ACTIVITY_SERVICE);
			for (ActivityManager.AppTask appTask : activityManager.getAppTasks()) {
				if(appTask.getTaskInfo().id == getCurrentActivity().getTaskId()) {
					ActivityManager.TaskDescription description = appTask.getTaskInfo().taskDescription;
					getCurrentActivity().setTaskDescription(new ActivityManager.TaskDescription(
            (label != null)? label : description.getLabel(), 
            description.getIcon(), 
            (color != null)? Color.parseColor(color) : description.getPrimaryColor()
          ));
				}
			}
		}
  }

}