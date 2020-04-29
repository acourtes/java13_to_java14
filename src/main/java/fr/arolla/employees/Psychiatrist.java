package fr.arolla.employees;

import fr.arolla.patient.types.Patient;
import fr.arolla.patient.types.PatientForPsychiatry;

public class Psychiatrist implements MedicalProcess {
    private final WaitRoom waitRoom;

    public Psychiatrist(WaitRoom waitRoom) {
        this.waitRoom = waitRoom;
    }

    public WaitRoom getWaitRoom() {
        return waitRoom;
    }

    @Override
    public Patient dealWithAPatient() {
        final var patient = waitRoom.getPatientsList().stream()
                .filter(p -> p instanceof PatientForPsychiatry)
                .findFirst()
                .orElse(null);
        waitRoom.getPatientsList().remove(patient);

        return patient;
    }

    @Override
    public boolean checkPatientDiagnostic(Patient patient) {
        boolean result = switch (patient.getDiagnostic()) {
            case CORONAVIRUS, NOTHING, TOOTH_RAGE, BROKEN_LEG -> false;
            case BIG_HEAD -> true;
        };

        if (!result) {
            waitRoom.getPatientsList().add(patient);
        }

        return result;
    }

    @Override
    public void actOnPatient(Patient patient) {
        patient.setDiagnostic(null);
    }

    @Override
    public void addReportForPatient(Patient patient, String report) {
        patient.getPatientFile().getPsychiatristFile().getReports().add(report);
    }
}
