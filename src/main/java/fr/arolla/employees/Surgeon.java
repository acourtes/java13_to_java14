package fr.arolla.employees;

import fr.arolla.diagnostics.PatientDiagnostic;
import fr.arolla.patient.types.Patient;
import fr.arolla.patient.types.PatientForSurgery;

import java.util.List;

import static fr.arolla.diagnostics.PatientDiagnostic.BROKEN_LEG;
import static fr.arolla.diagnostics.PatientDiagnostic.TOOTH_RAGE;

public class Surgeon {
    private final WaitRoom waitRoom;

    private static final List<PatientDiagnostic> DIAGNOSTICS_FOR_SURGERY = List.of(BROKEN_LEG, TOOTH_RAGE);

    public Surgeon(WaitRoom waitRoom) {
        this.waitRoom = waitRoom;
    }

    public Patient dealWithAPatient() {
        return waitRoom.getPatientsList().stream()
                .filter(patient -> patient instanceof PatientForSurgery)
                .findFirst()
                .orElse(null);
    }

    public boolean checkPatientDiagnostic(Patient patient) {
        final var patientDiagnostic = patient.getDiagnostic();

        return DIAGNOSTICS_FOR_SURGERY.contains(patientDiagnostic);
    }

    public void operatePatient(Patient patient) {
        patient.setDiagnostic(null);
    }

    public void addReportForPatient(Patient patient, String report) {
        patient.getPatientFile().getSurgeonFile().getReports().add(report);
    }
}
