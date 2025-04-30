
public class Transacao {
    private String descricao;
    private double valor;
    private boolean isReceita;

    public Transacao(String descricao, double valor, boolean isReceita) {
        this.descricao = descricao;
        this.valor = valor;
        this.isReceita = isReceita;
    }

    public double getValor() {
        return valor;
    }

    public boolean isReceita() {
        return isReceita;
    }

    public String getDescricao() {
        return descricao;
    }

    public double aplicarNaConta(double saldoAtual) {
        return isReceita ? saldoAtual + valor : saldoAtual - valor;
    }
}
