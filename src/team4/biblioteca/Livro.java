package team4.biblioteca;

public class Livro {
    private String titulo;
    private String autor;
    private String editora;
    private int anoPublicacao;

    public Livro(String titulo, String autor, String editora, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditora() {
        return editora;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "Titulo = " + titulo + '\'' +
                ", autor = " + autor + '\'' +
                ", editora = " + editora + '\'' +
                ", anoPublicação = " + anoPublicacao + '}';
    }
}