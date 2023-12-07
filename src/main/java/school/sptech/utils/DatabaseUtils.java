package school.sptech.utils;

import school.sptech.banco.conexao.Conexao;
import school.sptech.banco.conexao.ConexaoMySql;

public class DatabaseUtils {
    public static final Conexao[] CONEXOES = new Conexao[]{ new ConexaoMySql() };
}