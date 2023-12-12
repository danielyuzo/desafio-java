package school.sptech.view;

import school.sptech.enums.MenuComponenteEnum;
import school.sptech.model.Servidor;
import school.sptech.utils.LeitoresUtils;

public class MenuComponentes {

    public void exibirMenu(Servidor servidor) {

        MenuComponenteEnum opcao;
        MenuAlterarComponente alterarComponente;
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
                    alterarComponente = new MenuAlterarComponente(opcao);
                    alterarComponente.exibirMenu(servidor);
                    break;
                case VOLTAR:
                    break;
                default:
            }

        } while (opcao != MenuComponenteEnum.VOLTAR);
    }
}
