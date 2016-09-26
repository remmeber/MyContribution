package com.rhg.qf.pay.model;

public class KeyLibs {

    // 支付宝公钥
    public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQD30LTRdUgleujJEWZWpsUUuWN4RTP3JhpGEIG54uJ5ZnLWWpFi5Aqc2DoqMts8MO0Mj27WXwU4bzeUh18kV9mF4fWRV0jO7NVV+xLIsOCfneInygF06o0h1lAEqblg4k5DhnXh8MsjjpuQMHdVWo/b5CdHpnRYnUxQTxV0GqAD8QIDAQAB";
    public static String mark = "\"";
    // 签约的支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。
    public static String ali_partner = "2088422291942751";
    // 卖家支付宝账号（邮箱或手机号码格式）或其对应的支付宝唯一用户号（以2088开头的纯16位数字）。
    public static String ali_sellerId = "18858558505";
    //商户rsa私钥，pkcs8格式
    public static String ali_privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMIwLMEyitvEEctRirBarCnmtDqcIYxl2slRz6cTAFh0a4MqpUDTl505iiasFmLHJtNdMJohCkz+KjjKG7fTU4ZHy5Sy2andeULbyD+31cT+ZQOgNR2F5aAHU3CYvfx0qFw9ph5PA1AWqz+FoClPolsOOZKwrkObanbQplJebavhAgMBAAECgYAreHtcWIMrRU4ydLOWXQXzb1jjUfZUpqx+qtjQbvmB07YJq+9IftWO9cWOeLGeNTTk1hS+PC1BJRiwk9X2pdEpdqlCbri8mKPlu+Z37ZB+sNRiyl+2p4sDx9WTvw8dJHIsWFlDNnbHzS0oDexlOxX68fL4NcsZu5VLQLZV0W5YAQJBAPyIvCj9gw0OT1LPcj4Yks5V+5pjr4g7NqFxKEfxtPJErE8Zjz6Zm8x0/k2E8XCd63lVk8Dh13TJSqfYwh/+ZkECQQDE2nHL/X3qN4EEqsWfbB8piAO7/5Ux956fCrhUYKXiIPJsHyiojePAw4nXlf1Nd+Fnu6rjG35xgSNmUbu7Wh2hAkBEKzj3q69jp9g712nUX1fJwSYhAAXTNYDCxcQE37djqqwE0jZ7xIVtBKvdCyUNrGNzJmmzKIO7r9aqRnXoowjBAkBi3Rqdyne8c5e2UlXiFRkpcIf/mQLDD4t4cJfWuJtXEBjwOE3hKTGjFBFcVpXanER2JohSevJr6uFud8oC8+VBAkEAgNXtGYF9xdffvrpsjmq5H54W2TWq1GPDm7oF0Ct9jduElxZx11cdtcWYAzdU+bYjr+jrbut4X3IqDFhUMCOYfw==";
    // appId（在请同时修改  androidmanifest.xml里面，.PayActivityd里的属性
    //       <data android:scheme="wxb4ba3c02aa476ea1"/>为新设置的appid）
    public static String weixin_appId = null;
    // 商户号
    public static String weixin_mchId = null;
    //API密钥
    public static String weixin_privateKey = null;

}
