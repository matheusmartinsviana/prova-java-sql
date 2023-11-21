import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Autor {
    private int id;
    private String nome;
    private String nacionalidade;
    private static ArrayList<Autor> autores = new ArrayList<Autor>();

    public Autor(int id, String nome, String nacionalidade) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        autores.add(this);
    }

    public void inserirAutor() { 
        try {           
                Connection connManager = DriverManager
                .getConnection(
                        "jdbc:mysql://localhost:3306/biblioteca",
                        "root",
                        "");
            System.out.println("Conexão concluída.");
            String query = "INSERT INTO autor (nome, nacionalidade) VALUES (?,?)";
            PreparedStatement stmt = connManager.prepareStatement(query);
            stmt.setString(1,this.getNome());
            stmt.setString(2, this.getNacionalidade());
            stmt.executeUpdate();
            connManager.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao inserir o autor: " + erro.getMessage());
        }
    }

    public static void verLivros() {
        try {
            Connection connManager = DriverManager
                    .getConnection(
                            "jdbc:mysql://localhost:3306/biblioteca",
                            "root",
                            "");
            System.out.println("Conexão estabelecida.");
            PreparedStatement ps = connManager.prepareStatement("SELECT * FROM autor");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_autor");
                String name = rs.getString("nome");
                String nacionalidade = rs.getString("nacionalidade");
                System.out.println("User id: " + id);
                System.out.println("Name: " + name);
                System.out.println("Nacionalidade: " + nacionalidade);
                System.out.println("--------------");
            }
            rs.close();
            connManager.close();
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
        }
    }
    public int getId(int id) {
        try {
            Connection connManager = DriverManager
                    .getConnection(
                            "jdbc:mysql://localhost:3306/biblioteca",
                            "root",
                            "");
            System.out.println("Conexão estabelecida.");
            PreparedStatement ps = connManager.prepareStatement("SELECT livro.id_autor FROM biblioteca.livro");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
            rs.close();
            connManager.close();
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
        }
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getNacionalidade() {
        return this.nacionalidade;
    }

    public static ArrayList<Autor> getAutores() {
        return autores;
    }

    public String toString() {
        return "id: "+ this.id +"Autor: " + this.nome + " | Nacionalidade: " + this.nacionalidade;
    }

    public static void listarAutores() {
        for (int i = 0; i < autores.size(); i++) {
            System.out.println(i + " - " + autores.get(i).toString());
        }
    }
}