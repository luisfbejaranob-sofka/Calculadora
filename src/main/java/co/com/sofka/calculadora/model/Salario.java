package co.com.sofka.calculadora.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class Salario
{
	private final Double salarioBase;
	private final Double aporteSaludEmpleado;
	private final Double aporteSaludEmpleador;
	private final Double aportePensionEmpleado;
	private final Double aportePensionEmpleador;
	private final Double aporteRiesgosLaborales;
	private final Double aporteCajaDeCompensacion;
	private final Double aporteFSP;
	private final Double pagoNetoEmpleado;
	private final Double costoEmpleador;
}