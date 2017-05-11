package com.bupt.mountwutai.ui.activity.guide;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.fence.GeoFence;
import com.amap.api.fence.GeoFenceClient;
import com.amap.api.fence.GeoFenceListener;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.DPoint;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RidePath;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseFragment;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.util.AMapUtil;
import com.bupt.mountwutai.util.LogUtil;
import com.bupt.mountwutai.util.PermissionHelper;
import com.bupt.mountwutai.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import overlay.DrivingRouteOverlay;
import overlay.RideRouteOverlay;
import overlay.RouteOverlay;
import overlay.WalkRouteOverlay;

/**
 * 行程规划
 */

public class TravelPlanFragment extends BaseFragment implements View.OnClickListener, AMap.OnMapLoadedListener, GeoFenceListener, AMapLocationListener, AMap.OnMapClickListener,
        AMap.OnMarkerClickListener, AMap.InfoWindowAdapter, RouteSearch.OnRouteSearchListener /*,AMap.OnMyLocationChangeListener,AMap.OnCameraChangeListener,  AMap.OnInfoWindowClickListener*/ {
    //设置希望侦测的围栏触发行为，默认只侦测用户进入围栏的行为
    static final String GEOFENCE_BROADCAST_ACTION = "com.bupt.mountwutai.geofence.keyword";
    private final int RC_CRO_LOCA_PER = 0x0020;
    private final int RC_FINE_LOCA_PER = 0x0021;
    private final int RC_READ_PHONE_STATE_PER = 0x0022;
    private final int RC_READ_EXTERNAL_STORAGE_PER = 0x23;
    private final int ROUTE_TYPE_WALK = 1;
    private final int ROUTE_TYPE_DRIVE = 2;
    private final int ROUTE_TYPE_RIDE = 3;


    //UI相关
    private LinearLayout mRouteMapChooseLayout;
    private RelativeLayout mBottomLayout;
    private TextView mRotueTimeDes, mCancelRouteGuide;
    private ImageView mBus;
    private ImageView mDrive;
    private ImageView mWalk;
    private ImageView mRide;

    private MapView mapView;
    //地图相关
    private AMap aMap;
    //地图定位相关
    private MyLocationStyle myLocationStyle;
    private LatLng myLatLan;
    private AMapLocationClient mLocationClient = null;
    private AMapLocationClientOption mLocationClientOption = null;
    //路线搜索相关
    private RouteSearch mRouteSearch;
    private DriveRouteResult mDriveRouteResult;
    private WalkRouteResult mWalkRouteResult;
    private RideRouteResult mRideRouteResult;
    private LatLonPoint mStartPoint = null;//= new LatLonPoint(39.942295, 116.335891);//起点，116.335891,39.942295
    private LatLonPoint mEndPoint = null;//= new LatLonPoint(39.995576, 116.481288);//终点，116.481288,39.995576
    //    private LatLng testLatLng = new LatLng(39.007896372911915, 113.59743118286133);//五爷庙
    private RouteOverlay mCurrentOverlay;
    //marker标记相关
    private MarkerOptions markerOption;
    private MarkerOptions markerOption1;
    private MarkerOptions markerOption2;
    private MarkerOptions markerOption3;
    private Marker mCurrentMarker;
    private String mCurrentCityName = "北京";
    //围栏相关
    private Object lock = new Object();
    private GeoFenceClient mGeoFenceClient;
    private List<GeoFence> fenceList = new ArrayList<>();
    // 记录已经添加成功的围栏
    private HashMap<String, GeoFence> fenceMap = new HashMap<String, GeoFence>();
    // 当前的坐标点集合，主要用于进行地图的可视区域的缩放
    private LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
    private LatLng fenceLatLng;
    private int currentId = 1;
    private boolean isFirstLocate = true;
    private boolean isNavigating = false;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    StringBuffer sb = new StringBuffer();
                    sb.append("添加围栏成功");
                    String customId = (String) msg.obj;
                    if (!TextUtils.isEmpty(customId)) {
                        sb.append("customId: ").append(customId);
                    }
//                    ToastUtil.show(getApplicationContext(), sb.toString());
//                    drawFence2Map();
                    break;
                case 1:
                    int errorCode = msg.arg1;
                    ToastUtil.show(getApplicationContext(),
                            "添加围栏失败 " + errorCode);
                    break;
                case 2:
                    String statusStr = (String) msg.obj;
//                    tvResult.setVisibility(View.VISIBLE);
//                    tvResult.append(statusStr + "\n");
                    showToast(statusStr);
                    break;
                default:
                    break;
            }
        }
    };
    /**
     * 接收触发围栏后的广播,当添加围栏成功之后，会立即对所有围栏状态进行一次侦测，如果当前状态与用户设置的触发行为相符将会立即触发一次围栏广播；
     * 只有当触发围栏之后才会收到广播,对于同一触发行为只会发送一次广播不会重复发送，除非位置和围栏的关系再次发生了改变。
     */
    private BroadcastReceiver mGeoFenceReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // 接收广播
            if (intent.getAction().equals(GEOFENCE_BROADCAST_ACTION)) {
                Bundle bundle = intent.getExtras();
                String customId = bundle
                        .getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                String fenceId = bundle.getString(GeoFence.BUNDLE_KEY_FENCEID);
                //status标识的是当前的围栏状态，不是围栏行为
                int status = bundle.getInt(GeoFence.BUNDLE_KEY_FENCESTATUS);
                StringBuffer sb = new StringBuffer();
                switch (status) {
                    case GeoFence.STATUS_LOCFAIL:
                        sb.append("定位失败");
                        break;
                    case GeoFence.STATUS_IN:
                        sb.append("您已进入景区 ");
                        break;
                    case GeoFence.STATUS_OUT:
                        sb.append("您已离开景区 ");
                        break;
                    case GeoFence.STATUS_STAYED:
                        sb.append("您现在正在景区内");
                        break;
                    default:
                        break;
                }
                if (status != GeoFence.STATUS_LOCFAIL) {
                    if (!TextUtils.isEmpty(customId)) {
                        sb.append(" customId: " + customId);
                    }
                    sb.append(" fenceId: " + fenceId);
                }
                String str = sb.toString();
                Message msg = Message.obtain();
                msg.obj = str;
                msg.what = 2;
                handler.sendMessage(msg);
            }
        }
    };

    public static TravelPlanFragment newInstance() {
        Bundle args = new Bundle();
        TravelPlanFragment fragment = new TravelPlanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_travel_plan);
        initViews();
        mapView = (MapView) contentView.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        initMap();
    }

    private void initViews() {
        mRouteMapChooseLayout = (LinearLayout) contentView.findViewById(R.id.routemap_choose);
        mBottomLayout = (RelativeLayout) contentView.findViewById(R.id.bottom_layout);
        mRotueTimeDes = (TextView) contentView.findViewById(R.id.firstline);
        mCancelRouteGuide = (TextView) contentView.findViewById(R.id.cancel_route_guide);
        mDrive = (ImageView) contentView.findViewById(R.id.route_drive);
        mBus = (ImageView) contentView.findViewById(R.id.route_bus);
        mRide = (ImageView) contentView.findViewById(R.id.route_ride);
        mWalk = (ImageView) contentView.findViewById(R.id.route_walk);
    }

    @Override
    public void onMapLoaded() {
//        setUpMap();
        showToast("onMapLoade is called");
        addMarkersToMap();
        addKeywordFence();
    }

    /**
     * 注册监听
     */


    private void initMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
            if (!PermissionHelper.getHelper().checkPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                PermissionHelper.getHelper().getPermission(this, Manifest.permission.ACCESS_FINE_LOCATION, RC_FINE_LOCA_PER);
                return;
            }
            if (!PermissionHelper.getHelper().checkPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                PermissionHelper.getHelper().getPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, RC_READ_EXTERNAL_STORAGE_PER);
                return;
            }

            if (!PermissionHelper.getHelper().checkPermission(activity, Manifest.permission.READ_PHONE_STATE)) {
                PermissionHelper.getHelper().getPermission(this, Manifest.permission.READ_PHONE_STATE, RC_READ_PHONE_STATE_PER);
                return;
            }
            setUpMap();
        }
        LogUtil.e(TAG, "readPhoneStatePermission=" + PermissionHelper.getHelper().checkPermission(getActivity(), Manifest.permission.READ_PHONE_STATE));
        LogUtil.i(TAG, "readExternalStoragePermission=" + PermissionHelper.getHelper().checkPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE));
        LogUtil.d(TAG, "accessCoarseLocationPermission=" + PermissionHelper.getHelper().checkPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION));
    }

    private void setUpMap() {

        mRouteSearch = new RouteSearch(activity);
        mRouteSearch.setRouteSearchListener(this);
        registerListener();
        mLocationClient = new AMapLocationClient(activity);
        mLocationClientOption = new AMapLocationClientOption();
        mLocationClient.setLocationListener(this);
        mLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationClientOption.setInterval(1000);
        mLocationClientOption.setSensorEnable(true);
        mLocationClient.setLocationOption(mLocationClientOption);
        mLocationClient.startLocation();

        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.getUiSettings().setCompassEnabled(true);

        myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationEnabled(true);

    }

    private void registerListener() {

        mBus.setOnClickListener(this);
        mDrive.setOnClickListener(this);
        mRide.setOnClickListener(this);
        mWalk.setOnClickListener(this);
        mCancelRouteGuide.setOnClickListener(this);
        aMap.setOnMapLoadedListener(this);
        aMap.setOnMapClickListener(this);
        aMap.setOnMarkerClickListener(this);
        aMap.setInfoWindowAdapter(this);
//        aMap.setOnMyLocationChangeListener(this);
//        aMap.setOnCameraChangeListener(this);
//        aMap.setOnInfoWindowClickListener(this);
//        aMap.setOnPOIClickListener(this);
    }

    private void resetLocationStyle() {
        aMap.setMyLocationStyle(myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW));
    }

    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap() {
        markerOption = new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .position(new LatLng(39.908686950005766, 116.39747500419617))
                .title("天安门")
                .snippet("到天安门去")
                .draggable(false);
        markerOption1 = new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .position(new LatLng(39.00791304679197, 113.59742581844331))
                .title("五爷庙")
                .snippet("五爷庙塔详情")
                .draggable(false);
        markerOption2 = new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .position(new LatLng(39.007654601209516, 113.595929145813))
                .title("塔院寺")
                .snippet("塔院寺详情")
                .draggable(false);
        markerOption3 = new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .position(new LatLng(38.90318205210213, 113.64955186843873))
                .title("游客中心")
                .snippet("游客中心")
                .draggable(false);
        aMap.addMarker(markerOption);
        aMap.addMarker(markerOption1);
        aMap.addMarker(markerOption2);
        aMap.addMarker(markerOption3);
    }

    private void addKeywordFence() {
        mGeoFenceClient = new GeoFenceClient(getApplicationContext());
        DPoint dPoint = new DPoint(39.95489800347222, 116.36204915364583);

        IntentFilter filter = new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(GEOFENCE_BROADCAST_ACTION);
        activity.registerReceiver(mGeoFenceReceiver, filter);
        /**
         * 创建pendingIntent
         */

        mGeoFenceClient.createPendingIntent(GEOFENCE_BROADCAST_ACTION);
        mGeoFenceClient.setActivateAction(GeoFenceClient.GEOFENCE_IN | GeoFenceClient.GEOFENCE_OUT);
        mGeoFenceClient.setGeoFenceListener(this);
//        mGeoFenceClient.addGeoFence("庆亚大厦","写字楼","北京",2,"庆亚大厦北邮国安");
//        mGeoFenceClient.addGeoFence("庆亚大厦", "写字楼", dPoint,50,1,"庆亚大厦北邮国安");
        mGeoFenceClient.addGeoFence("五台山风景名胜区", "风景名胜", "五台县", 1, "五台山风景区");
//        mGeoFenceClient.addGeoFence(dPoint, 50, "庆亚大厦北邮国安");
//        mGeoFenceClient.addGeoFence("庆亚大厦","00FDTW103（庆亚大厦北邮国安）");
//        mGeoFenceClient.addGeoFence("五台山风景名胜区","五台山风景区");
    }

    void drawFence2Map() {
        new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (lock) {
                        if (null == fenceList || fenceList.isEmpty()) {
                            return;
                        }
                        for (GeoFence fence : fenceList) {
                            if (fenceMap.containsKey(fence.getFenceId())) {
                                continue;
                            }
                            drawFence(fence);
                            fenceMap.put(fence.getFenceId(), fence);
                        }
                    }
                } catch (Throwable e) {

                }
            }
        }.start();
    }

    private void drawFence(GeoFence fence) {
        switch (fence.getType()) {
            case GeoFence.TYPE_ROUND:
            case GeoFence.TYPE_AMAPPOI:
                drawCircle(fence);
                break;
            case GeoFence.TYPE_POLYGON:
                drawPolygon(fence);
            case GeoFence.TYPE_DISTRICT:
                drawPolygon(fence);
                break;
            default:
                break;
        }

        // 设置所有maker显示在当前可视区域地图中
//        LatLngBounds bounds = boundsBuilder.build();
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(fenceLatLng));
    }

    private void drawCircle(GeoFence fence) {
        LatLng center = new LatLng(fence.getCenter().getLatitude(),
                fence.getCenter().getLongitude());
        fenceLatLng = center;
        LogUtil.e(TAG, "drawCircle fence.getCenter().getLatitude()=" + fence.getCenter().getLatitude() + "....fence.getCenter().getLongitude()=" + fence.getCenter().getLongitude());
        // 绘制一个圆形
        LogUtil.e(TAG, "围栏半径为：" + fence.getRadius());
        aMap.addCircle(new CircleOptions().center(center)
                .radius(fence.getRadius()).strokeColor(Color.argb(180, 63, 145, 252))
                .fillColor(Color.argb(163, 118, 212, 243)).strokeWidth(5F));
        boundsBuilder.include(center);
    }

    private void drawPolygon(GeoFence fence) {
        final List<List<DPoint>> pointList = fence.getPointList();
        if (null == pointList || pointList.isEmpty()) {
            return;
        }
        for (List<DPoint> subList : pointList) {
            List<LatLng> lst = new ArrayList<>();

            PolygonOptions polygonOption = new PolygonOptions();
            for (DPoint point : subList) {
                lst.add(new LatLng(point.getLatitude(), point.getLongitude()));
                boundsBuilder.include(
                        new LatLng(point.getLatitude(), point.getLongitude()));
            }
            polygonOption.addAll(lst);

            polygonOption.strokeColor(Color.argb(180, 63, 145, 252)).strokeWidth(5F)
                    .fillColor(Color.argb(163, 118, 212, 243));
            aMap.addPolygon(polygonOption);
        }
    }

    private void changeCamera(CameraUpdate update) {
//        LogUtil.i(TAG, "changeCamera is called");
        aMap.moveCamera(update);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        LogUtil.i("onLocationChanged", "onLocationChanged is called aMapLocation.getLatitude=" + aMapLocation.getLatitude() + ".......aMapLocation.getLongitude()=" + aMapLocation.getLongitude());
        myLatLan = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
        if (isFirstLocate) {
            changeCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(myLatLan, 13, 0, 0)));
            isFirstLocate = false;
        }
        mStartPoint = new LatLonPoint(myLatLan.latitude, myLatLan.longitude);
        if (isNavigating) {
            aMap.setMyLocationStyle(myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE));
        } else {
            resetLocationStyle();
        }
    }

