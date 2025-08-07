package team4.biblioteca;

import team4.usuario.Usuario;

import java.time.LocalDate;

public class Emprestimo {
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private int quantidadeDeDias;
    private LocalDate dataDevolucao;

    public Emprestimo(Usuario usuario, Livro livro, LocalDate dataEmprestimo, int quantidadeDeDias) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.quantidadeDeDias = quantidadeDeDias;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public Livro getLivro() {
        return this.livro;
    }

    public boolean isDevolvido() {
        return this.dataDevolucao != null;
    }

    public boolean isAtrasado() {
        if (!isDevolvido()) {
            return LocalDate.now().isAfter(this.dataEmprestimo.plusDays(this.quantidadeDeDias));
        }
        return this.dataDevolucao.isAfter(this.dataEmprestimo.plusDays(this.quantidadeDeDias));
    }

    public int diasDeAtraso() {
        if (!isAtrasado()) return 0;

        LocalDate dataReferencia = isDevolvido() ? this.dataDevolucao : LocalDate.now();

        return (int) java.time.temporal.ChronoUnit.DAYS.between(this.dataEmprestimo.plusDays(this.quantidadeDeDias), dataReferencia);
    }

    public void registrarDevolucao(LocalDate dataDevolucao) {
        if (!isDevolvido()) {
            this.dataDevolucao = dataDevolucao;
            System.out.println("Livro devolvido com sucesso!.");
        } else {
            System.out.println("Livro já foi devolvido anteriormente.");
        }
    }

    @Override
    public String toString() {
        return "Empréstimo{" + "\n" +
                "usuario = " + usuario.getNome() + "\n" +
                "livro = " + livro.getTitulo() + "\n" +
                "dataEmprestimo = " + dataEmprestimo + "\n" +
                "dataDevolucaoPrevista = " + this.dataEmprestimo.plusDays(this.quantidadeDeDias) + "\n" +
                "dataDevolucao = " + (dataDevolucao != null ? dataDevolucao.toString() : " ") + "}";
    }
}