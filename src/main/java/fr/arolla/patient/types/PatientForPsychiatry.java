package fr.arolla.patient.types;

import fr.arolla.diagnostics.PatientDiagnostic;

public class PatientForPsychiatry extends Patient{
    public PatientForPsychiatry(PatientDiagnostic diagnostic) {
        super(diagnostic);
    }
}
