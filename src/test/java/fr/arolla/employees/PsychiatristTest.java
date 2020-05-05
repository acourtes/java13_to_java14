package fr.arolla.employees;

import fr.arolla.patient.PatientFile;
import fr.arolla.patient.types.Patient;
import fr.arolla.patient.types.PatientForPsychiatry;
import fr.arolla.patient.types.PatientForSurgery;
import org.junit.jupiter.api.Test;

import static fr.arolla.diagnostics.PatientDiagnostic.BIG_HEAD;
import static fr.arolla.diagnostics.PatientDiagnostic.BROKEN_LEG;
import static org.assertj.core.api.Assertions.assertThat;

public class PsychiatristTest {

    private static final WaitRoom waitRoom = new WaitRoom();
    private static final Psychiatrist psychiatrist = new Psychiatrist(waitRoom);
    private static final Patient patientForSurgery = new PatientForSurgery().setDiagnostic(BROKEN_LEG);
    private static final Patient patientForPsychiatry = new PatientForPsychiatry().setDiagnostic(BIG_HEAD);

    @Test
    void should_select_a_patient_for_psychiatry() {
        waitRoom.getPatientsList().removeAll(waitRoom.getPatientsList());
        waitRoom.getPatientsList().add(patientForSurgery);
        waitRoom.getPatientsList().add(patientForPsychiatry);

        final var result = psychiatrist.dealWithAPatient();

        assertThat(result).isEqualTo(patientForPsychiatry);
        assertThat(psychiatrist.getWaitRoom().getPatientsList()).hasSize(1);
    }

    @Test
    void should_check_that_the_patient_is_here_for_psychiatry_with_a_big_head() {
        final var patient = new PatientForPsychiatry().setDiagnostic(BIG_HEAD);
        final var result = psychiatrist.checkPatientDiagnostic(patient);

        assertThat(result).isTrue();
    }

    @Test
    void should_check_that_the_patient_is_not_here_for_psychiatry_with_a_broken_leg() {
        final var patient = new PatientForPsychiatry().setDiagnostic(BROKEN_LEG);
        psychiatrist.getWaitRoom().getPatientsList().removeAll(waitRoom.getPatientsList());

        final var result = psychiatrist.checkPatientDiagnostic(patient);

        assertThat(result).isFalse();
        assertThat(psychiatrist.getWaitRoom().getPatientsList()).hasSize(1);
    }

    @Test
    void should_act_on_the_patient_and_reset_diagnostic() {
        final var patient = new PatientForPsychiatry().setDiagnostic(BIG_HEAD);

        psychiatrist.actOnPatient(patient);

        assertThat(patient.getDiagnostic()).isNull();
    }

    @Test
    void should_add_a_report_in_psychiatrist_file() {
        final var patient = new PatientForPsychiatry().setDiagnostic(BIG_HEAD)
                .setPatientFile(new PatientFile());
        /* FIXME What can we use here as a preview to have an easy formatted text for this long string */
        final var report = "New psychiatric report \n" +
                "The patient is really too much narcissic" +
                "And I really like to have some structure in this text !";

        psychiatrist.addReportForPatient(patient, report);

        final var reports = patient.getPatientFile().getPsychiatristFile().getReports();
        assertThat(reports).hasSize(1);
        assertThat(reports.get(0)).isEqualTo(report);
    }
}
