package fr.arolla.employees;

import fr.arolla.Person;
import fr.arolla.diagnostics.PatientDiagnostic;
import fr.arolla.hospitalServices.HospitalServices;
import fr.arolla.patient.DoctorFile;
import fr.arolla.patient.types.*;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(RandomBeansExtension.class)
public class DoctorTest {
    private final Doctor doctor = new Doctor(new WaitRoom());

    @Test
    void should_create_a_doctor_file(@Random SimplePatient patient) {
        doctor.createDoctorFile(patient);

        final var doctorFile = doctor.getCurrentDoctorFile();
        assertThat(doctorFile).isNotNull();
        assertThat(doctorFile.getGender()).isNotNull();
    }

    @Test
    void should_give_a_diagnostic_from_a_patient(@Random SimplePatient patient) {
        doctor.createDoctorFile(patient);
        final PatientDiagnostic result = doctor.diagnosePatient(patient);

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
        final DoctorFile currentDoctorFile = createDoctorFile(PatientDiagnostic.CORONAVIRUS, null);
        doctor.setCurrentDoctorFile(currentDoctorFile);
        doctor.setNextStepForPatient();

        assertThat(doctor.getCurrentDoctorFile().getNextStep()).isEqualTo(HospitalServices.REANIMATION);
    }

    @Test
    void should_send_patient_to_surgery_when_patient_has_broken_leg() {
        final DoctorFile currentDoctorFile = createDoctorFile(PatientDiagnostic.BROKEN_LEG, null);
        doctor.setCurrentDoctorFile(currentDoctorFile);

        doctor.setNextStepForPatient();

        assertThat(doctor.getCurrentDoctorFile().getNextStep()).isEqualTo(HospitalServices.SURGERY);
    }

    @Test
    void should_send_patient_to_surgery_when_patient_has_tooth_rage() {
        final DoctorFile currentDoctorFile = createDoctorFile(PatientDiagnostic.TOOTH_RAGE, null);
        doctor.setCurrentDoctorFile(currentDoctorFile);

        doctor.setNextStepForPatient();

        assertThat(doctor.getCurrentDoctorFile().getNextStep()).isEqualTo(HospitalServices.SURGERY);
    }

    @Test
    void should_send_patient_to_psychiatry_when_patient_has_big_head() {
        final DoctorFile currentDoctorFile = createDoctorFile(PatientDiagnostic.BIG_HEAD, null);
        doctor.setCurrentDoctorFile(currentDoctorFile);

        doctor.setNextStepForPatient();

        assertThat(doctor.getCurrentDoctorFile().getNextStep()).isEqualTo(HospitalServices.PSYCHIATRY);
    }

    @Test
    void should_send_patient_home_when_patient_has_nothing() {
        final DoctorFile currentDoctorFile = createDoctorFile(PatientDiagnostic.NOTHING, null);
        doctor.setCurrentDoctorFile(currentDoctorFile);

        doctor.setNextStepForPatient();

        assertThat(doctor.getCurrentDoctorFile().getNextStep()).isEqualTo(HospitalServices.HOME);
    }

    @Test
    void should_send_patient_for_surgery_in_wait_room_with_right_diagnostic() {
        final var diagnostic = PatientDiagnostic.BROKEN_LEG;
        final DoctorFile currentDoctorFile = createDoctorFile(diagnostic, HospitalServices.SURGERY);
        doctor.setCurrentDoctorFile(currentDoctorFile)
                .setWaitRoom(new WaitRoom());

        doctor.putPatientInWaitRoom();

        final var patientsList = doctor.getWaitRoom().getPatientsList();
        assertThat(patientsList).hasSize(1);

        final var patient = patientsList.get(0);
        assertThat(patient).isInstanceOf(PatientForSurgery.class);
        final var patientForSurgery = (PatientForSurgery) patient;
        assertThat(patientForSurgery.getDiagnostic()).isEqualByComparingTo(diagnostic);
    }

    @Test
    void should_send_patient_for_reanimation_in_wait_room_with_right_diagnostic() {
        final var diagnostic = PatientDiagnostic.CORONAVIRUS;
        final DoctorFile currentDoctorFile = createDoctorFile(diagnostic, HospitalServices.REANIMATION);
        doctor.setCurrentDoctorFile(currentDoctorFile)
                .setWaitRoom(new WaitRoom());

        doctor.putPatientInWaitRoom();

        final var patientsList = doctor.getWaitRoom().getPatientsList();
        assertThat(patientsList).hasSize(1);

        final var patient = patientsList.get(0);
        assertThat(patient).isInstanceOf(PatientForReanimation.class);
        final var patientForReanimation = (PatientForReanimation) patient;
        assertThat(patientForReanimation.getDiagnostic()).isEqualByComparingTo(diagnostic);
    }

    @Test
    void should_not_send_patient_in_wait_room_if_the_patient_has_nothing() {
        final var diagnostic = PatientDiagnostic.NOTHING;
        final DoctorFile currentDoctorFile = createDoctorFile(diagnostic, HospitalServices.HOME);
        doctor.setCurrentDoctorFile(currentDoctorFile)
                .setWaitRoom(new WaitRoom());

        doctor.putPatientInWaitRoom();

        final var patientsList = doctor.getWaitRoom().getPatientsList();
        assertThat(patientsList).isEmpty();
    }

    @Test
    void should_send_patient_for_psychiatry_in_wait_room_with_right_diagnostic() {
        final var diagnostic = PatientDiagnostic.BIG_HEAD;
        final DoctorFile currentDoctorFile = createDoctorFile(diagnostic, HospitalServices.PSYCHIATRY);
        doctor.setCurrentDoctorFile(currentDoctorFile)
                .setWaitRoom(new WaitRoom());

        doctor.putPatientInWaitRoom();

        final var patientsList = doctor.getWaitRoom().getPatientsList();
        assertThat(patientsList).hasSize(1);

        final var patient = patientsList.get(0);
        assertThat(patient).isInstanceOf(PatientForPsychiatry.class);
        final var patientForPsychiatry = (PatientForPsychiatry) patient;
        assertThat(patientForPsychiatry.getDiagnostic()).isEqualByComparingTo(diagnostic);
    }

    @Test
    void should_get_the_first_patient_waiting_for_a_diagnostic(@Random(size = 3, type = SimplePatient.class)
                                                               List<SimplePatient> patients) {
        doctor.getWaitRoom().getPatientsForDoctorList().addAll(patients);
        final var expectedPatient = patients.get(0);

        final Patient result = doctor.callAPatientForDiagnose();

        assertThat(result).isEqualTo(expectedPatient);
        assertThat(doctor.getWaitRoom().getPatientsForDoctorList()).hasSize(2);
    }

    private DoctorFile createDoctorFile(PatientDiagnostic diagnostic, HospitalServices nextStep) {
        return new DoctorFile().setDiagnostic(diagnostic)
                .setNextStep(nextStep);
    }
}
