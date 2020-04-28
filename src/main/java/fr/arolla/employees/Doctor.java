package fr.arolla.employees;

import fr.arolla.Person;
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
    private WaitRoom waitRoom;

    public void createDoctorFile(Person person) {
        final var doctorFile = new DoctorFile();
        doctorFile.setGender(getGender(person));

        currentDoctorFile = doctorFile;
    }

    private Gender getGender(Person person) {
        final var genders = Gender.values();
        return genders[person.firstName.length() % genders.length];
    }

    public PatientDiagnostic diagnosePatient(Person person) {
        final var diagnostic = getDiagnostic(person);
        currentDoctorFile.setDiagnostic(diagnostic);

        return diagnostic;
    }

    private PatientDiagnostic getDiagnostic(Person person) {
        final var diagnostics = PatientDiagnostic.values();
        return diagnostics[person.lastName.length() % diagnostics.length];
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
                patient = new PatientForSurgery(diagnostic);
                break;
            case PSYCHIATRY:
                patient = new PatientForPsychiatry(diagnostic);
                break;
            case REANIMATION:
                patient = new PatientForReanimation(diagnostic);
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
}
