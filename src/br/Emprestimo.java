package br;

import java.time.LocalDate;

public class Emprestimo {

    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
    private boolean devolvido;

    public Emprestimo() {
    }

    public Emprestimo(Usuario usuario, Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista, LocalDate dataDevolucaoReal, boolean devolvido) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoReal = dataDevolucaoReal;
        this.devolvido = devolvido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public void registrarDevolucao(LocalDate dataDevolucao){
        if(!devolvido){
            this.dataDevolucaoReal = dataDevolucao;
            this.devolvido = true;
            livro.devolver();
        } else {
            System.out.println("Livro ja foi devolvido anteriormente.");
        }
    }
    public boolean isAtrasado(){
        if(!devolvido){
            return LocalDate.now().isAfter(this.dataDevolucaoPrevista);
        }
        return dataDevolucaoReal.isAfter(this.dataDevolucaoPrevista);
    }
    public int diasDeAtraso(){
        if(!isAtrasado()) return 0;
        LocalDate dataReferencia = devolvido ? dataDevolucaoReal : LocalDate.now();
        return (int) java.time.temporal.ChronoUnit.DAYS.between(dataDevolucaoPrevista, dataReferencia);
    }
    @Override
    public String toString() {
        return "Empréstimo{" +
                "usuario=" + usuario.getNome() +
                ", livro=" + livro.getTitulo() +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucaoPrevista=" + dataDevolucaoPrevista +
                ", dataDevolucaoReal=" + dataDevolucaoReal +
                ", devolvido=" + devolvido +
                '}';
    }
}