//    @Override
//    public void onMyLocationChange(Location location) {
//        if (location != null) {
//            Bundle bundle = location.getExtras();
//            if (bundle != null) {
//                int errorCode = bundle.getInt(MyLocationStyle.ERROR_CODE);
//                String errorInfo = bundle.getString(MyLocationStyle.ERROR_INFO);
//                // 定位类型，可能为GPS WIFI等，具体可以参考官网的定位SDK介绍
//                int locationType = bundle.getInt(MyLocationStyle.LOCATION_TYPE);
//                if (errorCode == 0) {
//                    LogUtil.i("onMyLocationChange", "定位成功");
//                    if (isFirstLocate) {
//                        myLatLan = new LatLng(location.getLatitude(), location.getLongitude());
//                        mStartPoint = new LatLonPoint(myLatLan.latitude, myLatLan.longitude);
//                        changeCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(myLatLan, 18, 0, 0)));
//                        isFirstLocate = false;
//                    }
//                }
//                LogUtil.e("onMyLocationChange", "定位信息， code: " + errorCode + " errorInfo: " + errorInfo + " locationType: " + locationType);
//            } else {
//                LogUtil.e("onMyLocationChange", "定位信息， bundle is null ");
//            }
//
//        } else {
//            LogUtil.e("amap", "定位失败");
//        }
//    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(final Marker marker) {
        View infoWindow = activity.getLayoutInflater().inflate(R.layout.custom_info_window, null);
        LinearLayout goLayout = (LinearLayout) infoWindow.findViewById(R.id.go_ll);
        LinearLayout detailLayout = (LinearLayout) infoWindow.findViewById(R.id.detail_ll);
        goLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marker.hideInfoWindow();
                if (myLatLan != null) {
                    mStartPoint = new LatLonPoint(myLatLan.latitude, myLatLan.longitude);
                    mEndPoint = new LatLonPoint(marker.getPosition().latitude, marker.getPosition().longitude);
                    mRouteMapChooseLayout.setVisibility(View.VISIBLE);
                    onWalkClick();
                } else {
                    ToastUtil.show(activity, "定位失败，无法制定路线,请重新定位");
                }
            }
        });
        detailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marker.hideInfoWindow();
                Intent intent = new Intent();
                intent.setClass(activity, DetailActivity.class);
                intent.putExtra(CodeConstants.ID, currentId);
                startActivity(intent);

            }
        });
        return infoWindow;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (mCurrentMarker != null)
            mCurrentMarker.hideInfoWindow();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        mCurrentMarker = marker;
