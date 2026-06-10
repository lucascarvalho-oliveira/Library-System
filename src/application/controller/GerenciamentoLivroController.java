package application.controller;

import model.Autor;
import model.Exemplar;
import model.Livro;
import model.enums.Genero;
import repository.AutorRepository;
import repository.LivroRepository;
import service.ExemplarService;
import service.LivroService;

import java.util.List;
import java.util.Scanner;

public class GerenciamentoLivroController {
    private LivroService serviceLivro;
    private AutorRepository repositoryAutor;
    private LivroRepository repositoryLivro;
    private ExemplarService serviceExemplar;

    public GerenciamentoLivroController(LivroService serviceLivro, AutorRepository repositoryAutor, LivroRepository repositoryLivro, ExemplarService serviceExemplar){
        this.serviceLivro = serviceLivro;
        this.repositoryAutor = repositoryAutor;
        this.repositoryLivro = repositoryLivro;
        this.serviceExemplar = serviceExemplar;
    }

    public void CadastraLivro(Scanner sc){
        boolean voltar = false;
        do{
            System.out.println("\n1 - Cadastrar livro.");
            System.out.println("2 - Consultar livro.");
            System.out.println("3 - Voltar.");
            System.out.println("Escolha uma das opções.");
            int menu = sc.nextInt();sc.nextLine();

            switch (menu){
                // cadastrar livro.
                case 1:
                    System.out.println("\nInforme o titulo do livro:");
                    String titulo = sc.nextLine();
                    System.out.println("Informe o volume do livro:");
                    int volume = sc.nextInt();sc.nextLine();
                    System.out.println("Informe a editora:");
                    String editora = sc.nextLine();

                    System.out.println("\nEscolha o gênero do livro:");
                    System.out.println("1 - Ação.");
                    System.out.println("2 - Romance.");
                    System.out.println("3 - Ficção Científica.");
                    System.out.println("4 - Comedia.");
                    System.out.println("5 - Drama.");
                    System.out.println("6 - Terror.");
                    System.out.println("7 - Fantasia.");
                    int menuGenero = sc.nextInt();sc.nextLine();

                    Genero genero = null;
                    switch (menuGenero){
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
                        case 7:
                            genero = Genero.FANTASIA;
                        default:
                            System.out.println("Opção inválida!");
                            break;
                    }

                    if(genero != null) {
                        Livro livro = new Livro(titulo, volume, editora, genero);

                        while (true) {
                            System.out.println("\nDigite parte do nome ou o nome completo do autor:");
                            String nome = sc.nextLine();

                            List<Autor> autorEncontrado = null;
                            try {
                                autorEncontrado = repositoryAutor.buscarAutor(nome);

                            }catch (RuntimeException e){
                                System.out.println(e.getMessage());
                            }

                            if (autorEncontrado.isEmpty()) {
                                System.out.println("Autor não encontrado.");
                                continue;
                            }

                            System.out.println("\nEscolha um autor:\n");
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
                        System.out.println("\nNão foi possível salvar  livro devido a um erro.");
                    }
                    break;

                // Consultar livro.
                case 2:
                    boolean voltarSegundoMenu = false;
                    do{
                        System.out.println("\nDigite o nome do livro:");
                        String tituloProcura = sc.nextLine();

                        List<Livro> achados = null;
                        try {
                            achados = repositoryLivro.buscarLivroList(tituloProcura);

                        }catch (RuntimeException e){
                            System.out.println(e.getMessage());
                        }

                        if(achados.isEmpty()){
                            System.out.println("Livro nao encontrado.");
                            break;
                        }

                        for (int i = 0; i < achados.size(); i++) {
                            System.out.println("\n" + i + 1 + " - " + achados.get(i).getTitulo() + " volume: " + achados.get(i).getVolume());
                        }
                        System.out.println("\nEscolha uma das opções ou digite '0' para voltar ao menu anterior.");
                        int escolhaLivro = sc.nextInt();sc.nextLine();

                        if(escolhaLivro == 0){
                            System.out.println("\nRetornando ao menu.");
                            break;

                        }else if (escolhaLivro < 1 || escolhaLivro > achados.size()){
                            System.out.println("Numero incorreto");
                            break;
                        }

                        int escolhido = achados.get(escolhaLivro - 1).getIdLivro();

                        System.out.println("\n1 - consultar Livro.");
                        System.out.println("2 - Cadastrar Exemplar.");
                        System.out.println("3 - voltar ao menu anterior.");
                        System.out.println("Escolha uma das opções.");
                        int escolha = sc.nextInt();sc.nextLine();

                        switch (escolha){
                            case 1:
                                try {
                                    repositoryLivro.consultarLivro(escolhido);

                                }catch (RuntimeException e){
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                break;

                            case 2:
                                Livro livroSelecionado = achados.get(escolhaLivro - 1);

                                System.out.println("\nInforme o patrimonio.");
                                int patrimonio = sc.nextInt();sc.nextLine();
                                System.out.println("Informe a localização.");
                                String localizacao = sc.nextLine();

                                Exemplar exemplar = new Exemplar(patrimonio, localizacao, livroSelecionado);
                                serviceExemplar.salvarExemplar(exemplar);

                            case 3:
                                voltarSegundoMenu = true;
                                break;

                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    }while(!voltarSegundoMenu);

                case 3:
                    voltar = true;
                    break;

                default:
                    System.out.println("Opção inválida!\n");
                    break;
            }
        }while(!voltar);
    }
}
