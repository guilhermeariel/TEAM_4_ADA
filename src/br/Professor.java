package br;

import java.util.Arrays;
import java.util.List;

public class Professor extends Usuario {
  private static final List<String> DEPARTAMENTOS_VALIDOS = Arrays.asList(
      "Matemática", "Informática", "Letras", "Física", "História", "Medicina");

  private String departamento;
  private String nivel; // Ex: mestre, doutor
  private boolean emAula;

  public Professor(String nome, String email, String telefone, String departamento) {
    super(nome, email, telefone);

    if (!DEPARTAMENTOS_VALIDOS.contains(departamento)) {
      throw new IllegalArgumentException("Departamento inválido: " + departamento);
    }

    this.departamento = departamento;
    this.nivel = "mestre";
    this.emAula = false;
  }

  public String getDepartamento() {
    return departamento;
  }

  public String getNivel() {
    return nivel;
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
  public boolean podePegarEmprestimo() {
    return estaDisponivelParaRetirada(); // Corrigido aqui
  }

  @Override
  public double calcularMulta(int diasAtraso) {
    return diasAtraso * 0.5;
  }

  @Override
  public int getDiasEmprestimo() {
    return 14;
  }
}
