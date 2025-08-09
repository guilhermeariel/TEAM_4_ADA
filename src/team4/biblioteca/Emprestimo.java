package team4.biblioteca;

import team4.usuario.Usuario;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Representa um empréstimo de um livro por um usuário, com datas e cálculo de atraso/multa.
 */
public class Emprestimo {
    private final Usuario usuario;
    private final Livro livro;
    private final LocalDate dataEmprestimo;
    private final int quantidadeDeDias; // regra do usuário no momento da criação
    private LocalDate dataDevolucao;

    /**
     * Cria um empréstimo válido.
     * Regras:
     * - usuario e livro não podem ser nulos.
     * - usuario deve poder pegar emprestimo no momento da criação.
     * - dataEmprestimo não pode ser futura.
     * - quantidadeDeDias > 0 e, preferencialmente, <= getDiasEmprestimo() do usuario.
     */
    public Emprestimo(Usuario usuario, Livro livro, LocalDate dataEmprestimo, int quantidadeDeDias) {
        if (usuario == null) throw new IllegalArgumentException("Usuário inválido.");
        if (livro == null) throw new IllegalArgumentException("Livro inválido.");
        if (dataEmprestimo == null) throw new IllegalArgumentException("Data de empréstimo inválida.");
        if (dataEmprestimo.isAfter(LocalDate.now())) throw new IllegalArgumentException("Data de empréstimo no futuro.");
        if (quantidadeDeDias <= 0) throw new IllegalArgumentException("Quantidade de dias deve ser positiva.");
        if (!usuario.podePegarEmprestimo()) throw new IllegalArgumentException("Usuário não está apto a emprestar.");
        // Opcional: restringir ao prazo padrão do usuário
        if (quantidadeDeDias > usuario.getDiasEmprestimo()) {
            throw new IllegalArgumentException("Prazo solicitado excede o permitido para o usuário.");
        }

        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.quantidadeDeDias = quantidadeDeDias;
    }

    public Usuario getUsuario() { return usuario; }
    public Livro getLivro() { return livro; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public int getQuantidadeDeDias() { return quantidadeDeDias; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }

    public LocalDate getDataPrevistaDevolucao() {
        return dataEmprestimo.plusDays(quantidadeDeDias);
    }

    public boolean isDevolvido() {
        return dataDevolucao != null;
    }

    public boolean isAtrasado() {
        LocalDate ref = isDevolvido() ? dataDevolucao : LocalDate.now();
        return ref.isAfter(getDataPrevistaDevolucao());
    }

    public int diasDeAtraso() {
        LocalDate ref = isDevolvido() ? dataDevolucao : LocalDate.now();
        long diff = ChronoUnit.DAYS.between(getDataPrevistaDevolucao(), ref);
        return (int) Math.max(0, diff);
    }

    /**
     * Registra devolução na data informada.
     * @throws IllegalStateException se já tiver sido devolvido.
     */
    public void registrarDevolucao(LocalDate dataDevolucao) {
        if (dataDevolucao == null) throw new IllegalArgumentException("Data de devolução inválida.");
        if (isDevolvido()) throw new IllegalStateException("Empréstimo já devolvido.");
        if (dataDevolucao.isBefore(dataEmprestimo)) throw new IllegalArgumentException("Devolução antes do empréstimo.");
        this.dataDevolucao = dataDevolucao;
    }

    /**
     * Calcula o valor da multa com base no tipo de usuário (polimorfismo).
     */
    public double valorMulta() {
        int atraso = diasDeAtraso();
        return atraso <= 0 ? 0.0 : usuario.calcularMulta(atraso);
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
            "usuario='" + usuario.getNome() + '\'' +
            ", livro='" + livro.getTitulo() + '\'' +
            ", dataEmprestimo=" + dataEmprestimo +
            ", dataPrevistaDevolucao=" + getDataPrevistaDevolucao() +
            ", dataDevolucao=" + (dataDevolucao != null ? dataDevolucao : "—") +
            ", diasAtraso=" + diasDeAtraso() +
            ", valorMulta=" + valorMulta() +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Emprestimo)) return false;
        Emprestimo that = (Emprestimo) o;
        return Objects.equals(usuario.getId(), that.usuario.getId())
            && Objects.equals(livro, that.livro)
            && Objects.equals(dataEmprestimo, that.dataEmprestimo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario.getId(), livro, dataEmprestimo);
    }
}
