package com.example.demo.controller.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.controller.entity.Trabajador;

public interface TrabajadorDao extends CrudRepository<Trabajador, Long>{

	@Query(nativeQuery = true, value = "SELECT * FROM vehiculo WHERE email = :email")
	Trabajador findByEmail(@Param("email") String email);
}
