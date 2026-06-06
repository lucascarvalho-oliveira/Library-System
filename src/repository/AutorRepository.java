package repository;

import database.Conexao;
import model.Autor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorRepository {
    public void salvarAutor(Autor autor){
        String sql = "INSERT INTO autor (nome, nacionalidade, data_nascimento) values (?, ?, ?)";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ){
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getNacionalidade());
            stmt.setDate(3, Date.valueOf(autor.getDataNascimento()));

            stmt.executeUpdate();

            try(ResultSet rs = stmt.getGeneratedKeys()){
                if(rs.next()) {
                    int idResgatado = rs.getInt(1);
                    autor.setIdAutor(idResgatado);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException("Erro ao salvar autor", e);
        }
    }

    public List<Autor> buscarAutor(String nome){
        String sql = "SELECT id_autor, nome FROM autor WHERE nome LIKE ?";

        List<Autor> autores = new ArrayList<>();

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setString(1, "%" + nome + "%");

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Autor autor = new Autor();

                autor.setIdAutor(rs.getInt("id_autor"));
                autor.setNome(rs.getString("nome"));

                autores.add(autor);
            }
        }catch (SQLException e){
            System.out.println("autor nao encontrada!");
            throw new RuntimeException(e);
        }
        return autores;
    }
}
