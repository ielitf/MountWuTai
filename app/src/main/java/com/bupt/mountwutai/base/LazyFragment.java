package com.bupt.mountwutai.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

import com.bupt.chengde.R;
import com.bupt.chengde.control.CodeConstants;
import com.bupt.chengde.util.LogUtil;

/**
 * Created by joycezhao on 16/10/25.
 */

/**
 * <h1>懒加载Fragment</h1> 只有创建并显示的时候才会调用onCreateViewLazy方法<br>
 * <br>
 * <p/>
 * 懒加载的原理onCreateView的时候Fragment有可能没有显示出来。<br>
 * 但是调用到setUserVisibleHint(boolean isVisibleToUser),isVisibleToUser =
 * true的时候就说明有显示出来<br>
 * 但是要考虑onCreateView和setUserVisibleHint的先后问题所以才有了下面的代码
 * <p/>
 * 注意：<br>
 * 《1》原先的Fragment的回调方法名字后面要加个Lazy，比如Fragment的onCreateView方法， 就写成onCreateViewLazy <br>
 * 《2》使用该LazyFragment会导致多一层布局深度
 *
 * @author LuckyJayce
 */
public abstract class LazyFragment extends BaseFragment {
    public static final String INTENT_BOOLEAN_LAZYLOAD = "intent_boolean_lazyLoad";
    protected FrameLayout layout;
    private boolean isInit = false;//真正要显示的View是否已经被初始化（正常加载）
    private Bundle savedInstanceState;
    private boolean isLazyLoad = true;
    private boolean isStart = false;//是否处于可见状态，in the screen

