package school.sptech.view;

import school.sptech.enums.AlterarComponenteEnum;
import school.sptech.enums.MenuComponenteEnum;
import school.sptech.utils.LeitoresUtils;

public class MenuCriarComponente {

    private MenuComponenteEnum componenteEscolhido;

    public MenuCriarComponente(MenuComponenteEnum componenteEscolhido) {
        this.componenteEscolhido = componenteEscolhido;
    }

    public void exibirMenu() {

        AlterarComponenteEnum opcao;
        do {
            System.out.println("Escolha uma opção:");
            for (AlterarComponenteEnum opcaoMenu : AlterarComponenteEnum.values()) {
                System.out.println(opcaoMenu);
            }
            opcao = AlterarComponenteEnum.of(LeitoresUtils.LEITOR_NUM.nextInt());

            switch (opcao) {
                case TOGGLE_COMPONENTE:
                    break;
                case ALTERAR_ALERTA:
                    break;
                case ALTERAR_CRITICO:
                    break;
                case ALTERAR_META:
                    break;
                default:

            }
        } while (opcao != AlterarComponenteEnum.VOLTAR);
    }
}
