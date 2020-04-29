package fr.arolla.employees;

import fr.arolla.Person;
import fr.arolla.patient.types.Patient;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(RandomBeansExtension.class)
public class ReceptionTest {

    private final Reception reception = new Reception(new WaitRoom());

    @DisplayName("Should create a patient with essential identity information")
    @Test
    void should_create_a_new_patient(@Random Person person) {
        final Patient result = reception.createPatient(person);

        assertThat(result).isNotNull();
        final var receptionFile = result.getPatientFile().getReceptionFile();
        assertThat(receptionFile.firstName()).isEqualTo(person.firstName);
        assertThat(receptionFile.lastName()).isEqualTo(person.lastName);
        assertThat(receptionFile.socialSecurityNumber()).isEqualTo(person.socialSecurityNumber);
    }

    @DisplayName("Should manage an ordered list of patients")
    @Test
    void should_manage_an_ordered_list_of_patients(@Random(size = 3, type = Person.class)
                                                           List<Person> persons) {
        persons.forEach(reception::addPatientToWaitList);

        final List<Patient> result = reception.getWaitRoom().getPatientsForDoctorList();

        assertThat(result).hasSize(3);
        assertThat(result.get(0)).isEqualTo(reception.createPatient(persons.get(0)));
        assertThat(result.get(1)).isEqualTo(reception.createPatient(persons.get(1)));
        assertThat(result.get(2)).isEqualTo(reception.createPatient(persons.get(2)));
    }
}
