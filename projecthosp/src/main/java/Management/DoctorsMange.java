package Management;

import daoimpl.DoctorsDaoImpI;
import entity.Doctor;

import java.util.List;
import java.util.Scanner;

public class DoctorsMange {
	private final DoctorsDaoImpI doctorDAO;

	public DoctorsMange(DoctorsDaoImpI doctorDAO) {
		this.doctorDAO = doctorDAO;
	}

	public void addDoctor(Scanner scanner) {
		Doctor doctor = new Doctor();

		System.out.print("Enter Doctor's Name: ");
		doctor.setName(scanner.nextLine());

		System.out.print("Enter Specialization: ");
		doctor.setSpecialization(scanner.nextLine());

		doctorDAO.addDoctor(doctor);
		System.out.println("Doctor added successfully!");
	}

	public void viewDoctors() {
		List<Doctor> doctors = doctorDAO.getAllDoctors();
		if (doctors.isEmpty()) {
			System.out.println("No doctors found.");
		} else {
			System.out.println("List of Doctors:");
			for (Doctor doctor : doctors) {
				System.out.println("ID: " + doctor.getId() + ", Name: " + doctor.getName() + ", Specialization: "
						+ doctor.getSpecialization());
			}
		}
	}
}
