package com.just.agentweb.sample.app;

import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.squareup.leakcanary.LeakCanary;
import com.tencent.smtt.sdk.QbSdk;

/**
 * Created by cenxiaozhong on 2017/5/23.
 * source code  https://github.com/Justson/AgentWeb
 */

public class App extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * 说明， WebView 初处初始化耗时 250ms 左右。
         * 提前初始化WebView ，好处可以提升页面初始化速度，减少白屏时间，
         * 坏处，拖慢了App 冷启动速度，如果 WebView 配合 VasSonic 使用，
         * 建议不要在此处提前初始化 WebView 。
         */
//        WebView mWebView=new WebView(new MutableContextWrapper(this));

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...

        QbSdk.setDownloadWithoutWifi(true);//设置非WIFI也需要主动下载X5内核
        // TODO... PS: 等待X5初始化完毕再创建webview, 否则会使用系统内核
        QbSdk.setNeedInitX5FirstTime(true);//true表示首次使用时需要先加载X5(避免启动界面广告打开webview时X5还未初始化完毕导致使用系统内核)
//        //这个函数内是异步执行所以不会阻塞 App 主线程，这个函数内是轻量级执行所以对 App 启动性能没有影响
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                Log.d("X5LOG", "BrowserSDKCore.initBrowserSDK.onCoreInitFinished: ...");
            }

            @Override
            public void onViewInitFinished(boolean finished) {
                Log.d("X5LOG", "BrowserSDKCore.initBrowserSDK.onViewInitFinished: finished = " + finished);
            }
        });

    }
}
