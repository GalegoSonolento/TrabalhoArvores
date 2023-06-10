import java.time.LocalDate;

public class Pessoa{
    private int CPF, RG;
    private LocalDate dataNascimento;
    private String nome, cidadeNatal;

    public Pessoa(int CPF, int RG, LocalDate dataNascimento, String nome, String cidadeNatal) {
        if (nome == null || cidadeNatal == null || dataNascimento == null) {
            throw new RuntimeException("Null pointers in Pessoa constructor");
        }
        this.CPF = CPF;
        this.RG = RG;
        this.dataNascimento = dataNascimento;
        this.nome = nome;
        this.cidadeNatal = cidadeNatal;
    }

    public Pessoa(int CPF, int RG, int ano, int mes, int dia, String nome, String cidadeNatal) {
        this.CPF = CPF;
        this.RG = RG;
        this.dataNascimento = LocalDate.of(ano, mes, dia);
        this.nome = nome;
        this.cidadeNatal = cidadeNatal;
    }

    public int getCPF() {
        return CPF;
    }

    public int getRG() {
        return RG;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getCidadeNatal() {
        return cidadeNatal;
    }

    // todo: lembrar de fzr o display com dia-mes-ano
    @Override
    public String toString() {
        return "Pessoa{" +
                "CPF=" + CPF +
                ", RG=" + RG +
                ", dataNascimento=" + dataNascimento +
                ", nome='" + nome + '\'' +
                ", cidadeNatal='" + cidadeNatal + '\'' +
                '}';
    }
}
