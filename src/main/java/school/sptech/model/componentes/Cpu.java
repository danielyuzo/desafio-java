package school.sptech.model.componentes;

import school.sptech.banco.dao.MedidaDao;
import school.sptech.model.Medida;
import school.sptech.model.Servidor;
import school.sptech.utils.ColetaDadosUtils;

public class Cpu extends Componente {

    public Cpu() {
        super(new Medida());
    }

    public Cpu(Medida medida) {
        super(medida);
    }

    public Cpu(Double uso) {
        super(new Medida(), uso);
    }
    public Cpu(Medida medida, Double uso) {
        super(medida, uso);
    }

    @Override
    public void registrarDados() {
        if (this.getMedida().getAtiva()) {
            this.setUso(ColetaDadosUtils.LOOCA.getProcessador().getUso());
        } else {
            this.setUso(null);
        }
    }

    @Override
    public void atualizarMedida(Servidor servidor) {
        Medida medida = MedidaDao.buscarMedidaPorServidorComponente(servidor, "CPU");
        this.setMedida(medida);
    }

    @Override
    public String toString() {
        return "Cpu{} " + super.toString();
    }
}
