package fr.arolla.employees;

import fr.arolla.Person;
import fr.arolla.diagnostics.PatientDiagnostic;
import fr.arolla.hospitalServices.HospitalServices;
import fr.arolla.patient.DoctorFile;
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
    void should_add_a_remark_in_doctor_file() {
        doctor.setCurrentDoctorFile(new DoctorFile());
        doctor.addRemark("A remark");

        assertThat(doctor.getCurrentDoctorFile().getRemarks()).isNotEmpty();
    }

    @Test
    void should_send_patient_to_reanimation_when_patient_has_coronavirus() {
        final DoctorFile currentDoctorFile = createDoctorFile(PatientDiagnostic.CORONAVIRUS);
        doctor.setCurrentDoctorFile(currentDoctorFile);
        doctor.setNextStepForPatient();

        assertThat(doctor.getCurrentDoctorFile().getNextStep()).isEqualTo(HospitalServices.REANIMATION);
    }

    @Test
    void should_send_patient_to_surgery_when_patient_has_broken_leg() {
        final DoctorFile currentDoctorFile = createDoctorFile(PatientDiagnostic.BROKEN_LEG);
        doctor.setCurrentDoctorFile(currentDoctorFile);
        doctor.setNextStepForPatient();

        assertThat(doctor.getCurrentDoctorFile().getNextStep()).isEqualTo(HospitalServices.SURGERY);
    }

    @Test
    void should_send_patient_to_surgery_when_patient_has_tooth_rage() {
        final DoctorFile currentDoctorFile = createDoctorFile(PatientDiagnostic.TOOTH_RAGE);
        doctor.setCurrentDoctorFile(currentDoctorFile);
        doctor.setNextStepForPatient();

        assertThat(doctor.getCurrentDoctorFile().getNextStep()).isEqualTo(HospitalServices.SURGERY);
    }

    @Test
    void should_send_patient_to_psychiatry_when_patient_has_big_head() {
        final DoctorFile currentDoctorFile = createDoctorFile(PatientDiagnostic.BIG_HEAD);
        doctor.setCurrentDoctorFile(currentDoctorFile);
        doctor.setNextStepForPatient();

        assertThat(doctor.getCurrentDoctorFile().getNextStep()).isEqualTo(HospitalServices.PSYCHIATRY);
    }

    @Test
    void should_send_patient_home_when_patient_has_nothing() {
        final DoctorFile currentDoctorFile = createDoctorFile(PatientDiagnostic.NOTHING);
        doctor.setCurrentDoctorFile(currentDoctorFile);
        doctor.setNextStepForPatient();

        assertThat(doctor.getCurrentDoctorFile().getNextStep()).isEqualTo(HospitalServices.HOME);
    }

    private DoctorFile createDoctorFile(PatientDiagnostic diagnostic) {
        final var currentDoctorFile = new DoctorFile();
        currentDoctorFile.setDiagnostic(diagnostic);
        return currentDoctorFile;
    }
}
