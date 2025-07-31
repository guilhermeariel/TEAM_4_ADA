package br;

public abstract class Usuario {
  private String nome;
  private int id;
  private String email;
  private String telefone;
  private boolean bloqueado;

  public Usuario(String nome, int id, String email, String telefone) {
    this.nome = nome;
    this.id = id;
    this.email = email;
    this.telefone = telefone;
    this.bloqueado = false;
  }

  public String getNome() {
    return nome;
  }

  public int getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getTelefone() {
    return telefone;
  }

  public boolean isBloqueado() {
    return bloqueado;
  }

  public void bloquear(String motivo) {
    this.bloqueado = true;
    System.out.println("Usuário bloqueado: " + motivo);
  }

  public void desbloquear() {
    this.bloqueado = false;
    System.out.println("Usuário desbloqueado.");
  }

  public boolean alterarEmail(String novoEmail) {
    if (novoEmail != null && novoEmail.contains("@")) {
      this.email = novoEmail;
      return true;
    }
    return false;
  }

  public abstract double calcularMulta(int diasAtraso);

  public abstract int getDiasEmprestimo();
}
