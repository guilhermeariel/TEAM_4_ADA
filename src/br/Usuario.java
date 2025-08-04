package br;
import java.util.regex.Pattern;

public abstract class Usuario {
  private static int proximoId = 1;
  private String nome;
  private int id;
  private String email;
  private String telefone;
  private boolean bloqueado;

  public Usuario(String nome, String email, String telefone) {
    if (nome == null || nome.trim().isEmpty()) {
      throw new IllegalArgumentException("Nome não pode ser vazio.");
    }
    if (!validarTelefone(telefone)) {
      throw new IllegalArgumentException("Telefone inválido.");
    }
    this.nome = nome;
    this.id = proximoId++;
    this.email = email;
    this.telefone = telefone;
    this.bloqueado = false;
  }

  private boolean validarTelefone(String telefone) {
    if (telefone == null || telefone.trim().isEmpty()) {
      return false;
    }
    String regex = "^\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}$";
    return Pattern.matches(regex, telefone.trim());
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
  public abstract boolean podePegarEmprestimo();
}
