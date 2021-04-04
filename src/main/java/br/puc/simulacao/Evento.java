package br.puc.simulacao;

public class Evento {

    // Chegada = 1
    // Saida = 2
    private Integer tipoEvento;

    private Double tempoEvento;

    public Evento(Integer tipoEvento, Double tempoEvento) {
        this.tipoEvento = tipoEvento;
        this.tempoEvento = tempoEvento;
    }

    public Integer getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(Integer tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Double getTempoEvento() {
        return tempoEvento;
    }

    public void setTempoEvento(Double tempoEvento) {
        this.tempoEvento = tempoEvento;
    }
    
}
