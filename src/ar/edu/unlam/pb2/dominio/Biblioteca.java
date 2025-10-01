package ar.edu.unlam.pb2.dominio;

import java.util.HashSet;
import java.util.Set;

public class Biblioteca {

	private Set<Libro> libros;
	private Set<Persona> socios;
	
	public Biblioteca() {
		this.libros = new HashSet<>();
		this.socios = new HashSet<>();
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

	public boolean agregarSocio(Persona persona) {
		return this.socios.add(persona);
	}	
}
