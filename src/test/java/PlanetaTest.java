import org.crescer.planeta.Planeta;
import org.crescer.recurso.Recurso;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class PlanetaTest {
    @Test(expected = IllegalArgumentException.class)
    public void naoDeveAceitarDistanciaNegativa() {
        new Planeta(-5, new ArrayList<>());
    }
    @Test
    public void deveTerValorTotalZeradoQuandoNaoExistirNenhumRecurso() {
        Planeta planeta = new Planeta(1, new ArrayList<>());
        Assert.assertEquals(0, planeta.getValorTotal());
    }
    @Test
    public void deveTerValorTotalQuandoExistirRecursosNoPlaneta() {
        Recurso agua = new Recurso("Água", 180, 10);
        Recurso oxigenio = new Recurso("Oxigênio", 300, 2);
        Planeta planeta = new Planeta(1, Arrays.asList(agua, oxigenio));
        Assert.assertEquals(480, planeta.getValorTotal());
    }
    @Test
    public void deveTerValorPorPesoZeradoQuandoNaoExistirNenhumRecurso() {
        Planeta planeta = new Planeta(1, new ArrayList<>());
        Assert.assertEquals(0, planeta.getValorPorPeso(), 0.001);
    }
    @Test
    public void deveTerValorPorPesoQuandoExistirRecursosNoPlaneta() {
        Recurso agua = new Recurso("Água", 180, 10);
        Recurso oxigenio = new Recurso("Oxigênio", 300, 2);
        Planeta planeta = new Planeta(1, Arrays.asList(agua, oxigenio));

        double esperado = (180.0 / 10) + (300.0 / 2);
        Assert.assertEquals(esperado, planeta.getValorPorPeso(), 0.001);
    }
}
