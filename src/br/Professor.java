package br;

public class Professor extends Usuario{
  //APLICACAO DE ATRIBUTOS ESPECILIZADOS
  private String departamento;

  //CONSTRUTOR
  public Professor(String nome, int id, String departamento) {
    super(nome, id);
    this.departamento = departamento;
  }

  //GETTERS E SETTER
  public String getDepartamento() {
    return departamento;
  }

  public void setDepartamento(String departamento) {
    this.departamento = departamento;
  }

  @Override
  public double calcularMulta(int diasAtraso) {
    return diasAtraso * 0.5;
  }

  @Override
  public int getDiasEmprestimo() {
    return 20;
  }
}
