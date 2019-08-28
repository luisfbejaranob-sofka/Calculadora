package co.com.sofka.calculadora.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class Credito
{
	private Integer periodo;
	private Double interes;
	private Double amortizacionCapital;
	private Double cuota;
	private Double capitaPendiente;
}