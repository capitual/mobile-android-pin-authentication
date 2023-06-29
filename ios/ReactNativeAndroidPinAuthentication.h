
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNReactNativeAndroidPinAuthenticationSpec.h"

@interface ReactNativeAndroidPinAuthentication : NSObject <NativeReactNativeAndroidPinAuthenticationSpec>
#else
#import <React/RCTBridgeModule.h>

@interface ReactNativeAndroidPinAuthentication : NSObject <RCTBridgeModule>
#endif

@end
