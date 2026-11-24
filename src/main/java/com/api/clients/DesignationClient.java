package com.api.clients;

import com.api.constants.Endpoints;
import com.api.models.core.DesignationDTO;
import io.restassured.response.Response;

public class DesignationClient extends BaseClient {

    public Response createDesignation(DesignationDTO designation) {
        return request()
                .body(designation)
                .when()
                .post(Endpoints.DESIGNATIONS);
    }

    public Response getDesignationById(Long id) {
        return request()
                .pathParam("id", id)
                .when()
                .get(Endpoints.DESIGNATION_BY_ID);
    }

    public Response getAllDesignations() {
        return request()
                .when()
                .get(Endpoints.DESIGNATIONS);
    }

    public Response updateDesignation(Long id, DesignationDTO designation) {
        return request()
                .pathParam("id", id)
                .body(designation)
                .when()
                .put(Endpoints.DESIGNATION_BY_ID);
    }

    public Response patchDesignation(Long id, DesignationDTO designation) {
        return request()
                .pathParam("id", id)
                .body(designation)
                .when()
                .patch(Endpoints.DESIGNATION_BY_ID);
    }

    public Response deleteDesignation(Long id) {
        return request()
                .pathParam("id", id)
                .when()
                .delete(Endpoints.DESIGNATION_BY_ID);
    }

    public Response getDesignationCount() {
        return request()
                .when()
                .get(Endpoints.DESIGNATION_COUNT);
    }
}
