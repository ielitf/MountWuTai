# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/jiezhao/Library/Android/sdk/tools/proguard/proguard-android.txt
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

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
  public *;
}
################common###############
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.preference.Preference
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.annotation.**
-keep public class * extends android.support.v7.**
-keep class com.bupt.mountwutai.entity.** { *; } #实体类不参与混淆
-keep class com.bupt.mountwutai.widget.** { *; } #自定义控件不参与混淆
# 保持自定义控件类不被混淆
-keepclasseswithmembers class * {
 public <init>(android.content.Context, android.util.AttributeSet);
}

# 保持自定义控件类不被混淆
-keepclasseswithmembers class * {
 public <init>(android.content.Context, android.util.AttributeSet, int);
}

# 保持自定义控件类不被混淆
-keepclassmembers class * extends android.app.Activity {
 public void *(android.view.View);
}

-keep class com.amap.api.** { *; }
-dontwarn com.amap.api.**
-keep class com.autonavi.aps.amapapi.model.** { *; }
-dontwarn com.autonavi.aps.amapapi.model.**
-keep class com.loc.** { *; }
-dontwarn com.loc.**

-keep class com.amap.api.services.** { *; }
-dontwarn com.amap.api.services.**

-keep class com.amap.api.** { *; }
-dontwarn com.amap.api.**
-keep class com.autonavi.** { *; }
-dontwarn com.autonavi.**

-keep class com.baidu.** { *; }
-dontwarn com.baidu.**