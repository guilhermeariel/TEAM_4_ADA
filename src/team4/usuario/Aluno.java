package team4.usuario;

import java.util.Arrays;
import java.util.List;

public class Aluno extends Usuario {
  private static final List<String> CURSOS_VALIDOS = Arrays.asList(
      "Medicina", "Engenharia", "Direito", "TI", "Pedagogia", "Matemática"
  );
  private static final List<String> PERIODOS_VALIDOS = Arrays.asList(
      "Manhã", "Tarde", "Noite"
  );

  private final String curso;
  private String periodo;
  private boolean ativo;

  public Aluno(String nome, String email, String telefone, String curso, String periodo) {
    super(nome, email, telefone);

    if (!CURSOS_VALIDOS.contains(curso)) {
      throw new IllegalArgumentException("Curso inválido: " + curso);
    }
    if (!PERIODOS_VALIDOS.contains(periodo)) {
      throw new IllegalArgumentException("Período inválido: " + periodo);
    }

    this.curso = curso;
    this.periodo = periodo;
    this.ativo = true;
  }

  public String getCurso() {
    return curso;
  }

  public String getPeriodo() {
    return periodo;
  }

  public boolean isAtivo() {
    return ativo;
  }

  public void trancarMatricula() {
    this.ativo = false;
  }

  public void reativarMatricula() {
    this.ativo = true;
  }

  public void atualizarPeriodo(String novoPeriodo) {
    if (!PERIODOS_VALIDOS.contains(novoPeriodo)) {
      throw new IllegalArgumentException("Período inválido: " + novoPeriodo);
    }
    this.periodo = novoPeriodo;
  }

  @Override
  public boolean podePegarEmprestimo() {
    return ativo && !isBloqueado();
  }

  @Override
  public double calcularMulta(int diasAtraso) {
    return diasAtraso * 1.0; // R$ 1 por dia de atraso
  }

  @Override
  public int getDiasEmprestimo() {
    return 7; // Prazo padrão para alunos
  }
}
