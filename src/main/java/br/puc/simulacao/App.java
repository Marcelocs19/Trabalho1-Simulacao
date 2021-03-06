package br.puc.simulacao;

import java.util.List;
import java.util.PriorityQueue;

import br.puc.simulacao.fila.Fila;
import br.puc.simulacao.fila.SimulacaoFila;
import br.puc.simulacao.leitura.LeituraYml;

public class App {

    public static void main(String[] args) {

        SimulacaoFila simulacaoFila = new LeituraYml().leituraYml("fila.yml");

        GeradorNumeroAleatorio geradorNumeroAleatorio = new GeradorNumeroAleatorio(simulacaoFila, simulacaoFila.getRndnumbers());
        
        List<Fila> filas = ExecucaoSimulacao.criadorFilas(simulacaoFila);

        Simulador simulador = new Simulador(filas, geradorNumeroAleatorio);
        
        ExecucaoSimulacao.executarSimulacao(simulacaoFila, filas, simulador, geradorNumeroAleatorio);
    
    }

}
