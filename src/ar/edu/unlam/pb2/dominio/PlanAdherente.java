package ar.edu.unlam.pb2.dominio;

public class PlanAdherente extends Plan {

	public PlanAdherente(Double precioBase) {
		super(precioBase);
		this.setCantidadMaximaDeLibrosSimultaneos(1);
	}
}
