package school.sptech.banco.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import school.sptech.model.ServidorComponente;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServidorComponenteRowMapper implements RowMapper<ServidorComponente> {
    @Override
    public ServidorComponente mapRow(ResultSet resultSet, int i) throws SQLException {
        Integer idServidorComponente = resultSet.getInt("idServidorComponente");
        Integer fkComponente = resultSet.getInt("fkComponente");
        Integer fkServidor = resultSet.getInt("fkServidor");
        return new ServidorComponente(idServidorComponente, fkComponente, fkServidor);
    }
}
