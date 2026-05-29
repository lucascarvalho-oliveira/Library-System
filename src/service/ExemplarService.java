package service;

import model.Exemplar;
import repository.ExemplarRepository;

public class ExemplarService {
    private ExemplarRepository repositoryExemplar;

    public ExemplarService(ExemplarRepository repositoryExemplar){
        this.repositoryExemplar = repositoryExemplar;
    }

    public void salvarExemplar(Exemplar exemplar){
        if(exemplar.getLocalizacao() == null || exemplar.getLocalizacao().isBlank()){
            throw new IllegalArgumentException("O exemplar precisa ter uma localização");
        }
        if(exemplar.getIdPatrimonio() == 0){
            throw new IllegalArgumentException("O patrimonio de um livro precisa começar em 1.");
        }

        repositoryExemplar.salvarExemplar(exemplar);
    }
}
