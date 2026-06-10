package application;

import application.controller.*;
import repository.*;
import service.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        UsuarioRepository repositoryUsuario = new UsuarioRepository();
        AutorRepository repositoryAutor = new AutorRepository();
        LivroRepository repositoryLivro = new LivroRepository();
        EmprestimoRepository repositoryEmprestimo = new EmprestimoRepository();
        ExemplarRepository repositoryExemplar = new ExemplarRepository();

        boolean sair = false;
        do {
            System.out.println("============ Library System ============");
            System.out.println("1 - Cadastrar usuário.");
            System.out.println("2 - Cadastrar autor.");
            System.out.println("3 - Gerenciamento de Livro.");
            System.out.println("4 - Empréstimo de Exemplar.");
            System.out.println("5 - Histórico.");
            System.out.println("6 - Apagar Exemplar.");
            System.out.println("7 - Fechar sistema.");
            System.out.println("Escolha uma das opções.");
            int menu = sc.nextInt();sc.nextLine();

            switch (menu){
                // Cadastrar usuário.
                case 1:
                    UsuarioService serviceUsuario = new UsuarioService(repositoryUsuario);
                    CadastrarUsuarioController cadastroUsuario = new CadastrarUsuarioController(serviceUsuario);

                    cadastroUsuario.cadastro(sc);
                    break;

                // Cadastrar autor.
                case 2:
                    AutorService serviceAutor = new AutorService(repositoryAutor);
                    CadastrarAutorController cadastroAutor = new CadastrarAutorController(serviceAutor);

                    cadastroAutor.CadastrarAutor(sc);
                    break;

                // Cadastrar livro e Consultar livro.
                case 3:
                    ExemplarService serviceExemplar = new ExemplarService(repositoryExemplar);
                    LivroService serviceLivro = new LivroService(repositoryLivro);
                    GerenciamentoLivroController cadastrarLivro = new GerenciamentoLivroController(serviceLivro, repositoryAutor, repositoryLivro, serviceExemplar);

                    cadastrarLivro.CadastraLivro(sc);
                    break;

                // Emprestimo de livro.
                case 4:
                    EmprestimoService serviceEmprestimo = new EmprestimoService(repositoryEmprestimo);
                    EmprestimoController emprestimoController = new EmprestimoController(repositoryUsuario, repositoryExemplar, serviceEmprestimo, repositoryEmprestimo);

                    emprestimoController.emprestimo(sc);
                    break;

                // Histórico.
                case 5:
                    HistoricoController historicoController = new HistoricoController(repositoryUsuario, repositoryEmprestimo);

                    historicoController.historico(sc);
                    break;

                // Apagar Exemplar.
                case 6:
                    DeleteExemplarController deleteExemplar = new DeleteExemplarController(repositoryExemplar);

                    deleteExemplar.deleteExemplar(sc);
                    break;

                // fechar sistema.
                case 7:
                    System.out.println("sistema finalizado.");
                    sair = true;
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        }while(!sair);
        sc.close();
    }
}
