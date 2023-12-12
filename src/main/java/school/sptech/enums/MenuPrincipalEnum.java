package school.sptech.enums;

public enum MenuPrincipalEnum {


    TOGGLE_MONITORAMENTO(1, "Iniciar/Parar Monitoramento"),
    ALTERAR_COMPONENTE(2, "Alterar Monitoramento de Componentes"),
    ALTERAR_SLACK(3, "Alterar WebHook do Slack"),
    SAIR(0, "Sair");

    private int codigo;
    private String descricao;

    MenuPrincipalEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static MenuPrincipalEnum of(int codigo) {
        switch (codigo) {
            case 1:
                return TOGGLE_MONITORAMENTO;
            case 2:
                return ALTERAR_COMPONENTE;
            case 3:
                return ALTERAR_SLACK;
            case 0:
                return SAIR;
            default:
                return null;
        }
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "%d - %s".formatted(this.codigo, this.descricao);
    }
}
