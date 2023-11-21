import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Midia {
    
    private String titulo;
    private boolean disponivel;

    public Midia(String titulo, boolean disponivel) {
        this.titulo = titulo;
        this.disponivel = disponivel;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public static int getMidiaId(int idMidiaDigital) {
        try {
            Connection connManager = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/biblioteca",
                    "root",
                    "");
            System.out.println("Conexão estabelecida");

            String query = "SELECT id_midiaDigital FROM biblioteca.midiaDigital WHERE id_midiaDigital = ?";
            PreparedStatement ps = connManager.prepareStatement(query);
            ps.setInt(1, idMidiaDigital);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                 idMidiaDigital = rs.getInt("id_midiaDigital");
                //numeroAutorLivro = autorId;
            } else {
                System.out.println("Autor não encontrado");
            }
            rs.close();
            connManager.close();
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
        }
        return idMidiaDigital;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public boolean getDisponivel() {
        return this.disponivel;
    }

    public String toString() {
        return "Título: " + this.titulo + ". Disponível: " + (this.disponivel ? "Sim" : "Não");
    }

    public void emprestar() throws Exception {
        if (!this.disponivel) {
            throw new Exception("Midia não está disponível");
        }
        this.disponivel = false;
    }

    public void devolver() throws Exception {
        if (this.disponivel) {
            throw new Exception("Midia já está disponível");
        }
        this.disponivel = true;
    }

}
