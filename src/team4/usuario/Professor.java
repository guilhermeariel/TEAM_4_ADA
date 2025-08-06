package team4.usuario;

import java.util.Arrays;
import java.util.List;

public class Professor extends Usuario {
  private static final List<String> DEPARTAMENTOS_VALIDOS = Arrays.asList("Matemática", "Informática",
      "Letras", "Física", "História", "Medicina");

  private String departamento;
<<<<<<< HEAD:src/br/Professor.java
  private String nivel; // Ex: mestre, doutor
  private boolean emAula ;
=======
  private String nivel; // ex: mestre, doutor
  private boolean emAula;
>>>>>>> c09ac29bae84e7b8f6d7f90a9ae0863350e5d165:src/team4/usuario/Professor.java

  // Alterado: remoção do parâmetro id (gerado automaticamente pela superclasse)
  // Adicionado: validação do departamento com base em lista permitida
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
  public double calcularMulta(int diasAtraso) {
    return diasAtraso * 0.5;
  }

  @Override
  public int getDiasEmprestimo() {
    return 14;
  }

  @Override
  public boolean podePegarEmprestimo() {
    return false;
  }
}

