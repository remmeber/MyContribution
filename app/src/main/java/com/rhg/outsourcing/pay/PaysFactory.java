package com.rhg.outsourcing.pay;


import com.rhg.outsourcing.pay.model.PayType;

public class PaysFactory {
	
	public static IPayable GetInstance(PayType payType){
		IPayable pay=null;
		switch (payType) {
		case AliPay:
//			pay=new AliPay();
			break;
		case WeixinPay:
//			pay=new WeixinPay();
		default:
			break;
		}
		return pay;
	}
}
