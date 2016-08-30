package com.atto.developers.atto.networkdata.userdata;
//나의 프로필 데이터 객체

import java.io.Serializable;

public class MyProfileData implements Serializable {
    private String member_alias;
    private String member_phone;

    public String getMember_zipcode_1() {
        return member_zipcode_1;
    }

    public void setMember_zipcode_1(String member_zipcode_1) {
        this.member_zipcode_1 = member_zipcode_1;
    }

    public String getMember_address_1() {
        return member_address_1;
    }

    public void setMember_address_1(String member_address_1) {
        this.member_address_1 = member_address_1;
    }

    private String member_profile_img;
    private String member_zipcode_1;
    private String member_address_1;

    public String getMember_alias() {
        return this.member_alias;
    }

    public void setMember_alias(String member_alias) {
        this.member_alias = member_alias;
    }

    public String getMember_phone() {
        return this.member_phone;
    }

    public void setMember_phone(String member_phone) {
        this.member_phone = member_phone;
    }


    public String getMember_profile_img() {
        return this.member_profile_img;
    }

    public void setMember_profile_img(String member_profile_img) {
        this.member_profile_img = member_profile_img;
    }

}
