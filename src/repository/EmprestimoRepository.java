package repository;

import database.Conexao;
import model.Emprestimo;
import model.Exemplar;

import java.sql.*;

public class EmprestimoRepository {

    public void salvarEmprestimo(Emprestimo emprestimo){
        String sqlEmprestimo = "INSERT INTO emprestimo (data_emprestimo, id_usuario, data_retorno, data_entrega) values (?, ?, ?, ?)";
        String sqlItens = "INSERT INTO emprestimo_exemplar (id_emprestimo, id_exemplar) VALUES (?, ?)";

        try(Connection conn = new Conexao().conectar()){
            conn.setAutoCommit(false);
            try {
                try (PreparedStatement stmtEmp = conn.prepareStatement(sqlEmprestimo, Statement.RETURN_GENERATED_KEYS)) {
                    stmtEmp.setObject(1, emprestimo.getDataEmprestimo());
                    stmtEmp.setInt(2, emprestimo.getUsuarios().getIdUsuario());
                    stmtEmp.setDate(4, Date.valueOf(emprestimo.getDataRetorno()));
                    stmtEmp.setDate(5, Date.valueOf(emprestimo.getDataEntrega()));

                    stmtEmp.executeUpdate();

                    try (ResultSet rs = stmtEmp.getGeneratedKeys()) {
                        if (rs.next()) {
                            int idGerado = rs.getInt(1);
                            emprestimo.setIdEmprestimo(idGerado);
                        }
                    }
                }

                try (PreparedStatement stmtItens = conn.prepareStatement(sqlItens)) {
                    for (Exemplar exemplar : emprestimo.getExemplares()) {
                        stmtItens.setInt(1, emprestimo.getIdEmprestimo());
                        stmtItens.setInt(2, exemplar.getIdExemplar());

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
            throw new RuntimeException("Erro ao salvar Empréstimo", e);
        }
    }

    public void salvarMulta(Emprestimo emprestimo){
        String sql = "UPDATE emprestimo SET multa = ? WHERE id_emprestimo = ?";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setDouble(1, emprestimo.getMulta());
            stmt.setInt(2, emprestimo.getIdEmprestimo());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
