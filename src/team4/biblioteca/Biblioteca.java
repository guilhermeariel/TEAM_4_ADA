package team4.biblioteca;

import team4.usuario.*;

import java.time.LocalDate;
import java.util.*;

public class Biblioteca {
    private Map<Livro, Integer> acervo;
    private Map<Integer, Usuario> usuarios;
    private List<Emprestimo> emprestimos;

    // Cadastrar livro
    public void adicionarAcervo(Scanner scanner) {
        this.acervo = new HashMap<>();

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        System.out.print("Editora: ");
        String editora = scanner.nextLine();

        System.out.print("Ano de publicação: ");
        int ano = scanner.nextInt();

        Livro livro = new Livro(titulo, autor, editora, ano);

        System.out.print("Quantidade total: ");
        int quantidade = scanner.nextInt();
        if (adicionarLivro(livro, quantidade))
            System.out.println(quantidade + " exemplar(es) de '" + livro.getTitulo() + "' adicionado(s) com sucesso.");
    }

    // Listar todos os livros disponíveis
    public void listarAcervo() {
        if (this.acervo.isEmpty()) {
            System.out.println("A biblioteca está vazia.");
        } else {
            System.out.println("Acervo da Biblioteca:");
            for (Map.Entry<Livro, Integer> entry : this.acervo.entrySet()) {
                System.out.println("  " + entry.getKey().getTitulo() + " - Quantidade: " + entry.getValue());
            }
        }
    }

    public Livro pesquisarLivroPorTitulo(String titulo) {
        for (Livro livro : this.acervo.keySet()) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro; // Retorna o objeto Livro assim que o encontra
            }
        }
        return null; // Retorna null se nenhum livro for encontrado
    }

    public boolean adicionarLivro(Livro livro, int quantidade) {
        if (quantidade > 0) {
            this.acervo.put(livro, this.acervo.getOrDefault(livro, 0) + quantidade);
            System.out.println(quantidade + " exemplar(es) de '" + livro.getTitulo() + "' adicionado(s) com sucesso.");
            return true;
        } else {
            System.out.println("A quantidade deve ser maior que zero.");
            return false;
        }
    }

    public boolean removerLivro(Livro livro, int quantidade) {
        if (this.acervo.containsKey(livro)) {
            int quantidadeAtual = this.acervo.getOrDefault(livro, 0)
                    ;
            if (quantidadeAtual > quantidade) {
                this.acervo.put(livro, quantidadeAtual - quantidade);
                System.out.println(quantidade + " exemplar(es) de '" + livro.getTitulo() + "' removido(s).");
                return true;
            } else if (quantidadeAtual == quantidade) {
                this.acervo.remove(livro);
                System.out.println("Todos os exemplares de '" + livro.getTitulo() + "' foram removidos.");
                return true;
            } else {
                System.out.println("Não há exemplares suficientes de '" + livro.getTitulo() + "'. Apenas " + quantidadeAtual + " em estoque.");
                return false;
            }
        } else {
            System.out.println("O livro '" + livro.getTitulo() + "' não foi encontrado no acervo.");
            return false;
        }
    }

    // Cadastrar usuários de diferentes tipos
    public void cadastrarUsuario(Scanner scanner) {
        this.usuarios = new HashMap<>();

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

            this.usuarios.put(usuario.getId(), usuario);
            System.out.println("Usuário cadastrado com sucesso!");

        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    // Listar todos os usuários
    public void listarUsuarios() {
        if (this.usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        for (Usuario u : this.usuarios.values()) {
            System.out.println("ID: " + u.getId());
            System.out.println("Nome: " + u.getNome());
            System.out.println("Email: " + u.getEmail());

            if (u instanceof Aluno aluno) {
                System.out.println("Tipo: " + (aluno instanceof AlunoBolsista ? "Aluno Bolsista" : "Aluno"));
                System.out.println("Curso: " + aluno.getCurso());
                System.out.println("Período: " + aluno.getPeriodo());
            } else if (u instanceof Professor professor) {
                System.out.println("Tipo: " + (professor instanceof ProfessorEstagiario ? "Professor Estagiário" : "Professor"));
                System.out.println("Departamento: " + professor.getDepartamento());
            } else {
                System.out.println("Tipo: Usuário desconhecido.");
            }

            System.out.println("--------------------------------");
        }
    }

    // Editar dados do usuário
    public void editarUsuario(Scanner scanner) {
        System.out.print("Informe o ID do usuário a editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Usuario usuario = this.usuarios.get(id);
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
        if (this.usuarios.remove(id) != null) {
            System.out.println("Usuário removido com sucesso.");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    // Realizar empréstimo
    public void realizarEmprestimo(Scanner scanner) {
        this.emprestimos = new ArrayList<>();

        System.out.print("Informe o ID do usuário: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine();
        Usuario usuario = this.usuarios.get(idUsuario);

        if (usuario == null || !usuario.podePegarEmprestimo()) {
            System.out.println("Usuário inválido ou não pode pegar empréstimo.");
            return;
        }

        listarAcervo();
        System.out.print("Informe o título do livro: ");
        String titulo = scanner.nextLine();

        Livro livro = pesquisarLivroPorTitulo(titulo);

        if (!removerLivro(livro, 1)) {
            return;
        }

        LocalDate hoje = LocalDate.now();
        Emprestimo emprestimo = new Emprestimo(usuario, livro, hoje, usuario.getDiasEmprestimo());
        this.emprestimos.add(emprestimo);
        System.out.println("Empréstimo registrado com sucesso.");
    }

    // Registrar devolução
    public void registrarDevolucao(Scanner scanner) {
        System.out.print("Informe o ID do usuário: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        List<Emprestimo> emprestimosUsuario = this.emprestimos.stream()
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
        if (adicionarLivro(emprestimo.getLivro(), 1)) {
            emprestimo.registrarDevolucao(LocalDate.now());
            System.out.println("Devolução registrada com sucesso.");
        } else {
            System.out.println("Livro não pertence ao acervo.");
        }
    }

    // Listar todos os empréstimos ativos
    public void listarEmprestimosAtivos() {
        this.emprestimos.stream()
                .filter(e -> !e.isDevolvido())
                .forEach(System.out::println);
    }

    // Listar empréstimos atrasados
    public void listarEmprestimosAtrasados() {
        this.emprestimos.stream()
                .filter(Emprestimo::isAtrasado)
                .forEach(e -> {
                    System.out.println(e);
                    System.out.println("Dias de atraso: " + e.diasDeAtraso());
                    System.out.println("Multa: R$ " + e.getUsuario().calcularMulta(e.diasDeAtraso()));
                });
    }
}