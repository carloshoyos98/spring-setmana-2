package org.formacio.mvc;

import org.formacio.repositori.AgendaService;
import org.formacio.repositori.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MockController {
	
	@Autowired
	AgendaService servei_agenda;
	
	@RequestMapping(path = "/nombre")
	@ResponseBody
	public int nombreContactes() {
		return servei_agenda.nombreContactes();
	}
	@RequestMapping(path = "/telefon")
	@ResponseBody
	public String obte_telefon(@RequestParam( name = "id",
											  required = true) String id) {
		return servei_agenda.recupera(id).getTelefon();
	}
	
	@RequestMapping(path = "/contacte/{id}", produces= {MediaType.APPLICATION_JSON_VALUE,
														MediaType.APPLICATION_XML_VALUE})

	public ResponseEntity<Persona> contacte(@PathVariable String id ) {
		if (!servei_agenda.getBD().containsKey(id)) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(servei_agenda.recupera(id), HttpStatus.OK);
			
	}
	
	@RequestMapping(path = "/afegir", method = RequestMethod.POST)
	@ResponseBody
	public void afegir(@RequestParam(name="id",
									 required=true) String id,
					   @RequestParam(name="nom",
							   		 required=true) String nom,
					   @RequestParam(name="telefon",
							   		 required=true) String telefon) {
		servei_agenda.inserta(id, nom, telefon);
		
	}
}

