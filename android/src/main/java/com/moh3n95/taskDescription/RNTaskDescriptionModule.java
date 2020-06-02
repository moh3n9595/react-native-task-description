package com.moh3n95.taskDescription;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.UiThreadUtil;

import android.app.ActivityManager;
import android.os.Build;
import android.graphics.Color;
import android.content.Context;
import android.app.Activity;

public class RNTaskDescriptionModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private String _color = null;
  private String _label = null;

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
    final Activity activity = getCurrentActivity();

    if (activity == null) return;

    _label = label;
    _color = color;

    UiThreadUtil.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
          ActivityManager activityManager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
          for (ActivityManager.AppTask appTask : activityManager.getAppTasks()) {
            if(appTask.getTaskInfo().id == activity.getTaskId()) {
              ActivityManager.TaskDescription description = appTask.getTaskInfo().taskDescription;
              activity.setTaskDescription(new ActivityManager.TaskDescription(
                (_label != null)? _label : description.getLabel(), 
                description.getIcon(), 
                (_color != null)? Color.parseColor(_color) : description.getPrimaryColor()
              ));
            }
          }
		    }
      }
    });
  }

}
