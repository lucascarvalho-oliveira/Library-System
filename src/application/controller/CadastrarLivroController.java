package application.controller;

import model.enums.Genero;
import service.LivroService;

import java.util.Scanner;

public class CadastrarLivroController {
    private LivroService serviceLivro;

    public CadastrarLivroController(LivroService serviceLivro){
        this.serviceLivro = serviceLivro;
    }

    public void CadastraLivro(Scanner sc){
        System.out.println("Informe o titulo do livro:");
        String titulo = sc.nextLine();
        System.out.println("Informe o autor do livro:");
        String nomeAutor = sc.nextLine();
        System.out.println("Informe o volume do livro:");
        int volume = sc.nextInt();sc.nextLine();
        System.out.println("Informe a editora:");
        String editora = sc.nextLine();

        System.out.println("Escolha o gênero do livro:");
        System.out.println("1 - Ação");
        System.out.println("2 - Romance");
        System.out.println("3 - Ficção Científica");
        System.out.println("4 - Comedia");
        System.out.println("5 - Drama");
        System.out.println("6 - Terror");
        int menu = sc.nextInt();sc.nextLine();

        Genero genero = null;
        switch (menu){
            case 1:
                genero = Genero.ACAO;
                break;
            case 2:
                genero = Genero.ROMANCE;
                break;
            case 3:
                genero = Genero.FICCAO_CIENTIFICA;
                break;
            case 4:
                genero = Genero.COMEDIA;
                break;
            case 5:
                genero = Genero.DRAMA;
                break;
            case 6:
                genero = Genero.TERROR;
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }

        if(genero != null){

        }else{
            System.out.println("Não foi possível salvar  livro devido a um erro no gênero.");
        }

    }
}
