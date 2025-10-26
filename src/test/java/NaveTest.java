import org.crescer.nave.Nave;
import org.crescer.planeta.Planeta;
import org.crescer.recurso.Recurso;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NaveTest {
    @Test
    public void deveFicarADerivaQuandoFaltarCombustivelParaIrAteUmPlaneta() {
        int posicaoEsperada = 3;
        int combustivelEsperado = 1;

        Nave nave = new Nave(4);
        Planeta planeta = new Planeta(3, new ArrayList<>());

        List<Recurso> recursos = nave.explorar(
                Collections.singletonList(planeta),
                Comparator.comparingInt(Planeta::getPosicao)
        );

        Assert.assertTrue(recursos.isEmpty());
        Assert.assertEquals(combustivelEsperado, nave.getQuantidadeDeCombustivel());
        Assert.assertEquals(posicaoEsperada, nave.getPosicao());
    }
    @Test
    public void deveExplorarComSucessoQuandoHouverCombustivelSuficiente() {
        Nave nave = new Nave(50);
        Recurso ouro = new Recurso("Ouro", 100, 5);
        Planeta planeta = new Planeta(10, Collections.singletonList(ouro));

        List<Recurso> recursos = nave.explorar(
                Collections.singletonList(planeta),
                Comparator.comparingInt(Planeta::getPosicao)
        );

        Assert.assertFalse(recursos.isEmpty());
        Assert.assertEquals(1, recursos.size());
        Assert.assertEquals("Ouro", recursos.get(0).getNome());
    }
    @Test
    public void naoDeveExplorarPlanetasQuandoDistanciaEhZero() {
        Nave nave = new Nave(50);
        Planeta planeta = new Planeta(0, new ArrayList<>());

        List<Recurso> recursos = nave.explorar(
                Collections.singletonList(planeta),
                Comparator.comparingInt(Planeta::getPosicao)
        );

        Assert.assertTrue(recursos.isEmpty());
    }
    @Test
    public void naoDeveExplorarQuandoCombustivelIgualADistancia() {
        Nave nave = new Nave(5);
        Planeta planeta = new Planeta(5, new ArrayList<>());

        List<Recurso> recursos = nave.explorar(
                Collections.singletonList(planeta),
                Comparator.comparingInt(Planeta::getPosicao)
        );

        Assert.assertTrue(recursos.isEmpty());
    }
    @Test
    public void devePararExploracaoQuandoCombustivelNaoForSuficienteParaContinuar() {
        Nave nave = new Nave(10);
        Recurso ouro = new Recurso("Ouro", 100, 5);
        Recurso prata = new Recurso("Prata", 50, 2);
        Planeta planeta1 = new Planeta(5, Collections.singletonList(ouro));
        Planeta planeta2 = new Planeta(15, Collections.singletonList(prata));

        List<Recurso> recursos = nave.explorar(
                Arrays.asList(planeta1, planeta2),
                Comparator.comparingInt(Planeta::getPosicao)
        );

        Assert.assertEquals(1, recursos.size());
        Assert.assertEquals("Ouro", recursos.get(0).getNome());
        Assert.assertEquals(5, nave.getQuantidadeDeCombustivel());
        Assert.assertEquals(5, nave.getPosicao());
    }
    @Test
    public void deveExplorarApenasPlanetasDentroDoAlcanceDoCombustivel() {
        Nave nave = new Nave(20);
        Recurso ferro = new Recurso("Ferro", 70, 10);
        Recurso aluminio = new Recurso("Alumínio", 90, 7);
        Planeta planeta1 = new Planeta(10, Collections.singletonList(ferro));
        Planeta planeta2 = new Planeta(25, Collections.singletonList(aluminio));

        List<Recurso> recursos = nave.explorar(
                Arrays.asList(planeta1, planeta2),
                Comparator.comparingInt(Planeta::getPosicao)
        );

        Assert.assertEquals(1, recursos.size());
        Assert.assertEquals("Ferro", recursos.get(0).getNome());
        Assert.assertEquals(10, nave.getPosicao());
    }
    @Test
    public void deveColetarTodosOsRecursosQuandoHouverCombustivelSuficiente() {
        Nave nave = new Nave(100);
        Planeta planeta = new Planeta(10, Arrays.asList(
                new Recurso("Água", 50, 10),
                new Recurso("Oxigênio", 30, 5)
        ));

        List<Recurso> recursos = nave.explorar(
                Collections.singletonList(planeta),
                Comparator.comparingInt(Planeta::getPosicao)
        );

        Assert.assertEquals(2, recursos.size());
    }
}
