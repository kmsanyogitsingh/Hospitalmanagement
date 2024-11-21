package dao;

import java.util.List;

import entity.Doctor;

public interface DoctorsDao {
	void addDoctor(Doctor doctor);

	List<Doctor> getAllDoctors();

	Doctor getDoctorById(int id);
}
