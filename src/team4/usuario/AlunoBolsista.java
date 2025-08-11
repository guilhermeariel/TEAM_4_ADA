package team4.usuario;

public class AlunoBolsista extends Aluno {

    // Alterado: remoção do parâmetro id (herdado de Usuario)
    public AlunoBolsista(String nome, String email, String telefone, String curso, int periodo) {
        super(nome, email, telefone, curso, periodo);
    }
    //Sobreposição
    @Override
    public double calcularMulta(int diasAtraso) {
        return diasAtraso * 0.5;
    }

    @Override
    public int getDiasEmprestimo() {
        return 10;
    }
}
