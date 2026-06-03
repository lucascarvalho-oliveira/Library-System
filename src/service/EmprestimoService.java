package service;

import model.Emprestimo;
import repository.EmprestimoRepository;

import java.time.Duration;

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

        repositoryEmprestimo.salvarEmprestimo(emprestimo);
    }

    public void calcularMulta(Emprestimo emprestimo){
        if(emprestimo.getDataEntrega().isEqual(emprestimo.getDataRetorno())){
            emprestimo.setMulta(0.0);
        }else{
            Duration duracao = Duration.between(emprestimo.getDataRetorno(), emprestimo.getDataEntrega());

            long minutosTotais = duracao.toMinutes();
            long diasCobrados = (long) Math.ceil(minutosTotais / 1440.0);

            emprestimo.setMulta(diasCobrados * 2.0);
        }

        repositoryEmprestimo.salvarMulta(emprestimo);
    }
}
