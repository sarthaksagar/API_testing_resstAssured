package com.api.clients;

import com.api.constants.Endpoints;
import com.api.models.core.DepartmentDTO;
import io.restassured.response.Response;

public class DepartmentClient extends BaseClient {

    public Response createDepartment(DepartmentDTO department) {
        return request()
                .body(department)
                .when()
                .post(Endpoints.DEPARTMENTS);
    }

    public Response createDepartmentUnauthenticated(DepartmentDTO department) {
        return unauthenticatedRequest()
                .body(department)
                .when()
                .post(Endpoints.DEPARTMENTS);
    }

    public Response getDepartmentById(Long id) {
        return request()
                .pathParam("id", id)
                .when()
                .get(Endpoints.DEPARTMENT_BY_ID);
    }

    public Response getDepartmentByIdUnauthenticated(Long id) {
        return unauthenticatedRequest()
                .pathParam("id", id)
                .when()
                .get(Endpoints.DEPARTMENT_BY_ID);
    }

    public Response getAllDepartments() {
        return request()
                .when()
                .get(Endpoints.DEPARTMENTS);
    }

    public Response updateDepartment(Long id, DepartmentDTO department) {
        return request()
                .pathParam("id", id)
                .body(department)
                .when()
                .put(Endpoints.DEPARTMENT_BY_ID);
    }

    public Response patchDepartment(Long id, DepartmentDTO department) {
        return request()
                .pathParam("id", id)
                .body(department)
                .when()
                .patch(Endpoints.DEPARTMENT_BY_ID);
    }

    public Response deleteDepartment(Long id) {
        return request()
                .pathParam("id", id)
                .when()
                .delete(Endpoints.DEPARTMENT_BY_ID);
    }

    public Response getDepartmentCount() {
        return request()
                .when()
                .get(Endpoints.DEPARTMENT_COUNT);
    }
}
