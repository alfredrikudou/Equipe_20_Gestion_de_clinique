package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class ClinicTest {
    @Test
    void laFileEstInitialementVide() {
        Clinic clinic = new Clinic();
        assertTrue(clinic.estVide());
    }

    @Test
    void quandAjouterPatientAvecMigraine() {
        Clinic clinic = new Clinic();

        Patient patient = new Patient("David", 0, VisibleSymptom.MIGRAINE);
        clinic.triagePatient(patient.getName(), patient.getGravite(), patient.getSymptom());

        Patient p = clinic.getFirstPatientMedecin();
        assertTrue(p.equals(patient));

        assertTrue(clinic.fileRadioEstVide());
    }

    @Test
    void quandAjouter2Patients_AvecFluFileNonVide() {
        Clinic clinic = new Clinic();

        Patient patientDoe = new Patient("Doe", 0, VisibleSymptom.FLU);

        clinic.triagePatient("John", 0, VisibleSymptom.MIGRAINE);
        clinic.triagePatient(patientDoe.getName(), patientDoe.getGravite(), patientDoe.getSymptom());

        Patient p = clinic.traiterProchainPatientMedecin();
        p = clinic.traiterProchainPatientMedecin(); // on retire le second patient de la Queue

        assertTrue(p.equals(patientDoe));
        assertTrue(clinic.fileRadioEstVide());
    }

    @Test
    void quandAjouterPatient_AvecEntorse() {
        Clinic clinic = new Clinic();

        Patient patient = new Patient("David", 0, VisibleSymptom.SPRAIN);
        clinic.triagePatient(patient.getName(), patient.getGravite(), patient.getSymptom());

        Patient p = clinic.getFirstPatientMedecin();
        assertTrue(p.equals(patient));

        Patient p2 = clinic.getFirstPatientRadio();
        assertTrue(p2.equals(patient));
    }

    @Test
    void quandAppelerPatient_RetirerPatientDeLaFileMedecin() {
        Clinic clinic = new Clinic();

        clinic.triagePatient("John", 0, VisibleSymptom.MIGRAINE);
        assertFalse(clinic.estVide());

        Patient patient = clinic.traiterProchainPatient();
        assertTrue(clinic.estVide());

    }

    @Test
    void quandAppelerPatientRadioLogie_RetirerPatientDeLaFileRadiologie() {
        Clinic clinic = new Clinic();

        clinic.triagePatient("Doe", 0, VisibleSymptom.SPRAIN);
        assertFalse(clinic.fileRadioEstVide());


        Patient patient = clinic.traiterProchainPatient();
        assertTrue(clinic.fileRadioEstVide());

    }

    @Test
    void quandPatientDansFileAttente_AlorsPatientAvecGrandeGravitePostionUn() {
        Clinic clinic = new Clinic(TriageType.GRAVITY, TriageType.FIFO);
        clinic.triagePatient("John", 0, VisibleSymptom.MIGRAINE);
        clinic.triagePatient("Doe", 7, VisibleSymptom.FLU);

        Patient p = clinic.getFirstPatientMedecin();
        Patient patientDoe = new Patient("Doe", 7, VisibleSymptom.FLU);

        assertTrue(p.equals(patientDoe));
    }

    @Test
    void quandPatientDansLesFilesAttentes_AlorsPatientGrandePrioriteEnRadioLogie() {
        Clinic clinic = new Clinic(TriageType.GRAVITY, TriageType.FIFO);
        clinic.triagePatient("John", 0, VisibleSymptom.BROKEN_BONE);
        clinic.triagePatient("Doe", 7, VisibleSymptom.SPRAIN);

        Patient p = clinic.traiterProchainPatientRadio();
        p = clinic.traiterProchainPatientRadio();
        Patient patientDoe = new Patient("Doe", 7, VisibleSymptom.FLU);

        assertTrue(p.equals(patientDoe));
    }
}
