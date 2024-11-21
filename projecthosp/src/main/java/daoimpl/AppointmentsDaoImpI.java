package daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.AppointmentsDao;
import entity.Appointment;

public class AppointmentsDaoImpI implements AppointmentsDao {
	private Connection connection;

	public AppointmentsDaoImpI(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void bookAppointment(Appointment appointment) {
		String query = "INSERT INTO appointments (patient_id, doctor_id, appointment_date) VALUES (?, ?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, appointment.getPatientId());
			preparedStatement.setInt(2, appointment.getDoctorId());
			preparedStatement.setDate(3, new java.sql.Date(appointment.getAppointmentDate().getTime()));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Appointment> getAppointmentsForDoctor(int doctorId) {
		List<Appointment> appointments = new ArrayList<>();
		String query = "SELECT * FROM appointments WHERE doctor_id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, doctorId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Appointment appointment = new Appointment();
				appointment.setId(resultSet.getInt("id"));
				appointment.setPatientId(resultSet.getInt("patient_id"));
				appointment.setDoctorId(resultSet.getInt("doctor_id"));
				appointment.setAppointmentDate(resultSet.getDate("appointment_date"));
				appointments.add(appointment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return appointments;
	}
}
