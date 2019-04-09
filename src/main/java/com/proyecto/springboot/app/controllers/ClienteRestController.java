package com.proyecto.springboot.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.springboot.app.models.entity.Cliente;
import com.proyecto.springboot.app.models.service.IClienteService;
import com.proyecto.springboot.app.view.xml.ClienteList;

@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {
	
	@Autowired
	private IClienteService clienteService;

	@GetMapping(value = "/listar")
	public List<Cliente> listar() {
		return clienteService.findAll();
	}
	
	//si se requiere q sea en formato xml y json con ?format=xml o  ?format=json
	@GetMapping(value = "/listarXmlORest")
	public ClienteList listarXmlORest() {
		return new ClienteList(clienteService.findAll());
	}
}
