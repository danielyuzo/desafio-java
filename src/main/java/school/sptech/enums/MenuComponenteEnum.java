package school.sptech.enums;

public enum MenuComponenteEnum {

    CPU(1, "CPU"),
    RAM(2, "RAM"),
    DISCO(3, "Disco"),
    VOLTAR(0, "Voltar");


    private int codigo;
    private String nome;

    MenuComponenteEnum(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public static MenuComponenteEnum of(int codigo) {
        switch (codigo) {
            case 1:
                return CPU;
            case 2:
                return RAM;
            case 3:
                return DISCO;
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
        return nome;
    }

    @Override
    public String toString() {
        return "%d - %s".formatted(this.codigo, this.nome);
    }
}
