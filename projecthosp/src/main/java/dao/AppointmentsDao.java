package dao;

import java.util.List;

import entity.Appointment;

public interface AppointmentsDao {
	void bookAppointment(Appointment appointment);

	List<Appointment> getAppointmentsForDoctor(int doctorId);
}
