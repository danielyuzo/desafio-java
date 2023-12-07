package school.sptech.model;

import school.sptech.model.componentes.Componente;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Dados {

    private Integer idDadosServidor;
    private List<Componente> componentes;
    private LocalDateTime dateDado;
    private Integer fkServidor;

    public Dados() {
        this.componentes = new ArrayList<>();
    }

    public Dados(Integer idDadosServidor, List<Componente> componentes, LocalDateTime dateDado, Integer fkServidor) {
        this.idDadosServidor = idDadosServidor;
        this.componentes = componentes;
        this.dateDado = dateDado;
        this.fkServidor = fkServidor;
    }

    public Dados(List<Componente> componentes, LocalDateTime dateDado, Integer fkServidor) {
        this.componentes = componentes;
        this.dateDado = dateDado;
        this.fkServidor = fkServidor;
    }

    public void registrarDados() {
        for (Componente componenteDaVez : this.componentes) {
            componenteDaVez.registrarDados();
        }
    }

    public Integer getIdDadosServidor() {
        return idDadosServidor;
    }

    public void setIdDadosServidor(Integer idDadosServidor) {
        this.idDadosServidor = idDadosServidor;
    }

    public List<Componente> getComponentes() {
        return componentes;
    }

    public LocalDateTime getDateDado() {
        return dateDado;
    }

    public void setDateDado(LocalDateTime dateDado) {
        this.dateDado = dateDado;
    }

    public Integer getFkServidor() {
        return fkServidor;
    }

    public void setFkServidor(Integer fkServidor) {
        this.fkServidor = fkServidor;
    }

    @Override
    public String toString() {
        return "Dados{" +
                "idDadosServidor=" + idDadosServidor +
                ", componentes=" + componentes +
                ", dateDado=" + dateDado +
                ", fkServidor=" + fkServidor +
                '}';
    }
}
