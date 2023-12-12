package school.sptech.view;

import school.sptech.banco.dao.ServidorDao;
import school.sptech.controller.AgendadorController;
import school.sptech.enums.MenuPrincipalEnum;
import school.sptech.model.Servidor;
import school.sptech.model.Usuario;
import school.sptech.utils.ColetaDadosUtils;
import school.sptech.utils.LeitoresUtils;
import school.sptech.utils.UsuarioUtils;

import java.util.Timer;

public class MenuPrincipal {

    private Usuario usuarioLogado;
    private Timer agendador;

    public MenuPrincipal() {
        agendador = new Timer();
    }

    public void logarUsuario() {
        System.out.println("GraphCar - Sistema de Monitoramento\n");

        do {
            System.out.println("Digite seu email:");
            String email = LeitoresUtils.LEITOR_TEXTO.nextLine();
            System.out.println("Digite sua senha");
            String senha = LeitoresUtils.LEITOR_TEXTO.nextLine();
            this.usuarioLogado = UsuarioUtils.validarLogin(email, senha);

            if (this.usuarioLogado == null) {
                System.out.println("Usuário inválido!");
            }
        } while (this.usuarioLogado == null);

        System.out.println("Bem-vindo(a), %s".formatted(this.usuarioLogado.getNome()));
    }

    public void exibirMenu() throws InterruptedException {
        MenuPrincipalEnum opcao;

        Servidor servidor = ColetaDadosUtils.obterDadosServidor();

        AgendadorController agendadorCtrl = new AgendadorController();

        do {
            System.out.println("Escolha uma opção:");
            for (MenuPrincipalEnum opcaoMenu : MenuPrincipalEnum.values()) {
                System.out.println(opcaoMenu);
            }
            opcao = MenuPrincipalEnum.of(LeitoresUtils.LEITOR_NUM.nextInt());

            if (opcao == MenuPrincipalEnum.TOGGLE_MONITORAMENTO) {
                agendadorCtrl.toggleAgendarTarefas(servidor);

            } else if (opcao == MenuPrincipalEnum.ALTERAR_COMPONENTE) {
                MenuComponentes menuComponentes = new MenuComponentes();
                menuComponentes.exibirMenu(servidor);

            } else if (opcao == MenuPrincipalEnum.ALTERAR_SLACK) {
                System.out.println("Webhook Atual: %s".formatted(servidor.getWebhookSlack()));
                System.out.println("Digite o novo WebHook do Slack:");
                String webhook = LeitoresUtils.LEITOR_TEXTO.nextLine();
                servidor.setWebhookSlack(webhook);
                ServidorDao.atualizarServidor(servidor);
            }

        } while (opcao != MenuPrincipalEnum.SAIR);

        this.usuarioLogado = null;
        agendadorCtrl.encerrarAgendador();
        System.out.println("Logout realizado com sucesso");
    }
}
