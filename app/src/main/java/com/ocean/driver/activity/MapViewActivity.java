package com.ocean.driver.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.AmapPageType;
import com.ocean.driver.R;

/**
 * Created by James on 2020/11/7.
 * 地图
 */
public class MapViewActivity extends Activity {
    MapView mMapView;
    AMap aMap;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MapViewActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapview);
//        mMapView=(MapView)findViewById(R.id.mapview);
//        mMapView.onCreate(savedInstanceState);
//
//        if (aMap == null) {
//            aMap = mMapView.getMap();
//        }
        AmapNaviParams params = new AmapNaviParams(null, null, null, AmapNaviType.DRIVER, AmapPageType.ROUTE);
//启动导航组件
        AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), params, null);
    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
//        mMapView.onDestroy();
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
//        mMapView.onResume();
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
//        mMapView.onPause();
//    }
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
//        mMapView.onSaveInstanceState(outState);
//    }
}
