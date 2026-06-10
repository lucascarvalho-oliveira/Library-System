package application.controller;

import model.Exemplar;
import repository.ExemplarRepository;

import java.util.Scanner;

public class DeleteExemplarController {
    private ExemplarRepository repositoryExemplar;

    public DeleteExemplarController(ExemplarRepository repositoryExemplar){
        this.repositoryExemplar = repositoryExemplar;
    }

    public void deleteExemplar(Scanner sc){
        while (true) {
            System.out.println("Informe o patrimonio do exemplar.");
            int patrimonio = sc.nextInt();sc.nextLine();

            Exemplar achado = repositoryExemplar.buscarExemplar(patrimonio);

            if(achado == null){
                System.out.println("Exemplar nao encontrado.");
                continue;
            }

            repositoryExemplar.apagarExemplar(achado.getIdExemplar());
        }
    }
}
