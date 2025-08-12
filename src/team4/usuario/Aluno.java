package team4.usuario;

import java.util.Arrays;
import java.util.List;

public class Aluno extends Usuario {
  // static final: unico valor não reatribuido
  // List: estrutura que armazena valores em sequência e permite verificação rápida com contains()
  // Arrays.asList: cria uma lista fixa em tamanho, inicializada com os cursos permitidos
  private static final List<String> CURSOS_VALIDOS = Arrays.asList("Medicina", "Engenharia",
      "Direito", "TI", "Pedagogia", "Matemática");

  //Herança por implementação
  private String curso;
  private int periodo;
  private boolean ativo;

  // Alterado: remoção do parâmetro id (gerado automaticamente pela superclasse)
  // Adicionado: validação do departamento com base em lista permitida
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
    return diasAtraso * 1.1;
  }

  @Override
  public int getDiasEmprestimo() {
    return 7;
  }


}

