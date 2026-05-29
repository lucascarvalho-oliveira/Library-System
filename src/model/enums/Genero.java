package model.enums;

public enum Genero {
    ACAO("Ação"),
    ROMANCE("Romance"),
    FICCAO_CIENTIFICA("Ficção Científica"),
    COMEDIA("Comedia"),
    SUSPENSE("Suspense"),
    DRAMA("Drama"),
    TERROR("Terror");

    private String descricao;

    Genero(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
