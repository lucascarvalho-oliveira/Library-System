package repository;

import database.Conexao;
import model.Emprestimo;

import java.sql.*;

public class EmprestimoRepository {

    public void salvarEmprestimo(Emprestimo emprestimo){
        String sql = "INSERT INTO emprestimo (data_emprestimo, data_retorno, data_entrega, id_usuario_fk) values (?, ?, ?, ?)";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ){
            stmt.setObject(1, emprestimo.getDataEmprestimo());
            stmt.setObject(2, emprestimo.getDataRetorno());
            stmt.setObject(3, emprestimo.getDataEntrega());
            stmt.setInt(4, emprestimo.getUsuarios().getId_usuario());

            stmt.executeUpdate();

            try(ResultSet rs = stmt.getGeneratedKeys()){
                int idGerado = rs.getInt(1);
                emprestimo.setId_emprestimo(idGerado);
            }
        }catch (SQLException e){
            System.out.println("\nErro ao salvar empréstimo!\n");
            throw new RuntimeException(e);
        }
    }

    public void salvarMulta(Emprestimo emprestimo){
        String sql = "UPDATE emprestimo SET multa = ? WHERE id_emprestimo = ?";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setDouble(1, emprestimo.getMulta());
            stmt.setInt(2, emprestimo.getId_emprestimo());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
