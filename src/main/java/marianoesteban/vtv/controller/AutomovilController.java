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

import marianoesteban.vtv.model.Automovil;
import marianoesteban.vtv.model.Propietario;
import marianoesteban.vtv.service.AutomovilService;
import marianoesteban.vtv.service.PropietarioService;

@Controller
public class AutomovilController {

	@Autowired
	private AutomovilService automovilService;

	@Autowired
	private PropietarioService propietarioService;

	@GetMapping("/abm/automoviles")
	public String listAutomoviles(Model model) {
		List<Automovil> automoviles = automovilService.listarAutomoviles();
		model.addAttribute("automoviles", automoviles);
		return "abm/automovil/list";
	}

	@GetMapping("/abm/automoviles/agregar")
	public String addAutomovil(Model model) {
		List<Propietario> propietarios = propietarioService.listarPropietarios();
		model.addAttribute("automovil", new Automovil());
		model.addAttribute("propietarios", propietarios);
		return "abm/automovil/add";
	}

	@PostMapping("/abm/automoviles/agregar")
	public String addAutomovil(@ModelAttribute Automovil automovil, Model model,
			final RedirectAttributes redirectAttributes) {
		// TODO: chequear que no haya otro auto con el mismo dominio
		// TODO: chequear que el dominio sea válido
		try {
			automovilService.agregarAutomovil(automovil);
			redirectAttributes.addFlashAttribute("success", "El automóvil se agregó exitosamente.");
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("error",
					"Ha ocurrido un error: el automóvil no se ha podido agregar.");
		}
		return "redirect:/abm/automoviles";
	}

	@GetMapping("/abm/automoviles/editar/{idAutomovil}")
	public String editAutomovil(@PathVariable("idAutomovil") long idAutomovil, Model model) {
		List<Propietario> propietarios = propietarioService.listarPropietarios();
		Automovil automovil = automovilService.getAutomovil(idAutomovil);
		model.addAttribute("automovil", automovil);
		model.addAttribute("propietarios", propietarios);
		return "abm/automovil/edit";
	}

	@PostMapping("/abm/automoviles/editar/{idAutomovil}")
	public String editAutomovil(@PathVariable("idAutomovil") long idAutomovil, @ModelAttribute Automovil automovil,
			Model model, final RedirectAttributes redirectAttributes) {
		// TODO: chequear que no haya otro auto con el mismo dominio
		// TODO: chequear que el dominio sea válido
		try {
			automovilService.editarAutomovil(idAutomovil, automovil);
			redirectAttributes.addFlashAttribute("success", "Los datos del automóvil se modificaron exitosamente.");
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("error",
					"Ha ocurrido un error: los datos del automóvil no se han podido modificar.");
		}
		return "redirect:/abm/automoviles";
	}

	@GetMapping("/abm/automoviles/eliminar/{idAutomovil}")
	public String deleteAutomovil(@PathVariable("idAutomovil") long idAutomovil,
			final RedirectAttributes redirectAttributes) {
		try {
			automovilService.eliminarAutomovil(idAutomovil);
			redirectAttributes.addFlashAttribute("success", "El automóvil ha sido eliminado exitosamente.");
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("error",
					"Ha ocurrido un error: no se ha podido eliminar el automóvil");
		}
		return "redirect:/abm/automoviles";
	}

	@GetMapping("/informes/automoviles/aptos")
	public String getAutomovilesAptos(Model model) {
		model.addAttribute("automoviles", automovilService.getAutomovilesPorEstadoInspeccion("Apto"));
		model.addAttribute("estadoInspeccion", "aptos");
		return "informes/automovil/estado";
	}

	@GetMapping("/informes/automoviles/condicionales")
	public String getAutomovilesCondicionales(Model model) {
		model.addAttribute("automoviles", automovilService.getAutomovilesPorEstadoInspeccion("Condicional"));
		model.addAttribute("estadoInspeccion", "condicionales");
		return "informes/automovil/estado";
	}

	@GetMapping("/informes/automoviles/rechazados")
	public String getAutomovilesRechazados(Model model) {
		model.addAttribute("automoviles", automovilService.getAutomovilesPorEstadoInspeccion("Rechazado"));
		model.addAttribute("estadoInspeccion", "rechazados");
		return "informes/automovil/estado";
	}
}
