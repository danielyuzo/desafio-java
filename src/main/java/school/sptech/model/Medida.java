package school.sptech.model;

public class Medida {

    private Integer idServidorComponente;
    private String nome;
    private String unidade;
    private Boolean ativa;
    private Double limiteAlerta;
    private Double limiteCritico;

    public Medida(Integer idServidorComponente, String nome, String unidade, Boolean ativa, Double limiteAlerta, Double limiteCritico) {
        this.idServidorComponente = idServidorComponente;
        this.nome = nome;
        this.unidade = unidade;
        this.ativa = ativa;
        this.limiteAlerta = limiteAlerta;
        this.limiteCritico = limiteCritico;
    }

    public Medida() {
        this.nome = "Uso";
        this.unidade = "%";
        this.ativa = true;
        this.limiteAlerta = 70.0;
        this.limiteCritico = 90.0;
    }

    public Integer getIdServidorComponente() {
        return idServidorComponente;
    }

    public void setIdServidorComponente(Integer idServidorComponente) {
        this.idServidorComponente = idServidorComponente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

    public Double getLimiteAlerta() {
        return limiteAlerta;
    }

    public void setLimiteAlerta(Double limiteAlerta) {
        this.limiteAlerta = limiteAlerta;
    }

    public Double getLimiteCritico() {
        return limiteCritico;
    }

    public void setLimiteCritico(Double limiteCritico) {
        this.limiteCritico = limiteCritico;
    }

    @Override
    public String toString() {
        return """
            Medida: %s, em %s
            Monitoramento ativo: %s
            Limite para Estado de Alerta: %s %s
            Limite para Estado Crítico: %s %s
            """.formatted(nome, unidade,
                ativa ? "Sim" : "Não",
                limiteAlerta, unidade,
                limiteCritico, unidade);
    }
}
