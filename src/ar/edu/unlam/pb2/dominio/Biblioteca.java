package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Biblioteca {

	private Set<Libro> libros;
	private Set<Persona> socios;
	private List<Prestamo> prestamos;
	
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

	public Boolean prestarLibro(Prestamo p) {
		if(p.getLibro().getStock() != 0 &&  p.getSocio().getLibros().size() < p.getSocio().getPlan().getCantidadMaximaDeLibrosSimultaneos()) {
			p.getSocio().getLibros().add(p.getLibro());
			p.getLibro().setStock(p.getLibro().getStock() - 1);
			return this.prestamos.add(p);
		}
		return false;
	}	
}
