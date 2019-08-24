package co.com.sofka.calculadora.controller;

import co.com.sofka.calculadora.model.Credito;
import co.com.sofka.calculadora.model.Salario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static co.com.sofka.calculadora.function.Calculadora.*;

@RestController
@RequestMapping("calculadora")
public class CalculadoraController
{
	@GetMapping("/sumar")
	public Mono<Integer> sumar(Integer a , Integer b)
	{
		return Mono.just(Arrays.asList(a , b)).flatMap(sumar);
	}

	@GetMapping("/restar")
	public Mono<Integer> restaDosNumeros (Integer a, Integer b)
	{
		return Mono.just(Arrays.asList(a,b)).flatMap(restar);
	}

	@GetMapping("/tabla-multiplicar")
	public Mono<String> tablaMultiplicar (Integer x)
	{
		return Mono.just(x).flatMap(tablaMultiplicar);
	}

	@GetMapping("/amortizacion")
	public Mono<List<Credito>> TableAmortizacion (Integer cuotas, Integer credito)
	{
		return Mono.just(Arrays.asList(cuotas, credito)).flatMap(amortizacion);
	}

	@GetMapping(value = "/deduccion")
	public Mono<List<Salario>> DeduccionSalario (Double salario) {
		return Mono.just(salario).flatMap(deduccionSalario);
	}

}
