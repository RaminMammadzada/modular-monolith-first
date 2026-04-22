package com.medflow.clinic.api.config;

import com.medflow.application.ports.ClinicPortBundle;
import com.medflow.application.usecase.MedFlowService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppBeans {
    @Bean
    MedFlowService medFlowService(
            ClinicPortBundle.Patients patients,
            ClinicPortBundle.Intakes intakes,
            ClinicPortBundle.Triages triages,
            ClinicPortBundle.Appointments appointments,
            ClinicPortBundle.Labs labs,
            ClinicPortBundle.Notifications notifications,
            ClinicPortBundle.Audit audit,
            ClinicPortBundle.Timeline timeline) {
        return new MedFlowService(patients, intakes, triages, appointments, labs, notifications, audit, timeline);
    }
}
