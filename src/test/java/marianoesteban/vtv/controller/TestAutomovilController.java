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
import marianoesteban.vtv.model.Marca;
import marianoesteban.vtv.model.Modelo;
import marianoesteban.vtv.model.Propietario;
import marianoesteban.vtv.model.Version;
import marianoesteban.vtv.service.AutomovilService;
import marianoesteban.vtv.service.MarcaService;
import marianoesteban.vtv.service.PropietarioService;

@WebMvcTest(AutomovilController.class)
public class TestAutomovilController {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private AutomovilService automovilService;
	
	@MockBean
	private MarcaService marcaService;

	@MockBean
	private PropietarioService propietarioService;

	private static final Marca MARCA_AUTOMOVIL_1 = new Marca(1L, "Fiat");
	private static final Marca MARCA_AUTOMOVIL_2 = new Marca(2L, "Volkswagen");

	private static final Modelo MODELO_AUTOMOVIL_1 = new Modelo(1L, MARCA_AUTOMOVIL_1, "Cronos");
	private static final Modelo MODELO_AUTOMOVIL_2 = new Modelo(2L, MARCA_AUTOMOVIL_2, "Gol");

	private static final Version VERSION_AUTOMOVIL_1 = new Version(1L, MODELO_AUTOMOVIL_1, "1.3L");
	private static final Version VERSION_AUTOMOVIL_2 = new Version(2L, MODELO_AUTOMOVIL_1, "1.8L");
	private static final Version VERSION_AUTOMOVIL_3 = new Version(3L, MODELO_AUTOMOVIL_2, "1.4L");

	private static final Propietario PROPIETARIO_1 = new Propietario(1L, "12345678", "Ricardo", "Lopez");
	private static final Propietario PROPIETARIO_2 = new Propietario(2L, "23456789", "Teresa", "Doaz");

	private static final Automovil AUTOMOVIL_1 = new Automovil(1L, "ABC123", VERSION_AUTOMOVIL_1, PROPIETARIO_1);
	private static final Automovil AUTOMOVIL_2 = new Automovil(2L, "DEF456", VERSION_AUTOMOVIL_2, PROPIETARIO_1);
	private static final Automovil AUTOMOVIL_3 = new Automovil(3L, "GH789IJ", VERSION_AUTOMOVIL_3, PROPIETARIO_2);

	@Test
	public void listAutomoviles() throws Exception {
		List<Automovil> automoviles = Arrays.asList(AUTOMOVIL_1, AUTOMOVIL_2, AUTOMOVIL_3);
		when(automovilService.listarAutomoviles()).thenReturn(automoviles);

		mockMvc.perform(get("/abm/automoviles").contentType(MediaType.TEXT_HTML)).andExpect(status().isOk())
				.andExpect(view().name("abm/automovil/list")).andExpect(xpath("count(//tr)").string("4"));
	}

	@Test
	public void addAutomovil() throws Exception {
		List<Propietario> propietarios = Arrays.asList(PROPIETARIO_1, PROPIETARIO_2);
		when(propietarioService.listarPropietarios()).thenReturn(propietarios);

		mockMvc.perform(get("/abm/automoviles/agregar").contentType(MediaType.TEXT_HTML)).andExpect(status().isOk())
				.andExpect(view().name("abm/automovil/add")).andExpect(xpath("count(//option)").string("2"));
	}

	@Test
	public void editAutomovil() throws Exception {
		List<Marca> marcas = Arrays.asList(MARCA_AUTOMOVIL_1, MARCA_AUTOMOVIL_2);
		List<Propietario> propietarios = Arrays.asList(PROPIETARIO_1, PROPIETARIO_2);
		when(marcaService.listarMarcas()).thenReturn(marcas);
		when(propietarioService.listarPropietarios()).thenReturn(propietarios);
		when(automovilService.getAutomovil(AUTOMOVIL_1.getId())).thenReturn(AUTOMOVIL_1);

		mockMvc.perform(get("/abm/automoviles/editar/" + AUTOMOVIL_1.getId()).contentType(MediaType.TEXT_HTML))
				.andExpect(status().isOk()).andExpect(view().name("abm/automovil/edit"))
				.andExpect(xpath("//input[@id='inputDominio']/@value").string(AUTOMOVIL_1.getDominio()))
				.andExpect(xpath("//select[@id='selectMarca']/option[@selected='selected']/@value").string(AUTOMOVIL_1.getVersionAutomovil().getModelo().getMarca().getId().toString()))
				.andExpect(xpath("//select[@id='selectPropietario']/option[@selected='selected']/@value")
						.string(AUTOMOVIL_1.getId().toString()));
	}
}
