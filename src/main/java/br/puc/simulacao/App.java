package br.puc.simulacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {

    public static Double tempoGlobal = 0.0;

    public static List<Fila> listaFilas = new ArrayList<>();

    public static List<Evento> listaEventos = new ArrayList<>();

    public static List<Double> listaNumerosAleatorios = new ArrayList<>();

    public static GeradorNumeroAleatorio gerador = new GeradorNumeroAleatorio();

    public static double tempoChegada = 3.0;

    public static Boolean parada = true;

    public static void main(String[] args) {

        Integer servers;
        Integer capacity;

        Double minArrival;
        Double maxArrival;

        Double minService;
        Double maxService;

        Scanner teclado = new Scanner(System.in);

        System.out.println("\n--- Simulação ---\n");

        System.out.println("Digite o número de servidores: ");
        servers = teclado.nextInt();

        System.out.println("Digite a capacidade da Fila: ");
        capacity = teclado.nextInt();

        System.out.println("Digite o tempo inicial da chegada: ");
        minArrival = teclado.nextDouble();

        System.out.println("Digite o tempo final da chegada: ");
        maxArrival = teclado.nextDouble();

        System.out.println("Digite o tempo inicial para o atendimento: ");
        minService = teclado.nextDouble();

        System.out.println("Digite o tempo final para o atendimento: ");
        maxService = teclado.nextDouble();

        teclado.close();

        Fila fila = new Fila(servers, capacity, minArrival, maxArrival, minService, maxService);

        if (servers > 1) {
            while (servers > 0) {
                listaFilas.add(fila);
                servers--;
            }
        } else {
            listaFilas.add(fila);
        }

        for(int i = 0; i < 1000; i++){
            listaNumerosAleatorios.add(gerador.geraNumerosAleatorios());
        }

        filaSimples(3.0);

        for(int i = 0; i < listaFilas.size(); i++) {
            System.out.println("Fila (" + i + "): " + listaFilas.get(i).getPerda());
        }
        System.out.println("Tempo: " + tempoGlobal);
    }

    private static void filaSimples(double tempoChegada) {
        chegadaNaFila(tempoChegada);
        
        while (parada || !listaEventos.isEmpty()) {
            List<Evento> menorTempoEvento = listaEventos.stream().sorted((a,b) -> a.getTempoEvento().compareTo(b.getTempoEvento())).collect(Collectors.toList());
            
            for (Fila fila : listaFilas) {
                //Caso a fila esteja cheia
                if(fila.getCapacityAtual() == fila.getCapacity()) {
                    if(menorTempoEvento.get(0).getTipoEvento() == 1) { // chegada                        
                        Integer perda = fila.getPerda();
                        fila.setPerda(++perda);
                        listaEventos.remove(menorTempoEvento.get(0));   
                        chegadaNaFila(menorTempoEvento.get(0).getTempoEvento());

                    } else if(menorTempoEvento.get(0).getTipoEvento() == 2) { // saída
                        saidaDaFila(menorTempoEvento.get(0).getTempoEvento());
                        listaEventos.remove(menorTempoEvento.get(0));
                    }
                } else { //Caso a fila não esteja cheia
                    if(menorTempoEvento.get(0).getTipoEvento() == 1) { // chegada
                        chegadaNaFila(menorTempoEvento.get(0).getTempoEvento());
                        listaEventos.remove(menorTempoEvento.get(0));                        

                    } else if(menorTempoEvento.get(0).getTipoEvento() == 2) { // saída
                        saidaDaFila(menorTempoEvento.get(0).getTempoEvento());
                        listaEventos.remove(menorTempoEvento.get(0));
                    }
                }
            }
            
        }

    }

    private static void chegadaNaFila(Double tempoAtual) {
        contabilizaTempo(tempoAtual);
        for (int i = 0; i < listaFilas.size(); i++) {
            if (listaFilas.get(i).getCapacityAtual() < listaFilas.get(i).getCapacity()) {
                if (listaFilas.get(i).getCapacityAtual() < listaFilas.get(i).getCapacity()) {
                    Integer capacityAtual = listaFilas.get(i).getCapacityAtual();
                    listaFilas.get(i).setCapacityAtual(capacityAtual + 1);

                    if (listaFilas.get(i).getCapacityAtual() <= listaFilas.get(i).getServers()) {
                        agendaTempoSaida(listaFilas.get(i).getMinService(), listaFilas.get(i).getMaxService());
                        saidaDaFila(tempoAtual);
                    }
                }
            }
        }
        agendaTempoChegada(listaFilas.get(0).getMinArrival(), listaFilas.get(0).getMaxArrival());
    }

    private static void saidaDaFila(Double tempoAtual) {
        contabilizaTempo(tempoAtual);
        for (Fila fila : listaFilas) {
            Integer capacityAtual = fila.getCapacityAtual();
            fila.setCapacityAtual(capacityAtual--);            
            if(fila.getCapacityAtual() >= fila.getServers()) {
                agendaTempoSaida(fila.getMinService(), fila.getMaxService());
            }
        }

    }

    private static void agendaTempoChegada(Double minArrival, Double maxArrival) {
        if(listaNumerosAleatorios.isEmpty()) {
            parada = false;
        } else {
            Double removeNumero = listaNumerosAleatorios.remove(0);
            tempoGlobal = tempoGlobal + gerador.rangeDoubleNumeros(minArrival, maxArrival);
            listaEventos.add(new Evento(1,tempoGlobal));
        }

    }

    private static void agendaTempoSaida(Double minService, Double maxService) {
        if(listaNumerosAleatorios.isEmpty()) {
            parada = false;
        } else {
            Double removeNumero = listaNumerosAleatorios.remove(0);
            tempoGlobal = tempoGlobal + gerador.rangeDoubleNumeros(minService, maxService);
            listaEventos.add(new Evento(2,tempoGlobal));
        }
    }

    private static void contabilizaTempo(Double tempoAtual) {
        tempoGlobal = tempoAtual - tempoGlobal;
    }

}
