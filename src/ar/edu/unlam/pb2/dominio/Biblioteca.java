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
	
	public Persona buscarSocioPorDni(Integer dni) {
		for(Persona p : this.socios) {
			if(p.getDni().equals(dni)) {
				return p;
			}
		}
		return null;
	}
	
	public Libro buscarLibroPorNombre(String nombre) {
		for(Libro l : this.libros) {
			if(l.getTitulo().equalsIgnoreCase(nombre)) {
				return l;
			}
		}
		return null;
	}
	
	public Prestamo buscarPrestamoPorSocioYLibro(Persona socio, Libro libro) {
		for(Prestamo p : this.prestamos) {
			if(p.getSocio().equals(socio) && p.getLibro().equals(libro)) {
				return p;
			}
		}
		return null;
	}

	public Boolean prestarLibro(Prestamo prestamo) {
		if (prestamo.getLibro().getStock() != 0
				&& prestamo.getSocio().getLibros().size() < prestamo.getSocio().getPlan().getCantidadMaximaDeLibrosSimultaneos()) {
			prestamo.getSocio().getLibros().add(prestamo.getLibro());
			prestamo.getLibro().setStock(prestamo.getLibro().getStock() - 1);
			prestamo.getSocio().setContadorPrestamos(prestamo.getSocio().getContadorPrestamos() + 1);
			return this.prestamos.add(prestamo);
		}
		return false;
	}

	public Boolean devolverLibro(Prestamo prestamo, LocalDate fechaDeDevolucion) {
		if(fechaDeDevolucion.isBefore(prestamo.getFechaDePrestamo())) {
			return false;
		}
		
		if (fechaDeDevolucion.isAfter(prestamo.getFechaDeDevolucion())) {
			Long diasDeRetraso = ChronoUnit.DAYS.between(prestamo.getFechaDeDevolucion(), fechaDeDevolucion);
			prestamo.getSocio().setMontoPagarPenalizacion(diasDeRetraso * this.PENALIZACION_POR_DIA);
		}

		prestamo.getSocio().getLibros().remove(prestamo.getLibro());
		prestamo.getLibro().setStock(prestamo.getLibro().getStock() + 1);
		return this.prestamos.remove(prestamo);
	}

	public Double calcularCuotaAPagarDeUnSocio(Persona socio) {
		return socio.getPlan().calcularCuota(socio) + socio.getMontoPagarPenalizacion();
	}
}
