package tests;

import org.junit.jupiter.api.Test;
import src.SameKeyException;
import src.Tree;

import static org.junit.jupiter.api.Assertions.*;

public class TreeTest {
    @Test
    void inserirTesteErroSameKey() {
        Tree arvore = new Tree(1);
        try{
           arvore.inserir(1);
       }catch (SameKeyException e){
           assertTrue(e.getMessage().contentEquals("Já existe uma chave com esse número."));
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
        Tree tree = new Tree();
        tree.inserir(15);
        tree.inserir(10);
        tree.inserir(20);
        tree.inserir(8);
        tree.inserir(16);
        tree.inserir(12);
        tree.inserir(25);
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
        Tree tree = new Tree();
        tree.inserir(15);
        tree.inserir(10);
        tree.inserir(20);
        tree.inserir(8);
        tree.inserir(16);
        tree.inserir(12);
        tree.inserir(25);
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
        Tree tree = new Tree();
        tree.inserir(15);
        tree.inserir(10);
        tree.inserir(20);
        tree.inserir(8);
        tree.inserir(16);
        tree.inserir(12);
        tree.inserir(25);
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
}
