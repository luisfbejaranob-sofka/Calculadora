package co.com.sofka.calculadora.function;

import co.com.sofka.calculadora.model.Credito;
import co.com.sofka.calculadora.model.Salario;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest
{
	@Test
	void showAmortizacionCredito()
	{
		double valor = 1000000;
		int cuotas = 2;
		Credito p1 = new Credito(1 , 10000.0 , 500000.0 , 510000.0 , 500000.0);
		Credito p2 = new Credito(2 , 10000.0 , 500000.0 , 510000.0 , 0.0);
		List<Credito> expected = new ArrayList<>();
		expected.add(p1);
		expected.add(p2);

		assertEquals(expected , Calculadora.amortizacionCredito.apply(valor , cuotas));
	}

	@Test
	void showDeducirTodo()
	{
		double salarioBase = 1000000;
		final Salario salario = Salario.builder()
			.salarioBase(salarioBase)
			.build();

		final Salario expected = Salario.builder()
			.salarioBase(salarioBase)
			.aporteSaludEmpleado(40000.0)
			.aporteSaludEmpleador(120000.0)
			.aportePensionEmpleado(40000.0)
			.aportePensionEmpleador(85000.0)
			.aporteRiesgosLaborales(5000.0)
			.aporteCajaDeCompensacion(40000.0)
			.aporteFSP(0.0)
			.pagoNetoEmpleado(null)
			.costoEmpleador(null)
			.build();

		assertEquals(expected , Calculadora.deducirTodo.apply(salario));
	}

	@Test
	void showSumarDeducciones()
	{
		double salarioBase = 1000000;
		final Salario salario = Salario.builder()
			.salarioBase(salarioBase)
			.aporteSaludEmpleado(40000.0)
			.aporteSaludEmpleador(120000.0)
			.aportePensionEmpleado(40000.0)
			.aportePensionEmpleador(85000.0)
			.aporteRiesgosLaborales(5000.0)
			.aporteCajaDeCompensacion(40000.0)
			.aporteFSP(0.0)
			.build();

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

		assertEquals(expected , Calculadora.sumarDeducciones.apply(salario));
	}

	@Test
	void showDeduccionSalario()
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

		assertEquals(expected , Calculadora.deduccionSalario.apply(salarioBase));
	}
}