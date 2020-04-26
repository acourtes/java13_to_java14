package fr.arolla.employees;

import fr.arolla.Person;
import fr.arolla.patient.SimplePatient;

import java.util.LinkedList;
import java.util.List;

public class Reception {
    private final LinkedList<SimplePatient> patientsWaitList = new LinkedList<>();

    SimplePatient createNewPatient(final Person person) {
        return new SimplePatient(person.firstName, person.lastName, person.socialSecurityNumber);
    }

    public void addPatientToWaitList(final Person person) {
        patientsWaitList.add(createNewPatient(person));
    }

    public List<SimplePatient> getPatientsWaitList() {
        return patientsWaitList;
    }

    public SimplePatient sendPatientToTheDoctor() {
        return patientsWaitList.poll();
    }
}
