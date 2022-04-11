package marianoesteban.vtv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import marianoesteban.vtv.model.Version;
import marianoesteban.vtv.service.VersionService;

@Controller
public class VersionController {

	@Autowired
	private VersionService versionService;
	
	@GetMapping("/abm/versiones")
	public String listVersiones(Model model) {
		List<Version> versiones = versionService.listarVersiones();
		model.addAttribute("versiones", versiones);
		return "abm/version/list";
	}
}
