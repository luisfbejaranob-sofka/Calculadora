package co.com.sofka.calculadora.controller;

import co.com.sofka.calculadora.model.Credito;
import co.com.sofka.calculadora.model.Salario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraControllerTest
{
	private CalculadoraController controller;

	@BeforeEach
	void setUp() {
		controller = new CalculadoraController();
	}

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

		assertEquals(expected , controller.amortizacionCredito(valor , cuotas));
	}

	@Test
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

		assertEquals(expected , controller.deducirSalario(salarioBase));
	}
}