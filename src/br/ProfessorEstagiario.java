package br;

public class ProfessorEstagiario extends Professor {

  public ProfessorEstagiario(String nome, String email, String telefone, String departamento) {
    super(nome, email, telefone, departamento);
  }

  @Override
  public double calcularMulta(int diasAtraso) {
    return diasAtraso * 0.5;
  }

  @Override
  public int getDiasEmprestimo() {
    return 7;
  }

  @Override
  public boolean podePegarEmprestimo() {
    return estaDisponivelParaRetirada(); // torna explícito o comportamento
  }
}
