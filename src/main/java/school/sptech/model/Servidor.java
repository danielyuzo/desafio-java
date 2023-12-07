package school.sptech.model;

import school.sptech.banco.dao.DadosDao;
import school.sptech.tasks.ChamadosTask;
import school.sptech.tasks.ColetaDadosTask;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

    private Integer idServidor;
    private String modeloServidor;
    private String sistemaOperacional;
    private String hostname;
    private String mac;
    private List<Dados> dados;

    public Servidor() {
        this.dados = new ArrayList<>();
    }

    public Servidor(Integer idServidor, String modeloServidor, String hostname, String mac, String sistemaOperacional, List<Dados> dados) {
        this.idServidor = idServidor;
        this.modeloServidor = modeloServidor;
        this.hostname = hostname;
        this.mac = mac;
        this.sistemaOperacional = sistemaOperacional;
        this.dados = dados;
    }

    public Servidor(Integer idServidor, String modeloServidor, String hostname, String mac, String sistemaOperacional) {
        this.idServidor = idServidor;
        this.modeloServidor = modeloServidor;
        this.hostname = hostname;
        this.mac = mac;
        this.sistemaOperacional = sistemaOperacional;
        this.dados = new ArrayList<>();
    }

    public Servidor(String modeloServidor, String hostname, String mac, String sistemaOperacional, List<Dados> dados) {
        this.modeloServidor = modeloServidor;
        this.hostname = hostname;
        this.mac = mac;
        this.sistemaOperacional = sistemaOperacional;
        this.dados = dados;
    }

    public Servidor(String modeloServidor, String hostname, String mac, String sistemaOperacional) {
        this.modeloServidor = modeloServidor;
        this.hostname = hostname;
        this.mac = mac;
        this.sistemaOperacional = sistemaOperacional;
        this.dados = new ArrayList<>();
    }

    public void adicionarDado(Dados dado) {
        if (dado != null) {
            dado.setFkServidor(this.idServidor);
            DadosDao.inserirDados(dado);
            String dataHora = dado.getDateDado().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            dado = DadosDao.buscarDadosPorServidorEDataHora(this.idServidor, dataHora);
            this.dados.add(dado);
        }
    }

    public void removerDadoPorId(Integer idDado) {

    }

    public Dados getUltimoDadosServidor() {
        if (this.dados.isEmpty()) {
            return null;
        } else {
            return this.dados.get(this.dados.size() - 1);
        }
    }

    public List<Dados> getDadosServidorParaAberturaDeChamados() {
        List<Dados> ultimosDados = new ArrayList<>();

        Integer qtdeChamados = ChamadosTask.PERIODO / ColetaDadosTask.PERIODO;

        for (int i = this.dados.size() - 1; i >= this.dados.size() - qtdeChamados && i >= 0; i--) {
            ultimosDados.add(this.dados.get(i));
        }

        return ultimosDados;
    }

    public Integer getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(Integer idServidor) {
        this.idServidor = idServidor;
    }

    public String getModeloServidor() {
        return modeloServidor;
    }

    public void setModeloServidor(String modeloServidor) {
        this.modeloServidor = modeloServidor;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }


    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }
}
