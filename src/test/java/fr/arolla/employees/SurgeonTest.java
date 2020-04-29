package fr.arolla.employees;

import fr.arolla.patient.PatientFile;
import fr.arolla.patient.types.Patient;
import fr.arolla.patient.types.PatientForPsychiatry;
import fr.arolla.patient.types.PatientForSurgery;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static fr.arolla.diagnostics.PatientDiagnostic.BIG_HEAD;
import static fr.arolla.diagnostics.PatientDiagnostic.BROKEN_LEG;
import static org.assertj.core.api.Assertions.assertThat;

public class SurgeonTest {
    private static final WaitRoom waitRoom = new WaitRoom();
    private static final Surgeon surgeon = new Surgeon(waitRoom);
    private static final Patient patientForSurgery = new PatientForSurgery().setDiagnostic(BROKEN_LEG);
    private static final Patient patientForPsychiatry = new PatientForPsychiatry().setDiagnostic(BIG_HEAD);

    @BeforeAll
    static void setUp() {
        waitRoom.getPatientsList().add(patientForPsychiatry);
        waitRoom.getPatientsList().add(patientForSurgery);
    }

    @Test
    void should_take_out_a_patient_for_surgery_from_the_wait_list() {
        final var result = surgeon.dealWithAPatient();

        assertThat(result).isEqualTo(patientForSurgery);
        assertThat(surgeon.getWaitRoom().getPatientsList()).hasSize(1);
    }

    @Test
    void should_check_that_the_patient_is_here_for_surgery_with_a_broken_leg() {
        final var patient = new PatientForSurgery().setDiagnostic(BROKEN_LEG);
        final var result = surgeon.checkPatientDiagnostic(patient);

        assertThat(result).isTrue();
    }

    @Test
    void should_check_that_the_patient_is_not_here_for_surgery_with_a_big_head() {
        final var patient = new PatientForSurgery().setDiagnostic(BIG_HEAD);
        final var result = surgeon.checkPatientDiagnostic(patient);

        assertThat(result).isFalse();
    }

    @Test
    void should_operate_the_patient_and_reset_diagnostic() {
        final var patient = new PatientForSurgery().setDiagnostic(BROKEN_LEG);

        surgeon.actOnPatient(patient);

        assertThat(patient.getDiagnostic()).isNull();
    }

    @Test
    void should_add_a_report_in_surgeon_file() {
        final var patient = new PatientForSurgery().setDiagnostic(BROKEN_LEG)
                .setPatientFile(new PatientFile());
        final var report = "New operation report";

        surgeon.addReportForPatient(patient, report);

        final var reports = patient.getPatientFile().getSurgeonFile().getReports();
        assertThat(reports).hasSize(1);
        assertThat(reports.get(0)).isEqualTo(report);
    }
}
