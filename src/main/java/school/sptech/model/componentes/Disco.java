package school.sptech.model.componentes;

import com.github.britooo.looca.api.group.discos.Volume;
import school.sptech.model.Medida;
import school.sptech.utils.ColetaDadosUtils;

import java.util.List;

public class Disco extends Componente {

    private Double uso;

    public Disco() {
        super(new Medida());
    }
    public Disco(Medida medida) {
        super(medida);
    }

    public Disco(Double uso) {
        super(new Medida());
        this.uso = uso;
    }

    public Disco(Medida medida, Double uso) {
        super(medida);
        this.uso = uso;
    }

    @Override
    public void registrarDados() {
        List<Volume> volumes = ColetaDadosUtils.LOOCA.getGrupoDeDiscos().getVolumes();
        Long espacoTotal = 0L;
        Long espacoUsado = 0L;
        for (Volume volumeAtual : volumes) {
            espacoTotal += volumeAtual.getTotal();
            espacoUsado += volumeAtual.getTotal() - volumeAtual.getDisponivel();
        }
        this.uso = 100.0 * espacoUsado / espacoTotal;
    }

    public Double getUso() {
        return uso;
    }

    public void setUso(Double uso) {
        this.uso = uso;
    }

    @Override
    public String toString() {
        return "Disco{" +
                "uso=" + uso +
                '}';
    }
}
