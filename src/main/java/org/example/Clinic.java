package org.example;

import java.util.LinkedList;

public class Clinic {
    private LinkedList<Patient> fileMedecin; 
    private LinkedList<Patient> fileRadio; 


    public Clinic(/* ... */) {
        fileMedecin = new LinkedList<>();
        fileRadio = new LinkedList<>();
    }

    public void triagePatient(String name, int gravity, VisibleSymptom visibleSymptom) {
        Patient patient = new Patient(name, gravity, visibleSymptom);
        fileMedecin.addLast(patient);
        if (visibleSymptom == VisibleSymptom.SPRAIN || visibleSymptom == VisibleSymptom.BROKEN_BONE) {
            fileRadio.addLast(patient);
        }
    }

    public Boolean estVide() {
        return fileMedecin.size() == 0;
    }

    public Patient getPatientMedecin(Patient patient) {
        Integer patientIndex = fileMedecin.indexOf(patient);
        return patientIndex == -1 ? null : fileMedecin.get(patientIndex);
    }

    public Patient getPatientMedecin(String name) {
        for (Patient patient: fileMedecin) {
            if (patient.getName().equals(name)) {
                return patient;
            }
        }
        return null;
    }

    public Patient getPatientMedecin(Integer patientIndex) {
        return patientIndex == -1 ? null : fileMedecin.get(patientIndex);
    }

    public Patient getPatientRadio(String name) {
        for (Patient patient: fileRadio) {
            if (patient.getName().equals(name)) {
                return patient;
            }
        }
        return null;
    }

}
