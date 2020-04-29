package fr.arolla.patient;

import java.util.Objects;

public class PatientFile {
    private ReceptionFile receptionFile;
    private DoctorFile doctorFile;
    private SurgeonFile surgeonFile;

    public PatientFile() {
        doctorFile = new DoctorFile();
        surgeonFile = new SurgeonFile();
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
}
