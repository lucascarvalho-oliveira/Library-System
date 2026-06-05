package repository;

import database.Conexao;
import model.Livro;

import java.sql.*;

public class LivroRepository {

    public void salvarLivro(Livro livro){
        String sql = "INSERT INTO livro (id_autor, titulo, volume, editora, genero) VALUES (?, ?, ?, ?, ?)";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ){
            stmt.setInt(1, livro.getAutor().getIdAutor());
            stmt.setString(2, livro.getTitulo());
            stmt.setInt(3, livro.getVolume());
            stmt.setString(4, livro.getEditora());
            stmt.setString(5, livro.getGenero().name());

            stmt.executeUpdate();

            try(ResultSet rs = stmt.getGeneratedKeys()){
                if(rs.next()){
                    int idGerado = rs.getInt(1);
                    livro.setIdLivro(idGerado);
                }
            }
        }catch (SQLException e){
            System.out.println("\nErro ao salvar livro!\n");
            throw new RuntimeException();
        }
    }
}
