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

import marianoesteban.vtv.model.Inspector;
import marianoesteban.vtv.service.InspeccionService;
import marianoesteban.vtv.service.InspectorService;

@Controller
public class InspectorController {

	@Autowired
	private InspectorService inspectorService;

	@Autowired
	private InspeccionService inspeccionService;

	@GetMapping("/abm/inspectores")
	public String listInspectores(Model model) {
		List<Inspector> inspectores = inspectorService.listarInspectores();
		model.addAttribute("inspectores", inspectores);
		return "abm/inspector/list";
	}

	@GetMapping("/abm/inspectores/agregar")
	public String addInspector(Model model) {
		model.addAttribute("inspector", new Inspector());
		return "abm/inspector/add";
	}

	@PostMapping("/abm/inspectores/agregar")
	public String addAutomovil(@ModelAttribute Inspector inspector, Model model,
			final RedirectAttributes redirectAttributes) {
		try {
			inspectorService.agregarInspector(inspector);
			redirectAttributes.addFlashAttribute("success", "El inspector se agregó exitosamente.");
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("error",
					"Ha ocurrido un error: el inspector no se ha podido agregar.");
		}
		return "redirect:/abm/inspectores";
	}

	@GetMapping("/abm/inspectores/editar/{idInspector}")
	public String editInspector(@PathVariable("idInspector") long idInspector, Model model) {
		Inspector inspector = inspectorService.getInspector(idInspector);
		model.addAttribute("inspector", inspector);
		return "abm/inspector/edit";
	}

	@PostMapping("/abm/inspectores/editar/{idInspector}")
	public String editInspector(@PathVariable("idInspector") long idInspector, @ModelAttribute Inspector inspector,
			Model model, final RedirectAttributes redirectAttributes) {
		try {
			inspectorService.editarInspector(idInspector, inspector);
			redirectAttributes.addFlashAttribute("success", "Los datos del inspector se modificaron exitosamente.");
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("error",
					"Ha ocurrido un error: los datos del inspector no se han podido modificar.");
		}
		return "redirect:/abm/inspectores";
	}

	@GetMapping("/abm/inspectores/eliminar/{idInspector}")
	public String deleteInspector(@PathVariable("idInspector") long idInspector,
			final RedirectAttributes redirectAttributes) {
		// TODO: chequear si el inspector realizó alguna inspección antes de eliminar
		try {
			inspectorService.eliminarInspector(idInspector);
			redirectAttributes.addFlashAttribute("success", "El inspector ha sido eliminado exitosamente.");
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("error",
					"Ha ocurrido un error: no se ha podido eliminar el inspector.");
		}
		return "redirect:/abm/inspectores";
	}

	@GetMapping("/informes/inspectores")
	public String showAllInspectores(Model model) {
		model.addAttribute("inspectores", inspectorService.listarInspectores());
		return "informes/inspector/all";
	}

	@GetMapping("/informes/inspectores/ultimos-tres-dias/{idInspector}")
	public String getInspeccionesUltimosTresDias(@PathVariable("idInspector") long idInspector, Model model) {
		model.addAttribute("inspector", inspectorService.getInspector(idInspector));
		model.addAttribute("inspecciones", inspeccionService.getInspeccionesUltimosTresDias(idInspector));
		return "informes/inspector/recent-inspecciones";
	}
}
