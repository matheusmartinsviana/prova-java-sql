import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Biblioteca {
    private int id;
    private String nome;
    private ArrayList<Livro> livros;
    private ArrayList<MidiaDigital> midiasDigitals;

    private static ArrayList<Biblioteca> bibliotecas = new ArrayList<>();

    public Biblioteca(int id,String nome) {
        this.id = id;
        this.nome = nome;
        this.livros = new ArrayList<>();
        this.midiasDigitals = new ArrayList<>();

        bibliotecas.add(this);
    }

    public String toString() {
        return "Nome: " + this.nome;
    }

    public void inserirBiblioteca() {
        try {
            Connection connManager = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/biblioteca",
                    "root",
                    "");
            System.out.println("Conexão estabelecida.");
            String query = "INSERT INTO biblioteca(nome_biblioteca) VALUES (?)";
            PreparedStatement stmt = connManager.prepareStatement(query);
            stmt.setString(1, this.getBiblioteca());
            stmt.executeUpdate();
            stmt.close();
            connManager.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao inserir o livro: " + erro.getMessage());
        }
    }

    public String getBiblioteca() {
        return this.nome;
    }

     public static int getBibliotecaId(int bibliotecaId) {
        try {
            Connection connManager = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/biblioteca",
                    "root",
                    "");
            System.out.println("Conexão estabelecida");

            String query = "SELECT id_biblioteca FROM biblioteca WHERE id_biblioteca = ?";
            PreparedStatement ps = connManager.prepareStatement(query);
            ps.setInt(1, bibliotecaId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                 bibliotecaId = rs.getInt("id_biblioteca");
                //numeroAutorLivro = autorId;
            } else {
                System.out.println("Autor não encontrado");
            }
            rs.close();
            connManager.close();
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
        }
        return bibliotecaId;
    }

    public void adicionarLivro(Livro livro) {
        livro.setBiblioteca(this);
        this.livros.add(livro);
    }

    public void inserirLivro(Livro livro) {
        try {
            Connection connManager = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/biblioteca",
                    "root",
                    "");
            System.out.println("Conexão estabelecida.");
            String query = "INSERT INTO biblioteca.livro(titulo, disponivel, id_autor, id_biblioteca) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connManager.prepareStatement(query);
            stmt.setString(1, livro.getTitulo());
            stmt.setBoolean(2, livro.getDisponivel());
            stmt.setInt(3, livro.getAutor().getId());
            stmt.setInt(4, this.getId());
            stmt.executeUpdate();
            stmt.close();
            connManager.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao inserir o livro: " + erro.getMessage());
        }
    }
    
    public static void adicionarLivro(int idBibLioteca, int idLivro) {
        try {
            Connection connManager = DriverManager
                    .getConnection(
                            "jdbc:mysql://localhost:3306/biblioteca",
                            "root",
                            "");
            System.out.println("Conexão estabelecida!");
            PreparedStatement ps = connManager.prepareStatement("update biblioteca.biblioteca set id_livro_biblioteca = ? where id_biblioteca = ?");
            ps.setInt(1, Livro.getLivroId(idLivro));
            ps.setInt(2, Biblioteca.getBibliotecaId(idBibLioteca));
 
            ps.executeUpdate();
            connManager.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void adicionarMidia(int idBibLioteca, int idMidiaDigital) {
        try {
            Connection connManager = DriverManager
                    .getConnection(
                            "jdbc:mysql://localhost:3306/biblioteca",
                            "root",
                            "");
            System.out.println("Conexão estabelecida!");
            PreparedStatement ps = connManager.prepareStatement("update biblioteca.biblioteca set id_midiaDigital_biblioteca = ? where id_biblioteca = ?");
            ps.setInt(1, MidiaDigital.getMidiaId(idMidiaDigital));
            ps.setInt(2, Biblioteca.getBibliotecaId(idBibLioteca));
 
            ps.executeUpdate();
            connManager.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public int getId() {
        return this.id;
    }
    public static int setLivro(int idLivro) {
        try {
            Connection connManager = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/biblioteca",
                    "root",
                    "");
            System.out.println("Conexão estabelecida");

            String query = "SELECT id_livro FROM livro WHERE id_livro = ?";
            PreparedStatement ps = connManager.prepareStatement(query);
            ps.setInt(1, idLivro);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                 idLivro = rs.getInt("id_livro");
                //numeroAutorLivro = autorId;
            } else {
                System.out.println("Autor não encontrado");
            }
            rs.close();
            connManager.close();
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
        }
        return idLivro;
    }

    public void adicionarMidiaDigital(MidiaDigital midiaDigitals) {
        this.midiasDigitals.add(midiaDigitals);
    }

    public void listarLivros() {
        for(int i = 0; i < this.livros.size(); i++) {
            System.out.println(i + " - " + this.livros.get(i).toString());
        }
    }

    public void listarMidias() {
        for(int i = 0; i < this.midiasDigitals.size(); i++) {
            System.out.println(i + " - " + this.midiasDigitals.get(i).toString());
        }
    }

    public static ArrayList<Biblioteca> getBibliotecas() {
        return bibliotecas;
    }

    public ArrayList<Livro> getLivros() {
        return this.livros;
    }

    public ArrayList<MidiaDigital> getMidiasDigitals() {
        return this.midiasDigitals;
    }

    public static void listarBibliotecas() {
        for(int i = 0; i < bibliotecas.size(); i++) {
            Biblioteca biblioteca = bibliotecas.get(i);
            System.out.println(i + " - " + biblioteca.toString());
        }
    }
}
