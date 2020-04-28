package fr.arolla.patient;

import fr.arolla.diagnostics.PatientDiagnostic;
import fr.arolla.hospitalServices.HospitalServices;

import java.util.ArrayList;
import java.util.List;

public class DoctorFile {

    private PatientDiagnostic diagnostic;
    private Gender gender;
    private List<String> remarks;
    private HospitalServices nextStep;

    public PatientDiagnostic getDiagnostic() {
        return diagnostic;
    }

    public DoctorFile setDiagnostic(PatientDiagnostic diagnostic) {
        this.diagnostic = diagnostic;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public DoctorFile setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public List<String> getRemarks() {
        if (remarks == null) {
            remarks = new ArrayList<>();
        }

        return remarks;
    }

    public HospitalServices getNextStep() {
        return nextStep;
    }

    public DoctorFile setNextStep(HospitalServices nextStep) {
        this.nextStep = nextStep;
        return this;
    }
}
