package repository;

import database.Conexao;
import model.Usuario;

import java.sql.*;

public class UsuarioRepository {

    public void salvarUsuario(Usuario usuario){
        String sql = "INSERT INTO usuario (nome, telefone, data_nascimento) VALUES (?, ?, ?)";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ){
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getTelefone());
            stmt.setDate(3, Date.valueOf(usuario.getDataNascimento()));

            stmt.executeUpdate();

            try(ResultSet rs = stmt.getGeneratedKeys()){
                if(rs.next()){
                    int idGerado = rs.getInt(1);
                    usuario.setIdUsuario(idGerado);

                    System.out.println("\nUsuário salvo com sucesso!\n");
                }
            }
        }catch (SQLException e){
            throw new RuntimeException("\nErro ao salvar usuário", e);
        }
    }

    public Usuario buscarUsuario(String nome, String telefone){
        String sql = "SELECT id_usuario FROM usuario WHERE nome = ? AND telefone = ?";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setString(1, nome);
            stmt.setString(2, telefone);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Usuario usuario = new Usuario();

                usuario.setIdUsuario(rs.getInt("id_usuario"));

                return usuario;
            }

        }catch (SQLException e){
            throw new RuntimeException("\nErro ao buscar usuário", e);
        }
        return null;
    }

}
