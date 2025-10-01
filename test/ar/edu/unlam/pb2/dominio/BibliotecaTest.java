package ar.edu.unlam.pb2.dominio;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BibliotecaTest {
	private Biblioteca biblioteca;
	private Plan planAdherente;
	private Plan planPleno;
	@Before
	public void setUp() {
		biblioteca = new Biblioteca();
		planAdherente = new planAdherente(1000.0);
		planPleno = new planPleno(2000.0);
	}
	
	@Test
	public void dadoQueExisteUnaBibliotecaAgregoUnLibro() {
		String titulo = "El Fin de la Infancia";
		TipoDeLibro tipo = TipoDeLibro.NOVELA;
		Integer stock = 5;
		Libro libro1 = new Libro(titulo, tipo, stock);
		
		assertTrue(biblioteca.agregarLibro(libro1));		
	}
	
	@Test
	public void dadoQueExisteUnaBibliotecaAgregoUnSocioAdherente() {
		String nombre = "Jose";
		Integer dni = 11000222;
		Persona socioAdherente1 = new Persona(nombre, dni, planAdherente);
		
		assertTrue(biblioteca.agregarSocio(socioAdherente1));	
	}
}
