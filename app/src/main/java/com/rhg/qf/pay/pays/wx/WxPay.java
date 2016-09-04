package com.rhg.qf.pay.pays.wx;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.rhg.qf.pay.model.KeyLibs;
import com.rhg.qf.pay.model.OrderInfo;
import com.rhg.qf.pay.pays.IPayable;
import com.rhg.qf.pay.security.wx.MD5;
import com.rhg.qf.utils.ToastHelper;
import com.rhg.qf.utils.XmlUtil;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * desc:
 * author：remember
 * time：2016/6/24 15:20
 * email：1013773046@qq.com
 */
public class WxPay implements IPayable {
    OkHttpClient client = null;
    //微信sdk对象
    private IWXAPI msgApi;
    //生成预付单需要的参数
    private Map<String, String> paramsForPrepay = null;
    //预付单
    private Map<String, String> resultOfPrepay;

    public WxPay() {
//        getPrepayId = new GetPrepayIdImpl(this);
    }

    @Override
    public String Pay(Activity activity, OrderInfo orderInfo, String prepayId) {
        boolean isSuccess = msgApi.sendReq(BuildCallAppParams(prepayId));
        return String.valueOf(isSuccess);
    }

    @Override
    public OrderInfo BuildOrderInfo(String body, String invalidTime,
                                    String notifyUrl, String tradeNo,
                                    String subject, String totalFee, String spbillCreateIp) {
        try {
            String nonceStr = GetNonceStr();
//            xml.append("</xml>");
            /*List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
            packageParams.add(new BasicNameValuePair("appid", KeyLibs.weixin_appId));
            packageParams.add(new BasicNameValuePair("body", subject));//和支付宝的subject类似
            packageParams.add(new BasicNameValuePair("mch_id", KeyLibs.weixin_mchId));
            packageParams.add(new BasicNameValuePair("nonce_str", nonceStr));
            packageParams.add(new BasicNameValuePair("notify_url", notifyUrl));
            packageParams.add(new BasicNameValuePair("out_trade_no", tradeNo));
            packageParams.add(new BasicNameValuePair("spbill_create_ip", spbillCreateIp));
            packageParams.add(new BasicNameValuePair("total_fee", totalFee));
            packageParams.add(new BasicNameValuePair("trade_type", "APP"));
*/
            Map<String, String> packageParams = new HashMap<>();
            packageParams.put("appid", KeyLibs.weixin_appId);/*微信开放平台审核通过的应用APPID*/
            packageParams.put("body", subject);/*商品或支付单简要描述，可以多个商品一起打包*/
            packageParams.put("mch_id", KeyLibs.weixin_mchId);/*微信支付分配的商户号*/
            packageParams.put("nonce_str", nonceStr);/*随机字符串*/
            packageParams.put("notify_url", notifyUrl);/*接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。*/
            packageParams.put("out_trade_no", tradeNo);/*商户系统内部的订单号,32个字符内、可包含字母, */
            packageParams.put("spbill_create_ip", spbillCreateIp);/*用户端实际ip*/
            packageParams.put("total_fee", totalFee);/*总金额*/
            packageParams.put("trade_type", "APP");/*支付类型*/
            paramsForPrepay = packageParams;//将参数保存一份，待调用支付时使用
            String sign = Sign(packageParams);
            packageParams.put("sign", sign);/*签名*/
            String xmlstring = XmlUtil.MapToXml(packageParams);

            return new OrderInfo(xmlstring);

        } catch (Exception e) {
            return null;
        }
    }

    public void RegisterApp(Context context, String appId) {
        msgApi = WXAPIFactory.createWXAPI(context, null);
        msgApi.registerApp(appId);
    }

    public void unRegisterApp() {
        if (msgApi != null) {
            msgApi.unregisterApp();
        }
    }

    public String GetPrepayId(final OrderInfo orderInfo) {
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        byte[] response = post(url, orderInfo.GetContent());
        String content = new String(response);
        content = content.replace("<![CDATA[", "");
        content = content.replace("]]>", "");
        Map<String, String> map = XmlUtil.DecodeXmlToMap(content);
        if (map == null) {
            return "";
        }
        if (map.get("prepay_id") == null) {
            return "";
        }
        return map.get("prepay_id");

        /*String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        byte[] buf = Util.httpPost(url, orderInfo.GetContent());
        Map<String, String> xml = null;
        if (buf != null) {
            content = new String(buf);
            xml = XmlUtil.DecodeXmlToMap(content);
        }
        if (xml != null) {
            resultOfPrepay = xml;//保存预支付订单
            return xml.get("prepay_id");
        } else return null;*/

    }

    private PayReq BuildCallAppParams(String prepayId) {

        PayReq req = new PayReq();
        req.appId = KeyLibs.weixin_appId;
        req.partnerId = KeyLibs.weixin_mchId;
        req.prepayId = prepayId;
        req.packageValue = "Sign=WXPay";
        req.nonceStr = GetNonceStr();
        req.timeStamp = String.valueOf(GetTimeStamp());

        /*List<NameValuePair> signParams = new LinkedList<NameValuePair>();
        signParams.add(new BasicNameValuePair("appid", req.appId));
        signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
        signParams.add(new BasicNameValuePair("package", req.packageValue));
        signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
        signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
        signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));*/
        Map<String, String> signParams = new HashMap<>();
        signParams.put("appid", req.appId);
        signParams.put("noncestr", req.nonceStr);
        signParams.put("package", req.packageValue);
        signParams.put("partnerid", req.partnerId);
        signParams.put("prepayid", req.prepayId);
        signParams.put("timestamp", req.timeStamp);

        req.sign = Sign(signParams);
        return req;
    }

    byte[] post(String url, String json) {
        RequestBody formBody = RequestBody.create(MediaType.parse("text/xml;charset=UTF-8"), json);

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        if (client == null)
            client = new OkHttpClient.Builder().readTimeout(5000, TimeUnit.MILLISECONDS)
                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
                    .writeTimeout(5000, TimeUnit.MILLISECONDS).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().bytes();
        } catch (IOException e) {
            return null;/*处理超时等异常*/
        }
    }

    private String GetNonceStr() {
        Random random = new Random();
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }

    private String Sign(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(KeyLibs.weixin_privateKey);
        String sign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
        return sign;
    }

    private long GetTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

}
