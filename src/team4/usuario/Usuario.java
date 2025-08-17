package team4.usuario;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public abstract class Usuario {
  private static final AtomicInteger PROX_ID = new AtomicInteger(1);
  private static final Pattern TEL_BR = Pattern.compile("^\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}$");
  private static final Pattern EMAIL = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

  private final int id;
  private final String nome;
  private String email;
  private String telefone;
  private boolean bloqueado;
  private String motivoBloqueio;
  private LocalDateTime dataBloqueio;

  public Usuario(String nome, String email, String telefone) {
    if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome não pode ser vazio.");
    if (!validarEmail(email)) throw new IllegalArgumentException("Email inválido.");
    if (!validarTelefone(telefone)) throw new IllegalArgumentException("Telefone inválido.");

    this.id = PROX_ID.getAndIncrement();
    this.nome = nome.trim();
    this.email = email.trim();
    this.telefone = normalizarTelefone(telefone);
    this.bloqueado = false;
  }

  protected static boolean validarTelefone(String telefone) {
    return telefone != null && TEL_BR.matcher(telefone.trim()).matches();
  }
  protected static String normalizarTelefone(String telefone) {
    return telefone.replaceAll("\\D", "");
  }
  protected static boolean validarEmail(String email) {
    return email != null && EMAIL.matcher(email.trim()).matches();
  }

  public String getNome() { return nome; }
  public int getId() { return id; }
  public String getEmail() { return email; }
  public String getTelefone() { return telefone; }
  public boolean isBloqueado() { return bloqueado; }
  public String getMotivoBloqueio() { return motivoBloqueio; }
  public LocalDateTime getDataBloqueio() { return dataBloqueio; }

  public void bloquear(String motivo) {
    this.bloqueado = true;
    this.motivoBloqueio = motivo;
    this.dataBloqueio = LocalDateTime.now();
  }
  public void desbloquear() {
    this.bloqueado = false;
    this.motivoBloqueio = null;
    this.dataBloqueio = null;
  }

  public void alterarEmail(String novoEmail) {
    if (!validarEmail(novoEmail)) throw new IllegalArgumentException("Email inválido.");
    this.email = novoEmail.trim();
  }

  public abstract double calcularMulta(int diasAtraso);
  public abstract int getDiasEmprestimo();
  public abstract boolean podePegarEmprestimo();

  @Override public String toString() { return "%s (id=%d)".formatted(nome, id); }
}
