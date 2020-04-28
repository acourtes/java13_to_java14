package fr.arolla.employees;

import fr.arolla.patient.types.Patient;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WaitRoom {
    private List<Patient> patientsList;
    private LinkedList<Patient> patientsForDoctorList;

    public List<Patient> getPatientsList() {
        if (patientsList == null) {
            patientsList = new ArrayList<>();
        }
        return patientsList;
    }

    public LinkedList<Patient> getPatientsForDoctorList() {
        if (patientsForDoctorList == null) {
            patientsForDoctorList = new LinkedList<>();
        }
        return patientsForDoctorList;
    }
}
