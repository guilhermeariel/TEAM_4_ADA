// 📁 model/Biblioteca.java
package br;

import java.time.LocalDate;
import java.util.*;

public class Biblioteca {
  private List<Usuario> usuarios = new ArrayList<>();
  private List<Livro> livros = new ArrayList<>();
  private List<Emprestimo> emprestimos = new ArrayList<>();
  private Scanner scanner = new Scanner(System.in);

  public void cadastrarLivro() {
    System.out.print("Título: ");
    String titulo = scanner.nextLine();
    System.out.print("Autor: ");
    String autor = scanner.nextLine();
    System.out.print("Editora: ");
    String editora = scanner.nextLine();
    System.out.print("Ano de publicação: ");
    int ano = Integer.parseInt(scanner.nextLine());
    System.out.print("Quantidade total: ");
    int total = Integer.parseInt(scanner.nextLine());

    Livro livro = new Livro(titulo, autor, editora, ano, total, total);
    livros.add(livro);
    System.out.println("Livro cadastrado com sucesso!");
  }

  public void cadastrarUsuario() {
    System.out.print("Nome: ");
    String nome = scanner.nextLine();
    System.out.print("Email: ");
    String email = scanner.nextLine();
    System.out.print("Telefone: ");
    String telefone = scanner.nextLine();

    System.out.println("Tipo de usuário: 1-Aluno  2-Professor  3-Aluno Bolsista  4-Professor Estagiário");
    int tipo = Integer.parseInt(scanner.nextLine());

    try {
      switch (tipo) {
        case 1 -> {
          System.out.print("Curso: ");
          String curso = scanner.nextLine();
          System.out.print("Período: ");
          int periodo = Integer.parseInt(scanner.nextLine());
          usuarios.add(new Aluno(nome, email, telefone, curso, periodo));
        }
        case 2 -> {
          System.out.print("Departamento: ");
          String depto = scanner.nextLine();
          usuarios.add(new Professor(nome, email, telefone, depto));
        }
        case 3 -> {
          System.out.print("Curso: ");
          String curso = scanner.nextLine();
          System.out.print("Período: ");
          int periodo = Integer.parseInt(scanner.nextLine());
          usuarios.add(new AlunoBolsista(nome, email, telefone, curso, periodo));
        }
        case 4 -> {
          System.out.print("Departamento: ");
          String depto = scanner.nextLine();
          usuarios.add(new ProfessorEstagiario(nome, email, telefone, depto));
        }
        default -> System.out.println("Tipo inválido");
      }
    } catch (IllegalArgumentException e) {
      System.out.println("Erro ao cadastrar: " + e.getMessage());
    }
  }

  public void listarUsuarios() {
    usuarios.forEach(System.out::println);
  }

  public void editarUsuario() {
    System.out.print("Digite o ID do usuário: ");
    int id = Integer.parseInt(scanner.nextLine());
    Optional<Usuario> opt = usuarios.stream().filter(u -> u.getId() == id).findFirst();
    if (opt.isEmpty()) {
      System.out.println("Usuário não encontrado.");
      return;
    }
    Usuario u = opt.get();
    System.out.print("Novo email: ");
    String novoEmail = scanner.nextLine();
    if (u.alterarEmail(novoEmail)) {
      System.out.println("Email atualizado.");
    } else {
      System.out.println("Email inválido.");
    }
  }

  public void removerUsuario() {
    System.out.print("Digite o ID do usuário a remover: ");
    int id = Integer.parseInt(scanner.nextLine());
    usuarios.removeIf(u -> u.getId() == id);
    System.out.println("Remoção concluída.");
  }

  public void realizarEmprestimo() {
    System.out.print("ID do usuário: ");
    int id = Integer.parseInt(scanner.nextLine());
    Optional<Usuario> opt = usuarios.stream().filter(u -> u.getId() == id).findFirst();
    if (opt.isEmpty()) {
      System.out.println("Usuário não encontrado.");
      return;
    }
    Usuario usuario = opt.get();
    if (!usuario.podePegarEmprestimo()) {
      System.out.println("Usuário não pode pegar empréstimo.");
      return;
    }
    System.out.print("Título do livro: ");
    String titulo = scanner.nextLine();
    Optional<Livro> livroOpt = livros.stream().filter(l -> l.getTitulo().equalsIgnoreCase(titulo)).findFirst();
    if (livroOpt.isEmpty()) {
      System.out.println("Livro não encontrado.");
      return;
    }
    Livro livro = livroOpt.get();
    if (!livro.emprestar()) {
      System.out.println("Livro indisponível.");
      return;
    }
    LocalDate hoje = LocalDate.now();
    LocalDate devolucao = hoje.plusDays(usuario.getDiasEmprestimo());
    Emprestimo e = new Emprestimo(usuario, livro, hoje, devolucao, null, false);
    emprestimos.add(e);
    System.out.println("Empréstimo realizado: " + e);
  }

  public void devolverLivro() {
    System.out.print("Digite o título do livro a devolver: ");
    String titulo = scanner.nextLine();
    Optional<Emprestimo> opt = emprestimos.stream()
        .filter(e -> e.getLivro().getTitulo().equalsIgnoreCase(titulo) && !e.isDevolvido())
        .findFirst();
    if (opt.isEmpty()) {
      System.out.println("Empréstimo não encontrado.");
      return;
    }
    Emprestimo e = opt.get();
    e.registrarDevolucao(LocalDate.now());
    System.out.println("Livro devolvido com sucesso.");
    if (e.isAtrasado()) {
      double multa = e.getUsuario().calcularMulta(e.diasDeAtraso());
      System.out.println("Livro devolvido com atraso. Multa: R$ " + multa);
    }
  }

  public void listarLivrosEmprestados() {
    emprestimos.stream()
        .filter(e -> !e.isDevolvido())
        .forEach(System.out::println);
  }

  public void listarTodosEmprestimos() {
    emprestimos.forEach(System.out::println);
  }
}
