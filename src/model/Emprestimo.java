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

    public Emprestimo(){}

    public Emprestimo(LocalDate dataEmprestimo, Usuario usuario){
        this.dataEmprestimo = dataEmprestimo;
        this.dataRetorno = dataEmprestimo.plusDays(10);

        this.usuarios = usuario;
        this.exemplares = new ArrayList<>();
    }

    public List<Exemplar> getExemplares() {
        return exemplares;
    }

    public void setExemplares(List<Exemplar> exemplares) {
        this.exemplares = exemplares;
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

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataRetorno() {
        return dataRetorno;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataRetorno(LocalDate dataRetorno) {
        this.dataRetorno = dataRetorno;
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
