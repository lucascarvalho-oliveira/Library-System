package service;

import model.Emprestimo;
import repository.EmprestimoRepository;

import java.time.temporal.ChronoUnit;

public class EmprestimoService {
    private EmprestimoRepository repositoryEmprestimo;

    public EmprestimoService(EmprestimoRepository repositoryEmprestimo){
        this.repositoryEmprestimo = repositoryEmprestimo;
    }

    public void salvarEmprestimo(Emprestimo emprestimo){
        if(emprestimo.getDataEmprestimo() == null){
            throw new IllegalArgumentException("Data do empréstimo incorreta.");
        }
        if(emprestimo.getDataRetorno() == null){
            throw new IllegalArgumentException("Data de retorno incorreta.");
        }

        try {
            repositoryEmprestimo.salvarEmprestimo(emprestimo);

        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public void calcularMulta(Emprestimo emprestimo){
        if(!emprestimo.getDataEntrega().isAfter(emprestimo.getDataRetorno())){
            emprestimo.setMulta(0.0);
        }else{
            long diasAtraso = ChronoUnit.DAYS.between(
                    emprestimo.getDataRetorno(),
                    emprestimo.getDataEntrega()
            );

            emprestimo.setMulta(diasAtraso * 2.0);
        }

        try {
            repositoryEmprestimo.updateEmprestimo(emprestimo);

        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
}
