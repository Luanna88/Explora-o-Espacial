package org.crescer.nave;

import org.crescer.planeta.Planeta;
import org.crescer.recurso.Recurso;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Nave {
    private int combustivel;
    private int posicao;

    public Nave(int combustivel, int posicao) {
        this.combustivel = combustivel;
        this.posicao = posicao;
    }

    public Nave(int combustivel) {
        this.combustivel = combustivel;
        this.posicao = 0;
    }

    public int getQuantidadeDeCombustivel() {
        return combustivel;
    }

    public int getPosicao() {
        return posicao;
    }

    public List<Recurso> explorar(List<Planeta> planetas, Comparator<Planeta> comparator) {
        List<Recurso> recursosColetados = new ArrayList<>();
        planetas.sort(comparator);

        for (Planeta planeta : planetas) {
            int distancia = Math.abs(planeta.getPosicao() - this.posicao);
            if (distancia > this.combustivel) {
                break;
            }
            this.combustivel -= distancia;
            this.posicao = planeta.getPosicao();
            recursosColetados.addAll(planeta.getRecursos());
        }
        return recursosColetados;
    }
}
