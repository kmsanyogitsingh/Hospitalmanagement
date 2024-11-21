package Management;

import java.util.List;
import java.util.Scanner;

import daoimpl.AppointmentsDaoImpI;
import entity.Appointment;

public class AppointmentsMange {
	private final AppointmentsDaoImpI appointmentDAO;

	public AppointmentsMange(AppointmentsDaoImpI appointmentDAO) {
		this.appointmentDAO = appointmentDAO;
		// TODO Auto-generated constructor stub
	}

	public void bookAppointment(Scanner scanner) {
		Appointment appointment = new Appointment();

		System.out.print("Enter Patient ID: ");
		appointment.setPatientId(scanner.nextInt());

		System.out.print("Enter Doctor ID: ");
		appointment.setDoctorId(scanner.nextInt());

		System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
		scanner.nextLine(); // Consume newline
		String date = scanner.nextLine();
		appointment.setAppointmentDate(java.sql.Date.valueOf(date));

		appointmentDAO.bookAppointment(appointment);
		System.out.println("Appointment booked successfully!");
	}

	public void viewAppointmentsForDoctor(Scanner scanner) {
		System.out.print("Enter Doctor ID: ");
		int doctorId = scanner.nextInt();

		List<Appointment> appointments = appointmentDAO.getAppointmentsForDoctor(doctorId);
		if (appointments.isEmpty()) {
			System.out.println("No appointments found for this doctor.");
		} else {
			System.out.println("Appointments for Doctor ID " + doctorId + ":");
			for (Appointment appointment : appointments) {
				System.out.println(
						"Patient ID: " + appointment.getPatientId() + ", Date: " + appointment.getAppointmentDate());
			}
		}
	}
}
