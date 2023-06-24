import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa{
    private final long CPF, RG;
    private final LocalDate dataNascimento;
    private final String nome;
    private final String cidadeNatal;

    public Pessoa(long CPF, long RG, LocalDate dataNascimento, String nome, String cidadeNatal) {
        if (nome == null || cidadeNatal == null || dataNascimento == null) {
            throw new RuntimeException("Null pointers in Pessoa constructor");
        }
        this.CPF = CPF;
        this.RG = RG;
        this.dataNascimento = dataNascimento;
        this.nome = nome;
        this.cidadeNatal = cidadeNatal;
    }

    public Pessoa(long CPF, long RG, int ano, int mes, int dia, String nome, String cidadeNatal) {
        this.CPF = CPF;
        this.RG = RG;
        this.dataNascimento = LocalDate.of(ano, mes, dia);
        this.nome = nome;
        this.cidadeNatal = cidadeNatal;
    }

    public long getCPF() {
        return CPF;
    }

    public long getRG() {
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


    @Override
    public String toString() {
        return "Nome: '" + nome + "'\n" +
                "RG: " + RG + '\n' +
                "Data Nascimento: " + dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + '\n' +
                "CPF: " + CPF + '\n' +
                "Cidade Natal: '" + cidadeNatal + "'\n" +
                "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-";
    }
}
