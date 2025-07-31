package br;

public class AlunoBolsista extends Aluno {

    public AlunoBolsista(String nome, int id, String email, String telefone, String curso, int periodo) {
        super(nome, id, email, telefone, curso, periodo);
    }

    @Override
    public double calcularMulta(int diasAtraso) {
        // Bolsista paga 50% da multa normal
        return diasAtraso * 0.5;
    }

    @Override
    public int getDiasEmprestimo() {
        // Bolsista tem mais dias de empréstimo
        return 10;
    }
}
