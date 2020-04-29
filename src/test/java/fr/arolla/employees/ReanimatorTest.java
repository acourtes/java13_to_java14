package fr.arolla.employees;

import fr.arolla.patient.PatientFile;
import fr.arolla.patient.types.Patient;
import fr.arolla.patient.types.PatientForReanimation;
import fr.arolla.patient.types.PatientForSurgery;
import org.junit.jupiter.api.Test;

import static fr.arolla.diagnostics.PatientDiagnostic.BROKEN_LEG;
import static fr.arolla.diagnostics.PatientDiagnostic.CORONAVIRUS;
import static org.assertj.core.api.Assertions.assertThat;

public class ReanimatorTest {

    private static final WaitRoom waitRoom = new WaitRoom();
    private static final Reanimator reanimator = new Reanimator(waitRoom);
    private static final Patient patientForSurgery = new PatientForSurgery().setDiagnostic(BROKEN_LEG);
    private static final Patient patientForReanimation = new PatientForReanimation().setDiagnostic(CORONAVIRUS);

    @Test
    void should_select_a_patient_for_reanimation() {
        waitRoom.getPatientsList().removeAll(waitRoom.getPatientsList());
        waitRoom.getPatientsList().add(patientForSurgery);
        waitRoom.getPatientsList().add(patientForReanimation);

        final var result = reanimator.dealWithAPatient();

        assertThat(result).isEqualTo(patientForReanimation);
        assertThat(reanimator.getWaitRoom().getPatientsList()).hasSize(1);
    }

    @Test
    void should_check_that_the_patient_is_here_for_reanimation_with_a_coronavirus() {
        final var patient = new PatientForReanimation().setDiagnostic(CORONAVIRUS);
        final var result = reanimator.checkPatientDiagnostic(patient);

        assertThat(result).isTrue();
    }

    @Test
    void should_check_that_the_patient_is_not_here_for_reanimation_with_a_broken_leg() {
        final var patient = new PatientForReanimation().setDiagnostic(BROKEN_LEG);
        reanimator.getWaitRoom().getPatientsList().removeAll(waitRoom.getPatientsList());

        final var result = reanimator.checkPatientDiagnostic(patient);

        assertThat(result).isFalse();
        assertThat(reanimator.getWaitRoom().getPatientsList()).hasSize(1);
    }

    @Test
    void should_act_on_the_patient_and_reset_diagnostic() {
        final var patient = new PatientForReanimation().setDiagnostic(CORONAVIRUS);

        reanimator.actOnPatient(patient);

        assertThat(patient.getDiagnostic()).isNull();
    }

    @Test
    void should_add_a_report_in_reanimator_file() {
        final var patient = new PatientForReanimation().setDiagnostic(CORONAVIRUS)
                .setPatientFile(new PatientFile());
        final var report = """
                New psychiatric report
                  I'd like so much to format easily this text
                \t Could you do something ?""";

        reanimator.addReportForPatient(patient, report);

        final var reports = patient.getPatientFile().getReanimatorFile().getReports();
        assertThat(reports).hasSize(1);
        assertThat(reports.get(0)).isEqualTo(report);
    }
}
