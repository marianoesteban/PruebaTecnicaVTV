package marianoesteban.vtv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import marianoesteban.vtv.model.Marca;
import marianoesteban.vtv.service.MarcaService;

@Controller
public class MarcaController {

	@Autowired
	private MarcaService marcaService;
	
	@GetMapping("/abm/marcas")
	public String listMarcas(Model model) {
		List<Marca> marcas = marcaService.listarMarcas();
		model.addAttribute("marcas", marcas);
		return "abm/marca/list";
	}
}
