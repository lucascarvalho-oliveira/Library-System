package repository;

import database.Conexao;
import model.Autor;
import model.Livro;

import java.sql.*;

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
            throw new RuntimeException("Erro ao salvar livro", e);
        }
    }
}
