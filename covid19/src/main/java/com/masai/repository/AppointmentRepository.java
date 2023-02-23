package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.beans.Appointment;
import com.masai.beans.VaccineRegistration;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
