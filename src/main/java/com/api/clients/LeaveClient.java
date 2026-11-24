package com.api.clients;

import com.api.constants.Endpoints;
import com.api.models.leave.LeaveTypeDTO;
import io.restassured.response.Response;

public class LeaveClient extends BaseClient {

    public Response createLeaveType(LeaveTypeDTO leaveType) {
        return request()
                .body(leaveType)
                .when()
                .post(Endpoints.LEAVE_TYPES);
    }

    public Response getLeaveTypeById(Long id) {
        return request()
                .pathParam("id", id)
                .when()
                .get(Endpoints.LEAVE_TYPE_BY_ID);
    }

    public Response getAllLeaveTypes() {
        return request()
                .when()
                .get(Endpoints.LEAVE_TYPES);
    }

    public Response deleteLeaveType(Long id) {
        return request()
                .pathParam("id", id)
                .when()
                .delete(Endpoints.LEAVE_TYPE_BY_ID);
    }

    public Response getLeaveTypeCount() {
        return request()
                .when()
                .get(Endpoints.LEAVE_TYPE_COUNT);
    }
}
