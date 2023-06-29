import { NativeModules } from 'react-native';

const LINKING_ERROR =
  "The package '@capitual/react-native-android-pin-authentication' doesn't seem to be linked. Make sure: \n\n" +
  '- You rebuilt the app after installing the package\n';

const RNmodule = NativeModules.ReactNativeAndroidPinAuthentication
  ? NativeModules.ReactNativeAndroidPinAuthentication
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function authenticatePinAndroid(): Promise<boolean> {
  return new Promise((resolve, reject) => {
    RNmodule.authenticatePin((success: boolean) => {
      if (success) resolve(success);
      else reject(success);
    });
  });
}
