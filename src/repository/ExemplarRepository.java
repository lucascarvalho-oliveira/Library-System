package repository;

import database.Conexao;
import model.Exemplar;

import java.sql.*;

public class ExemplarRepository {

    public void salvarExemplar(Exemplar exemplar){
        String sql = "INSERT INTO exemplar (id_livro, patrimonio, localizacao, status) values (?, ?, ?, ?)";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ){
            stmt.setInt(1, exemplar.getLivro().getIdLivro());
            stmt.setInt(2, exemplar.getPatrimonio());
            stmt.setString(3, exemplar.getLocalizacao());
            stmt.setString(4, exemplar.getStatus().name());

            stmt.executeUpdate();

            try(ResultSet rs = stmt.getGeneratedKeys()){
                if(rs.next()){
                    int idGerado = rs.getInt(1);
                    exemplar.setIdExemplar(idGerado);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException("Erro ao salvar exemplar", e);
        }
    }
}
