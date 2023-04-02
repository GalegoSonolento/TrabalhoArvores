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
        System.out.println(tree.getRoot());
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
    }
}
