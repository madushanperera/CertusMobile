package app.certus.com.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import app.certus.com.certusmobile.R;
import app.certus.com.model.ProductItem;
import app.certus.com.model.SingleItem;

/**
 * Created by shanaka on 3/1/16.
 */
public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewItemViewHolder> {
    private LayoutInflater inflater;
    private List<SingleItem.ReviewsEntity> reviewsEntities = Collections.emptyList();
    private Context context;

    public ReviewsAdapter(Context context, List<SingleItem.ReviewsEntity> reviewsEntities) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.reviewsEntities = reviewsEntities;
    }

    @Override
    public ReviewItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.review_item, parent, false);
        ReviewItemViewHolder reviewItemViewHolder = new ReviewItemViewHolder(view);

        return reviewItemViewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewItemViewHolder holder, int position) {
        SingleItem.ReviewsEntity entity = reviewsEntities.get(position);
        holder.user_name.setText(entity.getUser());
        holder.user_comment.setText(entity.getComment());
        holder.date.setText(entity.getDate());
    }

    @Override
    public int getItemCount() {
        return reviewsEntities != null ? reviewsEntities.size() : 0;
    }

    class ReviewItemViewHolder extends RecyclerView.ViewHolder {
        TextView user_name;
        TextView user_comment;
        TextView date;

        public ReviewItemViewHolder(View itemView) {
            super(itemView);
            user_name = (TextView) itemView.findViewById(R.id.user_name);
            user_comment = (TextView) itemView.findViewById(R.id.user_comment);
            date = (TextView) itemView.findViewById(R.id.date_comment);

        }
    }
}
