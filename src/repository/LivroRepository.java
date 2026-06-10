package repository;

import database.Conexao;
import model.Autor;
import model.Livro;
import model.enums.Genero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

public class LivroRepository {

    public void salvarLivro(Livro livro) {
        String sqlAutor = "INSERT INTO livro (titulo, volume, editora, genero) VALUES (?, ?, ?, ?)";
        String sqlItens = "INSERT INTO livro_autor (id_livro, id_autor) VALUES (?, ?)";

        try (Connection conn = new Conexao().conectar()) {
            conn.setAutoCommit(false);

            try {
                try (PreparedStatement stmtAut = conn.prepareStatement(sqlAutor, Statement.RETURN_GENERATED_KEYS)) {
                    stmtAut.setString(1, livro.getTitulo());
                    stmtAut.setInt(2, livro.getVolume());
                    stmtAut.setString(3, livro.getEditora());
                    stmtAut.setString(4, livro.getGenero().name());

                    stmtAut.executeUpdate();

                    try (ResultSet rs = stmtAut.getGeneratedKeys()) {
                        if (rs.next()) {
                            int idGerado = rs.getInt(1);
                            livro.setIdLivro(idGerado);

                            System.out.println("\nLivro salvo com sucesso!\n");
                        }
                    }

                }

                try (PreparedStatement stmtItens = conn.prepareStatement(sqlItens)) {
                    for (Autor autor : livro.getAutores()) {
                        stmtItens.setInt(1, livro.getIdLivro());
                        stmtItens.setInt(2, autor.getIdAutor());

                        stmtItens.addBatch();
                    }
                    stmtItens.executeBatch();
                }
                conn.commit();

            }catch (SQLException e) {
                conn.rollback();
                throw e;
            }

        }catch (SQLException e){
            throw new RuntimeException("\nErro ao salvar livro", e);
        }
    }

    public List<Livro> buscarLivroList(String titulo){
        String sql = "SELECT titulo, volume, id_livro FROM livro WHERE titulo LIKE ?";

        List<Livro> encontrado = new ArrayList<>();

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setString(1,"%" + titulo + "%");

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Livro livro = new Livro();

                livro.setTitulo(rs.getString("titulo"));
                livro.setVolume(rs.getInt("volume"));
                livro.setIdLivro(rs.getInt("id_livro"));

                encontrado.add(livro);
            }

        }catch (SQLException e){
            throw new RuntimeException("\nErro ao buscar livro", e);
        }
        return encontrado;
    }

    public void consultarLivro(int id_livro){
        String sql = "SELECT l.*, a.nome FROM livro_autor la " +
                "JOIN livro l ON la.id_livro = l.id_livro " +
                "JOIN autor a ON la.id_autor = a.id_autor " +
                "WHERE l.id_livro = ?";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1, id_livro);

            ResultSet rs = stmt.executeQuery();

            boolean livro = true;
            while (rs.next()) {
                if(!livro) {
                    System.out.println("\nTítulo: " + rs.getString("titulo"));
                    System.out.println("Volume: " + rs.getInt("volume"));
                    System.out.println("Editora: " + rs.getString("editora"));
                    System.out.println("Gênero: " + rs.getString("genero"));
                    System.out.println("Autores:");
                    livro = false;
                }
                System.out.println("- " + rs.getString("nome"));
            }

        }catch (SQLException e){
            throw new RuntimeException("\nErro ao consultar livro.", e);
        }
    }
}
