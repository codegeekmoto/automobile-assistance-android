package com.automobile.assistance.data.remote.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Job {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("owner_id")
    @Expose
    private Integer ownerId;
    @SerializedName("company_service_id")
    @Expose
    private Integer companyServiceId;
    @SerializedName("client_id")
    @Expose
    private Integer clientId;
    @SerializedName("mechanic_id")
    @Expose
    private Object mechanicId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("client_loc")
    @Expose
    private LatLang latLang;

    public LatLang getLatLang() {
        return latLang;
    }

    public void setLatLang(LatLang latLang) {
        this.latLang = latLang;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getCompanyServiceId() {
        return companyServiceId;
    }

    public void setCompanyServiceId(Integer companyServiceId) {
        this.companyServiceId = companyServiceId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Object getMechanicId() {
        return mechanicId;
    }

    public void setMechanicId(Object mechanicId) {
        this.mechanicId = mechanicId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
