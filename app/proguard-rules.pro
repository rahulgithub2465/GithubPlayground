# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/maverick8/Android/Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

-keep class com.squareup.** { *; }
-dontwarn okio.**
-keep interface com.squareup.** { *; }
-dontwarn com.squareup.okhttp.**

-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
-keep interface retrofit.** { *;}
-dontwarn rx.**
-dontwarn retrofit.**

-keepattributes Signature
-keepattributes Annotation
-keep class com.mavericklabs.transporto.model.** { *; }
-keepattributes InnerClasses
-keepattributes EnclosingMethod
-keep class com.citrus.sdk.** { *; }

#for event bus
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static *** d(...);
    public static *** e(...);
}

# OkHttp
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

#Dagger2
-dontwarn com.google.errorprone.annotations.*
-keep @interface dagger.Component
-keepclassmembers @dagger.Component class * { *; }

-keep public class com.google.android.gms.* { public *; }
-dontwarn com.google.android.gms.**