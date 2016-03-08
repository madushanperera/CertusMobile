package app.certus.com.adapters;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import app.certus.com.certusmobile.ProductDetailActivity;
import app.certus.com.certusmobile.R;

/**
 * Created by shanaka on 3/1/16.
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {
  //  private Drawable mDivider;
    private Paint paint;

    public DividerItemDecoration(Context context) {
       // mDivider = context.getResources().getDrawable(R.drawable.separator);
        paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.md_red_500));
        paint.setStrokeWidth(1);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int startX = parent.getPaddingLeft();
        int stopX = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount -1; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int y = child.getBottom() + params.bottomMargin;

            c.drawLine(startX, y, stopX, y, paint);
        }
    }
}
