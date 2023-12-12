package school.sptech.controller;

import school.sptech.integracoes.slack.SlackIntegracao;
import school.sptech.model.Dados;
import school.sptech.model.Servidor;
import school.sptech.model.componentes.Componente;
import school.sptech.model.componentes.Cpu;
import school.sptech.model.componentes.Disco;
import school.sptech.model.componentes.Memoria;

import java.io.IOException;
import java.util.List;

public class ChamadosController {

    public static void enviarMensagens(Servidor servidor) throws IOException, InterruptedException {
        List<Dados> ultimosDados = servidor.getDadosServidorParaAberturaDeChamados();
        System.out.println(ultimosDados);
        ContadorChamados contador = new ContadorChamados();
        contador.popularDados(ultimosDados);
        System.out.println(contador);
        if (contador.alertasCpu > 1) {
            SlackIntegracao.enviarMensagem("""
                Servidor %s - CPU atingiu nível %s
                Uso: %.2f %%
                """.formatted(servidor.getHostname(),
                    contador.maxCpu > contador.criticoCpu ? "Crítico" : "Alerta",
                    contador.maxCpu), servidor);
        }
        if (contador.alertasRam > 1) {
            SlackIntegracao.enviarMensagem("""
                Servidor %s - RAM atingiu nível %s
                Uso: %.2f %%
                """.formatted(servidor.getHostname(),
                    contador.maxRam > contador.criticoRam ? "Crítico" : "Alerta",
                    contador.maxRam), servidor);
        }
        if (contador.alertasDisco > 1) {
            SlackIntegracao.enviarMensagem("""
                Servidor %s - Disco atingiu nível %s
                Uso: %.2f %%
                """.formatted(servidor.getHostname(),
                    contador.maxDisco > contador.criticoDisco ? "Crítico" : "Alerta",
                    contador.maxDisco), servidor);
        }
    }

    private static class ContadorChamados {
        private int alertasCpu;
        private int alertasRam;
        private int alertasDisco;
        private double maxCpu;
        private double criticoCpu;
        private double maxRam;
        private double criticoRam;
        private double maxDisco;
        private double criticoDisco;

        private void popularDados(List<Dados> ultimosDados) {
            for (Dados dado : ultimosDados) {
                for (Componente componente : dado.getComponentes()) {
                    if (componente instanceof Cpu) {
                        Cpu cpu = (Cpu) componente;
                        this.criticoCpu = cpu.getMedida().getLimiteCritico();
                        if (cpu.getUso() > cpu.getMedida().getLimiteAlerta()) {
                            this.alertasCpu++;
                            if (cpu.getUso() > this.maxCpu){
                                this.maxCpu = cpu.getUso();
                            }
                        }
                    } else if (componente instanceof Memoria) {
                        Memoria ram = (Memoria) componente;
                        this.criticoRam = ram.getMedida().getLimiteCritico();
                        if (ram.getUso() > ram.getMedida().getLimiteAlerta()) {
                            this.alertasRam++;
                            if (ram.getUso() > this.maxRam){
                                this.maxRam = ram.getUso();
                            }
                        }
                    } else if (componente instanceof Disco) {
                        Disco disco = (Disco) componente;
                        this.criticoDisco = disco.getMedida().getLimiteCritico();
                        if (disco.getUso() > disco.getMedida().getLimiteAlerta()) {
                            this.alertasDisco++;
                            if (disco.getUso() > this.maxDisco){
                                this.maxDisco = disco.getUso();
                            }
                        }
                    }
                }
            }
        }

        @Override
        public String toString() {
            return "ContadorChamados{" +
                    "alertasCpu=" + alertasCpu +
                    ", alertasRam=" + alertasRam +
                    ", alertasDisco=" + alertasDisco +
                    ", maxCpu=" + maxCpu +
                    ", criticoCpu=" + criticoCpu +
                    ", maxRam=" + maxRam +
                    ", criticoRam=" + criticoRam +
                    ", maxDisco=" + maxDisco +
                    ", criticoDisco=" + criticoDisco +
                    '}';
        }
    }
}
