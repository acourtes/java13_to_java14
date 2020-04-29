package fr.arolla.employees;

import fr.arolla.patient.types.Patient;

public interface MedicalProcess {

    Patient dealWithAPatient();

    boolean checkPatientDiagnostic(Patient patient);

    void actOnPatient(Patient patient);

    void addReportForPatient(Patient patient, String report);
}
