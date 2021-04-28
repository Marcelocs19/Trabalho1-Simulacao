package br.puc.simulacao.leitura;

import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import br.puc.simulacao.fila.SimulacaoFila;

public class LeituraYml {

    public SimulacaoFila leituraYml(String nomeArquivo) {
        Representer representer = new Representer();
        representer.getPropertyUtils().setSkipMissingProperties(true);
        Yaml yaml = new Yaml(new Constructor(SimulacaoFila.class), representer);
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(nomeArquivo);
        return yaml.load(inputStream);
    }

}
