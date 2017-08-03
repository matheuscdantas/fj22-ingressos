package br.com.caelum.ingresso.validacao;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessaoTest {

	Filme fGenerico = new Filme("Rogue one", Duration.ofMinutes(120), "SCI-FI", BigDecimal.ONE);
	LocalTime horarioGenerico = LocalTime.parse("10:00:00");

	Sala sala;
	Sessao sessao;

	GerenciadorDeSessao gSessao;

	@Test
	public void garanteQueNaoDevePermitirSessaoNoMesmoHorario() {

		sala = new Sala("", BigDecimal.ONE);
		List<Sessao> sessoes = Arrays.asList(new Sessao(horarioGenerico, sala, fGenerico));

		sessao = new Sessao(horarioGenerico, sala, fGenerico);

		gSessao = new GerenciadorDeSessao(sessoes);

		Assert.assertFalse(gSessao.cabe(sessao));

	}

	@Test
	public void garanteQueNaoDevePermitirSessoesTerminandoDentroDoHorarioDeUmaSessaoJaExistente() {

		sala = new Sala("", BigDecimal.ONE);
		List<Sessao> sessoes = Arrays.asList(new Sessao(horarioGenerico, sala, fGenerico));

		sessao = new Sessao(horarioGenerico.minusHours(1), sala, fGenerico);

		gSessao = new GerenciadorDeSessao(sessoes);

		Assert.assertFalse(gSessao.cabe(sessao));

	}

	@Test
	public void garanteQueNaoDevePermitirSessoesIniciandoDentroDoHorarioDeUmaSessaoJaExistente() {

		sala = new Sala("", BigDecimal.ONE);
		List<Sessao> sessoes = Arrays.asList(new Sessao(horarioGenerico, sala, fGenerico));

		sessao = new Sessao(horarioGenerico, sala, fGenerico);

		gSessao = new GerenciadorDeSessao(sessoes);

		Assert.assertFalse(gSessao.cabe(new Sessao(horarioGenerico.plusHours(1), sala, fGenerico)));

	}

	@Test
	public void garanteQueDevePermitirUmaInsercaoDentroDoHorarioDeUmaSessaoJaExistente() {

		sala = new Sala("", BigDecimal.ONE);

		Filme f1 = new Filme("Rogue One", Duration.ofMinutes(90), "SCI-FI", BigDecimal.ONE);
		LocalTime dezHoras = LocalTime.parse("10:00:00");
		Sessao sDasDez = new Sessao(dezHoras, sala, f1);

		Filme f2 = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", BigDecimal.ONE);
		LocalTime dezoitoHoras = LocalTime.parse("18:00:00");
		Sessao sDasDezoito = new Sessao(dezoitoHoras, sala, f2);

		List<Sessao> sessoes = Arrays.asList(sDasDez, sDasDezoito);

		gSessao = new GerenciadorDeSessao(sessoes);

		Assert.assertTrue(gSessao.cabe(new Sessao(LocalTime.parse("13:00:00"), sala, f2)));

	}
}
