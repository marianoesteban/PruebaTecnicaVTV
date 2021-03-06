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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import marianoesteban.vtv.exception.VersionExistsException;
import marianoesteban.vtv.model.Marca;
import marianoesteban.vtv.model.Version;
import marianoesteban.vtv.service.MarcaService;
import marianoesteban.vtv.service.VersionService;

@Controller
public class VersionController {

	@Autowired
	private VersionService versionService;

	@Autowired
	private MarcaService marcaService;

	@GetMapping("/abm/versiones")
	public String listVersiones(Model model) {
		List<Version> versiones = versionService.listarVersiones();
		model.addAttribute("versiones", versiones);
		return "abm/version/list";
	}

	@GetMapping("/abm/versiones/agregar")
	public String addVersion(Model model) {
		List<Marca> marcas = marcaService.listarMarcas();
		model.addAttribute("version", new Version());
		model.addAttribute("marcas", marcas);
		return "abm/version/add";
	}

	@PostMapping("/abm/versiones/agregar")
	public String addVersion(@Valid @ModelAttribute Version version, BindingResult bindingResult, Model model,
			final RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("marcas", marcaService.listarMarcas());
			return "abm/version/add";
		}
		try {
			versionService.agregarVersion(version);
			redirectAttributes.addFlashAttribute("success", "La versi??n se agreg?? exitosamente.");
		} catch (VersionExistsException versionExistsException) {
			redirectAttributes.addFlashAttribute("error", "La versi??n especificada del autom??vil ya existe.");
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("error", "Ha ocurrido un error: la versi??n no se ha podido agregar");
		}
		return "redirect:/abm/versiones";
	}

	@GetMapping("/abm/versiones/eliminar/{idVersion}")
	public String deleteVersion(@PathVariable("idVersion") long idVersion,
			final RedirectAttributes redirectAttributes) {
		try {
			versionService.eliminarVersion(idVersion);
			redirectAttributes.addFlashAttribute("success", "La versi??n ha sido eliminada exitosamente.");
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("error", "Ha ocurrido un error: no se ha podido eliminar la versi??n.");
		}
		return "redirect:/abm/versiones";
	}

	@ResponseBody
	@GetMapping("/json/versiones")
	public String getVersionesByModeloJson(@RequestParam("idModelo") long idModelo) {
		Gson gson = new Gson();
		return gson.toJson(versionService.listarVersionesPorModelo(idModelo));
	}
}
