import java.util.Scanner;

public class Gerenciar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int op = 0;

        do {
            System.out.println("Minha Biblioteca");
            System.out.println("0 - Sair");
            System.out.println("1 - Criar autor");
            System.out.println("2 - Criar livro");
            System.out.println("3 - Criar mídia digital");
            System.out.println("4 - Criar biblioteca");
            System.out.println("5 - Adicionar livro");
            System.out.println("6 - Emprestar livro");
            System.out.println("7 - Devolver livro");
            System.out.println("8 - Adicionar mídia");
            System.out.println("9 - Emprestar mídia");
            System.out.println("10 - Devolver mídia");
            try {
                op = sc.nextInt();
            } catch (Exception e) {
                op = 99;
            }

            switch (op) {
                case 0: {
                    System.out.println("Sair....");
                    break;
                }
                case 1: {
                    System.out.println("Digite o nome do autor");
                    String nome = sc.next();
                    System.out.println("Digite o nacionalidade do autor");
                    String nacionalidade = sc.next();
                    Autor inserirAutor = new Autor(0, nome, nacionalidade);
                    inserirAutor.inserirAutor();
                    break;
                }
                case 2: {
                    System.out.println("Digite o titulo");
                    String titulo = sc.next();
                    int posicaoAutor = -1;
                    Autor.verLivros();
                    System.out.println("A qual ator o livro pertence?");
                    int numeroDoAutor = sc.nextInt();
                    Livro.getLivroId(numeroDoAutor);
                    /*do {
                        Autor.listarAutores();
                        try {
                            posicaoAutor = sc.nextInt();
                            if (posicaoAutor >= Autor.getAutores().size()) {
                                throw new Exception("Autor inválido");
                            }
                        } catch (Exception e) {
                            System.out.println("Autor inválido");
                            posicaoAutor = -1;
                        }*/
                    break;
                }
                case 3: {
                    System.out.println("Digite o titulo");
                    String titulo = sc.next();
                    System.out.println("Digite o album");
                    String album = sc.next();
                    new MidiaDigital(titulo, album, true);
                    break;
                }
                case 4: {
                    System.out.println("Digite o nome da biblioteca");
                    String nomeBiblioteca = sc.next();
                    new Biblioteca(0, nomeBiblioteca);
                    break;
                }
                case 5: {
                    System.out.println("Digite o ID da biblioteca");
                    int idBiblioteca = sc.nextInt();
                    System.out.println("Selecione o ID do livro");
                    int idLivro = sc.nextInt();
                    Biblioteca.adicionarLivro(idBiblioteca, idLivro);
                    break;
                }
                
                case 6: {
                    System.out.println("Digite o ID da biblioteca");
                    int idBiblioteca = sc.nextInt();
                    System.out.println("Selecione o ID do livro");
                    int idLivro = sc.nextInt();
                    Livro.emprestarLivro(idBiblioteca, idLivro);
                    break;
                }
                case 7: {
                    System.out.println("Digite o ID da biblioteca");
                    int idBiblioteca = sc.nextInt();
                    System.out.println("Selecione o ID do livro");
                    int idLivro = sc.nextInt();
                    Livro.devolverLivro(idBiblioteca, idLivro);
                    break;
                }
                case 8: {
                    System.out.println("Digite o ID da biblioteca");
                    int idBiblioteca = sc.nextInt();
                    System.out.println("Selecione o ID da midia digital");
                    int idMidiaDigital = sc.nextInt();
                    Biblioteca.adicionarMidia(idBiblioteca, idMidiaDigital);
                    break;
                }
                case 9: {
                    System.out.println("Digite o ID da biblioteca");
                    int idBiblioteca = sc.nextInt();
                    System.out.println("Selecione o ID da midia digital");
                    int idMidiaDigital = sc.nextInt();
                    MidiaDigital.emprestarMidia(idBiblioteca, idMidiaDigital);
                    break;
                }
                case 10: {
                    System.out.println("Digite o ID da biblioteca");
                    int idBiblioteca = sc.nextInt();
                    System.out.println("Selecione o ID da midia digital");
                    int idMidiaDigital = sc.nextInt();
                    MidiaDigital.devolverMidia(idBiblioteca, idMidiaDigital);
                    break;
                }
                default: {
                    System.out.println("Opção inválida");
                    break;
                }
            }
        } while (op != 0);
        sc.close();
    }
}
