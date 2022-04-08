package marianoesteban.vtv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import marianoesteban.vtv.model.Marca;
import marianoesteban.vtv.model.Modelo;
import marianoesteban.vtv.service.MarcaService;
import marianoesteban.vtv.service.ModeloService;

@Controller
public class ModeloController {

	@Autowired
	private ModeloService modeloService;

	@Autowired
	private MarcaService marcaService;

	@GetMapping("/abm/modelos")
	public String listModelos(Model model) {
		List<Modelo> modelos = modeloService.listarModelos();
		model.addAttribute("modelos", modelos);
		return "abm/modelo/list";
	}

	@GetMapping("/abm/modelos/agregar")
	public String addModelo(Model model) {
		List<Marca> marcas = marcaService.listarMarcas();
		model.addAttribute("modelo", new Modelo());
		model.addAttribute("marcas", marcas);
		return "abm/modelo/add";
	}

	@PostMapping("/abm/modelos/agregar")
	public String addModelo(@ModelAttribute Modelo modelo, Model model, final RedirectAttributes redirectAttributes) {
		try {
			modeloService.agregarModelo(modelo);
			redirectAttributes.addFlashAttribute("success", "El modelo se agregó exitosamente.");
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("error", "Ha ocurrido un error: el modelo no se ha podido agregar");
		}
		return "redirect:/abm/modelos";
	}

	@GetMapping("/abm/modelos/eliminar/{idModelo}")
	public String deleteModelo(@PathVariable("idModelo") long idModelo, final RedirectAttributes redirectAttributes) {
		try {
			modeloService.eliminarModelo(idModelo);
			redirectAttributes.addFlashAttribute("success", "El modelo ha sido eliminado exitosamente.");
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("error", "Ha ocurrido un error: no se ha podido eliminar el modelo.");
		}
		return "redirect:/abm/modelos";
	}
}
