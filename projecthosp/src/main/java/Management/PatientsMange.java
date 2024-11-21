package Management;

import java.util.List;
import java.util.Scanner;

import daoimpl.PatientsDaoImpI;
import entity.Patient;

public class PatientsMange {
	private final PatientsDaoImpI patientDAO;

	public PatientsMange(PatientsDaoImpI patientDAO) {
		// Connection connection = ConnectionProvider.getConnection();
		this.patientDAO = patientDAO;
	}

	public void addPatient(Scanner scanner) {
		Patient patient = new Patient();

		System.out.print("Enter Patient's Name: ");
		patient.setName(scanner.nextLine());

		System.out.print("Enter Patient's Age: ");
		patient.setAge(scanner.nextInt());

		System.out.print("Enter Patient's Gender: ");
		scanner.nextLine(); // Consume newline
		patient.setGender(scanner.nextLine());

		patientDAO.addPatient(patient);
		System.out.println("Patient added successfully!");
	}

	public void viewPatients() {
		List<Patient> patients = patientDAO.getAllPatients();
		if (patients.isEmpty()) {
			System.out.println("No patients found.");
		} else {
			System.out.println("List of Patients:");
			for (Patient patient : patients) {
				System.out.println("ID: " + patient.getId() + ", Name: " + patient.getName());
			}
		}
	}
}
