package model;

import model.enums.Status;

public class Exemplar {
    private int idExemplar;
    private int patrimonio;
    private String localizacao;

    private Livro livro;
    private Status status;

    public Exemplar(int idPatrimonio, String localizacao, Livro livro, Status status) {
        this.patrimonio = idPatrimonio;
        this.localizacao = localizacao;
        this.livro = livro;
        this.status = status;
    }

    public int getIdExemplar() {
        return idExemplar;
    }

    public void setIdExemplar(int idExemplar) {
        this.idExemplar = idExemplar;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public Livro getLivro() {
        return livro;
    }

    public int getPatrimonio() {
        return patrimonio;
    }

    public Status getStatus() {
        return status;
    }
}
