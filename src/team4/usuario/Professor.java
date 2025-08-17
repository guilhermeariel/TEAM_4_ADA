package team4.usuario;

import java.util.Arrays;
import java.util.List;

public class Professor extends Usuario {
    private static final List<String> DEPARTAMENTOS_VALIDOS = Arrays.asList(
        "Matemática", "Informática", "Letras", "Física", "História", "Medicina", "História"
    );
    private static final List<String> NIVEIS_VALIDOS = Arrays.asList(
        "mestre", "doutor"
    );

    private static final double FATOR_MULTA = 0.0; // professor paga metade da multa padrão
    private static final int PRAZO_DIAS = 14;       // prazo ampliado para professor

    private final String departamento;
    private String nivel; // "mestre" ou "doutor"
    private boolean emAula;

    public Professor(String nome, String email, String telefone, String departamento) {
        super(nome, email, telefone);

        if (!DEPARTAMENTOS_VALIDOS.contains(departamento)) {
            throw new IllegalArgumentException("Departamento inválido: " + departamento);
        }

        this.departamento = departamento;
        this.nivel = "mestre";
        this.emAula = false;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getNivel() {
        return nivel;
    }

    /**
     * Atualiza o nível acadêmico, validando contra a lista permitida.
     */
    public void atualizarNivel(String novoNivel) {
        if (!NIVEIS_VALIDOS.contains(novoNivel)) {
            throw new IllegalArgumentException("Nível inválido: " + novoNivel);
        }
        this.nivel = novoNivel;
    }

    /**
     * Inicia uma aula; retorna true se mudou o estado.
     */
    public boolean iniciarAula() {
        if (emAula) return false;
        emAula = true;
        return true;
    }

    /**
     * Encerra uma aula; retorna true se mudou o estado.
     */
    public boolean encerrarAula() {
        if (!emAula) return false;
        emAula = false;
        return true;
    }

    public boolean estaDisponivelParaRetirada() {
        return !emAula && !isBloqueado();
    }

    @Override
    public boolean podePegarEmprestimo() {
        return estaDisponivelParaRetirada();
    }

    @Override
    public double calcularMulta(int diasAtraso) {
        return diasAtraso * FATOR_MULTA;
    }

    @Override
    public int getDiasEmprestimo() {
        return PRAZO_DIAS;
    }

    @Override
    public String toString() {
        return "Professor{" +
            "id=" + getId() +
            ", nome='" + getNome() + '\'' +
            ", depto='" + departamento + '\'' +
            ", nivel='" + nivel + '\'' +
            ", emAula=" + emAula +
            '}';
    }
}
