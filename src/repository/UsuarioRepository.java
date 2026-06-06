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
                }
            }
        }catch (SQLException e){
            throw new RuntimeException("Erro ao salvar usuário", e);
        }
    }
}
