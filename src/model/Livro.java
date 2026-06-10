package model;

import model.enums.Genero;

import java.util.ArrayList;
import java.util.List;

public class Livro {
    private int idLivro;
    private String titulo;
    private int volume;
    private String editora;

    private Genero genero;
    private List<Autor> autores;

    public Livro(){}

    public Livro(String titulo, int volume, String editora, Genero genero) {
        this.titulo = titulo;
        this.volume = volume;
        this.editora = editora;
        this.genero = genero;
        this.autores = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
}
