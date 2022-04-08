package marianoesteban.vtv.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import marianoesteban.vtv.model.Propietario;
import marianoesteban.vtv.service.InspeccionService;
import marianoesteban.vtv.service.PropietarioService;

@Controller
public class PropietarioController {

	@Autowired
	private PropietarioService propietarioService;

	@Autowired
	private InspeccionService inspeccionService;

	@GetMapping("/abm/propietarios")
	public String listPropietarios(Model model) {
		List<Propietario> propietarios = propietarioService.listarPropietarios();
		model.addAttribute("propietarios", propietarios);
		return "abm/propietario/list";
	}

	@GetMapping("/abm/propietarios/agregar")
	public String addPropietario(Model model) {
		model.addAttribute("propietario", new Propietario());
		return "abm/propietario/add";
	}

	@PostMapping("/abm/propietarios/agregar")
	public String addPropietario(@Valid @ModelAttribute Propietario propietario, BindingResult bindingResult,
			Model model, final RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors())
			return "abm/propietario/add";
		try {
			propietarioService.agregarPropietario(propietario);
			redirectAttributes.addFlashAttribute("success", "El propietario se agregó exitosamente.");
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("error",
					"Ha ocurrido un error: el propietario no se ha podido agregar.");
		}
		return "redirect:/abm/propietarios";
	}

	@GetMapping("/abm/propietarios/editar/{idPropietario}")
	public String editPropietario(@PathVariable("idPropietario") long idPropietario, Model model) {
		Propietario propietario = propietarioService.getPropietario(idPropietario);
		model.addAttribute("propietario", propietario);
		return "abm/propietario/edit";
	}

	@PostMapping("/abm/propietarios/editar/{idPropietario}")
	public String editPropietario(@PathVariable("idPropietario") long idPropietario,
			@Valid @ModelAttribute Propietario propietario, BindingResult bindingResult, Model model,
			final RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors())
			return "abm/propietario/edit";
		try {
			propietarioService.editarPropietario(idPropietario, propietario);
			redirectAttributes.addFlashAttribute("success", "Los datos del propietario se modificaron exitosamente.");
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("error",
					"Ha ocurrido un error: los datos del propietario no se han podido modificar.");
		}
		return "redirect:/abm/propietarios";
	}

	@GetMapping("/abm/propietarios/eliminar/{idPropietario}")
	public String deletePropietario(@PathVariable("idPropietario") long idPropietario,
			final RedirectAttributes redirectAttributes) {
		// TODO: chequear si el propietario tiene algún automóvil antes de eliminar
		try {
			propietarioService.eliminarPropietario(idPropietario);
			redirectAttributes.addFlashAttribute("success", "El propietario ha sido eliminado exitosamente.");
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("error",
					"Ha ocurrido un error: no se ha podido eliminar el propietario.");
		}
		return "redirect:/abm/propietarios";
	}

	@GetMapping("/informes/propietarios")
	public String showAllPropietarios(Model model) {
		model.addAttribute("propietarios", propietarioService.listarPropietarios());
		return "informes/propietario/all";
	}

	@GetMapping("/informes/propietarios/inspecciones/{idPropietario}")
	public String getInspecciones(@PathVariable("idPropietario") long idPropietario, Model model) {
		model.addAttribute("propietario", propietarioService.getPropietario(idPropietario));
		model.addAttribute("inspecciones", inspeccionService.getInspeccionesPorPropietario(idPropietario));
		return "informes/propietario/inspecciones";
	}
}
