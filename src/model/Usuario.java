package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int idUsuario;
    private String nome;
    private String telefone;
    private LocalDate dataNascimento;

    private List<Emprestimo> emprestimos;

    public Usuario(){}

    public Usuario(String nome, String telefone, LocalDate dataNascimento) {
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;

        this.emprestimos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario= idUsuario;
    }
}
