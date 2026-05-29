package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id_usuario;
    private String nome;
    private String telefone;
    private LocalDate dataNascimento;

    private List<Emprestimo> emprestimos;

    public Usuario(int id_usuario, String nome, String telefone, LocalDate dataNascimento) {
        this.id_usuario = id_usuario;
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

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario= id_usuario;
    }
}
