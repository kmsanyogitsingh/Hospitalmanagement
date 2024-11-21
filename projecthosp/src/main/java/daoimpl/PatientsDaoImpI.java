package daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.PatientsDao;
import entity.Patient;

public class PatientsDaoImpI implements PatientsDao {
	private Connection connection;

	public PatientsDaoImpI(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void addPatient(Patient patient) {
		String query = "INSERT INTO patients (name, age, gender) VALUES (?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, patient.getName());
			preparedStatement.setInt(2, patient.getAge());
			preparedStatement.setString(3, patient.getGender());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error adding patient: " + e.getMessage());
		}
	}

	@Override
	public List<Patient> getAllPatients() {
		List<Patient> patients = new ArrayList<>();
		String query = "SELECT * FROM patients";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Patient patient = new Patient();
				patient.setId(resultSet.getInt("id"));
				patient.setName(resultSet.getString("name"));
				patient.setAge(resultSet.getInt("age"));
				patient.setGender(resultSet.getString("gender"));
				patients.add(patient);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patients;
	}

	@Override
	public Patient getPatientById(int id) {
		Patient patient = null;
		String query = "SELECT * FROM patients WHERE id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				patient = new Patient();
				patient.setId(resultSet.getInt("id"));
				patient.setName(resultSet.getString("name"));
				patient.setAge(resultSet.getInt("age"));
				patient.setGender(resultSet.getString("gender"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patient;
	}
}
