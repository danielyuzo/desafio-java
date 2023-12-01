package school.sptech.controller;

import school.sptech.model.Servidor;
import school.sptech.tasks.ChamadosTask;
import school.sptech.tasks.ColetaDadosTask;

import java.util.Timer;

public class AgendadorController {

    private Timer agendador;
    private boolean isRunning;
    private ChamadosTask taskIntegracoes;
    private ColetaDadosTask taskColeta;

    public AgendadorController() {
        agendador = new Timer();
        this.isRunning = false;
    }

    public void toggleAgendarTarefas(Servidor servidor) {
        if (this.isRunning) {
            this.taskColeta.cancel();
            this.taskIntegracoes.cancel();

            System.out.println("Monitoramento do servidor foi interrompido\n");
        } else {
            this.taskColeta = new ColetaDadosTask(servidor);
            this.taskIntegracoes = new ChamadosTask(servidor);

            this.agendador.schedule(this.taskColeta, ColetaDadosTask.DELAY, ColetaDadosTask.PERIODO);
            this.agendador.schedule(this.taskIntegracoes, ChamadosTask.DELAY, ChamadosTask.PERIODO);

            System.out.println("Monitoramento do servidor foi iniciado\n");
        }
    }

    public void encerrarAgendador() {
        if (this.isRunning) {
            this.taskColeta.cancel();
            this.taskIntegracoes.cancel();
        }
        this.agendador.cancel();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
