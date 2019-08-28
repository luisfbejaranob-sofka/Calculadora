package co.com.sofka.calculadora.controller;

import co.com.sofka.calculadora.function.Calculadora;
import co.com.sofka.calculadora.model.Salario;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraControllerTest
{
	/*@Test
	void showSumarDosNumeros()
	{
		Integer a = 27;
		Integer b = 86;
		Mono<Integer> expected = Mono.just(113);

		CalculadoraController controller = new CalculadoraController();

		assertEquals(expected , controller.sumar(a , b));
	}*/

	/*@Test
	void showCalcularAporteSaludEmpleado()
	{
		double salarioBase = 1000000;
		final Salario expected = Salario.builder()
			.salarioBase(salarioBase)
			.aporteSaludEmpleado(new Double(40000))
			.build();

		CalculadoraController controller = new CalculadoraController();

		assertEquals(expected.getAporteSaludEmpleado() , controller.Salario(salarioBase).getAporteSaludEmpleado());
	}*/

	@Test
	void showDeducirTodo()
	{
		double salarioBase = 1000000;
		final Salario expected = Salario.builder()
				.salarioBase(salarioBase)
				.aporteSaludEmpleado(40000.0)
				.aporteSaludEmpleador(120000.0)
				.aportePensionEmpleado(40000.0)
				.aportePensionEmpleador(85000.0)
				.aporteRiesgosLaborales(5000.0)
				.aporteCajaDeCompensacion(40000.0)
				.aporteFSP(0.0)
				.build();

		CalculadoraController controller = new CalculadoraController();

		assertEquals(expected.getAporteSaludEmpleado() , controller.deducirSalario(salarioBase).getAporteSaludEmpleado());
		assertEquals(expected.getAporteSaludEmpleador() , controller.deducirSalario(salarioBase).getAporteSaludEmpleador());
		assertEquals(expected.getAportePensionEmpleado() , controller.deducirSalario(salarioBase).getAportePensionEmpleado());
		assertEquals(expected.getAportePensionEmpleador() , controller.deducirSalario(salarioBase).getAportePensionEmpleador());
		assertEquals(expected.getAporteRiesgosLaborales() , controller.deducirSalario(salarioBase).getAporteRiesgosLaborales());
		assertEquals(expected.getAporteCajaDeCompensacion() , controller.deducirSalario(salarioBase).getAporteCajaDeCompensacion());
		assertEquals(expected.getAporteFSP() , controller.deducirSalario(salarioBase).getAporteFSP());
	}

	/*@Test
	void showDeducirSalario()
	{
		double salarioBase = 1000000;
		final Salario expected = Salario.builder()
				.salarioBase(salarioBase)
				.aporteSaludEmpleado(40000.0)
				.aporteSaludEmpleador(120000.0)
				.aportePensionEmpleado(40000.0)
				.aportePensionEmpleador(85000.0)
				.aporteRiesgosLaborales(5000.0)
				.aporteCajaDeCompensacion(40000.0)
				.aporteFSP(0.0)
				.pagoNetoEmpleado(1085000.0)
				.costoEmpleador(120000.0)
				.build();

		CalculadoraController controller = new CalculadoraController();

		assertEquals(expected , controller.deducirSalario(salarioBase));
	}*/
}