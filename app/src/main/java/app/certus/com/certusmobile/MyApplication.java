package app.certus.com.certusmobile;

import android.app.Application;
import android.content.Context;

import app.certus.com.SessionManage.Session;

/**
 * Created by shanaka on 2/27/16.
 */
public class MyApplication extends Application {
    private static MyApplication sInstance;
    private static Session session;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        session = new Session();
    }

    public static MyApplication getsInstance() {
        return sInstance;
    }

    public static Session getAndroidSession() {
        return session;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }
}
