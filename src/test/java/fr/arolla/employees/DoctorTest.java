package fr.arolla.employees;

import fr.arolla.Person;
import fr.arolla.diagnostics.PatientDiagnostic;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(RandomBeansExtension.class)
public class DoctorTest {
    private final Doctor doctor = new Doctor();

    @Test
    void should_create_a_doctor_file(@Random Person person) {
        doctor.createDoctorFile(person);

        final var doctorFile = doctor.getCurrentDoctorFile();
        assertThat(doctorFile).isNotNull();
        assertThat(doctorFile.getGender()).isNotNull();
    }

    @Test
    void should_give_a_diagnostic_from_a_patient(@Random Person person) {
        doctor.createDoctorFile(person);
        final PatientDiagnostic result = doctor.diagnosePatient(person);

        assertThat(result).isNotNull();
        assertThat(doctor.getCurrentDoctorFile().getDiagnostic()).isNotNull();
    }

    @Test
    void should_add_a_remark_in_doctor_file(@Random Person person) {
        doctor.createDoctorFile(person);
        doctor.addRemark("A remark");

        assertThat(doctor.getCurrentDoctorFile().getRemarks()).isNotEmpty();
    }
}
