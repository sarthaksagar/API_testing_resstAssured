package com.api.clients;

import com.api.constants.Endpoints;
import io.restassured.response.Response;

public class PayrollClient extends BaseClient {

    public Response getAllSalaries() {
        return request()
                .when()
                .get(Endpoints.SALARIES);
    }

    public Response getSalaryById(Long id) {
        return request()
                .pathParam("id", id)
                .when()
                .get(Endpoints.SALARY_BY_ID);
    }

    public Response getSalaryCount() {
        return request()
                .when()
                .get(Endpoints.SALARY_COUNT);
    }

    public Response getAllSalarySettings() {
        return request()
                .when()
                .get(Endpoints.SALARY_SETTINGS);
    }

    public Response getAllTaxSlabs() {
        return request()
                .when()
                .get(Endpoints.TAX_SLABS);
    }

    public Response getTaxSlabCount() {
        return request()
                .when()
                .get(Endpoints.TAX_SLAB_COUNT);
    }

    public Response getAllPaySlips() {
        return request()
                .when()
                .get(Endpoints.PAY_SLIPS);
    }

    public Response getPaySlipCount() {
        return request()
                .when()
                .get(Endpoints.PAY_SLIP_COUNT);
    }
}
