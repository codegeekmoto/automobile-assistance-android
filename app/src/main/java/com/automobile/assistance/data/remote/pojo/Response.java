package com.automobile.assistance.data.remote.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("code")
    @Expose
    private Integer code;

    @SerializedName("service")
    @Expose
    private List<Service> services;

    @SerializedName("alert")
    @Expose
    private Alert alert;

    @SerializedName("fcm_token")
    @Expose
    private FcmToken fcm_token;

    public FcmToken getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(FcmToken fcm_token) {
        this.fcm_token = fcm_token;
    }

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
