package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.certus.com.adapters.Product_ScrollerAdapter;
import app.certus.com.certusmobile.MainActivity;
import app.certus.com.certusmobile.R;
import app.certus.com.model.ProductItem;
import app.certus.com.model.Products;
import app.certus.com.model.SubCategories;
import app.certus.com.network.GsonRequest;
import app.certus.com.network.VolleySingleton;
import layout.home.factory.SlidingTabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private ViewPager mPager;
    private SlidingTabLayout mTabs;
    private RecyclerView product_scroller;
    private Product_ScrollerAdapter product_scrollerAdapter;
    private boolean firstScrolled = true;
    private List<Products> productsList = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPager.setAdapter(new SamplePagerAdapter());
        mTabs = (SlidingTabLayout) view.findViewById(R.id.tabs);
        mTabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (firstScrolled) {
                    String url = "http://10.0.2.2:8080/ECommerceApp/mobileFindSubCategoriesAction?catName=women";

                    RequestQueue requestQueue = VolleySingleton.getsInstance().getRequestQueue();
                    GsonRequest gsonRequest = new GsonRequest(Request.Method.GET, url, SubCategories.class, null, new Response.Listener() {
                        @Override
                        public void onResponse(Object response) {
                            if (response instanceof SubCategories) {
                                SubCategories subCategories = (SubCategories) response;
                                ((MainActivity) getActivity()).addItemsToSpinner(getItemsToSpinner(subCategories),
                                        subCategories.getCat_id());
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast t = Toast.makeText(getActivity(), "Error - " + error.toString(), Toast.LENGTH_SHORT);
                            t.show();
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

                    firstScrolled = false;
                }


            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    String url = "http://10.0.2.2:8080/ECommerceApp/mobileFindSubCategoriesAction?catName=women";

                    RequestQueue requestQueue = VolleySingleton.getsInstance().getRequestQueue();
                    GsonRequest gsonRequest = new GsonRequest(Request.Method.GET, url, SubCategories.class, null, new Response.Listener() {
                        @Override
                        public void onResponse(Object response) {
                            if (response instanceof SubCategories) {
                                SubCategories subCategories = (SubCategories) response;
                                ((MainActivity) getActivity()).addItemsToSpinner(getItemsToSpinner(subCategories),
                                        subCategories.getCat_id());
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast t = Toast.makeText(getActivity(), "Error - " + error.toString(), Toast.LENGTH_SHORT);
                            t.show();
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
                if (position == 1) {
                    String url = "http://10.0.2.2:8080/ECommerceApp/mobileFindSubCategoriesAction?catName=men";

                    RequestQueue requestQueue = VolleySingleton.getsInstance().getRequestQueue();
                    GsonRequest gsonRequest = new GsonRequest(Request.Method.GET, url, SubCategories.class, null, new Response.Listener() {
                        @Override
                        public void onResponse(Object response) {
                            if (response instanceof SubCategories) {
                                SubCategories subCategories = (SubCategories) response;
                                ((MainActivity) getActivity()).addItemsToSpinner(getItemsToSpinner(subCategories),
                                        subCategories.getCat_id());
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast t = Toast.makeText(getActivity(), "Error - " + error.toString(), Toast.LENGTH_SHORT);
                            t.show();
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
                if (position == 2) {
                    String url = "http://10.0.2.2:8080/ECommerceApp/mobileFindSubCategoriesAction?catName=household%20goods";

                    RequestQueue requestQueue = VolleySingleton.getsInstance().getRequestQueue();
                    GsonRequest gsonRequest = new GsonRequest(Request.Method.GET, url, SubCategories.class, null, new Response.Listener() {
                        @Override
                        public void onResponse(Object response) {
                            if (response instanceof SubCategories) {
                                SubCategories subCategories = (SubCategories) response;
                                ((MainActivity) getActivity()).addItemsToSpinner(getItemsToSpinner(subCategories),
                                        subCategories.getCat_id());
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast t = Toast.makeText(getActivity(), "Error - " + error.toString(), Toast.LENGTH_SHORT);
                            t.show();
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
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.md_text_white);
            }
        });
        mTabs.setViewPager(mPager);


    }

    public void setProduct_scrollerAdapter(List<Products> productsList) {
        this.productsList = productsList;


    }

    private List<String> getItemsToSpinner(SubCategories subCategories) {
        List<String> spinnerList = new ArrayList<>();
        for (SubCategories.SubCategoriesEntity categoriesEntity : subCategories.getSub_categories()) {
            spinnerList.add(WordUtils.capitalize(categoriesEntity.getSub_name()));
        }
        Collections.sort(spinnerList);
        return spinnerList;
    }


    private class SamplePagerAdapter extends PagerAdapter {
        String categories[] = {"WOMEN", "MEN", "HOUSE HOLD ITEMS"};

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //  return super.getPageTitle(position);
            return categories[position];
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.i("came_here", "" + position);
            // Inflate a new layout from our resources
            View view = getActivity().getLayoutInflater().inflate(R.layout.product_scroller_tabs,
                    container, false);
            product_scroller = (RecyclerView) view.findViewById(R.id.scrollView_products);
            product_scroller.setLayoutManager(new LinearLayoutManager(getActivity()));
            product_scrollerAdapter = new Product_ScrollerAdapter(getActivity(), productsList);
            product_scrollerAdapter.setProducts(productsList);
            product_scroller.setAdapter(product_scrollerAdapter);
            // Add the newly created View to the ViewPager
            container.addView(view);


            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            // Log.i(LOG_TAG, "destroyItem() [position: " + position + "]");
        }


    }
}
