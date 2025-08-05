package br;

import java.time.LocalDate;
import java.util.*;

public class Biblioteca {
  private Map<Integer, Usuario> usuarios = new HashMap<>();
  private List<Livro> livros = new ArrayList<>();
  private List<Emprestimo> emprestimos = new ArrayList<>();

  // Cadastrar usuários de diferentes tipos
  public void cadastrarUsuario(Scanner scanner) {
    System.out.println("Informe o tipo de usuário (1- Aluno, 2- Aluno Bolsista, 3- Professor, 4- Professor Estagiário): ");
    int tipo = scanner.nextInt();
    scanner.nextLine();

    System.out.print("Nome: ");
    String nome = scanner.nextLine();
    System.out.print("Email: ");
    String email = scanner.nextLine();
    System.out.print("Telefone: ");
    String telefone = scanner.nextLine();

    try {
      Usuario usuario = null;
      switch (tipo) {
        case 1 -> {
          System.out.print("Curso: ");
          String curso = scanner.nextLine();
          System.out.print("Período: ");
          int periodo = scanner.nextInt();
          usuario = new Aluno(nome, email, telefone, curso, periodo);
        }
        case 2 -> {
          System.out.print("Curso: ");
          String curso = scanner.nextLine();
          System.out.print("Período: ");
          int periodo = scanner.nextInt();
          usuario = new AlunoBolsista(nome, email, telefone, curso, periodo);
        }
        case 3 -> {
          System.out.print("Departamento: ");
          String departamento = scanner.nextLine();
          usuario = new Professor(nome, email, telefone, departamento);
        }
        case 4 -> {
          System.out.print("Departamento: ");
          String departamento = scanner.nextLine();
          usuario = new ProfessorEstagiario(nome, email, telefone, departamento);
        }
        default -> {
          System.out.println("Tipo inválido!");
          return;
        }
      }
      usuarios.put(usuario.getId(), usuario);
      System.out.println("Usuário cadastrado com sucesso!");
    } catch (IllegalArgumentException e) {
      System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
    }
  }

  // Editar dados do usuário
  public void editarUsuario(Scanner scanner) {
    System.out.print("Informe o ID do usuário a editar: ");
    int id = scanner.nextInt();
    scanner.nextLine();

    Usuario usuario = usuarios.get(id);
    if (usuario == null) {
      System.out.println("Usuário não encontrado.");
      return;
    }

    System.out.print("Novo email: ");
    String novoEmail = scanner.nextLine();
    if (usuario.alterarEmail(novoEmail)) {
      System.out.println("Email atualizado com sucesso.");
    } else {
      System.out.println("Email inválido.");
    }
  }

  // Remover usuário
  public void removerUsuario(Scanner scanner) {
    System.out.print("Informe o ID do usuário a remover: ");
    int id = scanner.nextInt();
    if (usuarios.remove(id) != null) {
      System.out.println("Usuário removido com sucesso.");
    } else {
      System.out.println("Usuário não encontrado.");
    }
  }

  // Cadastrar livro
  public void cadastrarLivro(Scanner scanner) {
    System.out.print("Título: ");
    String titulo = scanner.nextLine();
    System.out.print("Autor: ");
    String autor = scanner.nextLine();
    System.out.print("Editora: ");
    String editora = scanner.nextLine();
    System.out.print("Ano de publicação: ");
    int ano = scanner.nextInt();
    System.out.print("Quantidade total: ");
    int total = scanner.nextInt();

    Livro livro = new Livro(titulo, autor, editora, ano, total, total);
    livros.add(livro);
    System.out.println("Livro cadastrado com sucesso.");
  }

  // Realizar empréstimo
  public void realizarEmprestimo(Scanner scanner) {
    System.out.print("Informe o ID do usuário: ");
    int idUsuario = scanner.nextInt();
    scanner.nextLine();
    Usuario usuario = usuarios.get(idUsuario);

    if (usuario == null || !usuario.podePegarEmprestimo()) {
      System.out.println("Usuário inválido ou não pode pegar empréstimo.");
      return;
    }

    listarLivrosDisponiveis();
    System.out.print("Informe o título do livro: ");
    String titulo = scanner.nextLine();

    Livro livro = livros.stream()
        .filter(l -> l.getTitulo().equalsIgnoreCase(titulo) && l.getQuantidadeDisponivel() > 0)
        .findFirst()
        .orElse(null);

    if (livro == null) {
      System.out.println("Livro não encontrado ou indisponível.");
      return;
    }

    if (!livro.emprestar()) {
      System.out.println("Falha ao registrar empréstimo. Livro indisponível.");
      return;
    }

    LocalDate hoje = LocalDate.now();
    LocalDate dataDevolucaoPrevista = hoje.plusDays(usuario.getDiasEmprestimo());
    Emprestimo emprestimo = new Emprestimo(usuario, livro, hoje, dataDevolucaoPrevista, null, false);
    emprestimos.add(emprestimo);
    System.out.println("Empréstimo registrado com sucesso.");
  }

  // Registrar devolução
  public void registrarDevolucao(Scanner scanner) {
    System.out.print("Informe o ID do usuário: ");
    int id = scanner.nextInt();
    scanner.nextLine();

    List<Emprestimo> emprestimosUsuario = emprestimos.stream()
        .filter(e -> e.getUsuario().getId() == id && !e.isDevolvido())
        .toList();

    if (emprestimosUsuario.isEmpty()) {
      System.out.println("Nenhum empréstimo ativo encontrado.");
      return;
    }

    for (int i = 0; i < emprestimosUsuario.size(); i++) {
      System.out.println(i + ". " + emprestimosUsuario.get(i));
    }

    System.out.print("Escolha o número do empréstimo a devolver: ");
    int index = scanner.nextInt();
    if (index < 0 || index >= emprestimosUsuario.size()) {
      System.out.println("Opção inválida.");
      return;
    }

    Emprestimo emprestimo = emprestimosUsuario.get(index);
    emprestimo.registrarDevolucao(LocalDate.now());
    System.out.println("Devolução registrada com sucesso.");
  }

  // Listar todos os livros disponíveis
  public void listarLivrosDisponiveis() {
    livros.stream()
        .filter(l -> l.getQuantidadeDisponivel() > 0)
        .forEach(System.out::println);
  }

  // Listar todos os usuários
  public void listarUsuarios() {
    usuarios.values().forEach(u ->
        System.out.println("ID: " + u.getId() + ", Nome: " + u.getNome() + ", Email: " + u.getEmail()));
  }

  // Listar todos os empréstimos ativos
  public void listarEmprestimosAtivos() {
    emprestimos.stream()
        .filter(e -> !e.isDevolvido())
        .forEach(System.out::println);
  }

  // Listar empréstimos atrasados
  public void listarEmprestimosAtrasados() {
    emprestimos.stream()
        .filter(Emprestimo::isAtrasado)
        .forEach(e -> {
          System.out.println(e);
          System.out.println("Dias de atraso: " + e.diasDeAtraso());
          System.out.println("Multa: R$ " + e.getUsuario().calcularMulta(e.diasDeAtraso()));
        });
  }
}



