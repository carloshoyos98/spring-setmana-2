package org.formacio.mvc;

import org.formacio.repositori.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
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
}