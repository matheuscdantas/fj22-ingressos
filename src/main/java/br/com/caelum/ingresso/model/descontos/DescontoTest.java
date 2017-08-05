package br.com.caelum.ingresso.model.descontos;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;

public class DescontoTest {

	private Sala sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
	private Filme filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12"));
	private Sessao sessao = new Sessao(LocalTime.now(), sala, filme);
	private Ingresso ingresso;
	private BigDecimal bd;

	@Test
	public void DeveConcederDescontoDe30PorCentoParaIngressosDeClientesDeBancos() {
		Lugar lugar = new Lugar("A", 1);
		ingresso = new Ingresso(sessao, TipoDeIngresso.BANCO, lugar);
		
		bd = new BigDecimal("22.75");

		Assert.assertEquals(bd, ingresso.getPreco());

	}
	
	@Test
	public void DeveConcederDescontoDe50PorCentoParaIngressosDeEstudante() {
		Lugar lugar = new Lugar("A", 1);
		ingresso = new Ingresso(sessao, TipoDeIngresso.ESTUDANTE, lugar);

		bd = new BigDecimal("16.25");

		Assert.assertEquals(bd, ingresso.getPreco());

	}
	
	@Test
	public void naoDeveConcederDescontoParaIngressoNormal() {
		Lugar lugar = new Lugar("A", 1);
		ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRO, lugar);

		bd = new BigDecimal("32.5");

		Assert.assertEquals(bd, ingresso.getPreco());

	}
}
