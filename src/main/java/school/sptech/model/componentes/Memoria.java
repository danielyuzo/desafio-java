package school.sptech.model.componentes;

import school.sptech.model.Medida;
import school.sptech.utils.ColetaDadosUtils;

public class Memoria extends Componente {

    private Double uso;

    public Memoria() {
        super(new Medida());
    }
    public Memoria(Medida medida) {
        super(medida);
    }
    public Memoria(Double uso) {
        super(new Medida());
        this.uso = uso;
    }
    public Memoria(Medida medida, Double uso) {
        super(medida);
        this.uso = uso;
    }

    @Override
    public void registrarDados() {
        this.uso = 100. * ColetaDadosUtils.LOOCA.getMemoria().getEmUso() / ColetaDadosUtils.LOOCA.getMemoria().getTotal();
    }

    public Double getUso() {
        return uso;
    }

    public void setUso(Double uso) {
        this.uso = uso;
    }

    @Override
    public String toString() {
        return "Memoria{" +
                "uso=" + uso +
                '}';
    }
}
