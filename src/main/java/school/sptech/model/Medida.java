package school.sptech.model;

public class Medida {

    private Integer idServidorComponente;
    private String nome;
    private String unidade;
    private Double limiteAlerta;
    private Double limiteCritico;
    private Double meta;

    public Medida(Integer idServidorComponente, String nome, String unidade, Double limiteAlerta, Double limiteCritico, Double meta) {
        this.idServidorComponente = idServidorComponente;
        this.nome = nome;
        this.unidade = unidade;
        this.limiteAlerta = limiteAlerta;
        this.limiteCritico = limiteCritico;
        this.meta = meta;
    }

    public Medida() {
        this.nome = "Uso";
        this.unidade = "%";
        this.limiteAlerta = 70.0;
        this.limiteCritico = 90.0;
        this.meta = 5.0;
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

    public Double getMeta() {
        return meta;
    }

    public void setMeta(Double meta) {
        this.meta = meta;
    }

    @Override
    public String toString() {
        return "Medida{" +
                "idMedida=" + idServidorComponente +
                ", nome='" + nome + '\'' +
                ", unidade='" + unidade + '\'' +
                ", limiteAlerta=" + limiteAlerta +
                ", limiteCritico=" + limiteCritico +
                ", meta=" + meta +
                '}';
    }
}
