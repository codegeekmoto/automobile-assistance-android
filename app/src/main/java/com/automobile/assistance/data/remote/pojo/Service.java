package com.automobile.assistance.data.remote.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Service {

    @SerializedName("company_service_id")
    @Expose
    private Integer companyServiceId;
    @SerializedName("service_id")
    @Expose
    private Integer serviceId;
    @SerializedName("activated")
    @Expose
    private Boolean activated;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("latlng")
    @Expose
    private LatLang latlng;
    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("mobile")
    @Expose
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getCompanyServiceId() {
        return companyServiceId;
    }

    public void setCompanyServiceId(Integer companyServiceId) {
        this.companyServiceId = companyServiceId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LatLang getLatlng() {
        return latlng;
    }

    public void setLatlng(LatLang latlng) {
        this.latlng = latlng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
