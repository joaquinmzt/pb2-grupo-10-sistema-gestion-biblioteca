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
		Persona socioPleno1 = new Persona(nombre2, dni2, planPleno);
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

		String titulo2 = "Hellboy";
		TipoDeLibro tipo2 = TipoDeLibro.COMIC;
		Integer stock2 = 10;
		Libro libro2 = new Libro(titulo2, tipo2, stock2);

		assertTrue(biblioteca.agregarLibro(libro2));

		Prestamo prestamo2 = new Prestamo(socioAdherente1, libro2, fechaDePrestamo);
		assertFalse(biblioteca.prestarLibro(prestamo2));
	}

	@Test
	public void dadoQueExisteUnaBibliotecaYUnLibroSinStockNoEsPosiblePrestarlo() {
		String nombre = "Jose";
		Integer dni = 11000222;
		Persona socioAdherente1 = new Persona(nombre, dni, planAdherente);

		assertTrue(biblioteca.agregarSocio(socioAdherente1));

		String titulo = "El Fin de la Infancia";
		TipoDeLibro tipo = TipoDeLibro.NOVELA;
		Integer stock = 0;
		Libro libro1 = new Libro(titulo, tipo, stock);

		assertTrue(biblioteca.agregarLibro(libro1));

		LocalDate fechaDePrestamo = LocalDate.of(2025, 10, 1);

		Prestamo prestamo = new Prestamo(socioAdherente1, libro1, fechaDePrestamo);
		assertFalse(biblioteca.prestarLibro(prestamo));
	}

	@Test
	public void dadoQueExisteUnaBibliotecaYUnSocioQueRealizoUnPrestamoElMismoDevuelveElLibroDentroDeLaFechaLimite() {
		String nombre = "Jose";
		Integer dni = 11000222;
		Persona socioAdherente1 = new Persona(nombre, dni, planAdherente);

		biblioteca.agregarSocio(socioAdherente1);

		String titulo = "El Fin de la Infancia";
		TipoDeLibro tipo = TipoDeLibro.NOVELA;
		Integer stock = 5;
		Libro libro1 = new Libro(titulo, tipo, stock);

		biblioteca.agregarLibro(libro1);

		LocalDate fechaDePrestamo = LocalDate.of(2025, 10, 1);

		Prestamo prestamo = new Prestamo(socioAdherente1, libro1, fechaDePrestamo);

		LocalDate fechaDeDevolucion = LocalDate.of(2025, 10, 15);

		biblioteca.prestarLibro(prestamo);

		assertEquals(1, biblioteca.getPrestamos().size());
		assertTrue(biblioteca.devolverLibro(prestamo, fechaDeDevolucion));
		assertEquals(0, biblioteca.getPrestamos().size());
	}

	/*
	 * @Test public void
	 * dadoQueExisteUnaBibliotecaYUnSocioQueRealizoUnPrestamoElMismoPuedeDevolverloEnFechaCorrecta
	 * () { String nombre = "Jose"; Integer dni = 11000222; Persona socioAdherente1
	 * = new Persona(nombre, dni, planAdherente);
	 * 
	 * biblioteca.agregarSocio(socioAdherente1);
	 * 
	 * String titulo = "El Fin de la Infancia"; TipoDeLibro tipo =
	 * TipoDeLibro.NOVELA; Integer stock = 5; Libro libro1 = new Libro(titulo, tipo,
	 * stock);
	 * 
	 * biblioteca.agregarLibro(libro1);
	 * 
	 * LocalDate fechaDePrestamo = LocalDate.of(2025, 10, 1);
	 * 
	 * Prestamo prestamo = new Prestamo(socioAdherente1, libro1, fechaDePrestamo);
	 * 
	 * biblioteca.prestarLibro(prestamo);
	 * 
	 * double penalizacion = biblioteca.devolverLibro(prestamo);
	 * 
	 * assertEquals(0, penalizacion, 0); }
	 */

	@Test
	public void dadoQueExisteUnaBibliotecaYUnSocioQueRealizoUnPrestamoElMismoDevuelveElLibroFueraDeLaFechaLimiteYElMetodoDevuelveFalsePeroElLibroYaNoEstaEnElArray() {
		String nombre = "Jose";
		Integer dni = 11000222;
		Persona socioAdherente1 = new Persona(nombre, dni, planAdherente);

		biblioteca.agregarSocio(socioAdherente1);

		String titulo = "El Fin de la Infancia";
		TipoDeLibro tipo = TipoDeLibro.NOVELA;
		Integer stock = 5;
		Libro libro1 = new Libro(titulo, tipo, stock);

		biblioteca.agregarLibro(libro1);

		LocalDate fechaDePrestamo = LocalDate.of(2025, 10, 1);

		Prestamo prestamo = new Prestamo(socioAdherente1, libro1, fechaDePrestamo);

		LocalDate fechaDeDevolucion = LocalDate.of(2025, 11, 5);

		biblioteca.prestarLibro(prestamo);

		assertEquals(1, biblioteca.getPrestamos().size());
		assertFalse(biblioteca.devolverLibro(prestamo, fechaDeDevolucion));
		assertEquals(0, biblioteca.getPrestamos().size());
	}

	/*
	 * @Test public void
	 * dadoQueExisteUnaBibliotecaYUnSocioQueRealizoUnPrestamoElMismoPuedeDevolverloConPenalizacion
	 * () { String nombre = "Jose"; Integer dni = 11000222; Persona socioAdherente1
	 * = new Persona(nombre, dni, planAdherente);
	 * 
	 * biblioteca.agregarSocio(socioAdherente1);
	 * 
	 * String titulo = "El Fin de la Infancia"; TipoDeLibro tipo =
	 * TipoDeLibro.COMIC; Integer stock = 5; Libro libro1 = new Libro(titulo, tipo,
	 * stock);
	 * 
	 * biblioteca.agregarLibro(libro1);
	 * 
	 * LocalDate fechaDePrestamo = LocalDate.now().minusDays(20);
	 * 
	 * Prestamo prestamo = new Prestamo(socioAdherente1, libro1, fechaDePrestamo);
	 * 
	 * biblioteca.prestarLibro(prestamo);
	 * 
	 * double penalizacion = biblioteca.devolverLibro(prestamo);
	 * 
	 * assertEquals(600, penalizacion, 0); }
	 */

	/*
	 * @Test public void
	 * dadoQueExisteUnaBibliotecaYUnSocioQueRealizoUnPrestamoElMismoNoSePudoDevolver
	 * () { String nombre = "Jose"; Integer dni = 11000222; Persona socioAdherente1
	 * = new Persona(nombre, dni, planAdherente);
	 * 
	 * biblioteca.agregarSocio(socioAdherente1);
	 * 
	 * String titulo = "El Fin de la Infancia"; TipoDeLibro tipo =
	 * TipoDeLibro.COMIC; Integer stock = 5; Libro libro1 = new Libro(titulo, tipo,
	 * stock);
	 * 
	 * biblioteca.agregarLibro(libro1);
	 * 
	 * LocalDate fechaDePrestamo = LocalDate.now().minusDays(20);
	 * 
	 * Prestamo prestamo = new Prestamo(socioAdherente1, libro1, fechaDePrestamo);
	 * 
	 * //biblioteca.prestarLibro(prestamo);
	 * 
	 * double penalizacion = biblioteca.devolverLibro(prestamo);
	 * 
	 * assertEquals(-1, penalizacion, 0); }
	 */

	@Test
	public void dadoQueExisteUnSocioConUnPlanAdherenteSePuedeCalcularLaCuotaQueVaAPagar() {
		String nombre = "Jose";
		Integer dni = 11000222;
		Persona socioAdherente1 = new Persona(nombre, dni, planAdherente);

		biblioteca.agregarSocio(socioAdherente1);

		Double valorEsperado = 1000.0;
		Double valorObtenido = planAdherente.calcularCuota(socioAdherente1);

		assertEquals(valorEsperado, valorObtenido);

	}

	@Test
	public void dadoQueExisteUnSocioConUnPlanAdherenteSePuedeCalcularLaCuotaQueVaAPagarConDescuentoYaQueRealizoTresPrestamos() {
		String nombre = "Jose";
		Integer dni = 11000222;
		Persona socioAdherente1 = new Persona(nombre, dni, planAdherente);

		biblioteca.agregarSocio(socioAdherente1);

		String titulo = "El Fin de la Infancia";
		TipoDeLibro tipo = TipoDeLibro.NOVELA;
		Integer stock = 5;
		Libro libro1 = new Libro(titulo, tipo, stock);

		biblioteca.agregarLibro(libro1);

		LocalDate fechaDePrestamo = LocalDate.of(2025, 10, 1);

		Prestamo prestamo = new Prestamo(socioAdherente1, libro1, fechaDePrestamo);
		biblioteca.prestarLibro(prestamo);
		biblioteca.devolverLibro(prestamo, fechaDePrestamo);

		String titulo2 = "Hellboy";
		TipoDeLibro tipo2 = TipoDeLibro.COMIC;
		Integer stock2 = 3;
		Libro libro2 = new Libro(titulo2, tipo2, stock2);

		biblioteca.agregarLibro(libro2);

		Prestamo prestamo2 = new Prestamo(socioAdherente1, libro2, fechaDePrestamo);
		biblioteca.prestarLibro(prestamo2);
		biblioteca.devolverLibro(prestamo2, fechaDePrestamo);

		String titulo3 = "Tokyo Ghoul";
		TipoDeLibro tipo3 = TipoDeLibro.COMIC;
		Integer stock3 = 2;
		Libro libro3 = new Libro(titulo3, tipo3, stock3);

		biblioteca.agregarLibro(libro3);

		Prestamo prestamo3 = new Prestamo(socioAdherente1, libro3, fechaDePrestamo);
		biblioteca.prestarLibro(prestamo3);
		biblioteca.devolverLibro(prestamo3, fechaDePrestamo);

		Double valorEsperado = 950.0;
		Double valorObtenido = planAdherente.calcularCuota(socioAdherente1);

		assertEquals(valorEsperado, valorObtenido);

	}

	@Test
	public void dadoQueExisteUnSocioConUnPlanPlenoSePuedeCalcularLaCuotaQueVaAPagar() {
		String nombre = "Jose";
		Integer dni = 11000222;
		Persona socioPleno1 = new Persona(nombre, dni, planPleno);

		biblioteca.agregarSocio(socioPleno1);

		Double valorEsperado = 2000.0;
		Double valorObtenido = planPleno.calcularCuota(socioPleno1);

		assertEquals(valorEsperado, valorObtenido);

	}
	
	@Test
	public void dadoQueExisteUnSocioConUnPlanPlenoSePuedeCalcularLaCuotaQueVaAPagarConDescuentoYaQueRealizoCincoPrestamos() {
		String nombre = "Jose";
		Integer dni = 11000222;
		Persona socioPleno1 = new Persona(nombre, dni, planPleno);

		biblioteca.agregarSocio(socioPleno1);

		String titulo = "El Fin de la Infancia";
		TipoDeLibro tipo = TipoDeLibro.NOVELA;
		Integer stock = 5;
		Libro libro1 = new Libro(titulo, tipo, stock);

		biblioteca.agregarLibro(libro1);

		LocalDate fechaDePrestamo = LocalDate.of(2025, 10, 1);

		Prestamo prestamo = new Prestamo(socioPleno1, libro1, fechaDePrestamo);
		biblioteca.prestarLibro(prestamo);
		biblioteca.devolverLibro(prestamo, fechaDePrestamo);

		String titulo2 = "Hellboy";
		TipoDeLibro tipo2 = TipoDeLibro.COMIC;
		Integer stock2 = 3;
		Libro libro2 = new Libro(titulo2, tipo2, stock2);

		biblioteca.agregarLibro(libro2);

		Prestamo prestamo2 = new Prestamo(socioPleno1, libro2, fechaDePrestamo);
		biblioteca.prestarLibro(prestamo2);
		biblioteca.devolverLibro(prestamo2, fechaDePrestamo);

		String titulo3 = "Tokyo Ghoul";
		TipoDeLibro tipo3 = TipoDeLibro.COMIC;
		Integer stock3 = 2;
		Libro libro3 = new Libro(titulo3, tipo3, stock3);

		biblioteca.agregarLibro(libro3);

		Prestamo prestamo3 = new Prestamo(socioPleno1, libro3, fechaDePrestamo);
		biblioteca.prestarLibro(prestamo3);
		biblioteca.devolverLibro(prestamo3, fechaDePrestamo);
		
		Prestamo prestamo4 = new Prestamo(socioPleno1, libro3, fechaDePrestamo);
		biblioteca.prestarLibro(prestamo4);
		biblioteca.devolverLibro(prestamo4, fechaDePrestamo);
		
		Prestamo prestamo5 = new Prestamo(socioPleno1, libro3, fechaDePrestamo);
		biblioteca.prestarLibro(prestamo5);

		Double valorEsperado = 1700.0;
		Double valorObtenido = planPleno.calcularCuota(socioPleno1);

		assertEquals(valorEsperado, valorObtenido);

	}

}
