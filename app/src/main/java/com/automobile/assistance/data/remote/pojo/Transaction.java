package com.automobile.assistance.data.remote.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transaction {

    @SerializedName("service_name")
    @Expose
    private String serviceName;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("client_fname")
    @Expose
    private String clientFname;
    @SerializedName("client_lname")
    @Expose
    private String clientLname;
    @SerializedName("client_phone")
    @Expose
    private String clientPhone;
    @SerializedName("mechanic_fname")
    @Expose
    private Object mechanicFname;
    @SerializedName("mechanic_lname")
    @Expose
    private Object mechanicLname;
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
    private LatLang clientLoc;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getClientFname() {
        return clientFname;
    }

    public void setClientFname(String clientFname) {
        this.clientFname = clientFname;
    }

    public String getClientLname() {
        return clientLname;
    }

    public void setClientLname(String clientLname) {
        this.clientLname = clientLname;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public Object getMechanicFname() {
        return mechanicFname;
    }

    public void setMechanicFname(Object mechanicFname) {
        this.mechanicFname = mechanicFname;
    }

    public Object getMechanicLname() {
        return mechanicLname;
    }

    public void setMechanicLname(Object mechanicLname) {
        this.mechanicLname = mechanicLname;
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

    public LatLang getClientLoc() {
        return clientLoc;
    }

    public void setClientLoc(LatLang clientLoc) {
        this.clientLoc = clientLoc;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
