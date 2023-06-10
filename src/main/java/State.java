import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;

// todo: encontrar o erro do ChronoLocalDate para indexação da árvore
public class State {
    private ArrayList<Pessoa> pessoasLista;
    private Tree<Long, Pessoa> indexCPF;
    private Tree<String, Pessoa> indexNome;
    private Tree<ChronoLocalDate, Pessoa> indexDataNascimento;

    public State() {
        this.pessoasLista = new ArrayList<>();
        this.indexCPF = new Tree<>();
        this.indexNome = new Tree<>();
        this.indexDataNascimento = new Tree<>();
    }

    // todo adiciona uma pessoa; lembrar de atualizar os indexes!!!!
    public boolean adicionaPessoa(Pessoa fulano) {
        pessoasLista.add(fulano);
        indexCPF.inserir(fulano.getCPF(), fulano);
        indexNome.inserir(fulano.getNome(), fulano);
        indexDataNascimento.inserir(fulano.getDataNascimento(), fulano);
        return false;
    }

    public boolean retiraPessoa(Pessoa fulano) {
        pessoasLista.remove(fulano);
        indexCPF.excluir(fulano.getCPF());
        indexNome.excluir(fulano.getNome());
        indexDataNascimento.excluir(fulano.getDataNascimento());
        return false;
    }

    public Pessoa pesquisaPorCPF(long CPF) {
        return indexCPF.pesquisar(CPF);
    }

    // observação: o caminhamento que retorna a lista é o EmOrdem - usando a versão antiga, de recursão
    public ArrayList<Node> pesquisaPorNome(String pes) {
        return null;
    }
}
