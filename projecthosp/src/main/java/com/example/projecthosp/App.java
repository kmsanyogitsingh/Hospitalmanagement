package com.example.projecthosp;

import Management.AppointmentsMange;
import Management.DoctorsMange;
import Management.PatientsMange;
import daoimpl.AppointmentsDaoImpI;
import daoimpl.DoctorsDaoImpI;
import daoimpl.PatientsDaoImpI;
import utility.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		try (Connection connection = ConnectionProvider.getConnection()) {
			Scanner sc = new Scanner(System.in);

			DoctorsDaoImpI doctorDAO = new DoctorsDaoImpI(connection);
			PatientsDaoImpI patientDAO = new PatientsDaoImpI(connection);
			AppointmentsDaoImpI appointmentDAO = new AppointmentsDaoImpI(connection);

			DoctorsMange doctorManager = new DoctorsMange(doctorDAO);
			PatientsMange patientManager = new PatientsMange(patientDAO);
			AppointmentsMange appointmentManager = new AppointmentsMange(appointmentDAO);

			showMainMenu(sc, doctorManager, patientManager, appointmentManager);
		} catch (SQLException e) {
			System.err.println("Error connecting to the database: " + e.getMessage());
		}
	}

	public static void showMainMenu(Scanner sc, DoctorsMange doctorManager, PatientsMange patientManager,
			AppointmentsMange appointmentManager) {
		while (true) {
			System.out.println("\nHOSPITAL MANAGEMENT SYSTEM");
			System.out.println("1. Add Patient");
			System.out.println("2. View Patients");
			System.out.println("3. Add Doctor");
			System.out.println("4. View Doctors");
			System.out.println("5. Book Appointment");
			System.out.println("6. View Appointments for a Doctor");
			System.out.println("7. Exit");
			System.out.print("Enter your choice: ");
			int choice = sc.nextInt();
			sc.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				patientManager.addPatient(sc);
			case 2:
				patientManager.viewPatients();
			case 3:
				doctorManager.addDoctor(sc);
			case 4:
				doctorManager.viewDoctors();
			case 5:
				appointmentManager.bookAppointment(sc);
			case 6:
				appointmentManager.viewAppointmentsForDoctor(sc);
			case 7: {
				System.out.println("Thanks for visiting the Hospital Management System. Stay healthy!");
				return;
			}
			default:
				System.out.println("Invalid choice! Please enter a valid option.");
			}
		}
	}
}
