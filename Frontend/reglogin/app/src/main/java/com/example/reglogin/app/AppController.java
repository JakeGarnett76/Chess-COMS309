package com.example.reglogin.app;

import android.app.Application;
import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * A class used to facilitate JSON requests
 */
public class AppController extends Application {
    public static final String TAG = AppController.class
            .getSimpleName();
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static AppController mInstance;
    @Override

    /**
     * Creates a new instance
     */
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    /**
     * Returns an existing instance
     * @return the specified existing instance
     */
    public static synchronized AppController getInstance() {
        return mInstance;
    }

    /**
     * Returns an existing request queue
     * Creates a new request queue if the specified queue is equal to null
     * @return the specified existing request queue if not null; otherwise a new request queue
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    /**
     * Adds a request to the request queue with the specified tag
     * @param req The request
     * @param tag The request tag
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {
    // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    /**
     * Adds a request to the request queue without any tags
     * @param req The request
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    /**
     * Cancels any requests in a queue with a matching tag
     * @param tag The tag used to identify the request queue to cancel
     */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}

