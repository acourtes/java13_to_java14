package fr.arolla.employees;

import fr.arolla.patient.types.Patient;

import java.util.ArrayList;
import java.util.List;

public class WaitRoom {
    private List<Patient> patientsList;

    public List<Patient> getPatientsList() {
        if (patientsList == null) {
            patientsList = new ArrayList<>();
        }
        return patientsList;
    }
}
