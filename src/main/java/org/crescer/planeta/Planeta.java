package org.crescer.planeta;

import org.crescer.recurso.Recurso;
import java.util.List;

public class Planeta {
    private int posicao;
    private List<Recurso> recursos;

    public Planeta(int posicao, List<Recurso> recursos) {
        if (posicao < 0) {
            throw new IllegalArgumentException("A posição do planeta não pode ser negativa.");
        }
        this.posicao = posicao;
        this.recursos = recursos;
    }

    public int getPosicao() {
        return posicao;
    }

    public List<Recurso> getRecursos() {
        return recursos;
    }

    public int getValorTotal() {
        return recursos.stream().mapToInt(Recurso::getValor).sum();
    }

    public double getValorPorPeso() {
        return recursos.stream().mapToDouble(r -> (double) r.getValor() / r.getPeso()).sum();
    }
}
