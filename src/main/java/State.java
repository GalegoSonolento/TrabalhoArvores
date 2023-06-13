import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;

// todo: encontrar o erro do ChronoLocalDate para indexação da árvore
public class State {
    private ArrayList<Pessoa> pessoasLista;
    private Tree<Long, Pessoa> indexCPF;
    private Tree<String, ArrayList<Pessoa>> indexNome;
    private Tree<ChronoLocalDate, ArrayList<Pessoa>> indexDataNascimento;

    public State() {
        this.pessoasLista = new ArrayList<>();
        this.indexCPF = new Tree<>();
        this.indexNome = new Tree<>();
        this.indexDataNascimento = new Tree<>();
    }

    // todo adiciona uma pessoa; lembrar de atualizar os indexes!!!!
    public void adicionaPessoa(Pessoa fulano) {
        pessoasLista.add(fulano);
        indexCPF.inserir(fulano.getCPF(), fulano);
        ArrayList<Pessoa> nomes = indexNome.pesquisar(fulano.getNome());
        if (nomes == null) {
            nomes = new ArrayList<>();
            indexNome.inserir(fulano.getNome(), nomes);
        }
        nomes.add(fulano);
        ArrayList<Pessoa> nascidos = indexDataNascimento.pesquisar(fulano.getDataNascimento());
        if (nascidos == null) {
            nascidos = new ArrayList<>();
            indexDataNascimento.inserir(fulano.getDataNascimento(), nascidos);
        }
        nascidos.add(fulano);
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
