package model;

import model.enums.Genero;

public class Livro {
    private int id_livro;
    private String titulo;
    private int volume;
    private String editora;

    private Genero genero;
    private Autor autor;

    public Livro(int id_livro, String titulo, int volume, String editora, Genero genero, Autor autor) {
        this.id_livro = id_livro;
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

    public int getId_livro() {
        return id_livro;
    }

    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }
}
