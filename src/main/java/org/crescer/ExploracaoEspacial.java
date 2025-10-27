package org.crescer;

import org.crescer.nave.Nave;
import org.crescer.planeta.Planeta;
import org.crescer.recurso.Recurso;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ExploracaoEspacial {
    public static void main(String[] args) {
        Recurso agua = new Recurso("Água", 180, 10);
        Recurso oxigenio = new Recurso("Oxigênio", 300, 2);
        Recurso silicio = new Recurso("Silício", 60, 16);
        Recurso ouro = new Recurso("Ouro", 120, 25);
        Recurso ferro = new Recurso("Ferro", 30, 32);

        List<Recurso> recursosPlaneta1 = Arrays.asList(agua, oxigenio, ouro);
        Planeta planeta1 = new Planeta(3, recursosPlaneta1);

        List<Recurso> recursosPlaneta2 = Arrays.asList(silicio, ferro);
        Planeta planeta2 = new Planeta(6, recursosPlaneta2);

        Nave nave = new Nave(40);

        List<Planeta> planetas = Arrays.asList(planeta1, planeta2);
        List<Recurso> recursosColetados = nave.explorar(planetas, Comparator.comparingInt(Planeta::getValorTotal).reversed());

        System.out.println("Recursos coletados com prioridade por valor total:");
        for (Recurso recurso : recursosColetados) {
            System.out.println(recurso.getNome());
        }

        System.out.println("Combustível restante: " + nave.getQuantidadeDeCombustivel());
        System.out.println("Posição final da nave: " + nave.getPosicao());
    }
}
