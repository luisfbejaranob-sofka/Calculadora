package co.com.sofka.calculadora.function;

import co.com.sofka.calculadora.model.Credito;
import co.com.sofka.calculadora.model.Salario;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Calculadora
{
	public static final Function<List<Integer>,Mono<Integer> > sumar = lista ->
		Mono.just(
			lista.stream().
				reduce(0, (a, b) -> a + b)
		);


	public static final Function<List<Integer>,Mono<Integer> > restar =  lista ->
		Mono.just(
			lista.stream().
				reduce(0, (a, b) -> b - a)
		)
		.flatMap(x -> Mono.just(x * (-1)));

	public static final Function<Integer, Mono<String>> tablaMultiplicar = factor ->
	{
		StringBuilder tabla = new StringBuilder();
		IntStream.rangeClosed(0 , 10).forEach(
				digito -> tabla.append(factor + " x " + digito + " = " + factor * digito + "<br>")
		);
		return Mono.just(tabla.toString());
	};

	public static final Function<List<Integer>, Mono<List<Credito>>> amortizacion = lista ->
	{
		final Integer cuotas = lista.get(0);
		final Integer  credito = lista.get(1);
		final Double interes = 0.01;
		final Double amortizacionCapital = (double)  credito / cuotas;
		List<Credito> tablaPrestamo = new ArrayList<>(lista.get(0));
		IntStream.rangeClosed(1 , cuotas).forEach(
			periodo -> tablaPrestamo.add(
				Credito.builder()
				.periodo(periodo)
				.interes(credito * interes)
				.amortizacionCapital(amortizacionCapital)
				.cuota(credito * interes + amortizacionCapital)
				.capitaPendiente(credito - amortizacionCapital * periodo)
				.build()
			)
		);
		return Mono.just(tablaPrestamo);
	};

	final static Double APORTE_SALUD_EMPLEADO = 0.04;
	final static Double APORTE_SALUD_EMPLEADOR = 0.12;
	final static Double APORTE_PENSION_EMPLEADO = 0.04;
	final static Double APORTE_PENSION_EMPLEADOR = 0.085;
	final static Double APORTE_CAJA_COMPENSACION = 0.04;
	final static Double APORTE_RIESGOS_LABORALES = 0.005;
	final static Double APORTE_FSP = 0.01;
	final static Double SALARIO_MINIMO = 828116.0;

	public static BiFunction<Double , Double , Double> deducir()
	{
		return (a , b) -> a * b;
	}


	public static Function<Salario , Salario> deducirTodo()
	{
		return r -> r.toBuilder()
			.aporteSaludEmpleado(deducir().apply(r.getSalarioBase() , APORTE_SALUD_EMPLEADO))
			.aporteSaludEmpleador(deducir().apply(r.getSalarioBase() , APORTE_SALUD_EMPLEADOR))
			.aportePensionEmpleado(deducir().apply(r.getSalarioBase() , APORTE_PENSION_EMPLEADO))
			.aportePensionEmpleador(deducir().apply(r.getSalarioBase() , APORTE_PENSION_EMPLEADOR))
			.aporteCajaDeCompensacion(deducir().apply(r.getSalarioBase() , APORTE_CAJA_COMPENSACION))
			.aporteRiesgosLaborales(deducir().apply(r.getSalarioBase() , APORTE_RIESGOS_LABORALES))
			.aporteFSP(r.getSalarioBase() > (SALARIO_MINIMO * 4) ? deducir().apply(r.getSalarioBase() , APORTE_FSP) : 0.0)
			.build();
	}

	public static Function<Salario , Salario> sumarDeducciones()
	{
		return r -> r.toBuilder()
				.pagoNetoEmpleado(r.getSalarioBase() + (r.getAporteSaludEmpleador() + r.getAportePensionEmpleador()) - (r.getAporteSaludEmpleado() + r.getAportePensionEmpleado() + r.getAporteCajaDeCompensacion()) + r.getAporteFSP())
				.costoEmpleador(r.getAporteSaludEmpleado() + r.getAportePensionEmpleado() + r.getAporteCajaDeCompensacion())
				.build();
	}

	public static final Function<Double, Salario> deduccionSalario = salario -> {
		return Stream.of(Salario.builder()
			.salarioBase(salario)
			.build()).map(deducirTodo()).map(sumarDeducciones()).reduce(Salario.builder().build() , (r1 , r2) -> r2);
	};
}