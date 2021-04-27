package br.puc.simulacao;

import java.util.List;
import java.util.PriorityQueue;

import br.puc.simulacao.fila.Fila;
import br.puc.simulacao.fila.Tupla;

public class Simulador {

    private PriorityQueue<Evento> escalonador;

    private double tempoGlobal;

    private List<Fila> filas;

    private GeradorNumeroAleatorio geradorNumeroAleatorio;

    public Simulador(PriorityQueue<Evento> escalonador, List<Fila> filas, GeradorNumeroAleatorio geradorNumeroAleatorio) {
        this.escalonador = escalonador;
        this.tempoGlobal = 0;
        this.filas = filas;
        this.geradorNumeroAleatorio = geradorNumeroAleatorio;
    }

    public void chegada(Evento evento) {
        Fila chegada = evento.getDestino();
        contabilizaTempos(evento);
        if (chegada.getTamanhoFila() < chegada.getCapacidade()) {
            chegada.setTamanhoFila(chegada.getTamanhoFila() + 1);
            if (chegada.getTamanhoFila() <= chegada.getServidor()) {
                agendaTempoSaida(chegada);
            }
        } else chegada.setPerda(chegada.getPerda() + 1);
        escalonador.add(new Evento(tempoGlobal + numeroAleatorio(chegada.getTempoChegadamin(), chegada.getTempoChegadamax()), "chegada", null, chegada));
    }

    private void agendaTempoSaida(Fila chegada) {
        double random = numeroAleatorio(0, 1);
        Fila proximaFila = buscaRandomicamenteProximaFila(chegada.getFilasLigadas(), random);
        if (proximaFila != null) {
            escalonador.add(new Evento(tempoGlobal + numeroAleatorio(chegada.getTempoServiceMin(), chegada.getTempoServiceMax()), "passagem", chegada, proximaFila));
        } else {
            escalonador.add(new Evento(tempoGlobal + numeroAleatorio(chegada.getTempoServiceMin(), chegada.getTempoServiceMax()), "saida", chegada, null));
        }
    }
    
    public Fila buscaRandomicamenteProximaFila(List<Tupla> tuples, double random) {
        tuples.sort((a,b) -> a.getProbabilidade().compareTo(b.getProbabilidade()));
        double aux = 0;
        for (Tupla tupla : tuples) {
            aux += tupla.getProbabilidade();
            if (random < aux) {
                return tupla.getFila();
            }
        }
        return null;
    }

    private double numeroAleatorio(double a, double b) {
        final double random = geradorNumeroAleatorio.geraNumeroRandom();
        return (b - a) * random + a;
    }

    private void contabilizaTempos(Evento evento) {
        double delta = evento.getTempo() - tempoGlobal;
        for (Fila fila : filas) {
            int tamFila = fila.getTamanhoFila();
            fila.getTimes()[tamFila] = fila.getTimes()[tamFila] + delta;
        }
        tempoGlobal = evento.getTempo();
    }
}
