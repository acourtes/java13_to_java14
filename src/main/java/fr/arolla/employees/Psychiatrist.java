package fr.arolla.employees;

import fr.arolla.patient.types.Patient;
import fr.arolla.patient.types.PatientForPsychiatry;

public class Psychiatrist implements MedicalProcess{
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
        final var diagnostic = patient.getDiagnostic();
        boolean result = false;

        switch (diagnostic) {
            case CORONAVIRUS:
            case NOTHING:
            case TOOTH_RAGE:
            case BROKEN_LEG:
                break;
            case BIG_HEAD:
                result = true;
                break;
        }

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
