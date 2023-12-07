package school.sptech.banco.dao;

import school.sptech.banco.rowmappers.MedidaRowMapper;
import school.sptech.model.Medida;
import school.sptech.model.Servidor;
import school.sptech.model.componentes.Componente;
import school.sptech.utils.DatabaseUtils;

import java.util.List;

public class MedidaDao {

    public static Medida buscarMedidaPorServidorComponente(Servidor servidor, String tipoComponente) {
        List<Medida> listaMedidas = DatabaseUtils.CONEXOES[0].getConexaoDoBanco().query("""
                SELECT m.* FROM Medida m 
                INNER JOIN ServidorComponente sc ON m.idServidorComponente = sc.idServidorComponente
                INNER JOIN Componente c ON c.idComponente = sc.fkComponente 
                WHERE c.nome = ? WHERE sc.fkServidor = ?
                """, new MedidaRowMapper(), tipoComponente, servidor.getIdServidor());
        if (listaMedidas.isEmpty()) {
            return null;
        } else {
            return listaMedidas.get(0);
        }
    }

    public static void inserirMedida(Componente componente, Medida medida) {
        DatabaseUtils.CONEXOES[0].getConexaoDoBanco().update("""
            INSERT INTO Medida (nome, unidade, limiteAlerta, limiteCritico, meta) VALUES (?,?,?,?,?)
                """, medida.getNome(), medida.getUnidade(), medida.getLimiteAlerta(), medida.getLimiteCritico(), medida.getMeta());
    }

    public static void alterarMedida(Medida medida) {
        DatabaseUtils.CONEXOES[0].getConexaoDoBanco().update("""
            UPDATE Medida SET nome = ?, unidade = ?, limiteAlerta = ?, limiteCritico = ?, meta = ?
            WHERE idServidorComponente = ?                
            """, medida.getNome(), medida.getUnidade(), medida.getLimiteAlerta(), medida.getLimiteCritico(), medida.getMeta(), medida.getIdServidorComponente());
    }
}
