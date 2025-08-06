package team4.usuario;

public class ProfessorEstagiario extends Professor {

  // Alterado: remoção do parâmetro id (herdado de Usuario)
  public ProfessorEstagiario(String nome, String email, String telefone, String departamento) {
    super(nome, email, telefone, departamento);
  }

  @Override
  public double calcularMulta(int diasAtraso) {
    // Estagiário paga a mesma multa que o professorr
    return diasAtraso * 0.5;
  }

  @Override
  public int getDiasEmprestimo() {
    // Estagiário tem menos dias de empréstimo
    return 7;
  }
}
