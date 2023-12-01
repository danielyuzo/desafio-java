package school.sptech;

import school.sptech.utils.LeitoresUtils;
import school.sptech.view.MenuPrincipal;

public class App {
    public static void main(String[] args) {

        MenuPrincipal menu = new MenuPrincipal();
        menu.logarUsuario();

        try {
            menu.exibirMenu();
        } catch (InterruptedException e) {
            System.out.println("Houve um problema durante a execução: ");
            System.out.println(e);
        }

        LeitoresUtils.finalizarLeitores();
    }
}
