package team4.biblioteca;

import team4.usuario.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Biblioteca {
    private final Map<Livro, Integer> acervo = new HashMap<>();
    private final Map<Integer, Usuario> usuarios = new HashMap<>();
    private final List<Emprestimo> emprestimos = new ArrayList<>();

    // --- ACERVO ---

    public void cadastrarLivro(Scanner scanner) {
        System.out.print("Título: ");
        String titulo = scanner.nextLine().trim();
        System.out.print("Autor: ");
        String autor = scanner.nextLine().trim();
        System.out.print("Editora: ");
        String editora = scanner.nextLine().trim();

        System.out.print("Ano de publicação: ");
        int ano = scanner.nextInt();

        Livro livro;
        try {
            livro = new Livro(titulo, autor, editora, ano);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar livro: " + e.getMessage());
            return;
        }

        System.out.print("Quantidade total: ");
        int quantidade = scanner.nextInt();
        if (adicionarAcervo(livro, quantidade)) {
            System.out.println(quantidade + " exemplar(es) de '" + livro.getTitulo() + "' adicionado(s) com sucesso.");
        }
    }

    public Livro pesquisarLivroPorTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) return null;
        for (Livro livro : acervo.keySet()) {
            if (livro.getTitulo().equalsIgnoreCase(titulo.trim())) {
                return livro;
            }
        }
        return null;
    }

    public boolean adicionarAcervo(Livro livro, int quantidade) {
        if (livro == null) {
            System.out.println("Livro inválido.");
            return false;
        }
        if (quantidade <= 0) {
            System.out.println("A quantidade deve ser maior que zero.");
            return false;
        }
        acervo.put(livro, acervo.getOrDefault(livro, 0) + quantidade);
        return true;
    }

    public boolean removerLivro(Livro livro, int quantidade) {
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return false;
        }
        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser maior que zero.");
            return false;
        }
        Integer atual = acervo.get(livro);
        if (atual == null) {
            System.out.println("O livro '" + livro.getTitulo() + "' não foi encontrado no acervo.");
            return false;
        }
        if (atual > quantidade) {
            acervo.put(livro, atual - quantidade);
            System.out.println(quantidade + " exemplar(es) de '" + livro.getTitulo() + "' removido(s).");
            return true;
        } else if (atual == quantidade) {
            acervo.remove(livro);
            System.out.println("Todos os exemplares de '" + livro.getTitulo() + "' foram removidos.");
            return true;
        } else {
            System.out.println("Não há exemplares suficientes de '" + livro.getTitulo() + "'. Apenas " + atual + " em estoque.");
            return false;
        }
    }

    public void listarAcervo() {
        if (acervo.isEmpty()) {
            System.out.println("A biblioteca está vazia.");
            return;
        }
        System.out.println("Acervo da Biblioteca:");
        for (Map.Entry<Livro, Integer> e : acervo.entrySet()) {
            System.out.println("  " + e.getKey().getTitulo() + " - Quantidade: " + e.getValue());
        }
    }

    // --- USUÁRIOS ---

    public void cadastrarUsuario(Scanner scanner) {
        System.out.println("Informe o tipo de usuário (1- Aluno, 2- Aluno Bolsista, 3- Professor, 4- Professor Estagiário): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // consumir quebra

        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine().trim();

        try {
            Usuario usuario = null;
            switch (tipo) {
                case 1 -> {
                    System.out.print("Curso: ");
                    String curso = scanner.nextLine().trim();
                    System.out.print("Período (Manhã/Tarde/Noite): ");
                    String periodo = scanner.nextLine().trim();
                    usuario = new Aluno(nome, email, telefone, curso, periodo);
                }
                case 2 -> {
                    System.out.print("Curso: ");
                    String curso = scanner.nextLine().trim();
                    System.out.print("Período (Manhã/Tarde/Noite): ");
                    String periodo = scanner.nextLine().trim();
                    usuario = new AlunoBolsista(nome, email, telefone, curso, periodo);
                }
                case 3 -> {
                    System.out.print("Departamento: ");
                    String departamento = scanner.nextLine().trim();
                    usuario = new Professor(nome, email, telefone, departamento);
                }
                case 4 -> {
                    System.out.print("Departamento: ");
                    String departamento = scanner.nextLine().trim();
                    usuario = new ProfessorEstagiario(nome, email, telefone, departamento);
                }
                default -> {
                    System.out.println("Tipo inválido!");
                    return;
                }
            }
            if (usuarios.containsKey(usuario.getId())) {
                System.out.println("Já existe usuário com esse ID.");
                return;
            }
            adicionarUsuario(usuario.getId(), usuario);
            System.out.println("Usuário cadastrado: " + usuario.getNome() + " (id=" + usuario.getId() + ")");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    public void adicionarUsuario(int idUsuario, Usuario usuario) {
        usuarios.put(idUsuario, usuario);
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        for (Usuario u : usuarios.values()) {
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
        String novoEmail = scanner.nextLine().trim();
        try {
            usuario.alterarEmail(novoEmail);
            System.out.println("Email atualizado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println("Email inválido: " + e.getMessage());
        }
    }

    public void removerUsuario(Scanner scanner) {
        System.out.print("Informe o ID do usuário a remover: ");
        int id = scanner.nextInt();
        if (usuarios.remove(id) != null) {
            System.out.println("Usuário removido com sucesso.");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    // --- EMPRÉSTIMOS ---

    public void realizarEmprestimo(Scanner scanner) {
        listarUsuarios();
        System.out.print("Informe o ID do usuário: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine();
        Usuario usuario = usuarios.get(idUsuario);

        if (usuario == null || !usuario.podePegarEmprestimo()) {
            System.out.println("Usuário inválido ou não pode pegar empréstimo.");
            return;
        }

        listarAcervo();
        System.out.print("Informe o título do livro: ");
        String titulo = scanner.nextLine();
        Livro livro = pesquisarLivroPorTitulo(titulo);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
        if (!removerLivro(livro, 1)) return;

        LocalDate hoje = LocalDate.now();
        Emprestimo emp = new Emprestimo(usuario, livro, hoje, usuario.getDiasEmprestimo());
        emprestimos.add(emp);
        System.out.println("Empréstimo registrado com sucesso.");
    }

    public void listarEmprestimosAtivos() {
        emprestimos.stream()
            .filter(e -> !e.isDevolvido())
            .forEach(System.out::println);
    }

    public void listarEmprestimosAtrasados() {
        emprestimos.stream()
            .filter(Emprestimo::isAtrasado)
            .forEach(e -> {
                System.out.println(e);
                System.out.println("Dias de atraso: " + e.diasDeAtraso());
                System.out.println("Multa: R$ " + e.valorMulta());
            });
    }

    public void registrarDevolucao(Scanner scanner) {
        listarUsuarios();
        System.out.print("Informe o ID do usuário: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        List<Emprestimo> ativosDoUsuario = emprestimos.stream()
            .filter(e -> e.getUsuario().getId() == id && !e.isDevolvido())
            .collect(Collectors.toList());

        if (ativosDoUsuario.isEmpty()) {
            System.out.println("Nenhum empréstimo ativo encontrad0.");
            return;
        }

        for (int i = 0; i < ativosDoUsuario.size(); i++) {
            System.out.println(i + ". " + ativosDoUsuario.get(i));
        }

        System.out.print("Escolha o número do empréstimo a devolver: ");
        int index = scanner.nextInt();
        if (index < 0 || index >= ativosDoUsuario.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        Emprestimo emp = ativosDoUsuario.get(index);
        if (adicionarAcervo(emp.getLivro(), 1)) {
            try {
                emp.registrarDevolucao(LocalDate.now());
                System.out.println("Devolução registrada com sucesso.");
            } catch (IllegalStateException | IllegalArgumentException ex) {
                System.out.println("Erro ao registrar devolução: " + ex.getMessage());
            }
        } else {
            System.out.println("Falha ao devolver: livro não pertence ao acervo.");
        }
    }
}
