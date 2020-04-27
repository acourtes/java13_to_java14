package fr.arolla.employees;

import fr.arolla.Person;
import fr.arolla.diagnostics.PatientDiagnostic;
import fr.arolla.patient.DoctorFile;
import fr.arolla.patient.Gender;

public class Doctor {

    private DoctorFile currentDoctorFile;

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

    public void setCurrentDoctorFile(DoctorFile currentDoctorFile) {
        this.currentDoctorFile = currentDoctorFile;
    }
}
