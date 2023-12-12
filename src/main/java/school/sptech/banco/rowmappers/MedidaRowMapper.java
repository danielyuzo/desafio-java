package school.sptech.banco.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import school.sptech.model.Medida;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedidaRowMapper implements RowMapper<Medida> {

    @Override
    public Medida mapRow(ResultSet resultSet, int i) throws SQLException {

        Integer idServidorComponente = resultSet.getInt("idServidorComponente");
        String nome = resultSet.getString("nome");
        String unidade = resultSet.getString("unidade");
        Boolean ativa = resultSet.getBoolean("ativa");
        Double limiteAlerta = resultSet.getDouble("limiteAlerta");
        Double limiteCritico = resultSet.getDouble("limiteCritico");

        Medida medida = new Medida(idServidorComponente, nome, unidade, ativa, limiteAlerta, limiteCritico);
        return medida;
    }
}
