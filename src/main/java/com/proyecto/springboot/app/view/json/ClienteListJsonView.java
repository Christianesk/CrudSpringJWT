package com.proyecto.springboot.app.view.json;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.proyecto.springboot.app.models.entity.Cliente;

@SuppressWarnings("unchecked")
@Component("listar.json")
public class ClienteListJsonView extends MappingJackson2JsonView{

	@Override
	protected Object filterModel(Map<String, Object> model) {
		model.remove("titulo");
		model.remove("page");
		
		
		//Page<Cliente> clientes = (Page<Cliente>) model.get("clientes");
		List<Cliente> clientes = (List<Cliente>) model.get("clientes");
		
		model.remove("clientes");
		model.put("clientes", clientes);
		
		
		return super.filterModel(model);
	}
	
	

}
