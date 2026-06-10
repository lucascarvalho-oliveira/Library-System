package application.controller;

import model.Usuario;
import repository.EmprestimoRepository;
import repository.UsuarioRepository;

import java.util.Scanner;

public class HistoricoController {
    UsuarioRepository repositoryUsuario;
    EmprestimoRepository repositoryEmprestimo;

    public HistoricoController(UsuarioRepository repositoryUsuario, EmprestimoRepository repositoryEmprestimo){
        this.repositoryUsuario = repositoryUsuario;
        this.repositoryEmprestimo = repositoryEmprestimo;
    }

    public void historico(Scanner sc){
        boolean voltar = false;
        do{
            System.out.println("\n1 - Histórico de usuário.");
            System.out.println("2 - Histórico de livro.");
            System.out.println("3 - Voltar ao menu anterior");
            System.out.println("Escolha uma das opções.");
            int menu = sc.nextInt();sc.nextLine();

            switch (menu){
                case 1:
                    System.out.println("\nInforme o nome do usuário:");
                    String nome = sc.nextLine();
                    System.out.println("Informe o telefone do usuário (exemplo (DD)9.XXXX-XXXX)");
                    String telefone = sc.nextLine();

                    try {
                        Usuario usuario = repositoryUsuario.buscarUsuario(nome, telefone);

                        repositoryEmprestimo.historicoUsuario(usuario.getIdUsuario());

                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    break;

                case 2:
                    System.out.println("\nInforme o patrimonio do livro:");
                    int patrimonio = sc.nextInt(); sc.nextLine();

                    repositoryEmprestimo.historicoLivro(patrimonio);
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
