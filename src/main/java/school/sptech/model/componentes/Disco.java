package school.sptech.model.componentes;

import com.github.britooo.looca.api.group.discos.Volume;
import school.sptech.banco.dao.MedidaDao;
import school.sptech.model.Medida;
import school.sptech.model.Servidor;
import school.sptech.utils.ColetaDadosUtils;

import java.util.List;

public class Disco extends Componente {


    public Disco() {
        super(new Medida());
    }
    public Disco(Medida medida) {
        super(medida);
    }

    public Disco(Double uso) {
        super(new Medida(), uso);
    }

    public Disco(Medida medida, Double uso) {
        super(medida, uso);
    }

    @Override
    public void registrarDados() {
        if (this.getMedida().getAtiva()) {
            List<Volume> volumes = ColetaDadosUtils.LOOCA.getGrupoDeDiscos().getVolumes();
            Long espacoTotal = 0L;
            Long espacoUsado = 0L;
            for (Volume volumeAtual : volumes) {
                espacoTotal += volumeAtual.getTotal();
                espacoUsado += volumeAtual.getTotal() - volumeAtual.getDisponivel();
            }
            this.setUso(100.0 * espacoUsado / espacoTotal);
        } else {
            this.setUso(null);
        }
    }

    @Override
    public void atualizarMedida(Servidor servidor) {
        Medida medida = MedidaDao.buscarMedidaPorServidorComponente(servidor, "Disco");
        this.setMedida(medida);
    }

    @Override
    public String toString() {
        return "Disco{} " + super.toString();
    }
}
