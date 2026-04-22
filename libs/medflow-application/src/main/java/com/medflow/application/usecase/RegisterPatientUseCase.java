package com.medflow.application.usecase;

import com.medflow.application.ports.PatientRepository;
import com.medflow.domain.patient.EmailAddress;
import com.medflow.domain.patient.PatientId;

public class RegisterPatientUseCase {
    private final PatientRepository patientRepository;

    public RegisterPatientUseCase(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientId register(String fullName, String email) {
        var id = PatientId.newId();
        var normalizedEmail = new EmailAddress(email);
        patientRepository.save(new PatientRepository.PatientRecord(id, fullName, normalizedEmail.value()));
        return id;
    }
}
