package app.certus.com.listener;

import java.util.List;


/**
 * Created by shanaka on 3/6/16.
 */
public interface MyCustomListener<T> {

    public void onResponse( List<T> response);

    public void onError(String error_response);
}
