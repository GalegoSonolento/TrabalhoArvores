import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;

// todo: encontrar o erro do ChronoLocalDate para indexação da árvore
public class State {
    private final ArrayList<Pessoa> pessoasLista;
    private final Tree<Long, Pessoa> indexCPF;
    private final Tree<String, ArrayList<Pessoa>> indexNome;
    private final Tree<ChronoLocalDate, ArrayList<Pessoa>> indexDataNascimento;

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
        ArrayList<Pessoa> nomes = indexNome.pesquisar(fulano.getNome());
        if (nomes == null)
            return false;
        nomes.remove(fulano);
        if (nomes.isEmpty())
            indexNome.excluir(fulano.getNome());

        ArrayList<Pessoa> nascidos = indexDataNascimento.pesquisar(fulano.getDataNascimento());
        assert nascidos != null;
        nascidos.remove(fulano);
        pessoasLista.remove(fulano);
        if (nascidos.isEmpty())
            indexDataNascimento.excluir(fulano.getDataNascimento());

        indexCPF.excluir(fulano.getCPF());
        return true;
    }

    public Pessoa pesquisaPorCPF(long CPF) {
        return indexCPF.pesquisar(CPF);
    }

    public ArrayList<Pessoa> pesquisaPorNome(String nome) {
        return indexNome.pesquisar(nome);
    }

    public ArrayList<Pessoa> pesquisaPorDataDeNascimento(LocalDate date) {
        return indexDataNascimento.pesquisar(date);
    }

    /**
     * pesquisa por todas as pessoas nacidas em um dia especifico
     * @param dia
     * @param mes
     * @param ano
     * @return lista de pessoas
     */
    public ArrayList<Pessoa> pesquisaPorDataDeNascimento(int dia, int mes, int ano) {
        return pesquisaPorDataDeNascimento(LocalDate.of(ano, mes, dia));
    }

    /**
     * Esta função é case-sensitive, isso quer dizer que "Marcos" não começa com "m", mas sim com "M"
     * Esta função não garante nenhuma ordem dos elementos retornados
     * @param pes Pedaço de um nome para pesquisar
     * @return lista de todas as pessoas cujo nome começa com o pes
     */
    public ArrayList<Pessoa> pesquisaPorNomeParcial(String pes) {
        String end = pes.substring(0, pes.length()-1) + (char)(pes.charAt(pes.length()-1) + 1);
        ArrayList<Pessoa> resultado = new ArrayList<>();
        indexNome.filter(pes, end).forEach(resultado::addAll);
        return resultado;
    }

    /**
     * Procura no index de datas de nascimento todas as pessoas nascidas no intervalo [Inicial, Final[
     * <br />
     * Nota-se que o intervalo não inclui pessoas nascidas na data final!
     * @param diaInicial dia do mes que começa o intervalo
     * @param mesInicial mês do ano que começa o intervalo
     * @param anoInicial ano em que começa o intervalo
     * @param diaFinal dia final do intervalo
     * @param mesFinal mês final do intervalo
     * @param anoFinal ano final do intervalo
     * @return Todas as pessoas nascidas no intervalo [Inicial, Final[
     */
    public ArrayList<Pessoa> pesquisaPorDataIntervalo(int diaInicial, int mesInicial, int anoInicial, int diaFinal, int mesFinal, int anoFinal) {
        return pesquisaPorDataIntervalo(LocalDate.of(anoInicial, mesInicial, diaInicial), LocalDate.of(anoFinal, mesFinal, diaFinal));
    }

    public ArrayList<Pessoa> pesquisaPorDataIntervalo(LocalDate dataInicial, LocalDate dataFinal) {
        ArrayList<Pessoa> resultado = new ArrayList<>();
        indexDataNascimento.filter(dataInicial, dataFinal).forEach(resultado::addAll);
        return resultado;
    }
}
