package br;

public class Aluno extends Usuario {
  private String curso;
  private int periodo;
  private boolean ativo;

  public Aluno(String nome, int id, String email, String telefone, String curso, int periodo) {
    super(nome, id, email, telefone);
    this.curso = curso;
    this.periodo = periodo;
    this.ativo = true;
  }

  public String getCurso() {
    return curso;
  }

  public int getPeriodo() {
    return periodo;
  }

  public boolean isAtivo() {
    return ativo;
  }

  public void trancarMatricula() {
    this.ativo = false;
  }

  public void atualizarPeriodo() {
    this.periodo++;
  }

  public boolean podePegarEmprestimo() {
    return ativo && !isBloqueado();
  }

  //POLIFORMISMO DE SOBRECARGA
  @Override
  public double calcularMulta(int diasAtraso) {
    return diasAtraso * 1.0;
  }

  @Override
  public int getDiasEmprestimo() {
    return 7;
  }
}

