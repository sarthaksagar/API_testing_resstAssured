package com.api.constants;

/**
 * Centralized registry of all HRMS API endpoint paths from Swagger.
 */
public final class Endpoints {

    private Endpoints() {}

    // Authentication & Accounts
    public static final String AUTH_LOGIN = "/api/auth/login";
    public static final String AUTH_LOGOUT = "/api/auth/logout";
    public static final String ACCOUNT = "/api/account";
    public static final String AUTH_INFO = "/api/auth-info";
    public static final String AUTHORITIES = "/api/authorities";
    public static final String FCM_TOKEN = "/api/users/{userId}/company/{companyId}/fcm-token";

    // Core HR
    public static final String COMPANIES = "/api/companies";
    public static final String COMPANY_BY_ID = "/api/companies/{id}";
    public static final String COMPANY_COUNT = "/api/companies/count";

    public static final String BRANCHES = "/api/branches";
    public static final String BRANCH_BY_ID = "/api/branches/{id}";
    public static final String BRANCH_COUNT = "/api/branches/count";

    public static final String DEPARTMENTS = "/api/departments";
    public static final String DEPARTMENT_BY_ID = "/api/departments/{id}";
    public static final String DEPARTMENT_COUNT = "/api/departments/count";

    public static final String DESIGNATIONS = "/api/designations";
    public static final String DESIGNATION_BY_ID = "/api/designations/{id}";
    public static final String DESIGNATION_COUNT = "/api/designations/count";

    public static final String EMPLOYEES = "/api/employees";
    public static final String EMPLOYEE_BY_ID = "/api/employees/{id}";
    public static final String EMPLOYEE_COUNT = "/api/employees/count";
    public static final String EMPLOYEE_MINI = "/api/employee-minis";

    public static final String ADDRESSES = "/api/addresses";
    public static final String ADDRESS_BY_ID = "/api/addresses/{id}";
    public static final String ADDRESS_COUNT = "/api/addresses/count";

    public static final String CONTACTS = "/api/contacts";
    public static final String CONTACT_BY_ID = "/api/contacts/{id}";

    public static final String BANKS_DETAILS = "/api/banks-details";
    public static final String BANK_BY_ID = "/api/banks-details/{id}";

    // Leave & Attendance
    public static final String ATTENDANCES = "/api/attendances";
    public static final String ATTENDANCE_BY_ID = "/api/attendances/{id}";
    public static final String ATTENDANCE_COUNT = "/api/attendances/count";

    public static final String HOLIDAYS = "/api/holidays";
    public static final String HOLIDAY_BY_ID = "/api/holidays/{id}";

    public static final String LEAVE_TYPES = "/api/leave-types";
    public static final String LEAVE_TYPE_BY_ID = "/api/leave-types/{id}";
    public static final String LEAVE_TYPE_COUNT = "/api/leave-types/count";

    public static final String LEAVE_POLICIES = "/api/leave-policies";
    public static final String LEAVE_POLICY_BY_ID = "/api/leave-policies/{id}";

    public static final String LEAVE_APPLICATIONS = "/api/leave-applications";
    public static final String LEAVE_APPLICATION_BY_ID = "/api/leave-applications/{id}";
    public static final String LEAVE_APPLICATION_COUNT = "/api/leave-applications/count";

    // Payroll & Compensation
    public static final String SALARIES = "/api/salaries";
    public static final String SALARY_BY_ID = "/api/salaries/{id}";
    public static final String SALARY_COUNT = "/api/salaries/count";

    public static final String SALARY_SETTINGS = "/api/salary-settings";
    public static final String SALARY_SETTINGS_BY_ID = "/api/salary-settings/{id}";

    public static final String SALARY_COMPONENTS = "/api/salary-compnents";
    public static final String SALARY_COMPONENT_BY_ID = "/api/salary-compnents/{id}";

    public static final String PAY_SLIPS = "/api/pay-slips";
    public static final String PAY_SLIP_BY_ID = "/api/pay-slips/{id}";
    public static final String PAY_SLIP_COUNT = "/api/pay-slips/count";

    public static final String TAX_SLABS = "/api/tax-slabs";
    public static final String TAX_SLAB_BY_ID = "/api/tax-slabs/{id}";
    public static final String TAX_SLAB_COUNT = "/api/tax-slabs/count";

    public static final String TAX_REGIMES = "/api/tax-regimes";
    public static final String TAX_REGIME_BY_ID = "/api/tax-regimes/{id}";

    // Recruitment & Onboarding
    public static final String CANDIDATES = "/api/candidates";
    public static final String CANDIDATE_BY_ID = "/api/candidates/{id}";
    public static final String CANDIDATE_COUNT = "/api/candidates/count";

    public static final String VACANCIES = "/api/vacancies";
    public static final String VACANCY_BY_ID = "/api/vacancies/{id}";
    public static final String VACANCY_PUBLISH = "/api/vacancies/{id}/publish";
    public static final String VACANCY_COUNT = "/api/vacancies/count";

    public static final String RECRUITMENT_REQUISITIONS = "/api/recruitment-requisitions";
    public static final String RECRUITMENT_REQUISITION_BY_ID = "/api/recruitment-requisitions/{id}";

    // Assets
    public static final String ASSET_TYPES = "/api/asset-types";
    public static final String ASSET_TYPE_BY_ID = "/api/asset-types/{id}";
    public static final String ASSET_TYPE_COUNT = "/api/asset-types/count";

    public static final String ASSET_INVENTORIES = "/api/asset-inventories";
    public static final String ASSET_INVENTORY_BY_ID = "/api/asset-inventories/{id}";
    public static final String ASSET_INVENTORY_COUNT = "/api/asset-inventories/count";

    public static final String ASSET_APPLICATIONS = "/api/asset-applications";
    public static final String ASSET_APPLICATION_BY_ID = "/api/asset-applications/{id}";

    // Travel & Timesheet
    public static final String TRAVELS = "/api/travels";
    public static final String TRAVEL_BY_ID = "/api/travels/{id}";
    public static final String TRAVEL_COUNT = "/api/travels/count";

    public static final String TIME_SHEETS = "/api/time-sheets";
    public static final String TIME_SHEET_BY_ID = "/api/time-sheets/{id}";
    public static final String TIME_SHEET_COUNT = "/api/time-sheets/count";

    // Approvals & Workflow
    public static final String APPROVALS = "/api/approvals";
    public static final String APPROVAL_BY_ID = "/api/approvals/{id}";
    public static final String APPROVAL_APPLY_STATUS = "/api/approvals/apply-status";
    public static final String APPROVAL_SETTINGS = "/api/approval-settings";
    public static final String APPROVAL_LEVELS = "/api/approval-levels";

    // Roles & Permissions
    public static final String TECHVG_ROLES = "/api/techvg-roles";
    public static final String TECHVG_ROLE_BY_ID = "/api/techvg-roles/{id}";
    public static final String TECHVG_PERMISSIONS = "/api/techvg-permissions";
}
