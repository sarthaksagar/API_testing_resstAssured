package com.api.models.leave;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeaveTypeDTO {

    private Long id;
    private String leaveType;
    private Integer noOfDays;
    private Boolean hasCarryForward;
    private Boolean hasEarned;
    private Boolean hasCustomPolicy;
    private String status;
    private Long companyId;
    private String lastModified;
    private String lastModifiedBy;
    private Boolean allowBackward;
    private String leaveAbbreviation;

    public LeaveTypeDTO() {}

    public LeaveTypeDTO(String leaveType, Integer noOfDays, Long companyId, String status) {
        this.leaveType = leaveType;
        this.noOfDays = noOfDays;
        this.companyId = companyId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public Integer getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(Integer noOfDays) {
        this.noOfDays = noOfDays;
    }

    public Boolean getHasCarryForward() {
        return hasCarryForward;
    }

    public void setHasCarryForward(Boolean hasCarryForward) {
        this.hasCarryForward = hasCarryForward;
    }

    public Boolean getHasEarned() {
        return hasEarned;
    }

    public void setHasEarned(Boolean hasEarned) {
        this.hasEarned = hasEarned;
    }

    public Boolean getHasCustomPolicy() {
        return hasCustomPolicy;
    }

    public void setHasCustomPolicy(Boolean hasCustomPolicy) {
        this.hasCustomPolicy = hasCustomPolicy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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

    public Boolean getAllowBackward() {
        return allowBackward;
    }

    public void setAllowBackward(Boolean allowBackward) {
        this.allowBackward = allowBackward;
    }

    public String getLeaveAbbreviation() {
        return leaveAbbreviation;
    }

    public void setLeaveAbbreviation(String leaveAbbreviation) {
        this.leaveAbbreviation = leaveAbbreviation;
    }
}
