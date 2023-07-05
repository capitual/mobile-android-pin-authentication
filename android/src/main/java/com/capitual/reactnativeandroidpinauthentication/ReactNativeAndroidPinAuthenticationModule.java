package com.capitual.reactnativeandroidpinauthentication;

import androidx.annotation.NonNull;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import android.util.Log;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Callback;

@ReactModule(name = ReactNativeAndroidPinAuthenticationModule.NAME)
public class ReactNativeAndroidPinAuthenticationModule extends ReactContextBaseJavaModule {
  public static final String NAME = "ReactNativeAndroidPinAuthentication";
  private static final int REQUEST_CODE_PIN_VALIDATION = 123;
  private KeyguardManager keyguardManager;
  private ReactApplicationContext reactContext;
  private Callback authCallback;
  private final ActivityEventListener activityEventListener = new BaseActivityEventListener() {
  @Override
  public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
      if (requestCode == REQUEST_CODE_PIN_VALIDATION) {
        if (resultCode == Activity.RESULT_OK) {
          authCallback.invoke(true);
        } else {
          authCallback.invoke(false);
        }
      }
    }
  };

  public ReactNativeAndroidPinAuthenticationModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
    this.keyguardManager = (KeyguardManager) reactContext.getSystemService(Context.KEYGUARD_SERVICE);
    this.reactContext.addActivityEventListener(activityEventListener);
  }

  @ReactMethod
   public void authenticatePin(Callback callback) {
   this.authCallback = callback;
    if (keyguardManager.isKeyguardSecure()) {
      Intent intent = keyguardManager.createConfirmDeviceCredentialIntent(null, null);
      if (intent != null) {
        getCurrentActivity().startActivityForResult(intent, REQUEST_CODE_PIN_VALIDATION);
      }
    }
  }

  @ReactMethod
  public void isAvailablePin(Callback callback) {
    this.authCallback = callback;
    if(keyguardManager.isKeyguardSecure()){
      authCallback.invoke(true);
    }
    else{
      authCallback.invoke(false);
    }
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

}
