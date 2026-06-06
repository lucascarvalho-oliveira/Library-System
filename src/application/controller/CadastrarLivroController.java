package application.controller;

import model.Autor;
import model.Livro;
import model.enums.Genero;
import repository.AutorRepository;
import service.LivroService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastrarLivroController {
    private LivroService serviceLivro;
    private AutorRepository repositoryAutor;

    public CadastrarLivroController(LivroService serviceLivro, AutorRepository repositoryAutor){
        this.serviceLivro = serviceLivro;
        this.repositoryAutor = repositoryAutor;
    }

    public void CadastraLivro(Scanner sc){
        System.out.println("Informe o titulo do livro:");
        String titulo = sc.nextLine();
        System.out.println("Informe o volume do livro:");
        int volume = sc.nextInt();sc.nextLine();
        System.out.println("Informe a editora:");
        String editora = sc.nextLine();

        System.out.println("Escolha o gênero do livro:");
        System.out.println("1 - Ação.");
        System.out.println("2 - Romance.");
        System.out.println("3 - Ficção Científica.");
        System.out.println("4 - Comedia.");
        System.out.println("5 - Drama.");
        System.out.println("6 - Terror.");
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

        if(genero != null) {
            Livro livro = new Livro(titulo, volume, editora, genero);

            while (true) {
                System.out.println("Digite parte do nome ou o nome completo do autor:");
                String nome = sc.nextLine();

                List<Autor> autorEncontrado = repositoryAutor.buscarAutor(nome);

                if (autorEncontrado.isEmpty()) {
                    System.out.println("Autor não encontrado.");
                    continue;
                }

                System.out.println("Escolha um autor:");
                for (int i = 0; i < autorEncontrado.size(); i++) {
                    System.out.println(i + 1 + " - " + autorEncontrado.get(i).getNome());
                }
                int escolha = sc.nextInt();
                sc.nextLine();

                if (escolha < 1 || escolha > autorEncontrado.size()) {
                    System.out.println("Opção inválida.");
                    continue;
                }

                livro.getAutores().add(autorEncontrado.get(escolha - 1));

                System.out.println("Adicionar outro autor? (s/n):");
                String resposta = sc.nextLine();

                if (resposta.equalsIgnoreCase("n")) {
                    break;
                }
            }

            serviceLivro.salvarLivro(livro);

        }else{
            System.out.println("Não foi possível salvar  livro devido a um erro.");
        }

    }
}
