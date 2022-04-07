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

import marianoesteban.vtv.model.Propietario;
import marianoesteban.vtv.service.InspeccionService;
import marianoesteban.vtv.service.PropietarioService;

@WebMvcTest(PropietarioController.class)
public class TestPropietarioController {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private PropietarioService propietarioService;
	
	@MockBean
	private InspeccionService inspeccionService;
	
	private static final Propietario PROPIETARIO_1 = new Propietario(1L, "12345678", "Ricardo", "Lopez");
	private static final Propietario PROPIETARIO_2 = new Propietario(2L, "23456789", "Teresa", "Doaz");
	private static final Propietario PROPIETARIO_3 = new Propietario(3L, "23456789", "Juan", "Perez");
	
	@Test
	public void listarPropietarios() throws Exception {
		List<Propietario> propietarios = Arrays.asList(PROPIETARIO_1, PROPIETARIO_2, PROPIETARIO_3);
		when(propietarioService.listarPropietarios()).thenReturn(propietarios);
		
		mockMvc.perform(get("/abm/propietarios")
				.contentType(MediaType.TEXT_HTML))
			.andExpect(status().isOk())
			.andExpect(view().name("abm/propietario/list"))
			.andExpect(xpath("count(//tr)").string("4")); // 3 propietarios + el encabezado de la tabla
	}
	
	@Test
	public void editPropietarios() throws Exception {
		when(propietarioService.getPropietario(PROPIETARIO_1.getId())).thenReturn(PROPIETARIO_1);
		
		mockMvc.perform(get("/abm/propietarios/editar/" + PROPIETARIO_1.getId())
				.contentType(MediaType.TEXT_HTML))
			.andExpect(status().isOk())
			.andExpect(view().name("abm/propietario/edit"))
			.andExpect(xpath("//input[@id='inputDni']/@value").string(PROPIETARIO_1.getDni().toString()))
			.andExpect(xpath("//input[@id='inputNombres']/@value").string(PROPIETARIO_1.getNombres()))
			.andExpect(xpath("//input[@id='inputApellido']/@value").string(PROPIETARIO_1.getApellido()));
	}

	@Test
	public void showAllPropietarios() throws Exception {
		List<Propietario> propietarios = Arrays.asList(PROPIETARIO_1, PROPIETARIO_2, PROPIETARIO_3);
		when(propietarioService.listarPropietarios()).thenReturn(propietarios);

		mockMvc.perform(get("/informes/propietarios")
				.contentType(MediaType.TEXT_HTML))
			.andExpect(status().isOk())
			.andExpect(view().name("informes/propietario/all"))
			.andExpect(xpath("count(//tr)").string("4")); // 3 propietarios + el encabezado de la tabla
	}
}
