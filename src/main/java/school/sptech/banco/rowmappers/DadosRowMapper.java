package school.sptech.banco.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import school.sptech.banco.dao.MedidaDao;
import school.sptech.model.Dados;
import school.sptech.model.Medida;
import school.sptech.model.componentes.Componente;
import school.sptech.model.componentes.Cpu;
import school.sptech.model.componentes.Disco;
import school.sptech.model.componentes.Memoria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DadosRowMapper implements RowMapper<Dados> {

    @Override
    public Dados mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("idDados");
        List<Componente> componentes = new ArrayList<>();

        Double cpuUso = resultSet.getDouble("cpu");
        if (cpuUso != 0) {
            Medida medida = MedidaDao.buscarMedidaPorDadosComponente(id, "CPU");
            componentes.add(new Cpu(medida, cpuUso));
        }

        Double memoria = resultSet.getDouble("memoria");
        if (memoria != 0) {
            Medida medida = MedidaDao.buscarMedidaPorDadosComponente(id, "RAM");
            componentes.add(new Memoria(medida, memoria));
        }

        Double disco = resultSet.getDouble("disco");
        if (disco != 0) {
            Medida medida = MedidaDao.buscarMedidaPorDadosComponente(id, "Disco");
            componentes.add(new Disco(medida, disco));
        }

        LocalDateTime dataHora = resultSet.getTimestamp("dateDado").toLocalDateTime();
        Integer fkServidor = resultSet.getInt("fkServidor");

        Dados dado = new Dados(id, componentes, dataHora, fkServidor);
        return dado;
    }
}
