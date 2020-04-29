package fr.arolla.employees;

import fr.arolla.diagnostics.PatientDiagnostic;
import fr.arolla.patient.types.Patient;
import fr.arolla.patient.types.PatientForSurgery;

import java.util.List;

import static fr.arolla.diagnostics.PatientDiagnostic.BROKEN_LEG;
import static fr.arolla.diagnostics.PatientDiagnostic.TOOTH_RAGE;

public class Surgeon implements MedicalProcess {
    private final WaitRoom waitRoom;

    private static final List<PatientDiagnostic> DIAGNOSTICS_FOR_SURGERY = List.of(BROKEN_LEG, TOOTH_RAGE);

    public Surgeon(WaitRoom waitRoom) {
        this.waitRoom = waitRoom;
    }

    @Override
    public Patient dealWithAPatient() {
        return waitRoom.getPatientsList().stream()
                .filter(patient -> patient instanceof PatientForSurgery)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean checkPatientDiagnostic(Patient patient) {
        final var patientDiagnostic = patient.getDiagnostic();

        return DIAGNOSTICS_FOR_SURGERY.contains(patientDiagnostic);
    }

    @Override
    public void actOnPatient(Patient patient) {
        patient.setDiagnostic(null);
    }

    @Override
    public void addReportForPatient(Patient patient, String report) {
        patient.getPatientFile().getSurgeonFile().getReports().add(report);
    }
}
