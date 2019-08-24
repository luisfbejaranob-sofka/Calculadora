package co.com.sofka.calculadora.function;

import co.com.sofka.calculadora.model.Credito;
import co.com.sofka.calculadora.model.Salario;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
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

	public static final Function<Integer, Mono<String>> tablaMultiplicar = x ->
	{
		List<String> tabla = new ArrayList<>();
		for (int i = 1; i <= 10; i++)
		{
			int result = x * i;
			tabla.add(x + " x " + i + " = " + result);
		}
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

	public static final Function<Double, Mono<List<Salario>> > deduccionSalario = r -> {
		final Double APORTE_COMPENSACION = r*0.04;
		final Double APORTE_RIESGOS = r*0.005;
		final Double APORTE_PENSION_EMPLEADO = r*0.04;
		final Double APORTE_PENSION_EMPLEADOR = r*0.085;
		final Double APORTE_SALUD_EMPLEADO = r*0.04;
		final Double APORTE_SALUD_EMPLEADOR = r*0.012;
		final Double APORTE_FSP = r*0.01;
		final Double TOTAL_SALARIO = (r+(APORTE_PENSION_EMPLEADOR+APORTE_SALUD_EMPLEADOR))-(APORTE_COMPENSACION+APORTE_RIESGOS+APORTE_PENSION_EMPLEADO+APORTE_SALUD_EMPLEADO+APORTE_FSP);

		List<Salario> Deducciones = new ArrayList<>();
		Stream.of(Deducciones.add(Salario.builder()
			.salarioBase(r)
			.aporteSaludEmpleado(APORTE_SALUD_EMPLEADO)
			.aporteSaludEmpleador(APORTE_SALUD_EMPLEADOR)
			.aportePensionEmpleado(APORTE_PENSION_EMPLEADO)
			.aportePensionEmpleador(APORTE_PENSION_EMPLEADOR)
			.aporteCajaDeCompensacion(APORTE_COMPENSACION)
			.aporteRiesgosLaborales(APORTE_RIESGOS)
			.pagoNetoEmpleado(TOTAL_SALARIO)
			.aporteFSP(APORTE_FSP)
			.costoEmpleador(APORTE_SALUD_EMPLEADOR + APORTE_PENSION_EMPLEADOR + APORTE_RIESGOS)
			.build()));
		return  Mono.just(Deducciones);
	};
}