package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Autor {
    private int idAutor;
    private String nome;
    private String nacionalidade;
    private LocalDate dataNascimento;

    private List<Livro> livros;

    public Autor(){};

    public Autor(String nome, String nacionalidade, LocalDate dataNascimento) {
        this.idAutor = idAutor;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.dataNascimento = dataNascimento;

        this.livros = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }
}
