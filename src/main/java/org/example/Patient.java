package org.example;

public class Patient {
    private String name;
    private Integer gravite;
    private VisibleSymptom visibleSymptom;

    public Patient() {
        name = "David";
        gravite = 0;
        visibleSymptom = VisibleSymptom.MIGRAINE;
    }

    public Patient(String name, Integer gravite, VisibleSymptom symptom) {
        this.name = name;
        this.gravite = gravite;
        this.visibleSymptom = symptom;
    }

    public String getName() {
        return this.name;
    }

    public Integer getGravite() {
        return gravite;
    }

    public VisibleSymptom getSymptom() {
        return visibleSymptom;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Patient) {
            Patient p = (Patient)obj;
            return p.getName().equals(getName()) && p.getGravite() == getGravite() 
            && p.getSymptom() == getSymptom(); 
        }

        return false;
    }
}
