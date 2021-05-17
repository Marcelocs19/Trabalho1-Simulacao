package br.puc.simulacao;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import br.puc.simulacao.fila.SimulacaoFila;

public class GeradorNumeroAleatorio {

    private double a = 2147483;                              

    private double c = 11;

    private Long M = 9223372036L;

    private double proximaSemente;

    private Queue<Double> numerosAleatorios;

    private int limite;

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public Long getM() {
        return M;
    }

    public void setM(Long m) {
        M = m;
    }

    public double getProximaSemente() {
        return proximaSemente;
    }

    public void setProximaSemente(double proximaSemente) {
        this.proximaSemente = proximaSemente;
    }

    public Queue<Double> getNumerosAleatorios() {
        return numerosAleatorios;
    }

    public void setNumerosAleatorios(Queue<Double> numerosAleatorios) {
        this.numerosAleatorios = numerosAleatorios;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

    public GeradorNumeroAleatorio(SimulacaoFila semente, List<Double> numerosAleatorios) {
        proximaSemente = getSemente(semente);
        this.limite = getQuantidadeEventos(semente, numerosAleatorios);
        if (numerosAleatorios != null) {
            this.numerosAleatorios = new LinkedList<>(numerosAleatorios);
        }
    }

    private int getSemente(SimulacaoFila simulacao) {
        if (simulacao.getSeeds() != null) {
            return simulacao.getSeeds().isEmpty() ? 1 : Integer.parseInt(simulacao.getSeeds().get(0));
        }
        return 0;
    }

    private int getQuantidadeEventos(SimulacaoFila simulacao, List<Double> rndnumbers) {
        return simulacao.getRndnumbersPerSeed() == null ? rndnumbers.size() : simulacao.getRndnumbersPerSeed();
    }

    public double generateRandomValue(double semente) {
        return (semente * a + c) % M / M;
    }

    public double geraNumeroRandom() {
        limite--;
        if (numerosAleatorios != null && numerosAleatorios.size() > 0) {
            return numerosAleatorios.remove();
        }
        proximaSemente = (proximaSemente * a + c) % M;
        return proximaSemente / M;
    }

    public int getLimite() {
        return limite;
    }

}
