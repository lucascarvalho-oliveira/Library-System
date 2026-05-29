package service;

import model.Livro;
import repository.LivroRepository;

public class LivroService {
    private LivroRepository repositoryLivro;

    public LivroService(LivroRepository livroRepository){
        this.repositoryLivro = livroRepository;
    }

    public void salvarLivro(Livro livro){
        if(livro.getTitulo() == null || livro.getTitulo().isBlank()){
            throw new IllegalArgumentException("Titulo do livro incorreto.");
        }
        if(livro.getVolume() == 0){
            throw new IllegalArgumentException("Volume do livro incorreto.");
        }
        if(livro.getEditora() == null || livro.getEditora().isBlank()){
            throw new IllegalArgumentException("Editora de livro incorreto.");
        }

        repositoryLivro.salvarLivro(livro);
    }
}
