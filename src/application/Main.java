package application;

import application.controller.CadastrarUsuarioController;
import repository.UsuarioRepository;
import service.UsuarioService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean sair = false;
        do {
            System.out.println("============ Library System ============");
            System.out.println("1 - Cadastrar usuário.");
            System.out.println("2 - cadastrar autor.");
            System.out.println("3 - Cadastrar livro.");
            System.out.println("4 - Consultar livro.");
            System.out.println("5 - Empréstimo de livro.");
            System.out.println("6 - Histórico de usuário.");
            System.out.println("7 - Histórico de livro.");
            System.out.println("8 - Fechar sistema.");
            System.out.println("Escolha uma das opções.");
            int menu = sc.nextInt();sc.nextLine();

            switch (menu){
                case 1:
                    UsuarioRepository repositoryUsuario = new UsuarioRepository();
                    UsuarioService serviceUsuario = new UsuarioService(repositoryUsuario);
                    CadastrarUsuarioController cadastro = new CadastrarUsuarioController(serviceUsuario);

                    cadastro.cadastro(sc);
                    break;

                case 2:

            }

        }while(!sair);
        sc.close();
    }
}
