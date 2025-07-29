package br;

public abstract class Usuario {
  //ATRIBUTOS APLICAÇÂO DE ENCAPSULAMENTO
  private String nome;
  private int id;

  //CONSTRUTOR
  public Usuario(String nome, int id) {
    this.nome = nome;
    this.id = id;
  }

  //GETTERS SETTERS
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  //METODOS PUBLICOS APLICAÇÂO DE ABSTRACAO, DEFINIÇÃO DO CONTRATO
  public abstract double calcularMulta(int diasAtraso);
  public abstract int getDiasEmprestimo();

}
