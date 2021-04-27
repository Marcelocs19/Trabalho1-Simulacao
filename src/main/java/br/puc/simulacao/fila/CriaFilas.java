package br.puc.simulacao.fila;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CriaFilas {
    
    public static List<Fila> criadorFilas(SimulacaoFila simulacaoFila) {
        List<Fila> listaFilas = new ArrayList<>();

        for (Map.Entry<String, Queue> hashQueue : simulacaoFila.getQueues().entrySet()) {
            Queue queue = hashQueue.getValue();
            String idFila = hashQueue.getKey();
            listaFilas.add(new Fila(idFila, queue));
        }

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

}
