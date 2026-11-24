package com.api.clients;

import com.api.constants.Endpoints;
import io.restassured.response.Response;

import java.util.Map;

public class RecruitmentClient extends BaseClient {

    public Response createCandidate(Map<String, Object> candidatePayload) {
        return request()
                .body(candidatePayload)
                .when()
                .post(Endpoints.CANDIDATES);
    }

    public Response getCandidateById(Long id) {
        return request()
                .pathParam("id", id)
                .when()
                .get(Endpoints.CANDIDATE_BY_ID);
    }

    public Response getAllCandidates() {
        return request()
                .when()
                .get(Endpoints.CANDIDATES);
    }

    public Response getCandidateCount() {
        return request()
                .when()
                .get(Endpoints.CANDIDATE_COUNT);
    }

    public Response getAllVacancies() {
        return request()
                .when()
                .get(Endpoints.VACANCIES);
    }

    public Response getVacancyCount() {
        return request()
                .when()
                .get(Endpoints.VACANCY_COUNT);
    }
}
