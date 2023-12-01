package school.sptech.enums;

public enum AlterarComponenteEnum {


    TOGGLE_COMPONENTE(1, "Alterar monitoramento"),
    ALTERAR_ALERTA(2, "Alterar Limite Alerta"),
    ALTERAR_CRITICO(3, "Alterar Limite Crítico"),
    ALTERAR_META(4, "Alterar Meta"),
    VOLTAR(0, "Voltar");


    private int codigo;
    private String descricao;

    AlterarComponenteEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static AlterarComponenteEnum of(int codigo) {
        switch (codigo) {
            case 1:
                return TOGGLE_COMPONENTE;
            case 2:
                return ALTERAR_ALERTA;
            case 3:
                return ALTERAR_CRITICO;
            case 4:
                return ALTERAR_META;
            case 0:
                return VOLTAR;
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
