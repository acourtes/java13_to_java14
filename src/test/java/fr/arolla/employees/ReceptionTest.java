package fr.arolla.employees;

import fr.arolla.Person;
import fr.arolla.patient.ReceptionFile;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(RandomBeansExtension.class)
public class ReceptionTest {

    private final Reception reception = new Reception();

    @DisplayName("Should create a patient with essential identity information")
    @Test
    void should_create_a_new_patient(@Random Person person) {
        final ReceptionFile result = reception.createReceptionFile(person);

        assertThat(result).isNotNull();
        assertThat(result.firstName).isEqualTo(person.firstName);
        assertThat(result.lastName).isEqualTo(person.lastName);
        assertThat(result.socialSecurityNumber).isEqualTo(person.socialSecurityNumber);
    }

    @DisplayName("Should manage an ordered list of patients")
    @Test
    void should_manage_an_ordered_list_of_patients(@Random(size = 3, type = Person.class)
                                                           List<Person> persons) {
        persons.forEach(reception::addPatientToWaitList);

        final List<ReceptionFile> result = reception.getPatientsWaitList();

        assertThat(result).hasSize(3);
        assertThat(result.get(0)).isEqualTo(reception.createReceptionFile(persons.get(0)));
        assertThat(result.get(1)).isEqualTo(reception.createReceptionFile(persons.get(1)));
        assertThat(result.get(2)).isEqualTo(reception.createReceptionFile(persons.get(2)));
    }

    @DisplayName("Should remove the first patient in the wait list when a doctor is available")
    @Test
    void should_remove_the_first_patient_in_the_wait_list(@Random(size = 3, type = Person.class)
                                                           List<Person> persons) {
        persons.forEach(reception::addPatientToWaitList);

        final ReceptionFile result = reception.sendPatientToTheDoctor();

        assertThat(result).isEqualTo(reception.createReceptionFile(persons.get(0)));

        final var patientsWaitList = reception.getPatientsWaitList();

        assertThat(patientsWaitList).hasSize(2);
        assertThat(patientsWaitList.get(0)).isEqualTo(reception.createReceptionFile(persons.get(1)));
        assertThat(patientsWaitList.get(1)).isEqualTo(reception.createReceptionFile(persons.get(2)));
    }
}
