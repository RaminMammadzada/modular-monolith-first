package com.medflow.application.ports;

import com.medflow.domain.patient.PatientId;
import java.util.Optional;

public interface PatientRepository {
    void save(PatientRecord patient);
    Optional<PatientRecord> findById(PatientId id);

    record PatientRecord(PatientId id, String fullName, String email) {}
}
