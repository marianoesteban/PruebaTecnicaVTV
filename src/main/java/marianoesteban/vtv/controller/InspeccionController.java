package marianoesteban.vtv.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import marianoesteban.vtv.dto.MedicionesDto;
import marianoesteban.vtv.dto.ObservacionesDto;
import marianoesteban.vtv.model.Control;
import marianoesteban.vtv.model.Inspeccion;
import marianoesteban.vtv.service.AutomovilService;
import marianoesteban.vtv.service.InspeccionService;
import marianoesteban.vtv.service.InspectorService;

@Controller
public class InspeccionController {

	@Autowired
	private InspeccionService inspeccionService;

	@Autowired
	private InspectorService inspectorService;

	@Autowired
	private AutomovilService automovilService;

	@GetMapping("/inspeccionar")
	public String realizarInspeccion(Model model) {
		model.addAttribute("inspeccion", new Inspeccion());
		model.addAttribute("inspectores", inspectorService.listarInspectores());
		model.addAttribute("automoviles", automovilService.listarAutomoviles());
		model.addAttribute("observacionesForm", new ObservacionesDto());
		model.addAttribute("medicionesForm", new MedicionesDto());
		return "inspeccion";
	}

	@PostMapping("/inspeccionar")
	public String realizarInspeccion(@ModelAttribute Inspeccion inspeccion,
			@ModelAttribute ObservacionesDto observacionesForm, @ModelAttribute MedicionesDto medicionesForm,
			final RedirectAttributes redirectAttributes) {
		inspeccion.setEstadoInspeccion(
				calcularEstado(observacionesForm.getObservaciones(), medicionesForm.getMediciones()));
		try {
			inspeccionService.agregarInspeccion(inspeccion);
			redirectAttributes.addFlashAttribute("success", "La inspección ha sido agregada. El estado de la misma fue "
					+ inspeccion.getEstadoInspeccion().toUpperCase() + ".");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Se ha produco un error: la inspección no se pudo guardar.");
		}
		return "redirect:/";
	}

	@GetMapping("/abm/inspecciones")
	public String listInspecciones(Model model) {
		List<Inspeccion> inspecciones = inspeccionService.listarInspecciones();
		model.addAttribute("inspecciones", inspecciones);
		return "abm/inspeccion/list";
	}

	@GetMapping("/abm/inspecciones/agregar")
	public String addInspeccion(Model model) {
		model.addAttribute("inspeccion", new Inspeccion());
		model.addAttribute("inspectores", inspectorService.listarInspectores());
		model.addAttribute("automoviles", automovilService.listarAutomoviles());
		return "abm/inspeccion/add";
	}

	@PostMapping("/abm/inspecciones/agregar")
	public String addInspeccion(@ModelAttribute Inspeccion inspeccion, Model model,
			final RedirectAttributes redirectAttributes) {
		try {
			inspeccionService.agregarInspeccion(inspeccion);
			redirectAttributes.addFlashAttribute("success", "La inspección se agregó exitosamente.");
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("error",
					"Ha ocurrido un error: la inspección no se ha podido agregar.");
		}
		return "redirect:/abm/inspecciones";
	}

	@GetMapping("/abm/inspecciones/editar/{idInspeccion}")
	public String editInspeccion(@PathVariable("idInspeccion") long idInspeccion, Model model) {
		Inspeccion inspeccion = inspeccionService.getInspeccion(idInspeccion);
		model.addAttribute("inspeccion", inspeccion);
		model.addAttribute("inspectores", inspectorService.listarInspectores());
		model.addAttribute("automoviles", automovilService.listarAutomoviles());
		return "abm/inspeccion/edit";
	}

	@PostMapping("/abm/inspecciones/editar/{idInspeccion}")
	public String editInspeccion(@PathVariable("idInspeccion") long idInspeccion, @ModelAttribute Inspeccion inspeccion,
			Model model, final RedirectAttributes redirectAttributes) {
		try {
			inspeccionService.editarInspeccion(idInspeccion, inspeccion);
			redirectAttributes.addFlashAttribute("success", "La inspección se editó exitosamente.");
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("error",
					"Ha ocurrido un error: la inspección no se ha podido editar.");
		}
		return "redirect:/abm/inspecciones";
	}

	@GetMapping("/abm/inspecciones/eliminar/{idInspeccion}")
	public String deleteInspeccion(@PathVariable("idInspeccion") long idInspeccion,
			final RedirectAttributes redirectAttributes) {
		try {
			inspeccionService.eliminarInspeccion(idInspeccion);
			redirectAttributes.addFlashAttribute("success", "La inspección se eliminó exitosamente.");
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("error",
					"Ha ocurrido un error: la inspección no se ha podido eliminar.");
		}
		return "redirect:/abm/inspecciones";
	}

	@GetMapping("/informes/inspecciones/ultima-semana")
	public String getInspeccionesUltimaSemana(Model model) {
		// calcular la fecha de hace 6 días (inicio de última semana)
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DAY_OF_MONTH, -6);
		Date fecha = cal.getTime();
		model.addAttribute("inspecciones", inspeccionService.getInspeccionesRecientes(fecha));
		return "/informes/inspeccion/recent";
	}

	private String calcularEstado(List<Control> observaciones, List<Control> mediciones) {
		// ver si tiene algún "Rechazado"
		for (Control observacion : observaciones) {
			if (observacion.getEstado().equals("Rechazado"))
				return "Rechazado";
		}
		for (Control medicion : mediciones) {
			if (medicion.getEstado().equals("Rechazado"))
				return "Rechazado";
		}

		// ver si tiene algún "Condicional"
		for (Control observacion : observaciones) {
			if (observacion.getEstado().equals("Condicional"))
				return "Condicional";
		}
		for (Control medicion : mediciones) {
			if (medicion.getEstado().equals("Condicional"))
				return "Condicional";
		}

		// si no, son todos "Apto"
		return "Apto";
	}
}