    @Override
    protected final void onCreateView(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            isLazyLoad = bundle.getBoolean(CodeConstants.IS_LAZY_LOAD, isLazyLoad);
        }
        //判断是否懒加载
        layout = new FrameLayout(getApplicationContext());
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.fragment_lazy_loading, null);
        layout.addView(view);
        super.setContentView(layout);
        if (isLazyLoad) {
            //一旦isVisibleToUser==true即可对真正需要的显示内容进行加载
            if (getUserVisibleHint() && !isInit) {////说明：例如旅游的推荐模块，摄影的推荐模块的islazyLoad设为true时调用该分支
                this.savedInstanceState = savedInstanceState;
                onCreateViewLazy(savedInstanceState);
                isInit = true;
            }
        } else {
            //不需要懒加载，开门江山，调用onCreateViewLazy正常加载显示内容即可
            //说明：例如旅游的推荐模块，摄影的推荐模块的isLazyLoad设为false时调用该分支，其他非第一个加载的页面isLazyLoad为false时也调用该分支
            this.savedInstanceState = savedInstanceState;
            onCreateViewLazy(savedInstanceState);
            isInit = true;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.d(TAG, "tabIndex=" + getArguments().getInt(CodeConstants.TAB_INDEX) + "  setUserVisibleHint() called with: " + "isVisibleToUser = [" + isVisibleToUser + "]");
        //一旦isVisibleToUser==true即可进行对真正需要的显示内容的加载
        //可见，但还没被初始化
        if (isVisibleToUser && !isInit && getContentView() != null) {
            onCreateViewLazy(savedInstanceState);
            isInit = true;
            onResumeLazy();
        }
        //已经被初始化（正常加载）过了
        if (isInit && getContentView() != null) {
            if (isVisibleToUser) {
                isStart = true;
                onFragmentStartLazy();
            } else {
                isStart = false;
                onFragmentStopLazy();
            }
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        //判断若isLazyLoad==true,移除所有lazy view，加载真正要显示的view
        LogUtil.v(TAG, "setContentView(int layoutResID) is called");
        if (isLazyLoad && getContentView() != null && getContentView().getParent() != null) {
            layout.removeAllViews();
            View view = inflater.inflate(layoutResID, layout, false);
            layout.addView(view);
        }
        //否则，开门见山，直接加载
        else {
            super.setContentView(layoutResID);
        }
    }

    @Override
    public void setContentView(View view) {
        //判断若isLazyLoad==true,移除所有lazy view，加载真正要显示的view
        if (isLazyLoad && getContentView() != null && getContentView().getParent() != null) {
            layout.removeAllViews();
            layout.addView(view);
        }
        //否则，开门见山，直接加载
        else {
            super.setContentView(view);
        }
    }

    @Deprecated
    @Override
    public final void onStart() {
        LogUtil.d(TAG, "tabIndex=" + getArguments().getInt(CodeConstants.TAB_INDEX) + "的onStart() : " + "getUserVisibleHint():" + getUserVisibleHint());
        super.onStart();
        if (isInit && !isStart && getUserVisibleHint()) {
            isStart = true;
            onFragmentStartLazy();
        }
    }

    @Deprecated
    @Override
    public final void onStop() {
        super.onStop();
        LogUtil.d(TAG, "tabIndex=" + getArguments().getInt(CodeConstants.TAB_INDEX) + "的onStop() called: " + "getUserVisibleHint():" + getUserVisibleHint());
        if (isInit && isStart && getUserVisibleHint()) {
            isStart = false;
            onFragmentStopLazy();
        }
    }

    //当Fragment被滑到可见的位置时，调用
    protected void onFragmentStartLazy() {
        LogUtil.d(TAG, "tabIndex=" + getArguments().getInt(CodeConstants.TAB_INDEX) + "的onFragmentStartLazy() called with: " + "");
    }

    //当Fragment被滑到不可见的位置，offScreen时，调用
    protected void onFragmentStopLazy() {
        LogUtil.d(TAG, "tabIndex=" + getArguments().getInt(CodeConstants.TAB_INDEX) + "的onFragmentStopLazy() called with: " + "");
    }

    protected void onCreateViewLazy(Bundle savedInstanceState) {
        LogUtil.d(TAG, "tabIndex=" + getArguments().getInt(CodeConstants.TAB_INDEX) + "的onCreateViewLazy() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
    }

    protected void onResumeLazy() {
        LogUtil.d(TAG, "tabIndex=" + getArguments().getInt(CodeConstants.TAB_INDEX) + "的onResumeLazy() called with: " + "");
    }

    protected void onPauseLazy() {
        LogUtil.d(TAG, "tabIndex=" + getArguments().getInt(CodeConstants.TAB_INDEX) + "的onPauseLazy() called with: " + "");
    }

    protected void onDestroyViewLazy() {
        LogUtil.d(TAG, "tabIndex=" + getArguments().getInt(CodeConstants.TAB_INDEX) + "onDestroyViewLazy() : " + "getUserVisibleHint():" + getUserVisibleHint());
    }

    @Override
    public void onResume() {
        LogUtil.d(TAG, "tabIndex=" + getArguments().getInt(CodeConstants.TAB_INDEX) + "的onResume() : " + "getUserVisibleHint():" + getUserVisibleHint());
        super.onResume();
        if (isInit) {
            onResumeLazy();
        }
    }

    @Override
    public void onPause() {
        LogUtil.d(TAG, "tabIndex=" + getArguments().getInt(CodeConstants.TAB_INDEX) + "的onPause() : " + "getUserVisibleHint():" + getUserVisibleHint());
        super.onPause();
        if (isInit) {
            onPauseLazy();
        }
    }

    @Override
    public void onDestroyView() {
        LogUtil.d(TAG, "tabIndex=" + getArguments().getInt(CodeConstants.TAB_INDEX) + "的onDestroyView() : " + "getUserVisibleHint():" + getUserVisibleHint());
        super.onDestroyView();
        if (isInit) {
            onDestroyViewLazy();
        }
        isInit = false;

    }

    protected void showChild(int index) {
        int childCount = ((ViewGroup) getContentView()).getChildCount();
        if (getContentView() != null && childCount > 1) {
            for (int i = 0; i < childCount; i++) {
                if (i == index) {
                    ((ViewGroup) getContentView()).getChildAt(i).setVisibility(View.VISIBLE);
                } else {
                    ((ViewGroup) getContentView()).getChildAt(i).setVisibility(View.GONE);
                }
            }
        }
    }

}