package fr.arolla.employees;

import fr.arolla.diagnostics.PatientDiagnostic;
import fr.arolla.hospitalServices.HospitalServices;
import fr.arolla.patient.DoctorFile;
import fr.arolla.patient.Gender;
import fr.arolla.patient.types.Patient;
import fr.arolla.patient.types.PatientForPsychiatry;
import fr.arolla.patient.types.PatientForReanimation;
import fr.arolla.patient.types.PatientForSurgery;

public class Doctor {

    private DoctorFile currentDoctorFile;

    public Doctor(WaitRoom waitRoom) {
        this.waitRoom = waitRoom;
    }

    private WaitRoom waitRoom;

    public void createDoctorFile(Patient patient) {
        currentDoctorFile = new DoctorFile().setGender(getGender(patient));
    }

    private Gender getGender(Patient patient) {
        final var genders = Gender.values();
        return genders[patient.getPatientFile()
                .getReceptionFile().firstName.length() % genders.length];
    }

    public PatientDiagnostic diagnosePatient(Patient patient) {
        final var diagnostic = getDiagnostic(patient);
        currentDoctorFile.setDiagnostic(diagnostic);

        return diagnostic;
    }

    private PatientDiagnostic getDiagnostic(Patient patient) {
        final var diagnostics = PatientDiagnostic.values();
        return diagnostics[patient.getPatientFile()
                .getReceptionFile().lastName.length() % diagnostics.length];
    }

    public void addRemark(final String remark) {
        currentDoctorFile.getRemarks().add(remark);
    }

    public DoctorFile getCurrentDoctorFile() {
        return currentDoctorFile;
    }

    public Doctor setCurrentDoctorFile(DoctorFile currentDoctorFile) {
        this.currentDoctorFile = currentDoctorFile;

        return this;
    }

    public void setNextStepForPatient() {
        switch (currentDoctorFile.getDiagnostic()) {
            case CORONAVIRUS:
                currentDoctorFile.setNextStep(HospitalServices.REANIMATION);
                break;
            case BROKEN_LEG:
            case TOOTH_RAGE:
                currentDoctorFile.setNextStep(HospitalServices.SURGERY);
                break;
            case BIG_HEAD:
                currentDoctorFile.setNextStep(HospitalServices.PSYCHIATRY);
                break;
            case NOTHING:
                currentDoctorFile.setNextStep(HospitalServices.HOME);
                break;
        }
    }

    public void putPatientInWaitRoom() {
        final var diagnostic = currentDoctorFile.getDiagnostic();
        final var nextStep = currentDoctorFile.getNextStep();
        Patient patient = null;

        switch (nextStep) {
            case SURGERY:
                patient = new PatientForSurgery().setDiagnostic(diagnostic);
                break;
            case PSYCHIATRY:
                patient = new PatientForPsychiatry().setDiagnostic(diagnostic);
                break;
            case REANIMATION:
                patient = new PatientForReanimation().setDiagnostic(diagnostic);
                break;
            case HOME:
                break;
        }

        if (patient != null) {
            waitRoom.getPatientsList().add(patient);
        }
    }

    public WaitRoom getWaitRoom() {
        return waitRoom;
    }

    public void setWaitRoom(WaitRoom waitRoom) {
        this.waitRoom = waitRoom;
    }

    public Patient callAPatientForDiagnose() {
        return waitRoom.getPatientsForDoctorList().poll();
    }
}
