package model;

import model.enums.Status;

public class Exemplar {
    private int idExemplar;
    private int patrimonio;
    private String localizacao;

    private Livro livro;
    private Status status;

    public Exemplar(){};

    public Exemplar(int patrimonio, String localizacao, Livro livro) {
        this.patrimonio = patrimonio;
        this.localizacao = localizacao;
        this.livro = livro;
        this.status = Status.DISPONIVEL;
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

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public int getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(int patrimonio) {
        this.patrimonio = patrimonio;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
