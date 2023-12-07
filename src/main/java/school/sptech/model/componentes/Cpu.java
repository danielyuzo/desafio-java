package school.sptech.model.componentes;

import school.sptech.model.Medida;
import school.sptech.utils.ColetaDadosUtils;

public class Cpu extends Componente {

    private Double uso;

    public Cpu() {
        super(new Medida());
    }

    public Cpu(Medida medida) {
        super(medida);
    }

    public Cpu(Double uso) {
        super(new Medida());
        this.uso = uso;
    }

    public Cpu(Medida medida, Double uso) {
        super(medida);
        this.uso = uso;
    }

    @Override
    public void registrarDados() {
        this.uso = ColetaDadosUtils.LOOCA.getProcessador().getUso();
    }

    public Double getUso() {
        return uso;
    }

    public void setUso(Double uso) {
        this.uso = uso;
    }

    @Override
    public String toString() {
        return "Cpu{" +
                "uso=" + uso +
                '}';
    }
}
