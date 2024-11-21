package dao;

import java.util.List;

import entity.Patient;

public interface PatientsDao {
	void addPatient(Patient patient);

	List<Patient> getAllPatients();

	Patient getPatientById(int id);
}
