package school.sptech.view;

import school.sptech.banco.dao.MedidaDao;
import school.sptech.enums.AlterarComponenteEnum;
import school.sptech.enums.MenuComponenteEnum;
import school.sptech.model.Medida;
import school.sptech.model.Servidor;
import school.sptech.utils.LeitoresUtils;

public class MenuAlterarComponente {

    private MenuComponenteEnum componenteEscolhido;

    public MenuAlterarComponente(MenuComponenteEnum componenteEscolhido) {
        this.componenteEscolhido = componenteEscolhido;
    }

    public void exibirMenu(Servidor servidor) {
        Medida medida = MedidaDao.buscarMedidaPorServidorComponente(servidor, this.componenteEscolhido.getNome());
        AlterarComponenteEnum opcao;
        do {
            System.out.println("Componente: %s\n%s".formatted(componenteEscolhido.getNome(), medida));
            System.out.println("Escolha uma opção:");
            for (AlterarComponenteEnum opcaoMenu : AlterarComponenteEnum.values()) {
                System.out.println(opcaoMenu);
            }
            opcao = AlterarComponenteEnum.of(LeitoresUtils.LEITOR_NUM.nextInt());

            switch (opcao) {
                case TOGGLE_COMPONENTE:
                    if (medida.getAtiva()) {
                        System.out.println("Desligando monitoramento:");
                    } else {
                        System.out.println("Ligando monitoramento:");
                    }
                    medida.setAtiva(!medida.getAtiva());
                    MedidaDao.alterarMedida(medida);
                    break;
                case ALTERAR_ALERTA:
                    Double novoLimiteAlerta;
                    do {
                        System.out.println("Digite um novo valor para limite de alerta:");
                        novoLimiteAlerta = LeitoresUtils.LEITOR_NUM.nextDouble();
                        if (novoLimiteAlerta < 0. || novoLimiteAlerta > 100.) {
                            System.out.println("Digite um valor entre 0 e 100");
                        }
                    } while (novoLimiteAlerta < 0. || novoLimiteAlerta > 100.);
                    medida.setLimiteAlerta(novoLimiteAlerta);
                    MedidaDao.alterarMedida(medida);
                    break;
                case ALTERAR_CRITICO:
                    Double novoLimiteCritico;
                    do {
                        System.out.println("Digite um novo valor para limite crítico:");
                        novoLimiteCritico = LeitoresUtils.LEITOR_NUM.nextDouble();
                        if (novoLimiteCritico < 0. || novoLimiteCritico > 100.) {
                            System.out.println("Digite um valor entre 0 e 100");
                        }
                    } while (novoLimiteCritico < 0. || novoLimiteCritico > 100.);
                    medida.setLimiteAlerta(novoLimiteCritico);
                    MedidaDao.alterarMedida(medida);
                    break;
                case VOLTAR:
                    System.out.println("Componente atualizado");
                    break;
                default:
                    System.out.println("Digite uma opção válida!");
            }
        } while (opcao != AlterarComponenteEnum.VOLTAR);
    }


}
