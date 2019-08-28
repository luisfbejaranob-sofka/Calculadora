package co.com.sofka.calculadora.function;

import co.com.sofka.calculadora.model.Salario;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest
{
	/*@Test
	void showSumerDosNumeros()
	{
		int a = 27;
		int b = 86;
		List<Integer> integerList = new ArrayList<>();
		integerList.add(a);
		integerList.add(b);
		Mono<Integer> expected = Mono.just(113);

		assertEquals(expected , Calculadora.sumar.apply(integerList));
	}*/

	/*@Test
	void showCalcularDeduccuiones()
	{
		Double salario = 1000000.0;
		final Double APORTE_SALUD_EMPLEADO = salario * 0.04;
		final Double APORTE_SALUD_EMPLEADOR = salario * 0.012;
		final Double APORTE_PENSION_EMPLEADO = salario * 0.04;
		final Double APORTE_PENSION_EMPLEADOR = salario * 0.085;
		final Double APORTE_RIESGOS_LABORALES = salario * 0.005;
		final Double APORTE_CAJA_COMPENSACION = salario * 0.04;
		final Double APORTE_FSP = salario * 0.01;
		final Double TOTAL_SALARIO = (salario + (APORTE_PENSION_EMPLEADOR + APORTE_SALUD_EMPLEADOR)) - (APORTE_CAJA_COMPENSACION + APORTE_RIESGOS_LABORALES + APORTE_PENSION_EMPLEADO + APORTE_SALUD_EMPLEADO + APORTE_FSP);
		Mono<Salario> expected = Mono.just(
			Salario.builder()
			.salarioBase(salario)
			.aporteSaludEmpleado(APORTE_SALUD_EMPLEADO)
			.aporteSaludEmpleador(APORTE_SALUD_EMPLEADOR)
			.aportePensionEmpleado(APORTE_PENSION_EMPLEADO)
			.aportePensionEmpleador(APORTE_PENSION_EMPLEADOR)
			.aporteCajaDeCompensacion(APORTE_CAJA_COMPENSACION)
			.aporteRiesgosLaborales(APORTE_RIESGOS_LABORALES)
			.pagoNetoEmpleado(TOTAL_SALARIO)
			.aporteFSP(APORTE_FSP)
			.costoEmpleador(APORTE_SALUD_EMPLEADOR + APORTE_PENSION_EMPLEADOR + APORTE_RIESGOS_LABORALES)
			.build());

		assertEquals(expected , Calculadora.deduccionSalario.apply(salario));
	}*/


}