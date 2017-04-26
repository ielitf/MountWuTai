package com.bupt.mountwutai.ui.activity.guide;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.tts.auth.AuthInfo;
import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.customdata.DetailData;
import com.bupt.mountwutai.util.LogUtil;


public class DetailActivity extends BaseActivity implements View.OnClickListener, SpeechSynthesizerListener {

    private TextView titileTextView;//标题
    private ImageView showImageView;//详情页图片
    private TextView contentTextView;//显示内容

    private int id = 1;//0为天安门 1 为五爷庙，2为塔院寺，3为游客中心
    private SpeechSynthesizer mSpeechSynthesizer;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_detail);
    }

    @Override
    protected void initView() {
        id = getIntent().getIntExtra(CodeConstants.ID, 1);
        LogUtil.w(TAG, "id=" + id);
        titileTextView = (TextView) findViewById(R.id.detail_title);
        showImageView = (ImageView) findViewById(R.id.detail_imageview);
        contentTextView = (TextView) findViewById(R.id.detail_contenet);
        if (id != 0) {
            initTTS();
            showImageView.setBackgroundResource(DetailData.images[id - 1]);
            titileTextView.setText(DetailData.titles[id - 1]);
            contentTextView.setText(DetailData.contonts[id - 1]);
            findViewById(R.id.detail_play).setOnClickListener(this);
        }
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return "详情";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_play://播放按钮
                LogUtil.w(TAG, "播放的内容为：" + contentTextView.getText().toString().trim());
                mSpeechSynthesizer.stop();
                mSpeechSynthesizer.speak(contentTextView.getText().toString().trim());
                break;
        }
    }

    private void initTTS() {
        mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        mSpeechSynthesizer.setContext(this);
        mSpeechSynthesizer.setSpeechSynthesizerListener(this);

        mSpeechSynthesizer.setAppId("9572201");
        mSpeechSynthesizer.setApiKey("uobdSwHGkTtvTRZMXq1KUllo", "cb4b5fedd8298e75a6d673bf84af25d7");
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0");
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_HIGH_SPEED_NETWORK);
        AuthInfo authInfo = mSpeechSynthesizer.auth(TtsMode.MIX);
        if (authInfo.isSuccess()) {

        } else {
            String errorMsg = authInfo.getTtsError().getDetailMessage();
            LogUtil.e(TAG, "auth failed errorMsg=" + errorMsg);
        }
        mSpeechSynthesizer.initTts(TtsMode.MIX);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (id != 0)
            mSpeechSynthesizer.release();
    }

    @Override
    public void onSynthesizeStart(String s) {

    }

    /**
     * 合成数据和进度的回调接口，分多次回调
     *
     * @param utteranceId
     * @param data        合成的音频数据。该音频数据是采样率为16K，2字节精度，单声道的pcm数据。
     * @param progress    文本按字符划分的进度，比如:你好啊 进度是0-3
     */
    @Override
    public void onSynthesizeDataArrived(String utteranceId, byte[] data, int progress) {

    }

    /**
     * 合成正常结束，每句合成正常结束都会回调，如果过程中出错，则回调onError，不再回调此接口
     *
     * @param utteranceId
     */

    @Override
    public void onSynthesizeFinish(String utteranceId) {

    }

    /**
     * 播放开始，每句播放开始都会回调
     *
     * @param utteranceId
     */
    @Override
    public void onSpeechStart(String utteranceId) {

    }

    /**
     * 播放进度回调接口，分多次回调
     *
     * @param utteranceId
     * @param progress    文本按字符划分的进度，比如:你好啊 进度是0-3
     */
    @Override
    public void onSpeechProgressChanged(String utteranceId, int progress) {

    }

    /**
     * 播放正常结束，每句播放正常结束都会回调，如果过程中出错，则回调onError,不再回调此接口
     *
     * @param utteranceId
     */
    @Override
    public void onSpeechFinish(String utteranceId) {

    }


    /**
     * 当合成或者播放过程中出错时回调此接口
     *
     * @param utteranceId
     * @param speechError 包含错误码和错误信息
     */
    @Override
    public void onError(String utteranceId, SpeechError speechError) {
        LogUtil.e(TAG, "onError error=" + "(" + speechError.code + ")" + speechError.description + "--utteranceId=" + utteranceId);
    }

}
