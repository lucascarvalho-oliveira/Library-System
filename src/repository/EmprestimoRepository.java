package repository;

import database.Conexao;
import model.Emprestimo;
import model.EmprestimoDTO;
import model.Exemplar;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepository {

    public void salvarEmprestimo(Emprestimo emprestimo){
        String sqlEmprestimo = "INSERT INTO emprestimo (id_usuario, data_emprestimo, data_retorno) values (?, ?, ?)";
        String sqlItens = "INSERT INTO emprestimo_exemplar (id_emprestimo, id_exemplar) VALUES (?, ?)";

        try(Connection conn = new Conexao().conectar()){
            conn.setAutoCommit(false);
            try {
                try (PreparedStatement stmtEmp = conn.prepareStatement(sqlEmprestimo, Statement.RETURN_GENERATED_KEYS)) {
                    stmtEmp.setInt(1, emprestimo.getUsuarios().getIdUsuario());
                    stmtEmp.setObject(2, emprestimo.getDataEmprestimo());
                    stmtEmp.setDate(3, Date.valueOf(emprestimo.getDataRetorno()));

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
            throw new RuntimeException("\nErro ao salvar Empréstimo", e);
        }
    }

    public Emprestimo buscarPorId(int idEmprestimo) {
        String sql = "SELECT data_emprestimo, data_retorno " +
                "FROM emprestimo " +
                "WHERE id_emprestimo = ?";

        try (Connection conn = new Conexao().conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEmprestimo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Emprestimo emprestimo = new Emprestimo();

                    emprestimo.setIdEmprestimo(idEmprestimo);
                    emprestimo.setDataEmprestimo(rs.getObject("data_emprestimo", LocalDate.class));
                    emprestimo.setDataRetorno(rs.getObject("data_retorno", LocalDate.class));
                    return emprestimo;
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException("\nErro ao cbuscar empréstimo", e);
        }
        return null;
    }

    public List<EmprestimoDTO> buscarEmprestimo(int id_usuario){
        String sql = "SELECT e.id_emprestimo, e.data_emprestimo, e.data_retorno, ex.patrimonio " +
                "FROM emprestimo e " +
                "JOIN emprestimo_exemplar ee ON e.id_emprestimo = ee.id_emprestimo" +
                "JOIN Exemplar eX ON ee.id_exemplar = ex.id_exemplar " +
                "WHERE id_usuario = ? AND data_entrega IS NULL";

        List<EmprestimoDTO> emprestimos = new ArrayList<>();

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1, id_usuario);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                EmprestimoDTO dto = new EmprestimoDTO();
                dto.setIdEmprestimo(rs.getInt("id_emprestimo"));
                dto.setPatrimonio(rs.getInt("patrimonio"));
                dto.setDataEmprestimo(rs.getObject("data_emprestimo", LocalDate.class));
                dto.setDataRetorno(rs.getObject("data_retorno", LocalDate.class));

                emprestimos.add(dto);
            }
        }catch (SQLException e) {
            throw new RuntimeException("\nErro ao consultar empréstimo", e);
        }
        return emprestimos;
    }

    public List<Integer> buscarPatrimonio(int idEmprestimo) {
        String sql = "SELECT ex.patrimonio " +
                "FROM emprestimo_exemplar ee " +
                "JOIN exemplar ex ON ee.id_exemplar = ex.id_exemplar " +
                "WHERE ee.id_emprestimo = ?";

        List<Integer> patrimonios = new ArrayList<>();

        try (Connection conn = new Conexao().conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEmprestimo);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                patrimonios.add(rs.getInt("patrimonio"));
            }

            return patrimonios;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar patrimônios", e);
        }
    }

    public void updateEmprestimo(Emprestimo emprestimo){
        String sql = "UPDATE emprestimo SET multa = ? , data_entrega = ? WHERE id_emprestimo = ?";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setDouble(1, emprestimo.getMulta());
            stmt.setDate(2, Date.valueOf(emprestimo.getDataEntrega()));
            stmt.setInt(3, emprestimo.getIdEmprestimo());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("\nErro ao salvar multa e data de entrega", e);
        }
    }

    public void historicoLivro(int patrimonio){
        String sql = "SELECT u.nome, e.data_emprestimo, e.data_entrega, e.data_retorno " +
                "FROM emprestimo e " +
                "JOIN usuario u ON e.id_usuario = u.id_usuario " +
                "JOIN emprestimo_exemplar ee ON e.id_emprestimo = ee.id_emprestimo " +
                "JOIN exemplar ex ON ee.id_exemplar = ex.id_exemplar " +
                "WHERE ex.patrimonio = ? " +
                "ORDER BY e.data_emprestimo DESC";

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1, patrimonio);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("\nUsuário: " + rs.getString("nome"));
                System.out.println("Data Empréstimo: " + rs.getDate("data_emprestimo").toLocalDate().format(formatador));
                System.out.println("Data do retorno: " + rs.getDate("data_retorno").toLocalDate().format(formatador));

                Date dataEntrega = rs.getDate("data_entrega");
                if(dataEntrega != null) {
                    System.out.println("Data entrega: " + dataEntrega.toLocalDate().format(formatador));

                }else{
                    System.out.println("Data entrega: Não devolvido");
                }
                System.out.println("--------------------");
            }
        }catch (SQLException e) {
            throw new RuntimeException("\nErro ao busca histórico de livro", e);
        }
    }

    public void historicoUsuario(int id_usuario){
        String sql = "SELECT  l.titulo, ex.patrimonio, e.data_emprestimo, e.data_entrega, e.data_retorno, e.multa " +
                "FROM emprestimo e " +
                "LEFT JOIN emprestimo_exemplar ee ON e.id_emprestimo = ee.id_emprestimo " +
                "LEFT JOIN exemplar ex ON ee.id_exemplar = ex.id_exemplar " +
                "LEFT JOIN livro l ON ex.id_livro = l.id_livro " +
                "Where e.id_usuario = ? " +
                "ORDER BY e.data_emprestimo DESC";

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1, id_usuario);

            ResultSet rs = stmt.executeQuery();

            boolean encontrou = false;
            while(rs.next()) {
                encontrou = true;
                System.out.println("\nTítulo: " + rs.getString("titulo"));
                System.out.println("Patrimônio: " + rs.getInt("patrimonio"));
                System.out.println("Data empréstimo: " + rs.getDate("data_emprestimo").toLocalDate().format(formatador));
                System.out.println("Data do retorno: " + rs.getDate("data_retorno").toLocalDate().format(formatador));

                Date dataEntrega = rs.getDate("data_entrega");
                if(dataEntrega != null) {
                    System.out.println("Data entrega: " + rs.getDate("data_entrega").toLocalDate().format(formatador));

                }else{
                    System.out.println("Data entrega: Não devolvido");
                }
                System.out.println("Multa: R$ " + rs.getDouble("multa"));
                System.out.println("--------------------");
            }

            if (!encontrou) {
                System.out.println("\nNenhum empréstimo encontrado para este usuário.");
            }
        }catch (SQLException e) {
            throw new RuntimeException("\nErro ao buscar histórico de usuário", e);
        }
    }
}
