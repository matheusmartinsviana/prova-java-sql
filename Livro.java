import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Livro {
    private int id;
    private String titulo;
    private boolean disponivel;
    private Autor autor;
    private Biblioteca biblioteca;

    public Livro(int id, String titulo, int idAutor, boolean disponivel) {
        this.id = id;
        this.titulo = titulo;
        this.autor = new Autor(idAutor, "", "");
        this.disponivel = disponivel;
    }
    
    public static void emprestarLivro(int idBibLioteca, int idLivro) {
        try {
            Connection connManager = DriverManager
                    .getConnection(
                            "jdbc:mysql://localhost:3306/biblioteca",
                            "root",
                            "");
            System.out.println("Conexão estabelecida!");
            PreparedStatement ps = connManager.prepareStatement("update livro set disponivel = false where id_livro = ?");
            ps.setInt(1, Livro.getLivroId(idLivro));

 
            ps.executeUpdate();
            connManager.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

     public static void devolverLivro(int idBibLioteca, int idLivro) {
        try {
            Connection connManager = DriverManager
                    .getConnection(
                            "jdbc:mysql://localhost:3306/biblioteca",
                            "root",
                            "");
            System.out.println("Conexão estabelecida!");
            PreparedStatement ps = connManager.prepareStatement("update livro set disponivel = true where id_livro = ?");
            ps.setInt(1, Livro.getLivroId(idLivro));

 
            ps.executeUpdate();
            connManager.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void inserirLivro() {
        try {
            Connection connManager = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/biblioteca",
                    "root",
                    "");
            System.out.println("Conexão estabelecida.");
            String query = "INSERT INTO biblioteca.livro(titulo, disponivel, id_autor) VALUES (?, ?, ?)";
            PreparedStatement stmt = connManager.prepareStatement(query);
            stmt.setString(1, this.getTitulo());
            stmt.setBoolean(2, this.getDisponivel());
            stmt.setInt(3, this.getAutor().getId()); // Set the author's ID
            stmt.executeUpdate();
            stmt.close();
            connManager.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao inserir o livro: " + erro.getMessage());
        }
    }
    


    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
    
    public Biblioteca getBiblioteca() {
        return this.biblioteca;
    }
    
    
    


    public static int getLivroId(int numeroAutorLivro) {
        try {
            Connection connManager = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/biblioteca",
                    "root",
                    "");
            System.out.println("Conexão estabelecida");

            String query = "SELECT id_autor FROM biblioteca.autor WHERE id_autor = ?";
            PreparedStatement ps = connManager.prepareStatement(query);
            ps.setInt(1, numeroAutorLivro);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                 numeroAutorLivro = rs.getInt("id_autor");
                //numeroAutorLivro = autorId;
            } else {
                System.out.println("Autor não encontrado");
            }
            rs.close();
            connManager.close();
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
        }
        return numeroAutorLivro;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Autor getAutor() {
        return this.autor;
    }

    /*public static void verLivros() {
        try {
            Connection connManager = DriverManager
                    .getConnection(
                            "jdbc:mysql://localhost:3306/biblioteca",
                            "root",
                            "");
            System.out.println("Conexão estabelecida.");
            PreparedStatement ps = connManager.prepareStatement("SELECT * FROM livros");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
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
    }*/

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
