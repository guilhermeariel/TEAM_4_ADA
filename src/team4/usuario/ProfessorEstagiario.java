package team4.usuario;

/**
 * Professor estagiário: prazo reduzido e mesma política de multa do professor.
 */
public class ProfessorEstagiario extends Professor {

    private static final double FATOR_MULTA = 0.5; // mantém a multa reduzida
    private static final int PRAZO_DIAS = 7;       // prazo menor que professor pleno

    public ProfessorEstagiario(String nome, String email, String telefone, String departamento) {
        super(nome, email, telefone, departamento);
    }

    /**
     * Mantém o mesmo desconto de multa do professor pleno.
     */
    @Override
    public double calcularMulta(int diasAtraso) {
        return diasAtraso * FATOR_MULTA;
    }

    /**
     * Prazo reduzido para estagiários.
     */
    @Override
    public int getDiasEmprestimo() {
        return PRAZO_DIAS;
    }

    // Observação: comportamento de elegibilidade é o mesmo do Professor.
    // Override de podePegarEmprestimo seria redundante; herdamos do pai.
    @Override
    public String toString() {
        return "ProfessorEstagiario{id=" + getId() + ", nome='" + getNome() + "'}";
    }
}
