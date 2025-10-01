package ar.edu.unlam.pb2.dominio;

public class PlanPleno extends Plan {

	public PlanPleno(Double precioBase) {
		super(precioBase);
		this.setCantidadMaximaDeLibrosSimultaneos(2);
	}
}
