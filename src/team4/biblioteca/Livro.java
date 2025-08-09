package team4.biblioteca;

import java.time.Year;
import java.util.Objects;

/**
 * Entidade Livro: dados bibliográficos básicos e identidade por (título, autor, ano).
 */
public class Livro {
    private final String titulo;
    private final String autor;
    private final String editora;
    private final int anoPublicacao;

    /**
     * Cria um livro válido.
     * @throws IllegalArgumentException se algum campo textual for vazio ou o ano estiver fora da faixa [1450, anoAtual]
     */
    public Livro(String titulo, String autor, String editora, int anoPublicacao) {
        if (isBlank(titulo)) throw new IllegalArgumentException("Título não pode ser vazio.");
        if (isBlank(autor)) throw new IllegalArgumentException("Autor não pode ser vazio.");
        if (isBlank(editora)) throw new IllegalArgumentException("Editora não pode ser vazia.");

        int anoAtual = Year.now().getValue();
        if (anoPublicacao < 1450 || anoPublicacao > anoAtual) {
            throw new IllegalArgumentException("Ano de publicação inválido: " + anoPublicacao);
        }

        this.titulo = titulo.trim();
        this.autor = autor.trim();
        this.editora = editora.trim();
        this.anoPublicacao = anoPublicacao;
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getEditora() { return editora; }
    public int getAnoPublicacao() { return anoPublicacao; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Livro)) return false;
        Livro livro = (Livro) o;
        return anoPublicacao == livro.anoPublicacao
            && titulo.equalsIgnoreCase(livro.titulo)
            && autor.equalsIgnoreCase(livro.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo.toLowerCase(), autor.toLowerCase(), anoPublicacao);
    }

    @Override
    public String toString() {
        return "Livro{" +
            "titulo='" + titulo + '\'' +
            ", autor='" + autor + '\'' +
            ", editora='" + editora + '\'' +
            ", anoPublicacao=" + anoPublicacao +
            '}';
    }
}
