package fr.arolla.patient;

import java.util.Objects;

public class PatientFile {
    private ReceptionFile receptionFile;
    private final DoctorFile doctorFile;
    private final SurgeonFile surgeonFile;
    private final PsychiatristFile psychiatristFile;
    private final ReanimatorFile reanimatorFile;

    public PatientFile() {
        doctorFile = new DoctorFile();
        surgeonFile = new SurgeonFile();
        psychiatristFile = new PsychiatristFile();
        reanimatorFile = new ReanimatorFile();
    }

    public ReceptionFile getReceptionFile() {
        return receptionFile;
    }

    public void setReceptionFile(ReceptionFile receptionFile) {
        this.receptionFile = receptionFile;
    }

    public SurgeonFile getSurgeonFile() {
        return surgeonFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientFile that = (PatientFile) o;
        return Objects.equals(getReceptionFile(), that.getReceptionFile()) &&
                Objects.equals(doctorFile, that.doctorFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReceptionFile(), doctorFile);
    }

    public PsychiatristFile getPsychiatristFile() {
        return psychiatristFile;
    }

    public ReanimatorFile getReanimatorFile() {
        return reanimatorFile;
    }
}
