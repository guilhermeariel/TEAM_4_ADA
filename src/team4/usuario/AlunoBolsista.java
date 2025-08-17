package team4.usuario;

/**
 * Aluno bolsista: paga metade da multa e tem prazo de empréstimo maior.
 */
public class AlunoBolsista extends Aluno {

    private static final double FATOR_MULTA = 0.5; // 50% da multa padrão do aluno
    private static final int PRAZO_DIAS = 10;      // prazo ampliado para bolsistas

    // Ajustado: periodo como String ("Manhã", "Tarde", "Noite"), seguindo Aluno
    public AlunoBolsista(String nome, String email, String telefone, String curso, String periodo) {
        super(nome, email, telefone, curso, periodo);
    }

    /**
     * Bolsista paga 50% da multa aplicada a um aluno comum.
     */
    @Override
    public double calcularMulta(int diasAtraso) {
        return diasAtraso * FATOR_MULTA;
    }

    /**
     * Prazo ampliado para bolsistas.
     */
    @Override
    public int getDiasEmprestimo() {
        return PRAZO_DIAS;
    }
}
