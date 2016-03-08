package app.certus.com.network;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import app.certus.com.certusmobile.MyApplication;

/**
 * Created by shanaka on 2/27/16.
 */
public class VolleySingleton {
    private static VolleySingleton sInstance;
    private RequestQueue mRequestQueue;

    private VolleySingleton() {
        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
    }

    public static VolleySingleton getsInstance() {
        if (sInstance == null) {
            sInstance = new VolleySingleton();
        }
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}
