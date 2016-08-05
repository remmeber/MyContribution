package com.rhg.qf.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:
 * author：remember
 * time：2016/7/11 0:10
 * email：1013773046@qq.com
 */
public class PayModel implements Parcelable {
    public String receiver;
    public String phone;
    public String address;
    public ArrayList<PayBean> payBeanList;

    public PayModel() {
    }

    protected PayModel(Parcel in) {
        receiver = in.readString();
        phone = in.readString();
        address = in.readString();
        payBeanList = in.readArrayList(PayBean.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(receiver);
        dest.writeString(phone);
        dest.writeString(address);
        dest.writeList(payBeanList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PayModel> CREATOR = new Creator<PayModel>() {
        @Override
        public PayModel createFromParcel(Parcel in) {
            return new PayModel(in);
        }

        @Override
        public PayModel[] newArray(int size) {
            return new PayModel[size];
        }
    };

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<PayBean> getPayBeanList() {
        return payBeanList;
    }

    public void setPayBeanList(ArrayList<PayBean> payBeanList) {
        this.payBeanList = payBeanList;
    }

    public static class PayBean implements Parcelable{
        String productId;
        String productName;
        String productPrice;
        String productPic;
        String productNumber;
        boolean isChecked;

        protected PayBean(Parcel in) {
            productId = in.readString();
            productName = in.readString();
            productPrice = in.readString();
            productPic = in.readString();
            productNumber = in.readString();
            isChecked = in.readByte() != 0;
        }

        public PayBean() {
        }

        public static final Creator<PayBean> CREATOR = new Creator<PayBean>() {
            @Override
            public PayBean createFromParcel(Parcel in) {
                return new PayBean(in);
            }

            @Override
            public PayBean[] newArray(int size) {
                return new PayBean[size];
            }
        };

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(String productPrice) {
            this.productPrice = productPrice;
        }

        public String getProductPic() {
            return productPic;
        }

        public void setProductPic(String productPic) {
            this.productPic = productPic;
        }

        public String getProductNumber() {
            return productNumber;
        }

        public void setProductNumber(String productNumber) {
            this.productNumber = productNumber;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(productId);
            parcel.writeString(productName);
            parcel.writeString(productPrice);
            parcel.writeString(productPic);
            parcel.writeString(productNumber);
            parcel.writeByte((byte) (isChecked ? 1 : 0));
        }
    }

}