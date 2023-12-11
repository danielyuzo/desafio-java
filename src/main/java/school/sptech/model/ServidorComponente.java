package school.sptech.model;

public class ServidorComponente {
    private Integer idServidorComponente;
    private Integer fkComponente;
    private Integer fkServidor;

    public ServidorComponente(Integer idServidorComponente, Integer fkComponente, Integer fkServidor) {
        this.idServidorComponente = idServidorComponente;
        this.fkComponente = fkComponente;
        this.fkServidor = fkServidor;
    }

    public Integer getIdServidorComponente() {
        return idServidorComponente;
    }

    public void setIdServidorComponente(Integer idServidorComponente) {
        this.idServidorComponente = idServidorComponente;
    }

    public Integer getFkComponente() {
        return fkComponente;
    }

    public void setFkComponente(Integer fkComponente) {
        this.fkComponente = fkComponente;
    }

    public Integer getFkServidor() {
        return fkServidor;
    }

    public void setFkServidor(Integer fkServidor) {
        this.fkServidor = fkServidor;
    }
}
