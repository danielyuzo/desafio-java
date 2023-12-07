package school.sptech.model.componentes;

import school.sptech.model.Medida;

public abstract class Componente {

    private Medida medida;

    public Componente(Medida medida) {
        this.medida = medida;
    }

    public abstract void registrarDados();

    public Medida getMedida() {
        return medida;
    }

    public void setMedida(Medida medida) {
        this.medida = medida;
    }
}
