package br.puc.simulacao.fila;

public class Queue {

    private Integer servers;

    private Integer capacity;

    private Double minArrival;

    private Double maxArrival;

    private Double minService;

    private Double maxService;
    
    public Queue() {
    }

    public Queue(Integer servers, Integer capacity, Double minArrival, Double maxArrival,
            Double minService, Double maxService) {
        this.servers = servers;
        this.capacity = capacity;
        this.minArrival = minArrival;
        this.maxArrival = maxArrival;
        this.minService = minService;
        this.maxService = maxService;
    }

    public Integer getServers() {
        return servers;
    }

    public void setServers(Integer servers) {
        this.servers = servers;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
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
