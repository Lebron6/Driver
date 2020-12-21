package com.ocean.driver.api;

/**
 * Created by Administrator on 2018/1/4.
 */

public class BaseUrl {

    private static BaseUrl baseUrl;

    public static BaseUrl getInstence() {
        if (baseUrl == null) {
            return new BaseUrl();
        }
        return baseUrl;
    }

    public String ipAddress  = "http://d.oceanscm.com/";   //测试服务器
//    public String ipAddress  = "http://d.idalc.com/";   //线上服务器

    /**
     * 密码登录
     */
    public String passwordLogin() {
        return ipAddress + "/member/login/login_password/";
    }

    /**
     * 版本更新
     */
    public String getAndriodVersion() {
        return ipAddress + "/member/login/get_andriod_version/";
    }

    /**
     * 验证码登录/手机号注册
     */
    public String codeLogin() {
        return ipAddress + "/member/login/login_code/";
    }

    /**
     * 发送验证码
     */
    public String sendSMS() {
        return ipAddress + "/member/login/send_sms/";
    }

    /**
     * 忘记密码
     */
    public String passwordForget() {
        return ipAddress + "/member/login/password_forget/";
    }

    /**
     * 获取个人信息
     */
    public String getUserInfo() {
        return ipAddress + "/member/user/get_info/";
    }

    /**
     * 设置页面信息获取（包含个人资料）
     */
    public String settingInfo() {
        return ipAddress + "/member/user/setting_info/";
    }

    /**
     * 个人资料保存
     */
    public String infoSave() {
        return ipAddress + "/member/user/info_save/";
    }

    /**
     * 修改头像
     */
    public String changeHeadimg() {
        return ipAddress + "/member/user/change_headimg/";
    }

    /**
     * 获取车辆状态
     */
    public String getVehicleStatus() {
        return ipAddress + "/member/user/get_vehicle_status/";
    }

    /**
     * 更新车辆状态
     */
    public String changeVehicleStatus() {
        return ipAddress + "/member/user/change_vehicle_status/";
    }

    /**
     * 验证原密码
     */
    public String confirmPassword() {
        return ipAddress + "/member/user/confirm_password/";
    }

    /**
     * 修改密码
     */
    public String changePassword() {
        return ipAddress + "/member/user/change_password/";
    }

    /**
     * 验证原手机号
     */
    public String confirmPhone() {
        return ipAddress + "/member/user/confirm_phone/";
    }

    /**
     * 发送验证码到新手机号
     */
    public String sendSmsNew() {
        return ipAddress + "/member/user/send_sms_new/";
    }

    /**
     * 新手机号修改
     */
    public String changePhone() {
        return ipAddress + "/member/user/change_phone/";
    }

    /**
     * 提货单列表
     */
    public String listingGet() {
        return ipAddress + "/deliveryplan/listing/get/";
    }

    /**
     * 提货单详情
     */
    public String listingInfo() {
        return ipAddress + "/deliveryplan/listing/info/";
    }

    /**
     * 货物清单
     */
    public String quotation_info() {
        return ipAddress + "/deliveryplan/listing/goods_info/";
    }

    /**
     * 修改状态
     */
    public String listingChangeStatus() {
        return ipAddress + "/deliveryplan/listing/change_status/";
    }

    /**
     * 点货装车
     */
    public String countingInfo() {
        return ipAddress + "/deliveryplan/loading/counting_info/";
    }

    /**
     * 装车提交
     */
    public String countingSave() {
        return ipAddress + "/deliveryplan/loading/counting_save/";
    }

    /**
     * 操作单列表
     */
    public String operationListingGet() {
        return ipAddress + "/operation/listing/get/";
    }

    /**
     * 操作单详情
     */
    public String operationListingInfo() {
        return ipAddress + "/operation/listing/info/";
    }

    /**
     * 操作单-货物清单
     */
    public String operationListingGoodsList() {
        return ipAddress + "/operation/listing/goods_list/";
    }

    /**
     * 操作单-受理
     */
    public String listingReceipt() {
        return ipAddress + "/operation/listing/receipt/";
    }

    /**
     * 操作单-驳回
     */
    public String listingReject() {
        return ipAddress + "/operation/listing/reject/";
    }

    /**
     * 操作单-出发
     */
    public String listingSetout() {
        return ipAddress + "/operation/listing/setout/";
    }

    /**
     * 上传轨迹
     */
    public String locationUpload() {
        return ipAddress + "/member/location/upload/";
    }

    /**
     * 是否在途中
     */
    public String onWay() {
        return ipAddress + "/operation/listing/on_way/";
    }

    /**
     * 操作单-运输完成
     */
    public String listingFinish() {
        return ipAddress + "/operation/listing/finish/";
    }

    /**
     * 操作单出发
     */
    public String listingSetoutList() {
        return ipAddress + "/operation/listing/setout_list/";
    }

    /**
     * 操作单交接列表
     */
    public String listingReceiptList() {
        return ipAddress + "/operation/listing/receipt_list/";
    }

    /**
     * 操作单交接
     */
    public String listingTakeover() {
        return ipAddress + "/operation/listing/takeover/";
    }
}
