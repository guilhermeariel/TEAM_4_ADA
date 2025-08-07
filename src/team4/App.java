package team4;

import team4.biblioteca.Biblioteca;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Biblioteca biblioteca = new Biblioteca();
    int opcao;

    do {
      System.out.println("\n===== SISTEMA BIBLIOTECA =====");
      System.out.println("1. Cadastrar usuário");
      System.out.println("2. Cadastrar livro");
      System.out.println("3. Realizar empréstimo");
      System.out.println("4. Registrar devolução");
      System.out.println("5. Listar livros disponíveis");
      System.out.println("6. Listar usuários");
      System.out.println("7. Listar empréstimos ativos");
      System.out.println("8. Listar empréstimos atrasados");
      System.out.println("9. Editar usuário");
      System.out.println("10. Remover usuário");
      System.out.println("0. Sair");
      System.out.print("Escolha uma opção: ") ;

      opcao = scanner.nextInt();
      scanner.nextLine();

      switch (opcao) {
        case 1 -> biblioteca.cadastrarUsuario(scanner);
        case 2 -> biblioteca.adicionarAcervo(scanner);
        case 3 -> biblioteca.realizarEmprestimo(scanner);
        case 4 -> biblioteca.registrarDevolucao(scanner);
        case 5 -> biblioteca.listarAcervo();
        case 6 -> biblioteca.listarUsuarios();
        case 7 -> biblioteca.listarEmprestimosAtivos();
        case 8 -> biblioteca.listarEmprestimosAtrasados();
        case 9 -> biblioteca.editarUsuario(scanner);
        case 10 -> biblioteca.removerUsuario(scanner);
        case 0 -> System.out.println("Encerrando o sistema...");
        default -> System.out.println("Opção inválida. Tente novamente.");
      }
    } while (opcao != 0);

    scanner.close();
  }
}