package br;

public class Aluno extends Usuario{
  //ATRIBUTOS DE ESPECIALIZACAO
  private String curso;

  //CONSTRUTOR
  public Aluno(String nome, int id, String curso) {
    super(nome, id);
    this.curso = curso ;
  }

  //GETTERS SETTERS
  public String getCurso() {
    return curso;
  }

  public void setCurso(String curso) {
    this.curso = curso;
  }

  //POLIFORMISMO DE SOBRECARGA
  @Override
  public double calcularMulta(int diasAtraso) {
    return diasAtraso * 1.5;
  }

  @Override
  public int getDiasEmprestimo() {
    return 14;
  }
}
