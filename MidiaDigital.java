import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class MidiaDigital extends Midia {
    private String album;

    private static ArrayList<MidiaDigital> midiasDigitais = new ArrayList<>();

    public MidiaDigital(String titulo, String album, boolean disponivel) {
        super(titulo, disponivel);
        this.album = album;

        midiasDigitais.add(this);
    }

    public String toString() {
        return "Título: " + super.getTitulo()
            + ". Álbum: " + this.album
            + ". Disponível: " + (super.getDisponivel() ? "Sim" : "Não");
    }

    public void inserirMidiaDigital() {
        try {           
                Connection connManager = DriverManager
                .getConnection(
                        "jdbc:mysql://localhost:3306/biblioteca",
                        "root",
                        "");
            System.out.println("Conexão concluída.");
            String query = "INSERT INTO midiaDigital (titulo, album, disponivel) VALUES (?,?,?)";
            PreparedStatement stmt = connManager.prepareStatement(query);
            stmt.setString(1,this.getAlbum());
            stmt.setString(2, super.getTitulo());
            stmt.setBoolean(3, super.getDisponivel());
            stmt.executeUpdate();
            connManager.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao inserir o autor: " + erro.getMessage());
        }
    }

    public String getAlbum() {
        return this.album;
    }

    public static void listarMidiaDigitals() {
        for(int i = 0; i < midiasDigitais.size(); i++) {
            System.out.println(i + " - " + midiasDigitais.get(i).toString());
        }
    }

    public static ArrayList<MidiaDigital> getMidiasDigitals() {
        return midiasDigitais;
    }

    public static void emprestarMidia(int idBibLioteca, int idMidiaDigital) {
        try {
            Connection connManager = DriverManager
                    .getConnection(
                            "jdbc:mysql://localhost:3306/biblioteca",
                            "root",
                            "");
            System.out.println("Conexão estabelecida!");
            PreparedStatement ps = connManager.prepareStatement("update midiaDigital set disponivel = false where id_midiaDigital = ?");
            ps.setInt(1, Midia.getMidiaId(idMidiaDigital));

 
            ps.executeUpdate();
            connManager.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

     public static void devolverMidia(int idBibLioteca, int idMidiaDigital) {
        try {
            Connection connManager = DriverManager
                    .getConnection(
                            "jdbc:mysql://localhost:3306/biblioteca",
                            "root",
                            "");
            System.out.println("Conexão estabelecida!");
            PreparedStatement ps = connManager.prepareStatement("update midiaDigital set disponivel = true where id_midiaDigital = ?");
            ps.setInt(1, Midia.getMidiaId(idMidiaDigital));

 
            ps.executeUpdate();
            connManager.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

}
