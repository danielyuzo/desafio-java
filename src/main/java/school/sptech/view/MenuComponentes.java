package school.sptech.view;

import school.sptech.enums.MenuComponenteEnum;
import school.sptech.utils.LeitoresUtils;

public class MenuComponentes {

    public void exibirMenu() {

        MenuComponenteEnum opcao;
        MenuCriarComponente criarComponente;
        do {
            System.out.println("Escolha uma opção:");
            for (MenuComponenteEnum opcaoMenu : MenuComponenteEnum.values()) {
                System.out.println(opcaoMenu);
            }
            opcao = MenuComponenteEnum.of(LeitoresUtils.LEITOR_NUM.nextInt());

            switch (opcao) {
                case CPU:
                case RAM:
                case DISCO:
                    criarComponente = new MenuCriarComponente(opcao);
                    criarComponente.exibirMenu();
                    break;
                case VOLTAR:
                    break;
                default:
            }

        } while (opcao != MenuComponenteEnum.VOLTAR);
    }
}
