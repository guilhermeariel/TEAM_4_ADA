package br;
import java.util.Arrays;
import java.util.List;

public class Aluno extends Usuario {
  private static final List<String> CURSOS_VALIDOS = Arrays.asList("ADS", "Engenharia",
      "Direito", "TI", "Pedagogia");

  private String curso;
  private int periodo;
  private boolean ativo;

  public Aluno(String nome, String email, String telefone, String curso, int periodo) {
    super(nome, email, telefone);

    if (!CURSOS_VALIDOS.contains(curso)) {
      throw new IllegalArgumentException("Curso inválido: " + curso);
    }

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

  @Override
  public double calcularMulta(int diasAtraso) {
    return diasAtraso * 1.0;
  }

  @Override
  public int getDiasEmprestimo() {
    return 7;
  }
}

