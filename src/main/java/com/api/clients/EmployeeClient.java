package com.api.clients;

import com.api.constants.Endpoints;
import com.api.models.core.EmployeeDTO;
import io.restassured.response.Response;

public class EmployeeClient extends BaseClient {

    public Response createEmployee(EmployeeDTO employee) {
        return request()
                .body(employee)
                .when()
                .post(Endpoints.EMPLOYEES);
    }

    public Response getEmployeeById(Long id) {
        return request()
                .pathParam("id", id)
                .when()
                .get(Endpoints.EMPLOYEE_BY_ID);
    }

    public Response getAllEmployees() {
        return request()
                .when()
                .get(Endpoints.EMPLOYEES);
    }

    public Response updateEmployee(Long id, EmployeeDTO employee) {
        return request()
                .pathParam("id", id)
                .body(employee)
                .when()
                .put(Endpoints.EMPLOYEE_BY_ID);
    }

    public Response deleteEmployee(Long id) {
        return request()
                .pathParam("id", id)
                .when()
                .delete(Endpoints.EMPLOYEE_BY_ID);
    }

    public Response getEmployeeCount() {
        return request()
                .when()
                .get(Endpoints.EMPLOYEE_COUNT);
    }
}
