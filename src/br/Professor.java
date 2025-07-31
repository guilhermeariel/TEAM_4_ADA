package br;

public class Professor extends Usuario {
  private String departamento;
  private String nivel; // Exemplo: "mestre", "doutor"
  private boolean emAula;

  public Professor(String nome, int id, String email, String telefone, String departamento) {
    super(nome, id, email, telefone);
    this.departamento = departamento;
    this.nivel = "mestre"; // valor padrão
    this.emAula = false;
  }

  //GETTERS E SETTER
  public String getDepartamento() {
    return departamento;
  }

  public String getNivel() {
    return nivel;
  }

  public void setNivel(String nivel) {
    this.nivel = nivel;
  }

  public void iniciarAula() {
    emAula = true;
  }

  public void encerrarAula() {
    emAula = false;
  }

  public boolean estaDisponivelParaRetirada() {
    return !emAula && !isBloqueado();
  }

  @Override
  public double calcularMulta(int diasAtraso) {
    return diasAtraso * 0.5; // Professores pagam menos multa
  }

  @Override
  public int getDiasEmprestimo() {
    return 14; // Prazo maior para professores
  }
}
