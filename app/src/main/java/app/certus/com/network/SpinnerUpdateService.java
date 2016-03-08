package app.certus.com.network;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.Collections;

import app.certus.com.model.SubCategories;

/**
 * Created by shanaka on 2/29/16.
 */
public class SpinnerUpdateService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private ArrayList<String> subs = new ArrayList<>();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        volleySubCatFilter("women");
        intent = new Intent("spinner-update");
        intent.putStringArrayListExtra("spinner_data", subs);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    public void volleySubCatFilter(String catName) {
        String url = "http://10.0.3.2:8080/ECommerceApp/mobileFindSubCategoriesAction?catName=" + catName;

        RequestQueue requestQueue = VolleySingleton.getsInstance().getRequestQueue();
        GsonRequest gsonRequest = new GsonRequest(Request.Method.GET, url, SubCategories.class, null, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                if (response instanceof SubCategories) {
                    SubCategories subCategories = (SubCategories) response;
                    subs = getItemsToSpinner(subCategories);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        gsonRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        requestQueue.add(gsonRequest);

    }

    private ArrayList<String> getItemsToSpinner(SubCategories subCategories) {
        ArrayList<String> spinnerList = new ArrayList<>();
        for (SubCategories.SubCategoriesEntity categoriesEntity : subCategories.getSub_categories()) {
            spinnerList.add(WordUtils.capitalize(categoriesEntity.getSub_name()));
        }
        Collections.sort(spinnerList);
        return spinnerList;
    }
}
