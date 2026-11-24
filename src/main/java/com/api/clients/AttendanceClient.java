package com.api.clients;

import com.api.constants.Endpoints;
import io.restassured.response.Response;

import java.util.Map;

public class AttendanceClient extends BaseClient {

    public Response createAttendance(Map<String, Object> attendancePayload) {
        return request()
                .body(attendancePayload)
                .when()
                .post(Endpoints.ATTENDANCES);
    }

    public Response getAttendanceById(Long id) {
        return request()
                .pathParam("id", id)
                .when()
                .get(Endpoints.ATTENDANCE_BY_ID);
    }

    public Response getAllAttendances() {
        return request()
                .when()
                .get(Endpoints.ATTENDANCES);
    }

    public Response getAttendanceCount() {
        return request()
                .when()
                .get(Endpoints.ATTENDANCE_COUNT);
    }

    public Response deleteAttendance(Long id) {
        return request()
                .pathParam("id", id)
                .when()
                .delete(Endpoints.ATTENDANCE_BY_ID);
    }
}
