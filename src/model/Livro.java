package model;

import model.enums.Genero;

public class Livro {
    private int idLivro;
    private String titulo;
    private int volume;
    private String editora;

    private Genero genero;
    private Autor autor;

    public Livro(String titulo, int volume, String editora, Genero genero, Autor autor) {
        this.titulo = titulo;
        this.volume = volume;
        this.editora = editora;
        this.genero = genero;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getVolume() {
        return volume;
    }

    public String getEditora() {
        return editora;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }
}
