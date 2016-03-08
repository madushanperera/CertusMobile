package layout.home.factory;

import android.support.v7.widget.Toolbar;

/**
 * Created by shanaka on 2/26/16.
 */
public interface ToolbarOptions {
    public abstract void setToolbarForFragment(int toolbar_id);

    public abstract void setSpinnerForFragment(int spinner_id);

    public abstract void setHomeAsUpIndicatorForFragment(int img_id);

    public abstract void setDisplayHomeAsUpEnabledForFragment(boolean val);

    public abstract void setDisplayShowTitleEnabled(boolean val);
}
