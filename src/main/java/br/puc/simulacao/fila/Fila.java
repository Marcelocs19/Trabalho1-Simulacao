package br.puc.simulacao.fila;

import java.util.List;

public class Fila {

    private String nomeFila;

    private int servidor;

    private double tempoChegadamin;

    private double tempoChegadamax;

    private double tempoServiceMin;

    private double tempoServiceMax;

    private double[] times;

    private double tempoGlobal;

    private int perda;

    private int capacidade;

    private int tamanhoFila;

    private List<Tupla> filasLigadas;

    public Fila(String nomeFila, Queue queue) {
        this.nomeFila = nomeFila;
        this.servidor = queue.getServers();
        this.tempoChegadamin = queue.getMinArrival();
        this.tempoChegadamax = queue.getMaxArrival();
        this.tempoServiceMin = queue.getMinService();
        this.tempoServiceMax = queue.getMaxService();
        this.times = new double[queue.getCapacity() + 1];
        this.tempoGlobal = 0;
        this.perda = 0;
        this.capacidade = queue.getCapacity();
        this.tamanhoFila = 0;
        this.filasLigadas = null;
    }

    public String getNomeFila() {
        return nomeFila;
    }

    public void setNomeFila(String nomeFila) {
        this.nomeFila = nomeFila;
    }

    public int getServidor() {
        return servidor;
    }

    public void setServidor(int servidor) {
        this.servidor = servidor;
    }

    public double getTempoChegadamin() {
        return tempoChegadamin;
    }

    public void setTempoChegadamin(double tempoChegadamin) {
        this.tempoChegadamin = tempoChegadamin;
    }

    public double getTempoChegadamax() {
        return tempoChegadamax;
    }

    public void setTempoChegadamax(double tempoChegadamax) {
        this.tempoChegadamax = tempoChegadamax;
    }

    public double getTempoServicemin() {
        return tempoServiceMin;
    }

    public void setTempoServicemin(double tempoServiceMin) {
        this.tempoServiceMin = tempoServiceMin;
    }

    public double getTempoServiceMax() {
        return tempoServiceMax;
    }

    public void setTempoServicemax(double tempoServiceMax) {
        this.tempoServiceMax = tempoServiceMax;
    }

    public double[] getTimes() {
        return times;
    }

    public void setTimes(double[] times) {
        this.times = times;
    }

    public double getTempoGlobal() {
        return tempoGlobal;
    }

    public void setTempoGlobal(double tempoGlobal) {
        this.tempoGlobal = tempoGlobal;
    }

    public int getPerda() {
        return perda;
    }

    public void setPerda(int perda) {
        this.perda = perda;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getTamanhoFila() {
        return tamanhoFila;
    }

    public void setTamanhoFila(int tamanhoFila) {
        this.tamanhoFila = tamanhoFila;
    }
    
    public double getTempoServiceMin() {
        return tempoServiceMin;
    }

    public void setTempoServiceMin(double tempoServiceMin) {
        this.tempoServiceMin = tempoServiceMin;
    }

    public void setTempoServiceMax(double tempoServiceMax) {
        this.tempoServiceMax = tempoServiceMax;
    }

    public List<Tupla> getFilasLigadas() {
        return filasLigadas;
    }

    public void setFilasLigadas(List<Tupla> filasLigadas) {
        this.filasLigadas = filasLigadas;
    }

    public double[] getPropabilidade() {
        System.out.println("Total de perdas " + perda);
        double[] probabilidades = new double[times.length];
        for (int i = 0; i < probabilidades.length; i++) {
            probabilidades[i] = times[i] / tempoGlobal * 100;
        }
        return probabilidades;
    }    
    
}
