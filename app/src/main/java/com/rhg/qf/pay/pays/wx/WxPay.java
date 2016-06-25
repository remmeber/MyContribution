package com.rhg.qf.pay.pays.wx;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.rhg.qf.bean.OrderXml;
import com.rhg.qf.mvp.api.PayService;
import com.rhg.qf.mvp.api.QFoodApi;
import com.rhg.qf.pay.model.KeyLibs;
import com.rhg.qf.pay.model.OrderInfo;
import com.rhg.qf.pay.pays.IPayable;
import com.rhg.qf.pay.security.wx.MD5;
import com.rhg.qf.utils.XmlUtil;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * desc:
 * author：remember
 * time：2016/6/24 15:20
 * email：1013773046@qq.com
 */
public class WxPay implements IPayable {

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
//        StringBuffer xml = new StringBuffer();

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
        Log.i("RHG", "msgApi:" + msgApi);
    }

    public void unRegisterApp() {
        Log.i("RHG", "msgApi:" + msgApi);
        if (msgApi != null)
            msgApi.detach();
    }

    public Observable<String> GetPrepayId(final OrderInfo orderInfo) {

//        getPrepayId.getPrepayId(orderInfo.GetContent());
        String content;
        final Map<String, String> orderMap = XmlUtil.DecodeXmlToMap(orderInfo.GetContent());
        /*content = */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(QFoodApi.WXPAY_CREATE_ORDER_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        final PayService service = retrofit.create(PayService.class);
        return service.getPrepayId(orderMap)
                .flatMap(new Func1<OrderXml, Observable<String>>() {
                    @Override
                    public Observable<String> call(final OrderXml orderXml) {
                        Log.i("RHG", "return:" + orderXml.getReturn_msg());
                        return Observable.create(new Observable.OnSubscribe<String>() {
                            @Override
                            public void call(Subscriber<? super String> subscriber) {
                                subscriber.onNext(orderXml.getPrepay_id());
                            }
                        });
                    }
                });

        /*QFoodApiMamager.getInstant().getQFoodApiService().getPrepayId(orderMap)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())*/
                /*.flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(final String s) {
                        return Observable.create(new Observable.OnSubscribe<String>() {
                            @Override
                            public void call(Subscriber<? super String> subscriber) {
                                subscriber.onNext(s);
                            }
                        });
                    }
                })*/
        /*.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("RHG", "message: " + e.getMessage() + "cause:" + e.getCause());

            }

            @Override
            public void onNext(String s) {
                Log.i("RHG", "RETURN " + s);
            }
        });*/
        /*Log.i("RHG", content);
        Map<String, String> xml = null;
        xml = XmlUtil.DecodeXmlToMap(content);
        if (xml != null)
            return xml.get("prepay_id");
        else*/
      /*  String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
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
