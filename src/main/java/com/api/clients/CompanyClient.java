package com.api.clients;

import com.api.constants.Endpoints;
import com.api.models.core.CompanyDTO;
import io.restassured.response.Response;

public class CompanyClient extends BaseClient {

    public Response createCompany(CompanyDTO company) {
        return request()
                .body(company)
                .when()
                .post(Endpoints.COMPANIES);
    }

    public Response getCompanyById(Long id) {
        return request()
                .pathParam("id", id)
                .when()
                .get(Endpoints.COMPANY_BY_ID);
    }

    public Response getAllCompanies() {
        return request()
                .when()
                .get(Endpoints.COMPANIES);
    }

    public Response updateCompany(Long id, CompanyDTO company) {
        return request()
                .pathParam("id", id)
                .body(company)
                .when()
                .put(Endpoints.COMPANY_BY_ID);
    }

    public Response patchCompany(Long id, CompanyDTO company) {
        return request()
                .pathParam("id", id)
                .body(company)
                .when()
                .patch(Endpoints.COMPANY_BY_ID);
    }

    public Response deleteCompany(Long id) {
        return request()
                .pathParam("id", id)
                .when()
                .delete(Endpoints.COMPANY_BY_ID);
    }

    public Response getCompanyCount() {
        return request()
                .when()
                .get(Endpoints.COMPANY_COUNT);
    }
}
