package fr.arolla.patient.types;

public class PatientForReanimation extends Patient {

    private Boolean hasStillCoronavirus;

    public Boolean getHasStillCoronavirus() {
        return hasStillCoronavirus;
    }

    public void setHasStillCoronavirus(Boolean hasStillCoronavirus) {
        this.hasStillCoronavirus = hasStillCoronavirus;
    }
}
