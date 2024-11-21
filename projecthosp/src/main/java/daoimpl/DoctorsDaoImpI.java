package daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.DoctorsDao;
import entity.Doctor;

public class DoctorsDaoImpI implements DoctorsDao {
	private Connection connection;

	public DoctorsDaoImpI(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void addDoctor(Doctor doctor) {
		String query = "INSERT INTO doctors (name, specialization) VALUES (?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, doctor.getName());
			preparedStatement.setString(2, doctor.getSpecialization());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Doctor> getAllDoctors() {
		List<Doctor> doctors = new ArrayList<>();
		String query = "SELECT * FROM doctors";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Doctor doctor = new Doctor();
				doctor.setId(resultSet.getInt("id"));
				doctor.setName(resultSet.getString("name"));
				doctor.setSpecialization(resultSet.getString("specialization"));
				doctors.add(doctor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doctors;
	}

	@Override
	public Doctor getDoctorById(int id) {
		Doctor doctor = null;
		String query = "SELECT * FROM doctors WHERE id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				doctor = new Doctor();
				doctor.setId(resultSet.getInt("id"));
				doctor.setName(resultSet.getString("name"));
				doctor.setSpecialization(resultSet.getString("specialization"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doctor;
	}
}
