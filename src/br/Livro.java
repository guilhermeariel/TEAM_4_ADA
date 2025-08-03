package br;

public class Livro {

    private String titulo;
    private String autor;
    private String editora;
    private int anoPublicacao;
    private int quantidadeTotal;
    private int quantidadeDisponivel;

    public Livro(String titulo, String autor, String editora, int anoPublicacao, int quantidadeTotal, int quantidadeDisponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeTotal = quantidadeTotal;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public Livro() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public int getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(int quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }
    public boolean emprestar(){
        if(quantidadeDisponivel > 0){
            quantidadeDisponivel--;
            return true;
        }
        return false;
    }
    public void devolver(){
        if(quantidadeDisponivel < quantidadeTotal){
            quantidadeDisponivel++;
        }
    }

    @Override
    public String toString(){
        return "Livro{"+
                "Titulo = " +titulo+'\''+
                ", autor = " +autor+ '\''+
                ", editora = '" +editora + '\''+
                ", anoPublicação = '" + anoPublicacao +
                ", quantidade Total = " + quantidadeTotal+
                ", quantidade Disponivel = "+ quantidadeDisponivel+
                '}';

    }
}
