package br.puc.simulacao;

import java.util.List;
import java.util.PriorityQueue;

import br.puc.simulacao.fila.CriaFilas;
import br.puc.simulacao.fila.Fila;
import br.puc.simulacao.fila.SimulacaoFila;
import br.puc.simulacao.leitura.LeituraYml;

public class App {

    public static void main(String[] args) {

        SimulacaoFila simulacaoFila = new LeituraYml().leituraYml();
        GeradorNumeroAleatorio geradorNumeroAleatorio = new GeradorNumeroAleatorio(simulacaoFila, simulacaoFila.getRndnumbers());
        PriorityQueue<Evento> escalonador = new PriorityQueue<>();
        List<Fila> filas = CriaFilas.criadorFilas(simulacaoFila);
    
    }

}
