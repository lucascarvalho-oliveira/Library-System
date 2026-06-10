package application.controller;

import model.Emprestimo;
import model.Exemplar;
import model.Usuario;
import model.enums.Status;
import repository.EmprestimoRepository;
import repository.ExemplarRepository;
import repository.UsuarioRepository;
import service.EmprestimoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmprestimoController {
    private UsuarioRepository repositoryUsuario;
    private ExemplarRepository repositoryExemplar;
    private EmprestimoService serviceEmprestimo;
    private EmprestimoRepository repositoryEmprestimo;

    public EmprestimoController(UsuarioRepository repositoryUsuario, ExemplarRepository repositoryExemplar, EmprestimoService serviceEmprestimo, EmprestimoRepository repositoryEmprestimo){
        this.repositoryUsuario = repositoryUsuario;
        this.repositoryExemplar = repositoryExemplar;
        this.serviceEmprestimo = serviceEmprestimo;
        this.repositoryEmprestimo = repositoryEmprestimo;
    }

    public void emprestimo(Scanner sc){
        boolean voltar = false;
        do{
            System.out.println("\nInforme o nome do usuário");
            String nome = sc.nextLine();
            System.out.println("Informe o telefone do usuário (exemplo (DD)9.XXXX-XXXX):");
            String telefone = sc.nextLine();

            Usuario usuario = null;

            try{
                usuario = repositoryUsuario.buscarUsuario(nome, telefone);

            }catch (RuntimeException e){
                System.out.println(e.getMessage());
                return;
            }

            if(usuario == null){
                System.out.println("Usuário não encontrado.");
                return;
            }

            System.out.println("\n1 - Fazer empréstimo.");
            System.out.println("2 - Devolução de empréstimo");
            System.out.println("3 - Volta ao menu anterior.");
            System.out.println("Escolha a operação");
            int menu = sc.nextInt();sc.nextLine();

            switch (menu){
                case 1:
                    Emprestimo emprestimo = new Emprestimo(LocalDate.now(), usuario);

                    List<Exemplar> exemplares = new ArrayList<>();
                    while(true){
                        System.out.println("\ninforme o patrimonio do livro");
                        int patrimonio = sc.nextInt();sc.nextLine();

                        Exemplar exemplarEncontrado = null;
                        try {
                            exemplarEncontrado = repositoryExemplar.buscarExemplar(patrimonio);

                        }catch (RuntimeException e){
                            System.out.println(e.getMessage());
                        }

                        if(exemplarEncontrado == null){
                            System.out.println("Exemplar nao encontrado!");
                            break;
                        }

                        Status statusLivro = exemplarEncontrado.getStatus();

                        if(statusLivro == Status.EMPRESTADO){
                            System.out.println("Exemplar emprestado!");
                            return;
                        }

                        exemplares.add(exemplarEncontrado);

                        try {
                            repositoryExemplar.atualizarStatus(patrimonio, Status.EMPRESTADO);

                        }catch (RuntimeException e){
                            System.out.println(e.getMessage());
                            break;
                        }

                        System.out.println("O usuário levara mais algum livro ? (s/n)");
                        String escolha = sc.nextLine();

                        if(escolha.equalsIgnoreCase("n")){
                            break;
                        }
                    }
                    emprestimo.setExemplares(exemplares);

                    try {
                        serviceEmprestimo.salvarEmprestimo(emprestimo);

                    }catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    break;

                case 2:
                    Emprestimo emprestimoDV = null;
                    try{
                        emprestimoDV = repositoryEmprestimo.buscarEmprestimo(usuario.getIdUsuario());

                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }

                    emprestimoDV.setDataEntrega(LocalDate.now());

                    try {
                        serviceEmprestimo.calcularMulta(emprestimoDV);

                        List<Integer> patrimonios = repositoryEmprestimo.buscarPatrimonio(emprestimoDV.getIdEmprestimo());

                        for(Integer patrimonio : patrimonios){
                            repositoryExemplar.atualizarStatus(
                                    patrimonio,
                                    Status.DISPONIVEL
                            );
                        }

                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                        break;
                    }


                    if(emprestimoDV.getMulta() == 0.0){
                        System.out.println("\nNão ouve multa.");
                    }else{
                        System.out.println("\nValor da multa = R$ " + emprestimoDV.getMulta());
                    }
                    break;

                case 3:
                    voltar = true;
                    System.out.println();
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }while(!voltar);
    }
}
