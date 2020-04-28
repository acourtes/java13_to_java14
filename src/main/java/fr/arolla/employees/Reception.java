package fr.arolla.employees;

import fr.arolla.Person;
import fr.arolla.patient.PatientFile;
import fr.arolla.patient.ReceptionFile;
import fr.arolla.patient.types.Patient;
import fr.arolla.patient.types.SimplePatient;

public class Reception {
    private final WaitRoom waitRoom;

    public Reception(WaitRoom waitRoom) {
        this.waitRoom = waitRoom;
    }

    Patient createPatient(final Person person) {
        final var simplePatient = new SimplePatient();
        final var patientFile = new PatientFile();
        patientFile.setReceptionFile(
                new ReceptionFile(person.firstName, person.lastName, person.socialSecurityNumber));
        simplePatient.setPatientFile(patientFile);

        return simplePatient;
    }

    public void addPatientToWaitList(final Person person) {
        waitRoom.getPatientsForDoctorList().add(createPatient(person));
    }

    public WaitRoom getWaitRoom() {
        return waitRoom;
    }
}
