package com.bupt.mountwutai.base;

import android.app.Activity;
import java.util.HashMap;
import java.util.Map;

public class CodeConstants {
    public static final int RETURN_SUCCESS_CODE = 0;
    // 是否是内网开发环境
    public static boolean isIntranet = false;
    //承德IP:http://118.192.133.65:8080  域名：http://www.cd96888.com:8080
//    public static final String URL_IP= isIntranet?"http://192.168.10.253":"http://www.cd96888.com";
//    public static final String URL_PORT="21580";
    public static final String URL_PREFIX=isIntranet ? "http://192.168.10.253:21580/":"http://www.cd96888.com/";
    public static final String URL_DOMIN = "cdappService/";
//    public static final String URL_RES_SUFFIX="cdapp/";
    public static final String URL_HOST = URL_PREFIX+URL_DOMIN;
    public static final String URL_PIC_PREFIX=URL_PREFIX;
    public static final String URL_HTML_PREFIX=URL_PIC_PREFIX;
    public static final String URL_RES=URL_PIC_PREFIX;

    public static final String IS_LAZY_LOAD = "is_lazy_load";
    public static final String TAB_INDEX = "tab_index";


    public final static Map<String, Class<? extends Activity>> intentMap = new HashMap<>();



}
