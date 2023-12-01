package school.sptech.tasks;

import school.sptech.model.Servidor;
import school.sptech.utils.ColetaDadosUtils;

import java.util.TimerTask;

public class ColetaDadosTask extends TimerTask {

    private Servidor servidor;
    public static final int DELAY = 0;
    public static final int PERIODO = 30000;

    public ColetaDadosTask(Servidor servidor) {
        this.servidor = servidor;
    }

    @Override
    public void run() {
        ColetaDadosUtils.monitorarDados(this.servidor);
    }

    public Servidor getServidor() {
        return servidor;
    }
}
