package ar.edu.unlam.pb2.dominio;

public class Plan {
	private Double precioBase;
	
	public Plan(Double precioBase) {
		this.precioBase = precioBase;
	}

	public Double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(Double precioBase) {
		this.precioBase = precioBase;
	}
}
