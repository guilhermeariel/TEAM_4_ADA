package team4;

import team4.biblioteca.Biblioteca;
import team4.biblioteca.Livro;
import team4.usuario.*;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        Usuario aluno1 = new Aluno("Aluno 1", "aluno1@aluno.com", "11 99999-9999",
            "Medicina", 1);
        biblioteca.adicionarUsuario(aluno1.getId(), aluno1);

        Usuario aluno2 = new Aluno("Aluno 2", "aluno2@aluno.com", "11 99999-9999",
            "Engenharia", 2);
        biblioteca.adicionarUsuario(aluno2.getId(), aluno2);

        Usuario bolsista1 = new AlunoBolsista("Bolsista 1", "bolsista1@bolsista.com",
            "11 99999-9999", "Medicina", 3);
        biblioteca.adicionarUsuario(bolsista1.getId(), bolsista1);

        Usuario bolsista2 = new AlunoBolsista("Bolsista 2", "bolsista2@bolsista.com",
            "11 99999-9999", "Engenharia", 3);
        biblioteca.adicionarUsuario(bolsista2.getId(), bolsista2);

        Usuario professor1 = new Professor("Professor 1", "professor1@professor.com",
            "11 99999-9999", "Medicina");
        biblioteca.adicionarUsuario(professor1.getId(), professor1);

        Usuario professor2 = new Professor("Professor 2", "professor2@professor.com",
            "11 99999-9999", "Informática");
        biblioteca.adicionarUsuario(professor2.getId(), professor2);

        Usuario estagiario1 = new ProfessorEstagiario("Estagiario 1", "estagiario1@estagiario.com",
            "11 99999-9999", "Medicina");
        biblioteca.adicionarUsuario(estagiario1.getId(), estagiario1);

        Usuario estagiario2 = new ProfessorEstagiario("Estagiario 2", "estagiario2@estagiario.com",
            "11 99999-9999", "Informática");
        biblioteca.adicionarUsuario(estagiario2.getId(), estagiario2);

        // Criando objetos Livro
        Livro livro1 = new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "Allen & Unwin",
            1954);
        biblioteca.adicionarAcervo(livro1, 3);

        Livro livro2 = new Livro("Harry Potter e a Pedra Filosofal", "J.K. Rowling",
            "Bloomsbury", 1997);
        biblioteca.adicionarAcervo(livro2, 3);

        Livro livro3 = new Livro("1984", "George Orwell", "Secker & Warburg",
            1949);
        biblioteca.adicionarAcervo(livro3, 3);

        Livro livro4 = new Livro("Duna", "Frank Herbert", "Chilton Books",
            1965);
        biblioteca.adicionarAcervo(livro4, 3);

        Livro livro5 = new Livro("A Roda do Tempo", "Robert Jordan", "Tor Books",
            1990);
        biblioteca.adicionarAcervo(livro5, 3);

        Scanner scanner = new Scanner(System.in);

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
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> biblioteca.cadastrarUsuario(scanner);
                case 2 -> biblioteca.cadastrarLivro(scanner);
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