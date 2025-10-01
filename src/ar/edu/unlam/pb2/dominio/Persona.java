package ar.edu.unlam.pb2.dominio;

public class Persona {

	private String nombre;
	private Integer dni;
	private Plan plan;

	public Persona(String nombre, Integer dni, Plan plan) {
		this.nombre = nombre;
		this.plan = plan;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Object getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

}
