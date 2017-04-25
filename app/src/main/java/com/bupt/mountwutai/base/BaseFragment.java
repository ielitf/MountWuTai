package com.bupt.mountwutai.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bupt.mountwutai.util.LogUtil;

import java.lang.reflect.Field;


public abstract class BaseFragment extends Fragment {
    public BaseActivity activity;
    public Context mContext;
    protected String TAG = getClass().getName();
    protected LayoutInflater inflater;
    protected View contentView;
    protected ViewGroup container;
//    protected CompositeSubscription mCompositeSubscription;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.v(TAG, "onAttach(): ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.d(TAG, "onCreate(): ");
        super.onCreate(savedInstanceState);

    }

    @Override
    public final View onCreateView(LayoutInflater inflater,
                                   ViewGroup container, Bundle savedInstanceState) {
//        TAG = getClass().getName();
        activity = (BaseActivity) getActivity();
        mContext = activity.getApplicationContext();
        this.inflater = inflater;
        this.container = container;
        onCreateView(savedInstanceState);
        if (contentView == null)
            return super.onCreateView(inflater, container, savedInstanceState);
        return contentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LogUtil.v(TAG, "onActivityCreated(): ");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        LogUtil.d(TAG, "onStart(): ");
        super.onStart();
    }

    @Override
    public void onResume() {
        LogUtil.v(TAG, "onResume(): ");
        super.onResume();
    }

    protected abstract void onCreateView(Bundle savedInstanceState);

    public void setContentView(View view) {
        contentView = view;
    }

    public View getContentView() {
        return contentView;
    }

    public void setContentView(int layoutResID) {
        contentView = inflater.inflate(layoutResID, container, false);
    }

    public View findViewById(int id) {
        if (contentView != null)
            return contentView.findViewById(id);
        return null;
    }

    protected void intent2Activity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(activity, tarActivity);
        startActivity(intent);
    }

//    protected void intent2Activity(Class<? extends Activity> tarActivity, Bundle bundle) {
//        Intent intent = new Intent(activity, tarActivity);
//        intent.putExtra(CodeConstants.BUNDLE_KEY, bundle);
//        startActivity(intent);
//    }

    protected void showToast(String msg) {
        //ToastUtils.showToast(mActivity, msg, Toast.LENGTH_SHORT);
        activity.showToast(msg);
    }

    protected void showLog(String msg) {
        LogUtil.i(TAG, msg);
    }

    protected void showProgressDialog() {
        activity.showProgressDialog();
    }

    protected void dismissProgressDialog() {
        activity.dismissProgressDialog();
    }

//
//    public void addSubscription(Observable observable, Subscriber subscriber) {
//        if (mCompositeSubscription == null) {
//            mCompositeSubscription = new CompositeSubscription();
//        }
//        mCompositeSubscription.add(observable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber));
//    }
//
//
//    public void addSubscription(Subscription subscription) {
//        if (mCompositeSubscription == null) {
//            mCompositeSubscription = new CompositeSubscription();
//        }
//        mCompositeSubscription.add(subscription);
//    }
//
//    private void onUnsubscribe() {
//        //取消注册，以避免内存泄露
//        if (mCompositeSubscription != null) {
//            mCompositeSubscription.unsubscribe();
//        }
//    }

    public Context getApplicationContext() {
        return mContext;
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.d(TAG, "onPause is called");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.v(TAG, "onStop is called");

    }

    @Override
    public void onDestroyView() {
        LogUtil.d(TAG, "onDestroyView() : ");
        super.onDestroyView();
        contentView = null;
        container = null;
        inflater = null;
    }

    @Override
    public void onDetach() {
        LogUtil.d(TAG, "onDetach() : ");
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDestroy() {
        LogUtil.v(TAG, "onDestroy() : ");
//        onUnsubscribe();
//        mCompositeSubscription = null;
        super.onDestroy();
    }
}
