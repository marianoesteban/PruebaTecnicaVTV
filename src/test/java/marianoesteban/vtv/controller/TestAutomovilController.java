package marianoesteban.vtv.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import marianoesteban.vtv.model.Automovil;
import marianoesteban.vtv.model.Propietario;
import marianoesteban.vtv.service.AutomovilService;
import marianoesteban.vtv.service.PropietarioService;

@WebMvcTest(AutomovilController.class)
public class TestAutomovilController {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private AutomovilService automovilService;
	
	@MockBean
	private PropietarioService propietarioService;
	
	private static final Propietario PROPIETARIO_1 = new Propietario(1L, "12345678", "Ricardo", "Lopez");
	private static final Propietario PROPIETARIO_2 = new Propietario(2L, "23456789", "Teresa", "Doaz");
	
	private static final Automovil AUTOMOVIL_1 = new Automovil(1L, "ABC123", "Volkswagen", "Gol", PROPIETARIO_1);
	private static final Automovil AUTOMOVIL_2 = new Automovil(2L, "DEF456", "Peugeot", "207", PROPIETARIO_1);
	private static final Automovil AUTOMOVIL_3 = new Automovil(3L, "GH789IJ", "Renault", "Clio", PROPIETARIO_2);
	
	@Test
	public void listAutomoviles() throws Exception {
		List<Automovil> automoviles = Arrays.asList(AUTOMOVIL_1, AUTOMOVIL_2, AUTOMOVIL_3);
		when(automovilService.listarAutomoviles()).thenReturn(automoviles);
		
		mockMvc.perform(get("/abm/automoviles")
				.contentType(MediaType.TEXT_HTML))
			.andExpect(status().isOk())
			.andExpect(view().name("abm/automovil/list"))
			.andExpect(xpath("count(//tr)").string("4"));
	}
	
	@Test
	public void addAutomovil() throws Exception {
		List<Propietario> propietarios = Arrays.asList(PROPIETARIO_1, PROPIETARIO_2);
		when(propietarioService.listarPropietarios()).thenReturn(propietarios);
		
		mockMvc.perform(get("/abm/automoviles/agregar")
				.contentType(MediaType.TEXT_HTML))
			.andExpect(status().isOk())
			.andExpect(view().name("abm/automovil/add"))
			.andExpect(xpath("count(//option)").string("2"));
	}
	
	@Test
	public void editAutomovil() throws Exception {
		List<Propietario> propietarios = Arrays.asList(PROPIETARIO_1, PROPIETARIO_2);
		when(propietarioService.listarPropietarios()).thenReturn(propietarios);
		when(automovilService.getAutomovil(AUTOMOVIL_1.getId())).thenReturn(AUTOMOVIL_1);
		
		mockMvc.perform(get("/abm/automoviles/editar/" + AUTOMOVIL_1.getId())
				.contentType(MediaType.TEXT_HTML))
			.andExpect(status().isOk())
			.andExpect(view().name("abm/automovil/edit"))
			.andExpect(xpath("//input[@id='inputDominio']/@value").string(AUTOMOVIL_1.getDominio()))
			.andExpect(xpath("//input[@id='inputMarca']/@value").string(AUTOMOVIL_1.getMarca()))
			.andExpect(xpath("//input[@id='inputModelo']/@value").string(AUTOMOVIL_1.getModelo()))
			.andExpect(xpath("//select[@id='selectPropietario']/option[@selected='selected']/@value").string(AUTOMOVIL_1.getId().toString()))
			.andExpect(xpath("count(//option)").string("2"));
	}
}
