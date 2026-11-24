package com.api.models.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyDTO {

    private Long id;
    private String companyName;
    private String contactPerson;
    private String postalCode;
    private String email;
    private String phoneNumber;
    private String mobileNumber;
    private String websiteUrl;
    private String fax;
    private String regNumber;
    private String payoutDate;
    private Integer payslipDate;
    private Integer retirementAge;
    private String status;
    private String lastModified;
    private String lastModifiedBy;
    private String leaveSettingLevel;
    private Double noticePeriod;
    private String timezone;
    private String signatoryName;
    private Long signatoryDesignationId;
    private Long signatoryDepartmentId;

    public CompanyDTO() {}

    public CompanyDTO(String companyName, String email, String mobileNumber, String status) {
        this.companyName = companyName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getPayoutDate() {
        return payoutDate;
    }

    public void setPayoutDate(String payoutDate) {
        this.payoutDate = payoutDate;
    }

    public Integer getPayslipDate() {
        return payslipDate;
    }

    public void setPayslipDate(Integer payslipDate) {
        this.payslipDate = payslipDate;
    }

    public Integer getRetirementAge() {
        return retirementAge;
    }

    public void setRetirementAge(Integer retirementAge) {
        this.retirementAge = retirementAge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getLeaveSettingLevel() {
        return leaveSettingLevel;
    }

    public void setLeaveSettingLevel(String leaveSettingLevel) {
        this.leaveSettingLevel = leaveSettingLevel;
    }

    public Double getNoticePeriod() {
        return noticePeriod;
    }

    public void setNoticePeriod(Double noticePeriod) {
        this.noticePeriod = noticePeriod;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getSignatoryName() {
        return signatoryName;
    }

    public void setSignatoryName(String signatoryName) {
        this.signatoryName = signatoryName;
    }

    public Long getSignatoryDesignationId() {
        return signatoryDesignationId;
    }

    public void setSignatoryDesignationId(Long signatoryDesignationId) {
        this.signatoryDesignationId = signatoryDesignationId;
    }

    public Long getSignatoryDepartmentId() {
        return signatoryDepartmentId;
    }

    public void setSignatoryDepartmentId(Long signatoryDepartmentId) {
        this.signatoryDepartmentId = signatoryDepartmentId;
    }
}
