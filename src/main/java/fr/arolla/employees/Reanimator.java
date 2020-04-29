package fr.arolla.employees;

import fr.arolla.patient.types.Patient;
import fr.arolla.patient.types.PatientForReanimation;

public class Reanimator implements MedicalProcess {
    private final WaitRoom waitRoom;

    public Reanimator(WaitRoom waitRoom) {
        this.waitRoom = waitRoom;
    }

    @Override
    public Patient dealWithAPatient() {
        final var patient = waitRoom.getPatientsList().stream()
                .filter(p -> p instanceof PatientForReanimation)
                .findFirst()
                .orElse(null);
        waitRoom.getPatientsList().remove(patient);

        return patient;
    }

    @Override
    public boolean checkPatientDiagnostic(Patient patient) {
        boolean result = false;
        /* FIXME Not very sexy switch */
        switch (patient.getDiagnostic()) {
            case CORONAVIRUS:
                result = true;
                break;
            case BROKEN_LEG:
            case TOOTH_RAGE:
            case BIG_HEAD:
            case NOTHING:
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
        patient.getPatientFile().getReanimatorFile().getReports().add(report);
    }

    public WaitRoom getWaitRoom() {
        return waitRoom;
    }
}
