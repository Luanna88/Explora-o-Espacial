import org.crescer.recurso.Recurso;
import org.junit.Assert;
import org.junit.Test;

public class RecursoTest {
    @Test
    public void deveRetornarNomeQuandoRecursoTiverNome() {
        Recurso recurso = new Recurso("Recurso1", 10, 5);
        Assert.assertEquals("Recurso1", recurso.getNome());
    }
}
