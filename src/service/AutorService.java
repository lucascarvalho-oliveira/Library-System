package service;

import model.Autor;
import repository.AutorRepository;

public class AutorService {
    private AutorRepository repositoryAutor;

    public AutorService(AutorRepository repositoryAutor){
        this.repositoryAutor = repositoryAutor;
    }

    public void salvarAutor(Autor autor){
        if(autor.getNome() == null || autor.getNome().isBlank()){
            throw new IllegalArgumentException("Nome do autor incorreto.");
        }
        if(autor.getNacionalidade() == null || autor.getNacionalidade().isBlank()){
            throw new IllegalArgumentException("Nacionalidade do autor incorreta.");
        }
        if(autor.getDataNascimento() == null){
            throw new IllegalArgumentException("Data de nascimento do autor incorreta.");
        }

        repositoryAutor.salvarAutor(autor);
    }
}
