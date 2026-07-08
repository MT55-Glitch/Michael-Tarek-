# General rules
-keepattributes Signature
-keepattributes *Annotation*
-dontnote android.net.http.*
-dontnote org.apache.commons.codec.**
-dontnote org.apache.http.**

# Retrofit rules
-keepattributes Exceptions
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# Okhttp rules
-dontwarn okhttp3.**
-dontwarn okio.**
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }

# Gson rules
-keepattributes *Annotation*
-keepattributes EnclosingMethod
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.** { *; }
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Room rules
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.DatabaseConfiguration

# Hilt rules
-keep class dagger.hilt.** { *; }
-keep class * extends dagger.hilt.** { *; }

# TensorFlow Lite
-keep class org.tensorflow.** { *; }
-keep interface org.tensorflow.** { *; }

# Keep your app classes
-keep class com.englishlearning.app.** { *; }
-keepclassmembers class com.englishlearning.app.** { *; }

# BuildConfig
-keep class com.englishlearning.app.BuildConfig { *; }

# R classes
-keepclassmembers class **.R$* {
    public static <fields>;
}
