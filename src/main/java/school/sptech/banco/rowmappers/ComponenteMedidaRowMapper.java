package school.sptech.banco.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import school.sptech.model.Medida;
import school.sptech.model.componentes.Componente;
import school.sptech.model.componentes.Cpu;
import school.sptech.model.componentes.Disco;
import school.sptech.model.componentes.Memoria;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComponenteMedidaRowMapper implements RowMapper<Componente> {

    @Override
    public Componente mapRow(ResultSet resultSet, int i) throws SQLException {
        String tipo = resultSet.getString("tipo");

        Integer idServidorComponente = resultSet.getInt("idServidorComponente");
        String nome = resultSet.getString("nome");
        String unidade = resultSet.getString("unidade");
        Double limiteAlerta = resultSet.getDouble("limiteAlerta");
        Double limiteCritico = resultSet.getDouble("limiteCritico");
        Double meta = resultSet.getDouble("meta");
        Medida medida = new Medida(idServidorComponente, nome, unidade, limiteAlerta, limiteCritico, meta);
        Componente comp = null;
        if (tipo.equals("CPU")) {
            comp = new Cpu(medida);
        } else if (tipo.equals("RAM")) {
            comp = new Memoria(medida);
        } else {
            comp = new Disco(medida);
        }
        return comp;
    }
}
