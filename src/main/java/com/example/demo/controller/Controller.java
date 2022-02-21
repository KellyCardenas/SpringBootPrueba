package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.dao.TrabajadorDao;
import com.example.demo.controller.entity.Trabajador;

//servicio desplegado
@RestController
public class Controller {

	@Autowired
	private TrabajadorDao trabajadorDao;
	//permite hcer un control del servicio web que sera desplegado 

	@GetMapping("/hola")
	public String hola() {
		return "hola desde spring";
	}
	
	//me trae todos los resgistros de la base de datos
	@GetMapping("/trabajador")
	public Iterable<Trabajador> getTrabajador(){
		return trabajadorDao.findAll();
	}
		
		//metodos http, solicitud al servidor
		
		//metodo post del servicio, para insertar los datos a la base de datos
		//le indicamos la ruta para guardar
		//request body me va a permitir transformar ese json en el objeto java
		@PostMapping("/guardar")
		public void guardar(@RequestBody Trabajador trabajador) {
			trabajadorDao.save(trabajador);
		
		}
		
		@GetMapping("/consultar")
		public List<Trabajador> consultar() {
			
			return (List<Trabajador>) trabajadorDao.findAll();
		}
		
		
		//me permite consultar por id
		@GetMapping("/consultar/{id}")
		public Optional<Trabajador> consultarPorId(@PathVariable("id") Long id) {
			System.out.println("id " + id);
			return trabajadorDao.findById(id);
		
		}
	
		@GetMapping("/consultarEmail/{email}")
		public Trabajador cosultarEmail(@PathVariable("email") String email){
			
			return trabajadorDao.findByEmail(email);
		}
		
		//permie eliminar un trabajador por id
		@DeleteMapping("/eliminar/{id}")
		public String eliminar(@PathVariable("id") Long id) {
			
			try {
				trabajadorDao.deleteById(id);
				
				return "Trabajador eliminado";

			} catch (Exception e) {
				return "No se logro eliminar el trabajador";
				
			}
			
		}
		
		//permite actualizar un trabajador
		@PutMapping("/actualizar")
		public Trabajador actualizar(@RequestBody Trabajador trabajador) {
			
			return trabajadorDao.save(trabajador);
		}
}
