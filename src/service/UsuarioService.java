package service;

import model.Usuario;
import repository.UsuarioRepository;

public class UsuarioService {
    private UsuarioRepository repositoryUsuario;

    public UsuarioService(UsuarioRepository repositoryUsuario){
        this.repositoryUsuario = repositoryUsuario;
    }

    public void salvarUsuario(Usuario usuario){
        if(usuario.getNome() == null || usuario.getNome().isBlank()){
            throw new IllegalArgumentException("Nome do usuário incorreto.");
        }
        if(usuario.getTelefone() == null || usuario.getTelefone().isBlank() || usuario.getTelefone().length() != 15){
            throw new IllegalArgumentException("Telefone do usuário incorreto.");
        }
        if(usuario.getDataNascimento() == null){
            throw new IllegalArgumentException("Data de nascimento de usuário incorreto.");
        }

        repositoryUsuario.salvarUsuario(usuario);
    }
}
