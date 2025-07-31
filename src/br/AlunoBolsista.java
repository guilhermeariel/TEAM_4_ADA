package br;

public class AlunoBolsista extends Aluno {


  public AlunoBolsista(String nome, int id, String curso) {
    super(nome, id, curso);
  }

  @Override
  public double calcularMulta(int diasAtraso) {
    // Bolsista paga menos multa por dia
    return diasAtraso * 0.50;
  }

  @Override
  public int getDiasEmprestimo() {
    // Bolsista tem mais tempo de empréstimo
    return 10;
  }
}
