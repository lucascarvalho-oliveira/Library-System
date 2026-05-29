package model;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Emprestimo {
    private int id_emprestimo;
    private LocalDate dataEmprestimo;
    private LocalDate dataRetorno;
    private LocalDate dataEntreque;
    private double multa;

    private Usuario usuarios;
    private List<Exemplar> exemplares;

    public Emprestimo(LocalDate dataEmprestimo, Usuario usuario){
        this.dataEmprestimo = dataEmprestimo;

        this.usuarios = usuario;
        this.exemplares = new ArrayList<>();
    }

    public LocalDate getDataRetorno() {
        return dataRetorno;
    }

    public LocalDate getDataEntreque() {
        return dataEntreque;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public int getId_emprestimo() {
        return id_emprestimo;
    }

    public void setId_emprestimo(int id_emprestimo) {
        this.id_emprestimo = id_emprestimo;
    }

    public void setDataHoraEntreque(LocalDate dataHoraEntreque){
        this.dataEntreque = dataHoraEntreque;
    }

    public void dataRetorno(){
        dataRetorno = dataEmprestimo.plusDays(10);
    }
}
