package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import app.certus.com.adapters.CartItem_ScrollerAdapter;

import app.certus.com.certusmobile.MyApplication;
import app.certus.com.certusmobile.R;
import app.certus.com.listener.MyCustomListener;
import app.certus.com.model.CartDetails;
import app.certus.com.model.CartItem;
import app.certus.com.model.CompleteCartProItem;
import app.certus.com.network.VolleySingleton;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {
    private RecyclerView cart_horizontal_scroller;
    private CartItem_ScrollerAdapter cart_item_scrollerAdapter;
    private Button purchase_button;
    private List<CompleteCartProItem> completeCartProItems = new ArrayList<>();


    public CartFragment() {
        // Required empty public constructor
    }

    public void getData(MyCustomListener<CompleteCartProItem> customListener) {
        if (MyApplication.getAndroidSession().getAttribute("cart") != null) {
            Log.i("cart_null", "NOT null");
            RequestQueue requestQueue = VolleySingleton.getsInstance().getRequestQueue();
            CartDetails cartDetails = (CartDetails) MyApplication.getAndroidSession().getAttribute("cart");
            CopyOnWriteArrayList<CartItem> jsonSendArray = cartDetails.getShoppingList();
            final String jsonString = new Gson().toJson(jsonSendArray,
                    new TypeToken<CopyOnWriteArrayList<CartItem>>() {
                    }.getType());

            Log.i("json_object", jsonString);

            String url = "http://10.0.2.2:8080/ECommerceApp/getAllProductsAction";
            JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.POST, url,
                    response -> {
                        List<CompleteCartProItem> completeCart = new Gson().fromJson(response.toString(),
                                new TypeToken<List<CompleteCartProItem>>() {
                                }.getType());
                        Log.i("response", completeCart.get(0).getP_name());
                        customListener.onResponse(completeCart);

                    }, error -> Log.i("Volley_error", error.getMessage())) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/json");
                    params.put("cartList", jsonString);
                    return params;
                }

            };
            arrayRequest.setRetryPolicy(new RetryPolicy() {
                @Override
                public int getCurrentTimeout() {
                    return 5000;
                }

                @Override
                public int getCurrentRetryCount() {
                    return 5000;
                }

                @Override
                public void retry(VolleyError error) throws VolleyError {

                }
            });
            requestQueue.add(arrayRequest);
        } else {
            Log.i("cart_null", "null");
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        cart_horizontal_scroller = (RecyclerView) view.findViewById(R.id.horizontal_scrollView_cart_items);

        getData(new MyCustomListener<CompleteCartProItem>() {

            @Override
            public void onResponse(List<CompleteCartProItem> response) {
                completeCartProItems.addAll(response);
                //completeCartProItems.add(new CompleteCartProItem(1, 2340.0, "Extra Orient Top", "Orient", "", "S", 5));
                Log.i("check", completeCartProItems.get(0).getP_name());

            }

            @Override
            public void onError(String error_response) {

            }
        });

        cart_item_scrollerAdapter = new CartItem_ScrollerAdapter(getActivity(), completeCartProItems);

        cart_horizontal_scroller.setAdapter(cart_item_scrollerAdapter);
        cart_horizontal_scroller.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        purchase_button = (Button) view.findViewById(R.id.purchase_btn);
        purchase_button.setOnClickListener(v -> {
            Toast t = Toast.makeText(getActivity(), "Worked", Toast.LENGTH_LONG);
            t.show();
        });
        return view;
    }


}
