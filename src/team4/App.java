package team4;

import java.util.InputMismatchException;
import java.util.Scanner;
import team4.biblioteca.Biblioteca;
import team4.biblioteca.Livro;
import team4.usuario.Aluno;
import team4.usuario.AlunoBolsista;
import team4.usuario.Professor;
import team4.usuario.ProfessorEstagiario;
import team4.usuario.Usuario;

public class App {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        seedDados(biblioteca);

        try (Scanner scanner = new Scanner(System.in)) {
            int opcao;
            do {
                mostrarMenu();
                try {
                    System.out.print("Escolha uma opção: ");
                    opcao = scanner.nextInt();
                    scanner.nextLine(); // consome quebra de linha

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
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Digite um número.");
                    opcao = -1;
                    scanner.nextLine(); // limpa buffer
                }
            } while (opcao != 0);
        }
    }

    private static void mostrarMenu() {
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
    }

    private static void seedDados(Biblioteca biblioteca) {
        // Usuarios
        // 3 Professores
        Usuario professor1 = new Professor("Professor Xavier", "xavier@xmen.com", "11 99999-9999", "Informática");
        biblioteca.adicionarUsuario(professor1.getId(), professor1);

        Usuario professor2 = new Professor("Magneto", "magneto@xmen.com", "11 99999-9999", "Física");
        biblioteca.adicionarUsuario(professor2.getId(), professor2);

        Usuario professor3 = new Professor("Girafales", "professorgirafales@chaves.com", "11 99999-9999", "Letras");
        biblioteca.adicionarUsuario(professor3.getId(), professor3);

// 3 Alunos
        Usuario aluno1 = new Aluno("Chaves", "chaves@vila.com", "11 99999-9999", "História", "Manhã");
        biblioteca.adicionarUsuario(aluno1.getId(), aluno1);

        Usuario aluno2 = new Aluno("Quico", "quico@vila.com", "11 99999-9999", "Matemática", "Tarde");
        biblioteca.adicionarUsuario(aluno2.getId(), aluno2);

        Usuario aluno3 = new Aluno("Cebolinha", "cebolinha@bairro.com", "11 99999-9999", "Informática", "Noite");
        biblioteca.adicionarUsuario(aluno3.getId(), aluno3);

// 2 Alunos Bolsistas
        Usuario bolsista1 = new AlunoBolsista("Chiquinha", "chiquinha@vila.com", "11 99999-9999", "Medicina", "Manhã");
        biblioteca.adicionarUsuario(bolsista1.getId(), bolsista1);

        Usuario bolsista2 = new AlunoBolsista("Ciclope", "ciclope@xmen.com", "11 99999-9999", "Engenharia", "Tarde");
        biblioteca.adicionarUsuario(bolsista2.getId(), bolsista2);

// 1 Professor Estagiário
        Usuario estagiario1 = new ProfessorEstagiario("Colossus", "colossus@xmen.com", "11 99999-9999", "Matemática");
        biblioteca.adicionarUsuario(estagiario1.getId(), estagiario1);


        // ACERVO
        // Matemática
        biblioteca.adicionarAcervo(
            new Livro("A Matéria dos Números", "Marcus du Sautoy", "Zahar", 2018), 3);
        biblioteca.adicionarAcervo(
            new Livro("O Último Teorema de Fermat", "Simon Singh", "Record", 1997), 3);
        biblioteca.adicionarAcervo(
            new Livro("O Homem que Calculava", "Malba Tahan", "Record", 1938), 3);
        biblioteca.adicionarAcervo(
            new Livro("História da Matemática", "Carl B. Boyer", "Blucher", 1968), 3);
        biblioteca.adicionarAcervo(
            new Livro("Matemática Divertida e Curiosa", "Malba Tahan", "Record", 1940), 3);

        // Informática
        biblioteca.adicionarAcervo(
            new Livro("Clean Code", "Robert C. Martin", "Prentice Hall", 2008), 3);
        biblioteca.adicionarAcervo(
            new Livro("Código Limpo para Java", "Robert C. Martin", "Alta Books", 2011), 3);
        biblioteca.adicionarAcervo(
            new Livro("Engenharia de Software", "Ian Sommerville", "Pearson", 2016), 3);
        biblioteca.adicionarAcervo(
            new Livro("Design Patterns", "Erich Gamma", "Addison-Wesley", 1994), 3);
        biblioteca.adicionarAcervo(
            new Livro("Algoritmos: Teoria e Prática", "Thomas H. Cormen", "MIT Press", 2009), 3);

        // Letras
        biblioteca.adicionarAcervo(new Livro("Dom Casmurro", "Machado de Assis", "Garnier", 1899),
            3);
        biblioteca.adicionarAcervo(
            new Livro("Memórias Póstumas de Brás Cubas", "Machado de Assis", "Garnier", 1881), 3);
        biblioteca.adicionarAcervo(
            new Livro("Grande Sertao: Veredas", "João Guimarães Rosa", "José Olympio", 1956), 3);
        biblioteca.adicionarAcervo(
            new Livro("O Cortiço", "Aluísio Azevedo", "Domínio Público", 1890), 3);
        biblioteca.adicionarAcervo(
            new Livro("Capitães da Areia", "Jorge Amado", "José Olympio", 1937), 3);

        // Física
        biblioteca.adicionarAcervo(
            new Livro("Uma Breve História do Tempo", "Stephen Hawking", "Bantam Books", 1988), 3);
        biblioteca.adicionarAcervo(
            new Livro("O Universo numa Casca de Noz", "Stephen Hawking", "Bantam Books", 2001), 3);
        biblioteca.adicionarAcervo(new Livro("Cosmos", "Carl Sagan", "Random House", 1980), 3);
        biblioteca.adicionarAcervo(new Livro("O Tecido do Cosmos", "Brian Greene", "Vintage", 2004),
            3);
        biblioteca.adicionarAcervo(new Livro("A Ordem do Tempo", "Carlo Rovelli", "Objetiva", 2017),
            3);

        // História
        biblioteca.adicionarAcervo(
            new Livro("Sapiens: Uma Breve História da Humanidade", "Yuval Noah Harari",
                "Harvill Secker", 2011), 3);
        biblioteca.adicionarAcervo(
            new Livro("Homo Deus: Uma Breve História do Amanhã", "Yuval Noah Harari",
                "Harvill Secker", 2015), 3);
        biblioteca.adicionarAcervo(
            new Livro("A História do Mundo para Quem Tem Pressa", "Emma Marriott", "Valentina",
                2014), 3);
        biblioteca.adicionarAcervo(new Livro("História do Brasil", "Boris Fausto", "Edusp", 1995),
            3);
        biblioteca.adicionarAcervo(
            new Livro("As Veias Abertas da América Latina", "Eduardo Galeano", "Siglo XXI", 1971),
            3);

        // Medicina
        biblioteca.adicionarAcervo(
            new Livro("O Imperador de Todos os Males", "Siddhartha Mukherjee", "Scribner", 2010),
            3);
        biblioteca.adicionarAcervo(
            new Livro("O Gene: Uma História Íntima", "Siddhartha Mukherjee", "Scribner", 2016), 3);
        biblioteca.adicionarAcervo(
            new Livro("A História da Medicina", "Paul Strathern", "Zahar", 2016), 3);
        biblioteca.adicionarAcervo(new Livro("O Corpo Fala", "Pierre Weil", "Vozes", 1974), 3);
        biblioteca.adicionarAcervo(
            new Livro("Medicina Interna de Harrison", "J. Larry Jameson", "AMGH", 2018), 3);

    }
}
