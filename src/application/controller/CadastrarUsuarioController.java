package application.controller;

import model.Usuario;
import service.UsuarioService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CadastrarUsuarioController {
    private UsuarioService serviceUsuario;

    public CadastrarUsuarioController(UsuarioService serviceUsuario){
        this.serviceUsuario = serviceUsuario;
    }

    public void cadastro(Scanner sc){
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("\nInforme o nome.");
        String nome = sc.nextLine();
        System.out.println("Informe o telefone (exemplo (DD)9.XXXX-XXXX).");
        String telefone = sc.nextLine();
        System.out.println("Informe a data de nascimento (no formato DD/MM/AAAA).");
        String data = sc.nextLine();

        LocalDate dataConvertida = null;

        try {
            dataConvertida = LocalDate.parse(data, formatador);

        }catch (DateTimeParseException e) {
            System.out.println("Erro: A data inválida ou não está no formato DD/MM/AAAA.");
        }

        Usuario usuario = null;

        if(dataConvertida != null) {
            usuario = new Usuario(nome, telefone, dataConvertida);

        }else{
            System.out.println("Não foi possível criar o usuário devido a um erro na data.");
        }

        try{
            serviceUsuario.salvarUsuario(usuario);

        }catch (IllegalArgumentException e){
            System.out.println("\n" + e.getMessage() + "\n");
        }
    }
}
