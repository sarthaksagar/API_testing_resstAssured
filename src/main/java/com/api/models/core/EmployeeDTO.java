package com.api.models.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDTO {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String empUniqueId;
    private String joindate;
    private String retirementDate;
    private String confirmationDate;
    private Long companyId;
    private String status;
    private String emailId;
    private Long annualCtc;
    private Long salaryTemplateId;
    private Long employmentTypeId;
    private Long assignedHrId;
    private String employmentDate;
    private Boolean isConfirmed;
    private Boolean isUserCreated;
    private Double presentDaysCount;
    private String lastModified;
    private String lastModifiedBy;

    public EmployeeDTO() {}

    public EmployeeDTO(String firstName, String lastName, String emailId, Long companyId, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.companyId = companyId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmpUniqueId() {
        return empUniqueId;
    }

    public void setEmpUniqueId(String empUniqueId) {
        this.empUniqueId = empUniqueId;
    }

    public String getJoindate() {
        return joindate;
    }

    public void setJoindate(String joindate) {
        this.joindate = joindate;
    }

    public String getRetirementDate() {
        return retirementDate;
    }

    public void setRetirementDate(String retirementDate) {
        this.retirementDate = retirementDate;
    }

    public String getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(String confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Long getAnnualCtc() {
        return annualCtc;
    }

    public void setAnnualCtc(Long annualCtc) {
        this.annualCtc = annualCtc;
    }

    public Long getSalaryTemplateId() {
        return salaryTemplateId;
    }

    public void setSalaryTemplateId(Long salaryTemplateId) {
        this.salaryTemplateId = salaryTemplateId;
    }

    public Long getEmploymentTypeId() {
        return employmentTypeId;
    }

    public void setEmploymentTypeId(Long employmentTypeId) {
        this.employmentTypeId = employmentTypeId;
    }

    public Long getAssignedHrId() {
        return assignedHrId;
    }

    public void setAssignedHrId(Long assignedHrId) {
        this.assignedHrId = assignedHrId;
    }

    public String getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(String employmentDate) {
        this.employmentDate = employmentDate;
    }

    public Boolean getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
    }

    public Boolean getIsUserCreated() {
        return isUserCreated;
    }

    public void setIsUserCreated(Boolean userCreated) {
        isUserCreated = userCreated;
    }

    public Double getPresentDaysCount() {
        return presentDaysCount;
    }

    public void setPresentDaysCount(Double presentDaysCount) {
        this.presentDaysCount = presentDaysCount;
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
}
