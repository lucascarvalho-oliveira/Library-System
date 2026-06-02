package repository;

import database.Conexao;
import model.Autor;

import java.sql.*;

public class AutorRepository {
    public void salvarAutor(Autor autor){
        String sql = "INSERT INTO autor (nome, nacionalidade, data_nascimento) values (?, ?, ?)";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ){
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getNacionalidade());
            stmt.setObject(3, autor.getDataNascimento());

            stmt.executeUpdate();

            try(ResultSet rs = stmt.getGeneratedKeys()){
                int idResgatado = rs.getInt(1);
                autor.setIdAutor(idResgatado);
            }
        }catch (SQLException e){
            System.out.println("\nErro ao salvar autor!\n");
            throw new RuntimeException(e);
        }
    }
}
