package br.puc.simulacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.puc.simulacao.fila.Fila;
import br.puc.simulacao.fila.Network;
import br.puc.simulacao.fila.Queue;
import br.puc.simulacao.fila.SimulacaoFila;
import br.puc.simulacao.fila.Tupla;

public class ExecucaoSimulacao {
    
    public static List<Fila> criadorFilas(SimulacaoFila simulacaoFila) {
        List<Fila> listaFilas = new ArrayList<>();

        for (Map.Entry<String, Queue> hashQueue : simulacaoFila.getQueues().entrySet()) {
            Queue queue = hashQueue.getValue();
            String idFila = hashQueue.getKey();
            listaFilas.add(new Fila(idFila, queue));
        }

        definirFilasConectadas(simulacaoFila, listaFilas);
        return listaFilas;
    }

    public static void definirFilasConectadas(SimulacaoFila simulacaoFila, List<Fila> filas) {
        for (Fila f : filas) {
            List<Tupla> filasLigadas = new ArrayList<>();
            List<Network> networks = simulacaoFila.getNetwork();
            for (Network network : networks) {
                if (f.getNomeFila().equalsIgnoreCase(network.getSource())) {
                    Fila queueById1 = buscaQueuePeloNome(filas, network.getTarget());
                    filasLigadas.add(new Tupla(queueById1, network.getProbability()));
                }
            }
            f.setFilasLigadas(filasLigadas);
        }
    }

    public static Fila buscaQueuePeloNome(List<Fila> lista, String nome) {
        Fila retorno = null;
        for (Fila fila : lista) {            
            if(fila.getNomeFila().equalsIgnoreCase(nome)) {
                retorno = fila;
            }
        }
        return retorno;
    }

    public static void executarSimulacao(SimulacaoFila simulacaoFila, List<Fila> filas, Simulador simulador, GeradorNumeroAleatorio geradorNumeroAleatorio) {
        executarEventosIni(simulacaoFila, filas, simulador);
        while(geradorNumeroAleatorio.getLimite() > 0) {
            Evento evento = simulador.getEscalonador().poll();
            simulador.simulacao(evento);
        }

        for (int j = 0; j < filas.size(); j++) {
            final Fila fila = filas.get(j);
            fila.setTempoGlobal(simulador.getTempoGlobal());
            System.out.println("\nProbabilidades da fila " + fila.getNomeFila());
            double[] propabilidade = fila.getPropabilidade();
            System.out.println(String.format("Estado   Probabilidade   Tempo"));
            for (int i = 0; i < propabilidade.length && i < 20; i++) {
                System.out.println(String.format("%s: %12.02f%% %18.04f", i, propabilidade[i], fila.getTimes()[i]));
            }
        }
        System.out.println("Tempo global: " + simulador.getTempoGlobal());
    }

    public static void executarEventosIni(SimulacaoFila simulacaoFila, List<Fila> filas, Simulador simulador) {
        List<Evento> eventos = new ArrayList<>();
        for (Map.Entry<String, Double> hashArrival : simulacaoFila.getArrivals().entrySet()) {
            Fila queue = buscaQueuePeloNome(filas, hashArrival.getKey());
            eventos.add(new Evento(hashArrival.getValue(), "chegada", null, queue));
        }
        
        eventos.forEach(e -> simulador.simulacao(e));
        
    }
    


}
