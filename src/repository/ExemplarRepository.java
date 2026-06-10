package repository;

import database.Conexao;
import model.Exemplar;
import model.Livro;
import model.enums.Status;

import java.sql.*;
import java.util.List;

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

                    System.out.println("\nExemplar salvo com sucesso!\n");
                }
            }
        }catch (SQLException e){
            throw new RuntimeException("\nErro ao salvar exemplar", e);
        }
    }

    public void atualizarStatus(int patrimonio, Status status) {
        String sql = "UPDATE exemplar SET status = ? WHERE patrimonio = ?";

        try (Connection conn = new Conexao().conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status.name());
            stmt.setInt(2, patrimonio);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("\nErro ao atualizar status.", e);
        }
    }

    public Exemplar buscarExemplar(int patrimonio){
        String sql = "SELECT e.id_exemplar, e.status, l.titulo " +
                "FROM exemplar e " +
                "JOIN livro l ON e.id_livro = l.id_livro " +
                "WHERE e.patrimonio = ?";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1, patrimonio);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Exemplar exemplar = new Exemplar();
                exemplar.setStatus(Status.valueOf(rs.getString("status")));
                exemplar.setIdExemplar(rs.getInt("id_exemplar"));

                Livro livro = new Livro();
                livro.setTitulo(rs.getString("titulo"));

                exemplar.setLivro(livro);

                return exemplar;
            }
        }catch (SQLException e){
            throw new RuntimeException("\nErro ao buscar exemplar", e);
        }
        return null;
    }

    public void apagarExemplar(int id_exemplar){
        String sql = "DELETE FROM exemplar WHERE id_exemplar = ?";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1, id_exemplar);

            int linhaAfetada = stmt.executeUpdate();

            if(linhaAfetada > 1){
                System.out.println("Exemplar removido com sucesso!");
            }else{
                System.out.println("Nenhum exemplar encontrado com esse ID.");
            }

        }catch (SQLException e){
            throw new RuntimeException("\nErro ao apagar exemplar", e);
        }
    }

}
