package school.sptech.utils;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import school.sptech.banco.dao.ComponenteDao;
import school.sptech.banco.dao.ServidorDao;
import school.sptech.model.Dados;
import school.sptech.model.Servidor;
import school.sptech.model.componentes.Componente;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ColetaDadosUtils {
    public static final Looca LOOCA = new Looca();

    public static Servidor obterDadosServidor() {
        String hostName = LOOCA.getRede().getParametros().getHostName();
        String mac = null;

        for (RedeInterface redeInterface : LOOCA.getRede().getGrupoDeInterfaces().getInterfaces()) {
            if (redeInterface.getEnderecoMac().getBytes()[1] % 2 == 0) {
                mac = redeInterface.getEnderecoMac();
                mac = String.join("", mac.split(":"));
                break;
            }
        }

        Servidor servidorBuscado = ServidorDao.buscarServidorPorMac(mac);

        if (servidorBuscado == null) {
            System.out.println("Servidor não encontrado. Realizando cadastro no banco:");

            String nomeOs = LOOCA.getSistema().getSistemaOperacional();
            String modelo = LOOCA.getSistema().getFabricante();
            Servidor servidor = new Servidor(modelo, hostName, mac, null, nomeOs);
            ServidorDao.inserirServidor(servidor);

            servidor = ServidorDao.buscarServidorPorHostName(hostName);
            System.out.println("""
                Servidor %s cadastrado com sucesso!
                Cadastrando componentes com configurações padrão
                """.formatted(servidor.getHostname()));
            ComponenteDao.inserirComponenteServidorPadrao(servidor);
            return servidor;

        } else {
            System.out.println("""
                Servidor %s encontrado na base de dados!
                """.formatted(servidorBuscado.getHostname()));
            return servidorBuscado;
        }
    }

    @SuppressWarnings({"oshi.util.platform.windows.WmiQueryHandler"})
    public static void monitorarDados(Servidor servidor) {

        List<Componente> componentes = ComponenteDao.buscarComponenteMedidaPorServidor(servidor);
        Dados dado = new Dados(componentes, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), servidor.getIdServidor());
        dado.registrarDados(servidor);

        servidor.adicionarDado(dado);
    }
}
