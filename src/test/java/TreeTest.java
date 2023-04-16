import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TreeTest {
    Tree tree = new Tree();
    @BeforeEach
    void arvorePreMontada() {
        tree.inserir(15);
        tree.inserir(10);
        tree.inserir(20);
        tree.inserir(8);
        tree.inserir(16);
        tree.inserir(12);
        tree.inserir(25);
    }
    @Test
    void inserirTesteErroSameKey() {
        Tree arvore = new Tree(1);
        try{
           arvore.inserir(1);
       }catch (SameKeyException e){
           assertEquals("Já existe uma chave com o número 1.", e.getMessage());
       }
    }
    @Test
    void inserirCorreto() {
        Tree tree = new Tree();
        assertNull(tree.getRoot());
    }

    @Test
    void testeHeight() {
        Tree tree = new Tree(1);
        assertEquals(1, tree.getHeight());
    }
    @Test
    void testeLocalCerto() {
        // teste simples de uma árvore binária de 3 níveis para ver se todos os int são colocados nos lugares certos,
        // nessa primeira versão o desenvolvedor precisa ver o resultado do teste e a árvore necessariamente precisa ser
        // cheia e com a ordem correta de inserção.
        assertEquals(15, tree.getRoot().getKey());
        assertEquals(10, tree.getRoot().getLeftSon().getKey());
        assertEquals(20, tree.getRoot().getRightSon().getKey());
        assertEquals(8, tree.getRoot().getLeftSon().getLeftSon().getKey());
        assertEquals(12, tree.getRoot().getLeftSon().getRightSon().getKey());
        assertEquals(16, tree.getRoot().getRightSon().getLeftSon().getKey());
        assertEquals(25, tree.getRoot().getRightSon().getRightSon().getKey());
        System.out.println("       " + tree.getRoot().getKey() + "\n" +
                "  " + tree.getRoot().getLeftSon().getKey() + "-------" + tree.getRoot().getRightSon().getKey() + "\n" +
                tree.getRoot().getLeftSon().getLeftSon().getKey() + "---" + tree.getRoot().getLeftSon().getRightSon().getKey() + "   " + tree.getRoot().getRightSon().getLeftSon().getKey() + "---" + tree.getRoot().getRightSon().getRightSon().getKey());
    }

    @Test
    void testeHeightComHeightControl() {
        assertEquals(3, tree.getHeight());
        assertEquals(2, tree.getRoot().getLeftSon().getHeight());
        assertEquals(2, tree.getRoot().getRightSon().getHeight());
        assertEquals(1, tree.getRoot().getLeftSon().getLeftSon().getHeight());
        assertEquals(1, tree.getRoot().getLeftSon().getRightSon().getHeight());
        assertEquals(1, tree.getRoot().getRightSon().getLeftSon().getHeight());
        assertEquals(1, tree.getRoot().getRightSon().getRightSon().getHeight());
    }

    @Test
    void testeEmLargura() {
        assertArrayEquals(new Integer[]{15,10,20,8,12,16,25}, tree.emLargura());
    }

    @Test
    void testeEmLarguraBanguela() {
        Tree tree = new Tree();
        tree.inserir(15);
        tree.inserir(10);
        tree.inserir(20);
        tree.inserir(8);
        // tree.inserir(16); arvore fica com um null no meio
        tree.inserir(12);
        tree.inserir(25);
        assertArrayEquals(new Integer[]{15,10,20,8,12,null,25}, tree.emLargura());
    }

    @Test
    void testeDePesquisaElementoExiste() {
        assertEquals(8, tree.pesquisar(8).getKey());
        assertEquals(16, tree.pesquisar(16).getKey());
        assertNull(tree.pesquisar(55));
    }

    @Test
    void testeRebalanceamentoDireitaSimplesSemFilho() {
        Tree tree = new Tree();
        tree.inserir(15);
        tree.inserir(10);
        tree.inserir(8);
        System.out.println(Arrays.toString(tree.emLargura()));
    }

    @Test
    void testeRebalanceamentoDireitaSimplesComFilho() {
        Tree tree = new Tree();
        tree.inserir(15);
        tree.inserir(10);
        tree.inserir(16);
        tree.inserir(12);
        tree.inserir(8);
        tree.inserir(1);
        System.out.println(Arrays.toString(tree.emLargura()));
    }

    @Test
    void testeRebalanceamentoEsquerdaSimplesSemFilho() {
        Tree tree = new Tree();
        tree.inserir(8);
        tree.inserir(10);
        tree.inserir(15);
        System.out.println(Arrays.toString(tree.emLargura()));
    }

    @Test
    void testeRebalanceamentoEsquerdaSimplesComFilho() {
        Tree tree = new Tree();
        tree.inserir(8);
        tree.inserir(10);
        tree.inserir(6);
        tree.inserir(15);
        tree.inserir(16);
        System.out.println(Arrays.toString(tree.emLargura()));
    }

    @Test
    void testeRebalanceamentoDireitaDuploSemFilho() {
        Tree tree = new Tree();
        tree.inserir(15);
        tree.inserir(10);
        tree.inserir(8);
        System.out.println(Arrays.toString(tree.emLargura()));
    }
}
