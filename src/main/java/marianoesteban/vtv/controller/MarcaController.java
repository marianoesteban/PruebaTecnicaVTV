package marianoesteban.vtv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@GetMapping("/abm/marcas/agregar")
	public String addMarca(Model model) {
		model.addAttribute("marca", new Marca());
		return "abm/marca/add";
	}

	@PostMapping("/abm/marcas/agregar")
	public String addMarca(@ModelAttribute Marca marca, Model model, final RedirectAttributes redirectAttributes) {
		try {
			marcaService.agregarMarca(marca);
			redirectAttributes.addFlashAttribute("success", "La marca se agregó exitosamente");
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("error", "Ha ocurrido un error: la marca no se ha podido agregar");
		}
		return "redirect:/abm/marcas";
	}
}
