package com.ocean.driver.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ocean.driver.BuildConfig;
import com.ocean.driver.R;
import com.ocean.driver.activity.ClipImageActivity;
import com.ocean.driver.activity.SettingActivity;
import com.ocean.driver.activity.UpdateOnBoardStatusActivity;
import com.ocean.driver.activity.WebViewActivity;
import com.ocean.driver.api.BaseUrl;
import com.ocean.driver.api.HttpUtil;
import com.ocean.driver.dialog.CallServiceDialog;
import com.ocean.driver.entity.ApiResponse;
import com.ocean.driver.entity.UserInfo;
import com.ocean.driver.entity.VehicleStatus;
import com.ocean.driver.tools.GlideCircleTransform;
import com.ocean.driver.tools.PreferenceUtils;
import com.ocean.driver.tools.ToastUtil;
import com.ocean.driver.tools.Utils;
import com.ocean.driver.view.UpDateIconPop;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * Created by James on 2020/8/4.
 * 我的
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone_num)
    TextView tvPhoneNum;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.layout_tips)
    LinearLayout layoutTips;
    @BindView(R.id.iv_service)
    ImageView ivService;
    @BindView(R.id.layout_call_service)
    RelativeLayout layoutCallService;
    @BindView(R.id.iv_e9)
    ImageView ivE9;
    @BindView(R.id.layout_about_e9)
    RelativeLayout layoutAboutE9;
    @BindView(R.id.layout_center)
    LinearLayout layoutCenter;
    @BindView(R.id.iv_sett)
    ImageView ivSett;
    @BindView(R.id.layout_sett)
    RelativeLayout layoutSett;
    @BindView(R.id.view_Show_Pop)
    View viewShowPop;

    private String servicePhone;
    private UpDateIconPop upDateIconPop;

    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    private File tempFile;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViews() {
        getData();

    }

    private void getData() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().getUserInfo()).getUserInfo(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<ApiResponse<UserInfo>>() {
            @Override
            public void onResponse(Call<ApiResponse<UserInfo>> call, Response<ApiResponse<UserInfo>> response) {
                if (response.body().getCode() == 1) {
                    tvPhoneNum.setText(response.body().getData().getDriver().getPhone());
                    tvName.setText(response.body().getData().getDriver().getName());
                    Glide.with(getActivity()).load(response.body().getData().getDriver().getHeadimg()).bitmapTransform(new GlideCircleTransform(getActivity())).into(ivUserIcon);
                    tvTips.setText(response.body().getData().getNotice() + "");
                    servicePhone = response.body().getData().getService_phone();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }
            @Override
            public void onFailure(Call<ApiResponse<UserInfo>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取个人信息失败");
            }
        });
    }

    @Override
    protected void initDatas() {

    }


    @OnClick({R.id.iv_user_icon, R.id.layout_call_service, R.id.layout_about_e9, R.id.layout_sett})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_user_icon:
                upDateIconPop = new UpDateIconPop(getActivity(), itemsOnClick);
                upDateIconPop.showAtLocation(viewShowPop, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.layout_call_service:
                if (TextUtils.isEmpty(servicePhone)) {
                    ToastUtil.showToast("客服热线获取失败");
                    return;
                } else {
                    CallServiceDialog dialog = new CallServiceDialog(getActivity(), R.style.MyDialog, servicePhone);
                    dialog.show();
                }
                break;
            case R.id.layout_about_e9:
                WebViewActivity.actionStart(getActivity(), "关于E9", "https://www.baidu.com");
                break;
            case R.id.layout_sett:
                SettingActivity.actionStart(getActivity());
                break;
        }
    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_take_photo:                   //拍照取图
                    //权限判断
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        //申请WRITE_EXTERNAL_STORAGE权限
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                    } else {
                        //跳转到调用系统相机
                        gotoCamera();
                    }
                    break;
                case R.id.btn_chose:
                    // 3、调用从图库选取图片方法
                    //权限判断
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        //申请READ_EXTERNAL_STORAGE权限
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                READ_EXTERNAL_STORAGE_REQUEST_CODE);
                    } else {
                        //跳转到相册
                        gotoPhoto();
                    }
                    break;
            }
            upDateIconPop.dismiss();
        }
    };

    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        Log.d("evan", "*****************打开图库********************");
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }


    /**
     * 跳转到照相机
     */
    private void gotoCamera() {
        Log.d("evan", "*****************打开相机********************");
        //创建拍照存储的图片文件
        tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Log.e("参数", BuildConfig.APPLICATION_ID + ".fileProvider");
            Uri contentUri = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID + ".fileProvider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, REQUEST_CAPTURE);
    }

    /**
     * 检查文件是否存在
     */
    public static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    final String cropImagePath = Utils.getRealFilePathFromUri(getActivity(), uri);

                    File file = new File(cropImagePath);
                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/png"), file);
                    MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestFile);


                    HttpUtil.createRequest(TAG, BaseUrl.getInstence().changeHeadimg()).changeImage(PreferenceUtils.getInstance().getUserToken(), part).enqueue(new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            if (response.body() != null) {
                                if (response.body().getCode() == 1) {
                                    Glide.with(getActivity()).load(cropImagePath).bitmapTransform(new GlideCircleTransform(getActivity())).into(ivUserIcon);
                                    ToastUtil.showToast("头像修改成功");
                                } else {
                                    ToastUtil.showToast(response.body().getMsg());
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            ToastUtil.showToast("网络异常:上传失败");
                        }
                    });
                }
                break;
        }
    }


    /**
     * 打开截图界面
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(getActivity(), ClipImageActivity.class);
        intent.putExtra("type", 1);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }
}
