package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Biblioteca {

	private Set<Libro> libros;
	private Set<Persona> socios;
	private List<Prestamo> prestamos;
	private final double PENALIZACION_POR_DIA = 100.00;

	public Biblioteca() {
		this.libros = new HashSet<>();
		this.socios = new HashSet<>();
		this.prestamos = new ArrayList<>();
	}

	public Set<Libro> getLibros() {
		return libros;
	}

	public void setLibros(Set<Libro> libros) {
		this.libros = libros;
	}

	public Boolean agregarLibro(Libro libro) {
		return this.libros.add(libro);
	}

	public Boolean agregarSocio(Persona persona) {
		return this.socios.add(persona);
	}

	public Set<Persona> getSocios() {
		return socios;
	}

	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	public Boolean prestarLibro(Prestamo p) {
		if (p.getLibro().getStock() != 0
				&& p.getSocio().getLibros().size() < p.getSocio().getPlan().getCantidadMaximaDeLibrosSimultaneos()) {
			p.getSocio().getLibros().add(p.getLibro());
			p.getLibro().setStock(p.getLibro().getStock() - 1);
			p.getSocio().setContadorPrestamos(p.getSocio().getContadorPrestamos() + 1);
			return this.prestamos.add(p);
		}
		return false;
	}

	public boolean devolverLibro(Prestamo prestamo, LocalDate fechaDeDevolucion) {
		if(fechaDeDevolucion.isBefore(prestamo.getFechaDeDevolucion())) {
			prestamo.getSocio().getLibros().remove(prestamo.getLibro());
			prestamo.getLibro().setStock(prestamo.getLibro().getStock() + 1);
			return this.prestamos.remove(prestamo);
		}
		prestamo.getSocio().getLibros().remove(prestamo.getLibro());
		prestamo.getLibro().setStock(prestamo.getLibro().getStock() + 1);
		this.prestamos.remove(prestamo);
		return false;
	}

	/* public double devolverLibro(Prestamo prestamo) {
		double penalizacion = 0;
		if (prestamo.getFechaDeDevolucion().isBefore(LocalDate.now())) {
			Integer cantidadDiasPenalizacion = (int) ChronoUnit.DAYS.between(prestamo.getFechaDeDevolucion(),
					LocalDate.now());
			penalizacion = cantidadDiasPenalizacion * PENALIZACION_POR_DIA;
		}
		if (this.prestamos.remove(prestamo)) {
			if (penalizacion <= 0) {
				return 0;
			} else {
				return penalizacion;
			}
		} else {
			return -1;
		}

	} */
}
