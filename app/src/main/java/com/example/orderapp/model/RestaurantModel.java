package com.example.orderapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class RestaurantModel implements Parcelable {
    private  String name;
    private  String address;
    private  float delivery_charge;
    private  String image;
    private  Hours hours;
    private List<Menu> menus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getDelivery_charge() {
        return delivery_charge;
    }

    public void setDelivery_charge(float delivery_charge) {
        this.delivery_charge = delivery_charge;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Hours getHours() {
        return hours;
    }

    public void setHours(Hours hours) {
        this.hours = hours;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    protected RestaurantModel(Parcel in) {
        name = in.readString();
        address = in.readString();
        delivery_charge = in.readFloat();
        image = in.readString();
        menus = in.createTypedArrayList(Menu.CREATOR);
    }

    //Parcelable thường được sử dụng để gửi dữ liệu (dạng Object) giữa các activity với nhau thông qua Bunble gửi cùng Intent.
    //ghi đè phương thức writeToParcel().
    //Trong phương thức này triển khai ghi tất cả dữ liệu có trong lớp tới Parcel,
    //tiếp theo triển khai một đối tuợng static final Parcelable.Creator để giải tuần tự tái tạo lại Java Object.
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeFloat(delivery_charge);
        dest.writeString(image);
        dest.writeTypedList(menus);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RestaurantModel> CREATOR = new Creator<RestaurantModel>() {
        @Override
        public RestaurantModel createFromParcel(Parcel in) {
            return new RestaurantModel(in);
        }

        @Override
        public RestaurantModel[] newArray(int size) {
            return new RestaurantModel[size];
        }
    };
}
