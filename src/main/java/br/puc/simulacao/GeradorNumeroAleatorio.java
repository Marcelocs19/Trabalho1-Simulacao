package br.puc.simulacao;

import java.time.ZonedDateTime;
import java.util.Random;

public class GeradorNumeroAleatorio {

    private static final Integer constante = 214748;

    private Integer seed;

    public GeradorNumeroAleatorio() {
    }

    public Double geraNumerosAleatorios() {
        seed = ZonedDateTime.now().getSecond();
        Integer ini = ((3647 * seed + 3647) % constante) % constante;
        return (double) ini;
    }

    public Double rangeDoubleNumeros(Double ini, Double fim) {
        Random random = new Random();
        double r = random.nextDouble();
        if (ini < fim) {
            r = r * (fim - ini) + ini;
            if (r >= fim) 
                r = Double.longBitsToDouble(Double.doubleToLongBits(fim) - 1);
        }
        return r;
    }

}
