package application.controller;

import model.Autor;
import service.AutorService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CadastrarAutorController {
    private AutorService serviceAutor;

    public CadastrarAutorController(AutorService serviceAutor){
        this.serviceAutor = serviceAutor;
    }

    public void CadastrarAutor(Scanner sc){
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("\nInforme o nome do autor.");
        String nome = sc.nextLine();
        System.out.println("Informe a nacionalidade do autor.");
        String nacionalidade = sc.nextLine();
        System.out.println("informe a data de nascimento do auto (no formato DD/MM/AAAA).");
        String data = sc.nextLine();

        LocalDate dataConvertida = null;

        try {
            dataConvertida = LocalDate.parse(data, formatador);

        }catch (DateTimeParseException e) {
            System.out.println("Erro: A data inválida ou não está no formato DD/MM/AAAA.");
        }

        Autor autor = null;

        if(dataConvertida != null){
            autor = new Autor(nome, nacionalidade, dataConvertida);

        }else{
            System.out.println("Não foi possível criar o autor devido a um erro na data.");
        }

        try{
            serviceAutor.salvarAutor(autor);

        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
