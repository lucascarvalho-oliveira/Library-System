package model;

import model.enums.Status;

public class Exemplar {
    private int id_exemplar;
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

    public String getLocalizacao() {
        return localizacao;
    }

    public int getIdPatrimonio() {
        return patrimonio;
    }

    public int getId_exemplar() {
        return id_exemplar;
    }

    public void setId_exemplar(int id_exemplar) {
        this.id_exemplar = id_exemplar;
    }
}
