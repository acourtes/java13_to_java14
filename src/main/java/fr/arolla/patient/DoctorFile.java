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

    public void setDiagnostic(PatientDiagnostic diagnostic) {
        this.diagnostic = diagnostic;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

    public void setNextStep(HospitalServices nextStep) {
        this.nextStep = nextStep;
    }
}
