package school.sptech.model.componentes;

import school.sptech.banco.dao.MedidaDao;
import school.sptech.model.Medida;
import school.sptech.model.Servidor;
import school.sptech.utils.ColetaDadosUtils;

public class Memoria extends Componente {

    public Memoria() {
        super(new Medida());
    }
    public Memoria(Medida medida) {
        super(medida);
    }
    public Memoria(Double uso) {
        super(new Medida(), uso);
    }
    public Memoria(Medida medida, Double uso) {
        super(medida, uso);
    }

    @Override
    public void registrarDados() {
        if (this.getMedida().getAtiva()) {
            this.setUso(100. * ColetaDadosUtils.LOOCA.getMemoria().getEmUso() / ColetaDadosUtils.LOOCA.getMemoria().getTotal());
        } else {
            this.setUso(null);
        }
    }

    @Override
    public void atualizarMedida(Servidor servidor) {
        Medida medida = MedidaDao.buscarMedidaPorServidorComponente(servidor, "RAM");
        this.setMedida(medida);
    }

    @Override
    public String toString() {
        return "Memoria{} " + super.toString();
    }
}
