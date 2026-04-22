package com.medflow.domain.lab;

import com.medflow.domain.patient.PatientId;
import java.time.Instant;

public record LabOrder(LabOrderId id, PatientId patientId, String testName, Instant orderedAt, String status) {}
