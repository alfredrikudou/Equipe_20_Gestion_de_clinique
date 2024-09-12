package org.example;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Clinic {
    private Queue<Patient> fileMedecin;
    private Queue<Patient> fileRadio;
    private TriageType doctorTriageType;
    private TriageType radiologyTriageType;

    public Clinic() {
        this.doctorTriageType = TriageType.FIFO;
        this.radiologyTriageType = TriageType.FIFO;
        fileMedecin = new LinkedList<>();
        fileRadio = new LinkedList<>();
    }

    public Clinic(TriageType doctorTriageType, TriageType radiologyTriageType) {
        this.doctorTriageType = doctorTriageType;
        this.radiologyTriageType = radiologyTriageType;

        if (doctorTriageType == TriageType.GRAVITY) {
            fileMedecin = new PriorityQueue<>((p1, p2) -> Integer.compare(p2.getGravite(), p1.getGravite()));
        } else {
            fileMedecin = new LinkedList<>();
        }

        if (doctorTriageType == TriageType.GRAVITY) {
            fileMedecin = new PriorityQueue<>((p1, p2) -> Integer.compare(p2.getGravite(), p1.getGravite()));
        } else {
            fileRadio = new LinkedList<>();
        }
    }

    public void triagePatient(String name, int gravity, VisibleSymptom visibleSymptom) {
        Patient patient = new Patient(name, gravity, visibleSymptom);
        if (doctorTriageType == TriageType.FIFO) {
            fileMedecin.add(patient);
        } else {

        }

        if (visibleSymptom == VisibleSymptom.SPRAIN || visibleSymptom == VisibleSymptom.BROKEN_BONE) {
            fileRadio.add(patient);
        }
    }

    public Boolean estVide() {
        return fileMedecin.size() == 0;
    }

    public Boolean fileMedecinEstVide() {
        return fileMedecin.size() == 0;
    }
    public Boolean fileRadioEstVide() {
        return fileRadio.size() == 0;
    }

    public Patient getFirstPatientMedecin() {
        return fileMedecin.peek();
    }

    public Patient getLastMedecin() {
        Patient result = null;
        for (Patient p : fileMedecin) {
            result = p;
        }
        return result;
    }

    public Patient getFirstPatientRadio() {
        return fileRadio.peek();
    }

    public Patient getLastRadio() {
        Patient result = null;
        for (Patient p : fileRadio) {
            result = p;
        }
        return result;
    }

    public Patient traiterProchainPatient() {
        if (fileMedecin.size() > 0) {
            Patient patient =  fileMedecin.poll();
            if (patient.getSymptom() == VisibleSymptom.SPRAIN || patient.getSymptom() == VisibleSymptom.BROKEN_BONE) {
                patient = fileRadio.poll();
            }
            return patient;
        }
        return null;
    }

    public Patient traiterProchainPatientMedecin() {
        if (fileMedecin.size() > 0) {
            return fileMedecin.poll();
        }
        return null;
    }

    public Patient traiterProchainPatientRadio() {
        if (fileRadio.size() > 0) {
            return fileRadio.poll();
        }
        return null;

    }

}
