package com.medflow.clinic.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ClinicControllerTest {
    @Autowired MockMvc mvc;

    @Test
    void registersPatient() throws Exception {
        mvc.perform(post("/api/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"givenName":"Ada","familyName":"Patient","email":"ada@example.test","phone":"+12025550101","dateOfBirth":"1990-01-01"}
                                """))
                .andExpect(status().isOk());
    }
}
