package school.sptech.model.componentes;

import school.sptech.enums.NivelAlertaEnum;
import school.sptech.model.Medida;
import school.sptech.model.Servidor;

public abstract class Componente {

    private Medida medida;
    private Double uso;
    public Componente(Medida medida) {
        this.medida = medida;
    }

    public Componente(Medida medida, Double uso) {
        this.medida = medida;
        this.uso = uso;
    }

    public abstract void registrarDados();

    public abstract void atualizarMedida(Servidor servidor);

    public NivelAlertaEnum getNivelAlerta() {
        if (this.uso >= this.getMedida().getLimiteCritico()) {
            return NivelAlertaEnum.CRITICO;
        } else if (this.uso >= this.getMedida().getLimiteAlerta()) {
            return NivelAlertaEnum.ALERTA;
        } else {
            return NivelAlertaEnum.NORMAL;
        }
    }

    public Boolean isEmAlerta() {
        return this.uso >= this.getMedida().getLimiteAlerta();
    }

    public Medida getMedida() {
        return medida;
    }

    public void setMedida(Medida medida) {
        this.medida = medida;
    }
    public Double getUso() {
        return uso;
    }

    public void setUso(Double uso) {
        this.uso = uso;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Componente{");
        sb.append("medida=").append(medida);
        sb.append(", uso=").append(uso);
        sb.append('}');
        return sb.toString();
    }
}
