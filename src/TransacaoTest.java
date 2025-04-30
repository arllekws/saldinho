import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransacaoTest {

    @Test
    public void testAplicarNaConta_Receita() {

        Transacao transacao = new Transacao("Pagamento salário", 1500.00, true);
        double saldoAtual = 2000.00;

        double saldoFinal = transacao.aplicarNaConta(saldoAtual);

        assertEquals(3500.00, saldoFinal, 0.01);
    }

    @Test
    public void testAplicarNaConta_Despesa() {
        Transacao transacao = new Transacao("Compra supermercado", 300.00, false);
        double saldoAtual = 2000.00;

        double saldoFinal = transacao.aplicarNaConta(saldoAtual);

        assertEquals(1700.00, saldoFinal, 0.01);
    }

    @Test
    public void testDescricao() {
        Transacao transacao = new Transacao("Pagamento aluguel", 1000.00, false);

        assertEquals("Pagamento aluguel", transacao.getDescricao());
    }

    @Test
    public void testValor() {
        Transacao transacao = new Transacao("Recebimento de pagamento", 500.00, true);

        assertEquals(500.00, transacao.getValor(), 0.01);
    }

    @Test
    public void testIsReceita() {
        Transacao transacaoReceita = new Transacao("Pagamento salário", 1500.00, true);
        Transacao transacaoDespesa = new Transacao("Compra supermercado", 200.00, false);

        assertTrue(transacaoReceita.isReceita());
        assertFalse(transacaoDespesa.isReceita());
    }
}
