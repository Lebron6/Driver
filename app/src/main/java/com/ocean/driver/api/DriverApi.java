package com.ocean.driver.api;


import com.ocean.driver.entity.ApiResponse;
import com.ocean.driver.entity.BillData;
import com.ocean.driver.entity.BillDetailsData;
import com.ocean.driver.entity.CountingInfo;
import com.ocean.driver.entity.HandoverListData;
import com.ocean.driver.entity.LoginResult;
import com.ocean.driver.entity.OnWay;
import com.ocean.driver.entity.OperaDetails;
import com.ocean.driver.entity.OperaGoodsListData;
import com.ocean.driver.entity.OperaListData;
import com.ocean.driver.entity.OperaSetoutList;
import com.ocean.driver.entity.QuotationData;
import com.ocean.driver.entity.SettingResult;
import com.ocean.driver.entity.UserInfo;
import com.ocean.driver.entity.VehicleStatus;
import com.ocean.driver.entity.Version;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by James on 2018/1/4.
 */

public interface DriverApi {

    String Content_Type = "translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=";

    /**
     * 密码登录
     * @param phone
     * @param password
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<LoginResult>> userLogin(@Field("phone") String phone, @Field("password") String password);

    /**
     * 验证码登录/注册
     *
     * @param phone
     * @param code
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<LoginResult>> codeLogin(@Field("phone") String phone, @Field("code") String code);

    /**
     * 发送验证码
     *
     * @param phone
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> sendSMS(@Field("phone") String phone);

    /**
     * 忘记密码
     *
     * @param phone
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> passwordForget(@Field("phone") String phone, @Field("code") String code, @Field("password") String password);

    /**
     * 获取个人信息
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<UserInfo>> getUserInfo(@Header("token") String token);


    /**
     * 设置页面信息获取（包含个人资料）
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<SettingResult>> settinInfo(@Header("token") String token);

    /**
     * 个人资料保存
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> infoSave(@Header("token") String token, @Field("name") String name, @Field("id_card") String id_card,
                               @Field("type") String type, @Field("license_num") String license_num,
                               @Field("tel_name") String tel_name, @Field("tel_num") String tel_num);

    /**
     * 修改头像
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @Multipart
    Call<ApiResponse> changeImage(@Header("token") String token, @Part MultipartBody.Part part);

    /**
     * 获取车辆状态
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<VehicleStatus>> getVehicleStatus(@Header("token") String token);

    /**
     * 更新车辆状态
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> changeVehicleStatus(@Header("token") String token, @Field("vehicle_id") String vehicle_id,
                                          @Field("status") String status,
                                          @Field("remain_weight") String remain_weight, @Field("remain_volume") String remain_volume);

    /**
     * 原密码验证
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> confirmPassword(@Header("token") String token, @Field("password") String password
    );

    /**
     * 新密码修改
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> changePassword(@Header("token") String token, @Field("password") String password
    );

    /**
     * 验证原手机号
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> confirmPhone(@Header("token") String token, @Field("code") String code
    );

    /**
     * 发送验证码到新手机号
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> sendSmsNew(@Header("token") String token, @Field("phone") String phone
    );

    /**
     * 新手机号修改
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> changePhone(@Header("token") String token, @Field("phone") String phone, @Field("code") String code
    );

    /**
     * 提货单列表
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<BillData> listingGet(@Header("token") String token, @Field("page") String page, @Field("status") String status
    );

    /**
     * 提货单详情
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<BillDetailsData>> listingInfo(@Header("token") String token, @Field("dpv_id") String dpv_id
    );

    /**
     * 报价单
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<QuotationData>> quotation(@Header("token") String token, @Query("dpv_id") String dpv_id
    );

    /**
     * 修改状态
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> listingChangeStatus(@Header("token") String token, @Field("dpv_id") String dpv_id, @Field("status") String status
    );

    /**
     * 点货装车
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<CountingInfo>> countingInfo(@Header("token") String token, @Field("dpv_id") String dpv_id
    );

    /**
     * 装车提交
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> countingSave(@Header("token") String token, @Field("dpv_id") String dpv_id, @Field("g_num") String g_num
    );

    /**
     * 操作单列表
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<OperaListData> operationListingGet(@Header("token") String token, @Field("page") String page, @Field("type") String type
    );

    /**
     * 操作单出发列表
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<OperaSetoutList>> listingSetoutList(@Header("token") String token
    );


    /**
     * 操作单交接列表
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<HandoverListData>> listingReceiptList(@Header("token") String token
    );

    /**
     * 操作单详情
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<OperaDetails>> operationListingInfo(@Header("token") String token, @Field("osd_id") String osd_id
    );

    /**
     * 操作单-货物清单
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<OperaGoodsListData>> operationListingGoodsList(@Header("token") String token, @Field("o_id") String o_id
    );

    /**
     * 操作单受理
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> listingReceipt(@Header("token") String token, @Field("osd_id") String osd_id
    );

    /**
     * 操作单驳回
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> listingReject(@Header("token") String token, @Field("osd_id") String osd_id, @Field("reject") String reject
    );

    /**
     * 操作单出发
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> listingSetout(@Header("token") String token, @Field("osd_id") String osd_id, @Field("lng") String lng, @Field("lat") String lat
    );

    /**
     * 轨迹上传
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> locationUpload(@Header("token") String token, @Field("lng") String lng, @Field("lat") String lat
    );

    /**
     * 是否在途中
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<OnWay>> onWay(@Header("token") String token
    );


    /**
     * 操作单运输完成
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> listingFinish(@Header("token") String token, @Field("osd_id") String osd_id, @Field("lng") String lng, @Field("lat") String lat
    );

    /**
     * 操作单交接
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> listingTakeover(@Header("token") String token, @Field("osd_id") String osd_id,@Field("o_id") String o_id, @Field("lng") String lng, @Field("lat") String lat
    );

    /**
     * 获取版本更新
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<Version>> getVersion(
    );

    /**
     * 下载文件用
     * @param fileUrl
     * @return
     */
    @Streaming //添加这个注解用来下载大文件
    @GET()
    Call<ResponseBody> downloadFileUrl(@Url String fileUrl);
}