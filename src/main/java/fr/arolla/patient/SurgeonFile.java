package fr.arolla.patient;

import java.util.ArrayList;
import java.util.List;

public class SurgeonFile {
    private List<String> reports;

    public List<String> getReports() {
        if (reports == null) {
            reports = new ArrayList<>();
        }
        return reports;
    }
}
