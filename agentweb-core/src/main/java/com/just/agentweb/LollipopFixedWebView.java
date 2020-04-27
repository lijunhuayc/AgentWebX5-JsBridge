package com.just.agentweb;

import android.content.Context;
import android.util.AttributeSet;

import com.tencent.smtt.sdk.WebView;

/**
 * 修复 Android 5.0 & 5.1 打开 WebView 闪退问题：
 * 参阅 https://stackoverflow.com/questions/41025200/android-view-inflateexception-error-inflating-class-android-webkit-webview
 */
@SuppressWarnings("unused")
public class LollipopFixedWebView extends WebView {
    public LollipopFixedWebView(Context context) {
        super(getFixedContext(context));
    }

    public LollipopFixedWebView(Context context, AttributeSet attrs) {
        super(getFixedContext(context), attrs);
    }

    public LollipopFixedWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(getFixedContext(context), attrs, defStyleAttr);
    }

    /**
     * X5 没有此构造函数
     */
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public LollipopFixedWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(getFixedContext(context), attrs, defStyleAttr, true);
//    }
    public LollipopFixedWebView(Context context, AttributeSet attrs, int defStyleAttr, boolean privateBrowsing) {
        super(getFixedContext(context), attrs, defStyleAttr, privateBrowsing);
    }

    /**
     * 不能使用此方式处理Android L中 WebView Resources$NotFoundException 的问题,
     * 因为X5 内部会直接获取context转Activity, 此方式处理后context 变为 ContextImpl 了.
     * @param context
     * @return
     */
    public static Context getFixedContext(Context context) {
//        if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT < 23) {
//            // Avoid crashing on Android 5 and 6 (API level 21 to 23)
//            return context.createConfigurationContext(new Configuration());
//        }
        return context;
    }


}
