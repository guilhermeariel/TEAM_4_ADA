package br;

public class ProfessorEstagiario extends Professor {

  public ProfessorEstagiario(String nome, int id, String email, String telefone, String departamento) {
    super(nome, id, email, telefone, departamento);
  }

  @Override
  public double calcularMulta(int diasAtraso) {
    // Estagiário tem a mesma taxa de multa que professor
    return diasAtraso * 0.5;
  }

  @Override
  public int getDiasEmprestimo() {
    // Estagiário tem um prazo de empréstimo reduzido
    return 7;
  }
}
