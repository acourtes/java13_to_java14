package fr.arolla.patient.types;

import fr.arolla.diagnostics.PatientDiagnostic;

public abstract class Patient {
    private PatientDiagnostic diagnostic;

    public Patient(PatientDiagnostic diagnostic) {
        this.diagnostic = diagnostic;
    }

    public PatientDiagnostic getDiagnostic() {
        return diagnostic;
    }
}
