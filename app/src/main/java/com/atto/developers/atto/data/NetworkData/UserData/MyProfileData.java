package com.atto.developers.atto.data.NetworkData.UserData;

//나의 프로필 데이터 객체


import java.io.Serializable;

public class MyProfileData implements Serializable {
    private String member_alias;
    private String member_phone;
    private String member_address;
    private String member_profile_img;

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

    public String getMember_address() {
        return this.member_address;
    }

    public void setMember_address(String member_address) {
        this.member_address = member_address;
    }

    public String getMember_profile_img() {
        return this.member_profile_img;
    }

    public void setMember_profile_img(String member_profile_img) {
        this.member_profile_img = member_profile_img;
    }

}
