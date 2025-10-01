package ar.edu.unlam.pb2.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class BibliotecaTest {
	private Biblioteca biblioteca;
	private Plan planAdherente;
	private Plan planPleno;

	@Before
	public void setUp() {
		biblioteca = new Biblioteca();
		planAdherente = new PlanAdherente(1000.0);
		planPleno = new PlanPleno(2000.0);
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

	@Test
	public void dadoQueExisteUnaBibliotecaYDosPersonasConElMismoDNINoSePermiteRegistrarloComoSocioDosVeces() {
		String nombre = "Maria";
		Integer dni = 22333444;
		Persona socioAdherente1 = new Persona(nombre, dni, planAdherente);
		assertTrue(biblioteca.agregarSocio(socioAdherente1));

		String nombre2 = "Maria";
		Integer dni2 = 22333444;
		Persona socioPleno1 = new Persona(nombre, dni, planPleno);
		assertFalse(biblioteca.agregarSocio(socioPleno1));

	}

	@Test
	public void dadoQueExisteUnaBibliotecaUnSocioPuedePedirPrestadoUnLibro() {
		String nombre = "Jose";
		Integer dni = 11000222;
		Persona socioAdherente1 = new Persona(nombre, dni, planAdherente);

		assertTrue(biblioteca.agregarSocio(socioAdherente1));

		String titulo = "El Fin de la Infancia";
		TipoDeLibro tipo = TipoDeLibro.NOVELA;
		Integer stock = 5;
		Libro libro1 = new Libro(titulo, tipo, stock);

		assertTrue(biblioteca.agregarLibro(libro1));

		LocalDate fechaDePrestamo = LocalDate.of(2025, 10, 1);

		Prestamo prestamo = new Prestamo(socioAdherente1, libro1, fechaDePrestamo);

		assertTrue(biblioteca.prestarLibro(prestamo));
	}

	@Test
	public void dadoQueExisteUnaBibliotecaYUnPrestamoSePuedeCalcularLaFechaDeDevolucionLimiteDelLibro() {
		String nombre = "Jose";
		Integer dni = 11000222;
		Persona socioAdherente1 = new Persona(nombre, dni, planAdherente);

		assertTrue(biblioteca.agregarSocio(socioAdherente1));

		String titulo = "El Fin de la Infancia";
		TipoDeLibro tipo = TipoDeLibro.NOVELA;
		Integer stock = 5;
		Libro libro1 = new Libro(titulo, tipo, stock);

		assertTrue(biblioteca.agregarLibro(libro1));

		LocalDate fechaDePrestamo = LocalDate.of(2025, 10, 1);

		Prestamo prestamo = new Prestamo(socioAdherente1, libro1, fechaDePrestamo);
		
		assertTrue(biblioteca.prestarLibro(prestamo));
		
		LocalDate fechaDeseada = LocalDate.of(2025, 11, 1);
		LocalDate fechaObtenida = prestamo.getFechaDeDevolucion();
		assertEquals(fechaDeseada, fechaObtenida);
	}
	
	@Test
	public void dadoQueExisteUnaBibliotecaYUnSocioAdherenteConunLibroNoPuedePedirPrestadoOtro() {
		String nombre = "Jose";
		Integer dni = 11000222;
		Persona socioAdherente1 = new Persona(nombre, dni, planAdherente);
		
		assertTrue(biblioteca.agregarSocio(socioAdherente1));
		
		String titulo = "El Fin de la Infancia";
		TipoDeLibro tipo = TipoDeLibro.NOVELA;
		Integer stock = 5;
		Libro libro1 = new Libro(titulo, tipo, stock);
		
		assertTrue(biblioteca.agregarLibro(libro1));
		
		LocalDate fechaDePrestamo = LocalDate.of(2025, 10, 1);
		
		Prestamo prestamo = new Prestamo(socioAdherente1, libro1, fechaDePrestamo);
		
		assertTrue(biblioteca.prestarLibro(prestamo));
		
		
	}
	
	

}
