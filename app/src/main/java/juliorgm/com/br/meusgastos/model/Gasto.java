package juliorgm.com.br.meusgastos.model;

public class Gasto {
    private int idGasto;
    private double valor;
    private String data;
    private String descricao;
    private String categoria;

    public Gasto(int idGasto, double valor, String data, String descricao, String categoria) {
        this.idGasto = idGasto;
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public Gasto(double valor, String data, String descricao, String categoria) {
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public int getIdGasto() {
        return idGasto;
    }

    public double getValor() {
        return valor;
    }

    public String getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }
}
