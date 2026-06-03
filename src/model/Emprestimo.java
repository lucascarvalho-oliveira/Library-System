package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Emprestimo {
    private int idEmprestimo;
    private LocalDate dataEmprestimo;
    private LocalDate dataRetorno;
    private LocalDate dataEntrega;
    private double multa;

    private Usuario usuarios;
    private List<Exemplar> exemplares;

    public Emprestimo(LocalDate dataEmprestimo, Usuario usuario){
        this.dataEmprestimo = dataEmprestimo;
        this.dataRetorno = dataEmprestimo.plusDays(10);

        this.usuarios = usuario;
        this.exemplares = new ArrayList<>();
    }

    public void adicionarExemplar(Exemplar exemplar){
        this.exemplares.add(exemplar);
    }

    public List<Exemplar> getExemplares() {
        return exemplares;
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataRetorno() {
        return dataRetorno;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntreque(LocalDate dataEntreque){
        this.dataEntrega = dataEntreque;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public Usuario getUsuarios() {
        return usuarios;
    }
}
