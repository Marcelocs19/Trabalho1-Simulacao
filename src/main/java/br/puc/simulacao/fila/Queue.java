package br.puc.simulacao.fila;

public class Queue {

    private int servers;

    private int capacity;

    private Double minArrival;

    private Double maxArrival;

    private Double minService;

    private Double maxService;
    
    public Queue() {
    }

    public Queue(int servers, int capacity, Double minArrival, Double maxArrival,
            Double minService, Double maxService) {
        this.servers = servers;
        this.capacity = capacity;
        this.minArrival = minArrival;
        this.maxArrival = maxArrival;
        this.minService = minService;
        this.maxService = maxService;
    }

    public int getServers() {
        return servers;
    }

    public void setServers(int servers) {
        this.servers = servers;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Double getMinArrival() {
        return minArrival;
    }

    public void setMinArrival(Double minArrival) {
        this.minArrival = minArrival;
    }

    public Double getMaxArrival() {
        return maxArrival;
    }

    public void setMaxArrival(Double maxArrival) {
        this.maxArrival = maxArrival;
    }

    public Double getMinService() {
        return minService;
    }

    public void setMinService(Double minService) {
        this.minService = minService;
    }

    public Double getMaxService() {
        return maxService;
    }

    public void setMaxService(Double maxService) {
        this.maxService = maxService;
    }
        
    @Override
    public String toString() {
        return "Fila {capacity=" + capacity + 
                     ", maxArrival=" + maxArrival + 
                     ", maxService=" + maxService + 
                     ", minArrival=" + minArrival + 
                     ", minService=" + minService + 
                     ", servers=" + servers + "}";
    }
    
    
}
