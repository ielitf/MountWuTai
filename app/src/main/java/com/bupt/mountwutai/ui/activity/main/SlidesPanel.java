package com.bupt.mountwutai.ui.activity.main;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BasePanel;
import com.bupt.mountwutai.entity.mian.SlidesBean;
import com.bupt.mountwutai.util.ToastUtil;
import com.bupt.mylibrary.utils.ViewUtils;

import java.util.List;

/**
 * 轮播图panel
 * Created by Wyf on 2017/5/10.
 */

public class SlidesPanel extends BasePanel {
    private ConvenientBanner convenientBanner;

    public SlidesPanel(Context context) {
        super(context);
        int slidesHeight = ViewUtils.getScreenHeight((Activity) context) -
                ViewUtils.getScreenWidth((Activity) context) -
                ViewUtils.getStatusBarHeight(context)//状态栏
                - 2 * ViewUtils.dpToPx(5);//RecyclerView GridView的上下padding
        convenientBanner = new ConvenientBanner(context);
        convenientBanner.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, slidesHeight));
        convenientBanner.setCanLoop(true);//设置循环滑动
//        convenientBanner.setManualPageable(false);//设置不能手动影响

        setContentView(convenientBanner);
    }

    /**
     * 设置轮播图展示数据
     *
     * @param infoList List<SlidesBean>
     */
    public void setData(List<SlidesBean> infoList) {
        if (infoList == null || infoList.isEmpty()) return;

        convenientBanner.setPages(new CBViewHolderCreator<SlideshowHolder>() {
                                      @Override
                                      public SlideshowHolder createHolder() {
                                          return new SlideshowHolder();
                                      }
                                  },
                infoList)
                .setPageIndicator(new int[]{R.mipmap.bg_slide_point_normal, R.mipmap.bg_slide_point_choose})//设置选中指示器
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);//设置指示器位置
    }

    /**
     * 轮播图开始循环滑动
     * 单位毫秒
     */
    public void startLoop(int time) {
        convenientBanner.startTurning(time);
    }

    /**
     * 轮播图暂停循环滑动
     */
    public void stopLoop() {
        convenientBanner.stopTurning();
    }

    /**
     * 轮播图item的holder
     */
    public class SlideshowHolder implements Holder<SlidesBean> {

        private ImageView img;
        private TextView txt;
        private View inflate;

        @Override
        public View createView(Context context) {
            inflate = LayoutInflater.from(context).inflate(R.layout.main_slides_item, null);
            img = (ImageView) inflate.findViewById(R.id.img);
            txt = (TextView) inflate.findViewById(R.id.txt);
            return inflate;
        }

        @Override
        public void UpdateUI(final Context context, final int position, final SlidesBean data) {
//            ImageLoader.loadCenterCrop(context, data.getImgUrl(), img);
            txt.setText(data.getDesc());
            inflate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.show(context,data.getDesc());
                }
            });
        }
    }
}
