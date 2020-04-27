package fr.arolla.employees;

import fr.arolla.Person;
import fr.arolla.patient.ReceptionFile;

import java.util.LinkedList;
import java.util.List;

public class Reception {
    private final LinkedList<ReceptionFile> patientsWaitList = new LinkedList<>();

    ReceptionFile createReceptionFile(final Person person) {
        return new ReceptionFile(person.firstName, person.lastName, person.socialSecurityNumber);
    }

    public void addPatientToWaitList(final Person person) {
        patientsWaitList.add(createReceptionFile(person));
    }

    public List<ReceptionFile> getPatientsWaitList() {
        return patientsWaitList;
    }

    public ReceptionFile sendPatientToTheDoctor() {
        return patientsWaitList.poll();
    }
}
