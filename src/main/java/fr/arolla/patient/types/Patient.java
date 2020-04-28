package fr.arolla.patient.types;

import fr.arolla.diagnostics.PatientDiagnostic;
import fr.arolla.patient.PatientFile;

import java.util.Objects;

public abstract class Patient {
    private PatientDiagnostic diagnostic;
    private PatientFile patientFile;

    public PatientDiagnostic getDiagnostic() {
        return diagnostic;
    }

    public Patient setDiagnostic(PatientDiagnostic diagnostic) {
        this.diagnostic = diagnostic;

        return this;
    }

    public PatientFile getPatientFile() {
        return patientFile;
    }

    public void setPatientFile(PatientFile patientFile) {
        this.patientFile = patientFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return getDiagnostic() == patient.getDiagnostic() &&
                Objects.equals(getPatientFile(), patient.getPatientFile());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDiagnostic(), getPatientFile());
    }

    @Override
    public String toString() {
        return "Patient{" +
                "diagnostic=" + diagnostic +
                ", patientFile=" + patientFile +
                '}';
    }
}