//        ToastUtil.show(activity, "onMarkerClick markerId=" + mCurrentMarker.getId());
//        ToastUtil.show(activity, "onMarkerClick markerTitle=" + marker.getTitle());
        if (marker.getTitle().equals("五爷庙")) {
            currentId = 1;
        } else if (marker.getTitle().equals("塔院寺")) {
            currentId = 2;
        } else if (marker.getTitle().equals("游客中心")) {
            currentId = 3;
        } else {
            currentId = 0;
        }
        return false;
    }

    /**
     * 步行路线搜索
     */
    public void onWalkClick() {
        searchRouteResult(ROUTE_TYPE_WALK, RouteSearch.WalkDefault);
        mWalk.setImageResource(R.mipmap.route_walk_select);
        mDrive.setImageResource(R.mipmap.route_drive_normal);
        mRide.setImageResource(R.mipmap.route_ride_normal);
        mapView.setVisibility(View.VISIBLE);
    }

    /**
     * 驾车路线搜索
     */

    public void onDriveClick() {
        searchRouteResult(ROUTE_TYPE_DRIVE, RouteSearch.DrivingDefault);
        mWalk.setImageResource(R.mipmap.route_walk_normal);
        mDrive.setImageResource(R.mipmap.route_drive_select);
        mRide.setImageResource(R.mipmap.route_ride_normal);
        mapView.setVisibility(View.VISIBLE);
    }

    /**
     * 骑行路线搜索
     */
    public void onRideClick() {
        searchRouteResult(ROUTE_TYPE_RIDE, RouteSearch.RIDING_DEFAULT);
        mWalk.setImageResource(R.mipmap.route_walk_normal);
        mDrive.setImageResource(R.mipmap.route_drive_normal);
        mRide.setImageResource(R.mipmap.route_ride_select);
        mapView.setVisibility(View.VISIBLE);
    }

    /**
     * 开始搜索路径规划方案
     */
    public void searchRouteResult(int routeType, int mode) {
        if (mStartPoint == null) {
            ToastUtil.show(mContext, "起点未设置");
            return;
        }

        if (mEndPoint == null) {
            ToastUtil.show(mContext, "终点未设置");
            return;
        }
        showProgressDialog();
        if (mCurrentOverlay != null)
            mCurrentOverlay.removeFromMap();
        final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
                mStartPoint, mEndPoint);
        if (routeType == ROUTE_TYPE_WALK) {// 步行路径规划
            RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo, mode);
            mRouteSearch.calculateWalkRouteAsyn(query);// 异步路径规划步行模式查询
        } else if (routeType == ROUTE_TYPE_DRIVE) {// 驾车路径规划
            RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, mode, null,
                    null, "");// 第一个参数表示路径规划的起点和终点，第二个参数表示驾车模式，第三个参数表示途经点，第四个参数表示避让区域，第五个参数表示避让道路
            mRouteSearch.calculateDriveRouteAsyn(query);// 异步路径规划驾车模式查询
        } else if (routeType == ROUTE_TYPE_RIDE) {
            RouteSearch.RideRouteQuery query = new RouteSearch.RideRouteQuery(fromAndTo, mode);
            mRouteSearch.calculateRideRouteAsyn(query);
        }
    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult result, int errorCode) {
        dismissProgressDialog();
        mBottomLayout.setVisibility(View.VISIBLE);
        mRotueTimeDes.setText(R.string.no_result);
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {
                    isNavigating = true;
                    aMap.setMyLocationStyle(myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE));
                    mWalkRouteResult = result;
                    final WalkPath walkPath = mWalkRouteResult.getPaths()
                            .get(0);
                    WalkRouteOverlay walkRouteOverlay = new WalkRouteOverlay(
                            this.getActivity(), aMap, walkPath,
                            mWalkRouteResult.getStartPos(),
                            mWalkRouteResult.getTargetPos());
                    walkRouteOverlay.removeFromMap();
                    walkRouteOverlay.addToMap();
                    walkRouteOverlay.zoomToSpan();

                    int dis = (int) walkPath.getDistance();
                    int dur = (int) walkPath.getDuration();
                    String des = AMapUtil.getFriendlyTime(dur) + "(" + AMapUtil.getFriendlyLength(dis) + ")";
                    mRotueTimeDes.setText(des);
                    mCurrentOverlay = walkRouteOverlay;
                } else if (result != null && result.getPaths() == null) {
                    ToastUtil.show(mContext, R.string.no_result + " path.size=" + result.getPaths().size());
                }

            } else {
                ToastUtil.show(mContext, R.string.no_result);
            }
        } else {
            ToastUtil.showerror(this.getApplicationContext(), errorCode);
        }
    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult result, int errorCode) {
        dismissProgressDialog();
        mBottomLayout.setVisibility(View.VISIBLE);
        mRotueTimeDes.setText(R.string.no_result);
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {
                    isNavigating = true;
                    aMap.setMyLocationStyle(myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE));
                    mDriveRouteResult = result;
                    final DrivePath drivePath = mDriveRouteResult.getPaths()
                            .get(0);
                    DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(
                            activity, aMap, drivePath,
                            mDriveRouteResult.getStartPos(),
                            mDriveRouteResult.getTargetPos(), null);
                    drivingRouteOverlay.setNodeIconVisibility(false);//设置节点marker是否显示
                    drivingRouteOverlay.setIsColorfulline(true);//是否用颜色展示交通拥堵情况，默认true
                    drivingRouteOverlay.removeFromMap();
                    drivingRouteOverlay.addToMap();
                    drivingRouteOverlay.zoomToSpan();

                    mCurrentOverlay = drivingRouteOverlay;
                    int dis = (int) drivePath.getDistance();
                    int dur = (int) drivePath.getDuration();
                    String des = AMapUtil.getFriendlyTime(dur) + "(" + AMapUtil.getFriendlyLength(dis) + ")";
                    mRotueTimeDes.setText(des);
//                    mRouteDetailDes.setVisibility(View.VISIBLE);
                } else if (result != null && result.getPaths() == null) {
                    ToastUtil.show(activity, R.string.no_result);
                }

            } else {
                ToastUtil.show(activity, R.string.no_result);
            }
        } else {
            ToastUtil.showerror(this.getApplicationContext(), errorCode);
        }

    }

    @Override
    public void onRideRouteSearched(RideRouteResult result, int errorCode) {
        dismissProgressDialog();
        mBottomLayout.setVisibility(View.VISIBLE);
        mRotueTimeDes.setText(R.string.no_result);
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {
                    isNavigating = true;
                    aMap.setMyLocationStyle(myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE));
                    mRideRouteResult = result;
                    final RidePath ridePath = mRideRouteResult.getPaths()
                            .get(0);
                    RideRouteOverlay rideRouteOverlay = new RideRouteOverlay(
                            activity, aMap, ridePath,
                            mRideRouteResult.getStartPos(),
                            mRideRouteResult.getTargetPos());
                    rideRouteOverlay.removeFromMap();
                    rideRouteOverlay.addToMap();
                    rideRouteOverlay.zoomToSpan();

                    mCurrentOverlay = rideRouteOverlay;
                    int dis = (int) ridePath.getDistance();
                    int dur = (int) ridePath.getDuration();
                    String des = AMapUtil.getFriendlyTime(dur) + "(" + AMapUtil.getFriendlyLength(dis) + ")";
                    mRotueTimeDes.setText(des);
                } else if (result != null && result.getPaths() == null) {
                    ToastUtil.show(activity, R.string.no_result);

                }
            } else {
                ToastUtil.show(activity, R.string.no_result);
            }
        } else {
            ToastUtil.showerror(this.getApplicationContext(), errorCode);
//            ToastUtil.show(activity, R.string.no_result);
        }
    }

    @Override
    public void onBusRouteSearched(BusRouteResult result, int errorCode) {
    }


    @Override
    protected boolean hasPopWindow() {
        return false;
    }

    @Override
    protected boolean isNeedInitBack() {
        return false;
    }

    @Override
    protected String getTopbarTitle() {
        return null;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.route_walk:
                onWalkClick();
                break;
            case R.id.route_drive:
                onDriveClick();
                break;
            case R.id.route_ride:
                onRideClick();
                break;
            case R.id.cancel_route_guide:
                resetMapView();
            default:
                break;
        }
    }

    private void resetMapView() {
        aMap.clear(true);
        isNavigating = false;
        mBottomLayout.setVisibility(View.GONE);
        mRouteMapChooseLayout.setVisibility(View.GONE);
        if (null != mCurrentOverlay)
            mCurrentOverlay.removeFromMap();
        addMarkersToMap();
        resetLocationStyle();
    }

    @Override
    public void onGeoFenceCreateFinished(List<GeoFence> geoFenceList, int errorCode, String customId) {
        Message msg = Message.obtain();
        if (errorCode == GeoFence.ADDGEOFENCE_SUCCESS) {
            fenceList = geoFenceList;
            msg.obj = customId;
            msg.what = 0;
        } else {
            msg.arg1 = errorCode;
            msg.what = 1;
        }
        LogUtil.i("onGeoFenceCreateFinished", "geoFenceList.size()=" + geoFenceList.size() + "....geoFnecelist.get(0).getradius=" + geoFenceList.get(0).getRadius());
        handler.sendMessage(msg);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RC_FINE_LOCA_PER) {

            if (!PermissionHelper.getHelper().checkPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                PermissionHelper.getHelper().getPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, RC_READ_EXTERNAL_STORAGE_PER);
            } else {

                if (!PermissionHelper.getHelper().checkPermission(activity, Manifest.permission.READ_PHONE_STATE)) {
                    PermissionHelper.getHelper().getPermission(this, Manifest.permission.READ_PHONE_STATE, RC_READ_PHONE_STATE_PER);
                } else {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                        setUpMap();
                }
            }

        } else if (requestCode == RC_READ_EXTERNAL_STORAGE_PER) {
            if (!PermissionHelper.getHelper().checkPermission(activity, Manifest.permission.READ_PHONE_STATE)) {
                PermissionHelper.getHelper().getPermission(this, Manifest.permission.READ_PHONE_STATE, RC_READ_PHONE_STATE_PER);
            } else {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    setUpMap();
            }
        } else if (requestCode == RC_READ_PHONE_STATE_PER) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                setUpMap();

        }
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        try {
            if (null != mGeoFenceReceiver)
                activity.unregisterReceiver(mGeoFenceReceiver);
        } catch (Throwable e) {
        }

        if (null != mGeoFenceClient) {
            mGeoFenceClient.removeGeoFence();
        }
        if (null != mLocationClient) {
            mLocationClient.onDestroy();
        }

    }


}

