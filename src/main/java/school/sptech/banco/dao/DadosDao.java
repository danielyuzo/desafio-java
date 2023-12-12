package school.sptech.banco.dao;

import school.sptech.banco.rowmappers.DadosRowMapper;
import school.sptech.model.Dados;
import school.sptech.model.componentes.Componente;
import school.sptech.model.componentes.Cpu;
import school.sptech.model.componentes.Disco;
import school.sptech.model.componentes.Memoria;
import school.sptech.utils.DatabaseUtils;

import java.util.List;
import java.util.Map;

public class DadosDao {

    public static Dados buscarDadosPorServidorEDataHora(Integer fkServidor, String dataHora) {
        List<Dados> listaDados = DatabaseUtils.CONEXOES[0].getConexaoDoBanco().query("""
            SELECT * FROM Dados WHERE fkServidor = ? AND dateDado = ?
            """, new DadosRowMapper(), fkServidor, dataHora);

        if (listaDados.isEmpty()) {
            return null;
        } else {
            return listaDados.get(0);
        }
    }

    public static Map<String, Object> buscarResumoPorServidor(Integer idServidor) {
        List<Map<String, Object>> resultados = DatabaseUtils.CONEXOES[0].getConexaoDoBanco().queryForList("""
                SELECT MAX(cpu) AS maxCpu, AVG(cpu) AS avgCpu, MIN(cpu) AS minCpu,
                MAX(memoria) AS maxRam, AVG(memoria) AS avgRam, MIN(memoria) AS minRam,
                MAX(disco) AS maxDisco, AVG(disco) AS avgDisco, MIN(disco) AS minDisco
                FROM Dados WHERE fkServidor = ?;""", idServidor);

        if (resultados.isEmpty()) {
            return null;
        } else {
            return resultados.get(0);
        }
    }

    public static void inserirDados(Dados dadosServidor) {
        List<Componente> componentes = dadosServidor.getComponentes();
        Double[] dados = new Double[3];

        for (Componente componenteDaVez : componentes) {
            if (componenteDaVez instanceof Cpu) {
                dados[0] = ((Cpu) componenteDaVez).getUso();
            } else if (componenteDaVez instanceof Memoria) {
                dados[1] = ((Memoria) componenteDaVez).getUso();
            } else {
                dados[2] = ((Disco) componenteDaVez).getUso();
            }
        }

        DatabaseUtils.CONEXOES[0].getConexaoDoBanco().update("""
            INSERT INTO Dados (cpu, memoria, disco, dateDado, fkServidor) 
            VALUES (?, ?, ?, ?, ?)
            """, dados[0], dados[1], dados[2], dadosServidor.getDateDado(), dadosServidor.getFkServidor());
    }
}
