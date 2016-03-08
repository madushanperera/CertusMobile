package app.certus.com.certusmobile;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import com.google.common.collect.Ordering;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import app.certus.com.adapters.DividerItemDecoration;
import app.certus.com.adapters.ReviewsAdapter;

import app.certus.com.model.CartDetails;
import app.certus.com.model.CartItem;
import app.certus.com.model.SingleItem;

public class ProductDetailActivity extends AppCompatActivity {
    private String selectedSize = null;
    private int selectedAmount;
    public static Comparator<String> SIZES_COMPARATOR = Ordering.explicit("S", "M", "L", "XL", "XXL");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_product_detail_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Product Detail");

        final Bundle productDetails = getIntent().getExtras();
        final SingleItem product = (SingleItem) productDetails.get("singleItem");
        final TextView price = (TextView) findViewById(R.id.product_detail_price);
        if (product != null) {
            ImageView pro_img = (ImageView) findViewById(R.id.product_detail_imageView);
            TextView pro_name = (TextView) findViewById(R.id.product_detail_product_name);
            TextView desc = (TextView) findViewById(R.id.product_detail_product_description);
            pro_name.setText(product.getName());
            price.setText("Rs " + product.getPrice());
            desc.setText(Html.fromHtml(product.getDesc()));
            String image_url = "http://10.0.2.2:8080/ECommerceApp/" + product.getImg();
            Picasso.with(ProductDetailActivity.this).load(image_url).into(pro_img);

        }

        ImageButton viewReviewBtn = (ImageButton) findViewById(R.id.product_detail_view_comment);
        viewReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDialog reviewsDialog = new MaterialDialog.Builder(ProductDetailActivity.this)
                        .title("Reviews")
                        .customView(R.layout.review_recycler, false)
                        .positiveText("OK")
                        .build();
                View view = reviewsDialog.getCustomView();
                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_review);
                recyclerView.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(ProductDetailActivity.this,
                        LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(ProductDetailActivity.this);
                recyclerView.addItemDecoration(itemDecoration);
                ReviewsAdapter reviewsAdapter = new ReviewsAdapter(ProductDetailActivity.this,
                        product.getReviews());
                recyclerView.setAdapter(reviewsAdapter);
                reviewsDialog.show();

            }
        });

        final Button changeSizeBtn = (Button) findViewById(R.id.size_btn);
        changeSizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> pSizes = product.getSizes();
                Collections.sort(pSizes, SIZES_COMPARATOR);
                MaterialDialog sizeDialog = new MaterialDialog.Builder(ProductDetailActivity.this)
                        .title("Choose Size")
                        .items(pSizes)
                        .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                selectedSize = text.toString();
                                changeSizeBtn.setText(selectedSize);
                                if (product.getDisc_per() > 0) {
                                    price.setText("Rs " + getDiscountPrice(product.getPriceBySize(selectedSize)
                                            , product.getDisc_per()));
                                } else {
                                    price.setText("Rs " + product.getPriceBySize(selectedSize));
                                }
                                return true;
                            }
                        })
                        .positiveText("SELECT").build();
                sizeDialog.show();
            }
        });

        final Button chgQntyBtn = (Button) findViewById(R.id.btn_qnty_sel);
        chgQntyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                MaterialDialog qntyDialog = new MaterialDialog.Builder(ProductDetailActivity.this)
                        .title("Choose Size")
                        .items(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))
                        .itemsCallbackSingleChoice(1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                boolean flag = false;
                                if (selectedSize != null) {
                                    if (Integer.parseInt(text.toString()) > (int) product.getQntyBySize(selectedSize)) {
                                        Snackbar.make(v, "Sorry ! Only " + (int) product.getQntyBySize(selectedSize)
                                                + " available in selected size", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null)
                                                .setActionTextColor(getResources().getColor(R.color.md_red_500))
                                                .show();
                                    } else {
                                        selectedAmount = Integer.parseInt(text.toString());
                                        chgQntyBtn.setText(selectedAmount + "");
                                        flag = true;
                                    }
                                } else {
                                    Snackbar.make(v, "Please Select product size !", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null)
                                            .setActionTextColor(getResources().getColor(R.color.md_red_500))
                                            .show();
                                }
                                return flag;
                            }
                        })
                        .positiveText("SELECT").build();
                qntyDialog.show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MyApplication.getAndroidSession().getAttribute("cart") == null) {
                    CartDetails cart = new CartDetails();
                    cart.addItem(new CartItem(product.getPid(), selectedAmount, selectedSize));
                    MyApplication.getAndroidSession().setAttribute("cart", cart);
                } else {
                    CartDetails cart = (CartDetails) MyApplication.getAndroidSession()
                            .getAttribute("cart");
                    cart.addItem(new CartItem(product.getPid(), selectedAmount, selectedSize));
                }
                Snackbar.make(view, "Product Added to the Cart !", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
    }

    public double getDiscountPrice(double price, int percentage) {
        return (int) (price - (price * (percentage / 100.0f)));
    }

}
