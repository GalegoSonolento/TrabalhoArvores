package tests;

import org.junit.jupiter.api.Test;
import src.SameKeyException;
import src.Tree;
import src.Tree;

import static org.junit.jupiter.api.Assertions.*;

public class TreeTest {
    @Test
    void inserirTesteErroSameKey() {
        Tree arvoreUwU = new Tree(1);
        try{
           arvoreUwU.inserir(1);
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
}
