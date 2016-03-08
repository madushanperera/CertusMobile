package app.certus.com.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import app.certus.com.certusmobile.ProductDetailActivity;
import app.certus.com.certusmobile.R;
import app.certus.com.model.Products;
import app.certus.com.model.SingleItem;
import app.certus.com.network.GsonRequest;
import app.certus.com.network.VolleySingleton;

/**
 * Created by shanaka on 2/25/16.
 */

public class Product_ScrollerAdapter extends RecyclerView.Adapter<Product_ScrollerAdapter.ProductItemViewHolder> {

    private LayoutInflater inflater;
    private List<Products> products = Collections.emptyList();
    private Context context;

    public Product_ScrollerAdapter(Context context, List<Products> products) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.products = products;

    }

    public void setProducts(List<Products> products) {
        this.products = products;

    }

    @Override
    public ProductItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.scroll_item, parent, false);
        ProductItemViewHolder productItemViewHolder = new ProductItemViewHolder(view);
        return productItemViewHolder;
    }

    @Override
    public void onBindViewHolder(ProductItemViewHolder holder, int position) {
        Products pr = this.products.get(position);
        holder.pro_name.setText(pr.getName());
        holder.pro_brand.setText(pr.getBrand());
        holder.pro_real_price.setText("Rs. " + pr.getPrice());
        holder.pro_disc_price.setText("Rs. " + getDiscountPrice(pr.getPrice(), pr.getDic_per()));
        holder.pro_image.setImageResource(R.drawable.img_test);
        String image_url = "http://10.0.2.2:8080/ECommerceApp/" + pr.getImg();
        Picasso.with(context).load(image_url).into(holder.pro_image);

    }

    public double getDiscountPrice(double price, int percentage) {
        return (int) (price - (price * (percentage / 100.0f)));
    }

    @Override
    public int getItemCount() {
        return this.products != null ? this.products.size() : 0;
    }

    class ProductItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView pro_name;
        TextView pro_brand;
        TextView pro_real_price;
        TextView pro_disc_price;
        ImageView pro_image;

        public ProductItemViewHolder(View itemView) {
            super(itemView);
            pro_name = (TextView) itemView.findViewById(R.id.scroll_product_product_name);
            pro_brand = (TextView) itemView.findViewById(R.id.scroll_product_product_brand);
            pro_real_price = (TextView) itemView.findViewById(R.id.scroll_product_product_original_price);
            pro_disc_price = (TextView) itemView.findViewById(R.id.scroll_product_product_price);
            pro_image = (ImageView) itemView.findViewById(R.id.scroll_product_img);
            pro_image.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            Toast toast = Toast.makeText(itemView.getContext(),""+products.get(getAdapterPosition()).getPro_name(),Toast.LENGTH_LONG);
//            toast.show();
            RequestQueue requestQueue = VolleySingleton.getsInstance().getRequestQueue();

            String url = "http://10.0.2.2:8080/ECommerceApp/mobileSingleItemAction?id="
                    + products.get(getAdapterPosition()).getPid();
            GsonRequest gsonRequest = new GsonRequest(Request.Method.GET, url, SingleItem.class, null, new Response.Listener() {
                @Override
                public void onResponse(Object response) {
                    if (response instanceof SingleItem) {
                        SingleItem singleItem = (SingleItem) response;
                        Intent intent = new Intent(context, ProductDetailActivity.class);
                        intent.putExtra("singleItem", singleItem);
                        context.startActivity(intent);
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
    }
}
