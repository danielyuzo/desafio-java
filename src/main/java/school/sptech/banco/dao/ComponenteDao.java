package school.sptech.banco.dao;

import school.sptech.banco.rowmappers.ComponenteMedidaRowMapper;
import school.sptech.banco.rowmappers.ComponenteRowMapper;
import school.sptech.banco.rowmappers.ServidorComponenteRowMapper;
import school.sptech.model.Servidor;
import school.sptech.model.ServidorComponente;
import school.sptech.model.componentes.Componente;
import school.sptech.utils.DatabaseUtils;

import java.util.List;

public class ComponenteDao {
    public static List<Componente> buscarComponentePorServidor(Servidor servidor) {
        List<Componente> listaComponentes = DatabaseUtils.CONEXOES[0].getConexaoDoBanco().query("""
                SELECT c.NomeComponente AS tipo FROM Componente c INNER JOIN
                    ModeloComponente mc ON mc.fkComponente = c.idComponentes WHERE mc.fkServidor = ?
                """, new ComponenteRowMapper(), servidor.getIdServidor());
        return listaComponentes;
    }

    public static List<Componente> buscarComponenteMedidaPorServidor(Servidor servidor) {
        List<Componente> listaComponentes = DatabaseUtils.CONEXOES[0].getConexaoDoBanco().query("""
                SELECT c.NomeComponente AS tipo, m.idServidorComponente as idServidorComponente,
                    m.nome AS nomeMedida, m.unidade AS unidade, m.limiteAlerta AS limiteAlerta, 
                    m.limiteCritico AS limiteCritico, m.meta AS meta
                    FROM Componente c INNER JOIN
                    ServidorComponente sc ON sc.fkComponente = c.idComponente
                    INNER JOIN Medida m ON m.idServidorComponente = sc.idServidorComponente
                    WHERE mc.fkServidor = ?
                """, new ComponenteMedidaRowMapper(), servidor.getIdServidor());
        return listaComponentes;
    }

    public static void inserirComponenteServidorPadrao(Servidor servidor) {
        DatabaseUtils.CONEXOES[0].getConexaoDoBanco().update("""
                INSERT INTO ServidorComponente (fkComponente, fkServidor) VALUES
                    (1, ?), (2, ?), (3, ?)
                """, servidor.getIdServidor(), servidor.getIdServidor(), servidor.getIdServidor());
        List<ServidorComponente> listarServidorComponente = DatabaseUtils.CONEXOES[0]
                .getConexaoDoBanco().query("""
                        SELECT * FROM ServidorComponente
                        WHERE fkServidor = ?
                        """, new ServidorComponenteRowMapper(), servidor.getIdServidor());
        for (ServidorComponente servidorComponente : listarServidorComponente) {
            DatabaseUtils.CONEXOES[0].getConexaoDoBanco().update("""
                INSERT INTO Medida VALUES (NULL, ?, ?, ?, ?, ?)
                """, )
        }
        // TODO Insert no Medida e no Medida Servidor Componente
    }
}
