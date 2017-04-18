package com.bupt.mountwutai.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bupt.mountwutai.util.LogUtil;

//以下为RxJava 2.x.y 版本
//import org.reactivestreams.Subscriber;
//import org.reactivestreams.Subscription;
//import io.reactivex.Observable;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.schedulers.Schedulers;




/**
 * Created by joycezhao on 16/10/21.
 */

public abstract class BaseActivity extends AppCompatActivity/*FragmentActivity*//*implements BaseApp.Activity*/ {

    public MyApplication application;
    public AppCompatActivity activity;
//    public FragmentActivity activity;
    protected String TAG;
    private Dialog progressDialog;
//    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = MyApplication.getMyApplication();
        activity = this;
//        AppManager.getAppManager().addActivity(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setLayout();
//        ButterKnife.bind(this);
//        init();
//        initArgs();
//        if (!TextUtils.isEmpty(getTopbarTitle())) {
//            TextView titleTv = (TextView) findViewById(R.id.top_name_tv);
//            titleTv.setText(getTopbarTitle());
//        }
//        if (isNeedInitBack()) {
//            ImageButton backBtn = (ImageButton) findViewById(R.id.top_back_btn);
//            backBtn.setVisibility(View.VISIBLE);
//            backBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    BaseActivity.this.finish();
//                    initBackBtn();
//                }
//            });
//        }

    }

    private void init() {
        TAG = getClass().getSimpleName();
//        progressDialog = DialogUtils.createProgressDialog(this);
    }
    protected void initArgs(){
    }

    abstract protected void setLayout();

    abstract protected boolean isNeedInitBack();

    abstract protected String getTopbarTitle();

    protected void initBackBtn() {

    }
    /**
     * 左侧有返回键的标题栏
     * <p>如果在此基础上还要加其他内容,比如右侧有文字按钮,可以获取该方法返回值继续设置其他内容
     *
     * @param title 标题
     */
//    protected TitleBuilder initBackTitle(String title) {
//        return new TitleBuilder(this)
//                .setTitleText(title)
//                //.setLeftImage(R.dr)
//                .setLeftOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        finish();
//                    }
//                });
//    }

    /**
     * 跳转页面,无extra简易型
     *
     * @param tarActivity 目标页面
     */
    public void intent2Activity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(this, tarActivity);
        startActivity(intent);
    }
//    public void intent2Activity(Class<? extends Activity> tarActivity,Bundle bundle){
//        Intent intent=new Intent(this,tarActivity);
//        intent.putExtra(CodeConstants.BUNDLE_KEY,bundle);
//        startActivity(intent);
//    }

    public void showToast(String msg) {
        application.showToast(msg);
    }

    public void showLog(String msg) {
        LogUtil.i(TAG, msg);
    }

    public void showProgressDialog() {
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }


//    public void addSubscription(Observable observable, Subscriber subscriber) {
//        if (mCompositeSubscription == null) {
//            mCompositeSubscription = new CompositeSubscription();
//        }
//
//        mCompositeSubscription.add(observable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber));
//    }
//
//    public void addSubscription(Subscription subscription) {
//        if (mCompositeSubscription == null) {
//            mCompositeSubscription = new CompositeSubscription();
//        }
//        mCompositeSubscription.add(subscription);
//    }
//
//    public void onUnsubscribe() {
//        showLog("onUnsubscribe is called");
//        //取消注册，以避免内存泄露
//        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions())
//            mCompositeSubscription.unsubscribe();
//    }

    @Override
    protected void onDestroy() {
//        onUnsubscribe();
        super.onDestroy();
    }
}
