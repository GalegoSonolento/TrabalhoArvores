import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import java.util.ArrayList;
import java.util.Queue;

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
        /*
        System.out.println("       " + tree.getRoot().getKey() + "\n" +
                "  " + tree.getRoot().getLeftSon().getKey() + "-------" + tree.getRoot().getRightSon().getKey() + "\n" +
                tree.getRoot().getLeftSon().getLeftSon().getKey() + "---" + tree.getRoot().getLeftSon().getRightSon().getKey() + "   " + tree.getRoot().getRightSon().getLeftSon().getKey() + "---" + tree.getRoot().getRightSon().getRightSon().getKey());
        */
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

   // @Test
    void testeRebalanceamentoDireitaSimplesSemFilho() {
        Tree tree = new Tree();
        tree.inserir(15);
        tree.inserir(10);
        tree.inserir(12);
        // System.out.println(Arrays.toString(tree.emLargura()));
        // root check
        assertNotNull(tree.getRoot());
        assertEquals(12, tree.getRoot().getKey());
        assertEquals(2, tree.getRoot().getHeight());
        assertEquals(0, tree.getRoot().getCb());

        // Ve se os filhos que devem existir existem e se os que n devem existir n existem
        assertNotNull(tree.getRoot().getLeftSon());
        assertNotNull(tree.getRoot().getRightSon());
        // Ve se os filhos tem os pais certos
        assertEquals(tree.getRoot(), tree.getRoot().getLeftSon().getDaddy());
        assertEquals(tree.getRoot(), tree.getRoot().getRightSon().getDaddy());
        // Ve se as keys estão certas
        assertEquals(10, tree.getRoot().getLeftSon().getKey());
        assertEquals(15, tree.getRoot().getRightSon().getKey());
        assertEquals(1, tree.getRoot().getLeftSon().getHeight());
        assertEquals(1, tree.getRoot().getRightSon().getHeight());
        assertEquals(0, tree.getRoot().getLeftSon().getCb());
        assertEquals(0, tree.getRoot().getRightSon().getCb());
    }
    @Test
    void testeRebalanceamentoDireitaSimplesSemFilho2() {
        Tree tree = new Tree();
        tree.inserir(15);
        tree.inserir(10);
        tree.inserir(8);
        // System.out.println(Arrays.toString(tree.emLargura()));
        // root check
        assertNotNull(tree.getRoot());
        assertEquals(10, tree.getRoot().getKey());
        assertEquals(2, tree.getRoot().getHeight());
        assertEquals(0, tree.getRoot().getCb());

        // Ve se os filhos que devem existir existem e se os que n devem existir n existem
        assertNotNull(tree.getRoot().getLeftSon());
        assertNotNull(tree.getRoot().getRightSon());
        // Ve se os filhos tem os pais certos
        assertEquals(tree.getRoot(), tree.getRoot().getLeftSon().getDaddy());
        assertEquals(tree.getRoot(), tree.getRoot().getRightSon().getDaddy());
        // Ve se as keys estão certas
        assertEquals(8, tree.getRoot().getLeftSon().getKey());
        assertEquals(15, tree.getRoot().getRightSon().getKey());
        assertEquals(1, tree.getRoot().getLeftSon().getHeight());
        assertEquals(1, tree.getRoot().getRightSon().getHeight());
        assertEquals(0, tree.getRoot().getLeftSon().getCb());
        assertEquals(0, tree.getRoot().getRightSon().getCb());
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
        // System.out.println(Arrays.toString(tree.emLargura()));
        // root check
        assertNotNull(tree.getRoot());
        assertEquals(10, tree.getRoot().getKey());
        assertEquals(3, tree.getRoot().getHeight());
        // Ve se os filhos que devem existir existem e se os que n devem existir n existem
        assertNotNull(tree.getRoot().getLeftSon());
        assertNotNull(tree.getRoot().getRightSon());
        // Ve se os filhos tem os pais certos
        assertEquals(tree.getRoot(), tree.getRoot().getLeftSon().getDaddy());
        assertEquals(tree.getRoot(), tree.getRoot().getRightSon().getDaddy());
        // Ve se as keys estão certas
        assertEquals(8, tree.getRoot().getLeftSon().getKey());
        assertEquals(15, tree.getRoot().getRightSon().getKey());
        assertEquals(2, tree.getRoot().getLeftSon().getHeight());
        assertEquals(2, tree.getRoot().getRightSon().getHeight());
        assertEquals(1, tree.getRoot().getLeftSon().getCb());
        assertEquals(0, tree.getRoot().getRightSon().getCb());
        // Ve se os filhos que devem existir existem e se os que n devem existir n existem
        assertNotNull(tree.getRoot().getLeftSon().getLeftSon());
        assertNull(tree.getRoot().getLeftSon().getRightSon());
        assertNotNull(tree.getRoot().getRightSon().getRightSon());
        assertNotNull(tree.getRoot().getRightSon().getLeftSon());
        // Ve se os filhos tem os pais certos
        assertEquals(tree.getRoot().getLeftSon(), tree.getRoot().getLeftSon().getLeftSon().getDaddy());
        assertEquals(tree.getRoot().getRightSon(), tree.getRoot().getRightSon().getRightSon().getDaddy());
        assertEquals(tree.getRoot().getRightSon(), tree.getRoot().getRightSon().getLeftSon().getDaddy());
        // Ve se as keys estão certas
        assertEquals(1, tree.getRoot().getLeftSon().getLeftSon().getKey());
        assertEquals(16, tree.getRoot().getRightSon().getRightSon().getKey());
        assertEquals(12, tree.getRoot().getRightSon().getLeftSon().getKey());
        assertEquals(1, tree.getRoot().getLeftSon().getLeftSon().getHeight());
        assertEquals(1, tree.getRoot().getRightSon().getRightSon().getHeight());
        assertEquals(1, tree.getRoot().getRightSon().getLeftSon().getHeight());
        assertEquals(0, tree.getRoot().getLeftSon().getLeftSon().getCb());
        assertEquals(0, tree.getRoot().getRightSon().getLeftSon().getCb());
        assertEquals(0, tree.getRoot().getRightSon().getRightSon().getCb());
    }

    @Test
    void testeRebalanceamentoEsquerdaSimplesSemFilho() {
        Tree tree = new Tree();
        tree.inserir(8);
        tree.inserir(10);
        tree.inserir(15);
        // System.out.println(Arrays.toString(tree.emLargura()));
        // root check
        assertNotNull(tree.getRoot());
        assertEquals(10, tree.getRoot().getKey());
        assertEquals(2, tree.getRoot().getHeight());
        assertEquals(0, tree.getRoot().getCb());

        // Ve se os filhos que devem existir existem e se os que n devem existir n existem
        assertNotNull(tree.getRoot().getLeftSon());
        assertNotNull(tree.getRoot().getRightSon());
        // Ve se os filhos tem os pais certos
        assertEquals(tree.getRoot(), tree.getRoot().getLeftSon().getDaddy());
        assertEquals(tree.getRoot(), tree.getRoot().getRightSon().getDaddy());
        // Ve se as keys estão certas
        assertEquals(8, tree.getRoot().getLeftSon().getKey());
        assertEquals(15, tree.getRoot().getRightSon().getKey());
        assertEquals(1, tree.getRoot().getLeftSon().getHeight());
        assertEquals(1, tree.getRoot().getRightSon().getHeight());
        assertEquals(0, tree.getRoot().getLeftSon().getCb());
        assertEquals(0, tree.getRoot().getRightSon().getCb());
    }

    @Test
    void testeRebalanceamentoEsquerdaSimplesComFilho() {
        Tree tree = new Tree();
        tree.inserir(8);
        tree.inserir(10);
        tree.inserir(6);
        tree.inserir(9);
        tree.inserir(16);
        tree.inserir(17);
        // System.out.println(Arrays.toString(tree.emLargura()));
        // root check
        assertNotNull(tree.getRoot());
        assertEquals(10, tree.getRoot().getKey());
        assertEquals(3, tree.getRoot().getHeight());
        assertEquals(0, tree.getRoot().getCb());

        // Ve se os filhos que devem existir existem e se os que n devem existir n existem
        assertNotNull(tree.getRoot().getLeftSon());
        assertNotNull(tree.getRoot().getRightSon());
        // Ve se os filhos tem os pais certos
        assertEquals(tree.getRoot(), tree.getRoot().getLeftSon().getDaddy());
        assertEquals(tree.getRoot(), tree.getRoot().getRightSon().getDaddy());
        // Ve se as keys estão certas
        assertEquals(8, tree.getRoot().getLeftSon().getKey());
        assertEquals(16, tree.getRoot().getRightSon().getKey());
        assertEquals(2, tree.getRoot().getLeftSon().getHeight());
        assertEquals(2, tree.getRoot().getRightSon().getHeight());
        assertEquals(0, tree.getRoot().getLeftSon().getCb());
        assertEquals(-1, tree.getRoot().getRightSon().getCb());
        // Ve se os filhos que devem existir existem e se os que n devem existir n existem
        assertNotNull(tree.getRoot().getLeftSon().getLeftSon());
        assertNotNull(tree.getRoot().getLeftSon().getRightSon());
        assertNotNull(tree.getRoot().getRightSon().getRightSon());
        assertNull(tree.getRoot().getRightSon().getLeftSon());
        // Ve se os filhos tem os pais certos
        System.out.println(tree.getRoot().getLeftSon());
        System.out.println(tree.getRoot().getLeftSon().getLeftSon());
        assertEquals(tree.getRoot().getLeftSon(), tree.getRoot().getLeftSon().getLeftSon().getDaddy());
        assertEquals(tree.getRoot().getRightSon(), tree.getRoot().getRightSon().getRightSon().getDaddy());
        // Ve se as keys estão certas
        assertEquals(6, tree.getRoot().getLeftSon().getLeftSon().getKey());
        assertEquals(9, tree.getRoot().getLeftSon().getRightSon().getKey());
        assertEquals(17, tree.getRoot().getRightSon().getRightSon().getKey());
        assertEquals(1, tree.getRoot().getLeftSon().getLeftSon().getHeight());
        assertEquals(1, tree.getRoot().getLeftSon().getRightSon().getHeight());
        assertEquals(1, tree.getRoot().getRightSon().getRightSon().getHeight());
        assertEquals(0, tree.getRoot().getLeftSon().getLeftSon().getCb());
        assertEquals(0, tree.getRoot().getLeftSon().getRightSon().getCb());
        assertEquals(0, tree.getRoot().getRightSon().getRightSon().getCb());
    }

    @Test
    void testeRebalanceamentoDireitaDuploSemFilho() {
        Tree tree = new Tree();
        tree.inserir(15);
        tree.inserir(8);
        tree.inserir(10);
        assertNotNull(tree.getRoot());
        assertEquals(10, tree.getRoot().getKey());
        assertEquals(2, tree.getRoot().getHeight());
        assertEquals(0, tree.getRoot().getCb());

        assertNotNull(tree.getRoot().getLeftSon());
        assertNotNull(tree.getRoot().getRightSon());

        assertEquals(tree.getRoot(), tree.getRoot().getRightSon().getDaddy());
        assertEquals(tree.getRoot(), tree.getRoot().getLeftSon().getDaddy());
        assertEquals(15, tree.getRoot().getRightSon().getKey());
        assertEquals(8, tree.getRoot().getLeftSon().getKey());
        assertEquals(1, tree.getRoot().getLeftSon().getHeight());
        assertEquals(1, tree.getRoot().getRightSon().getHeight());
        assertEquals(0, tree.getRoot().getLeftSon().getCb());
        assertEquals(0, tree.getRoot().getRightSon().getCb());
    }

    // @Test
    void testeRebalanceamentoDireitaDuploComFilho() {
        Tree tree = new Tree();
        tree.inserir(15);
        tree.inserir(10);
        tree.inserir(16);
        tree.inserir(12);
        tree.inserir(8);
        tree.inserir(13);
        // System.out.println(Arrays.toString(tree.emLargura()));
        // root check
        assertNotNull(tree.getRoot());
        assertEquals(12, tree.getRoot().getKey());
        assertEquals(3, tree.getRoot().getHeight());
        assertEquals(0, tree.getRoot().getCb());

        // Ve se os filhos que devem existir existem e se os que n devem existir n existem
        assertNotNull(tree.getRoot().getLeftSon());
        assertNotNull(tree.getRoot().getRightSon());

        // Ve se os filhos tem os pais certos
        assertEquals(tree.getRoot(), tree.getRoot().getLeftSon().getDaddy());
        assertEquals(tree.getRoot(), tree.getRoot().getRightSon().getDaddy());

        // Ve se as propriedades estão certas
        assertEquals(10, tree.getRoot().getLeftSon().getKey());
        assertEquals(15, tree.getRoot().getRightSon().getKey());

        assertEquals(2, tree.getRoot().getLeftSon().getHeight());
        assertEquals(2, tree.getRoot().getRightSon().getHeight());

        assertEquals(1, tree.getRoot().getLeftSon().getCb());
        assertEquals(0, tree.getRoot().getRightSon().getCb());

        // Ve se os filhos que devem existir existem e se os que n devem existir n existem
        assertNotNull(tree.getRoot().getLeftSon().getLeftSon());
        assertNull(tree.getRoot().getLeftSon().getRightSon());

        assertNotNull(tree.getRoot().getRightSon().getRightSon());
        assertNotNull(tree.getRoot().getRightSon().getLeftSon());

        // Ve se os filhos tem os pais certos
        assertEquals(tree.getRoot().getLeftSon(), tree.getRoot().getLeftSon().getLeftSon().getDaddy());
        assertEquals(tree.getRoot().getRightSon(), tree.getRoot().getRightSon().getRightSon().getDaddy());
        assertEquals(tree.getRoot().getRightSon(), tree.getRoot().getRightSon().getLeftSon().getDaddy());

        // Ve se as keys estão certas
        assertEquals(8, tree.getRoot().getLeftSon().getLeftSon().getKey());
        assertEquals(16, tree.getRoot().getRightSon().getRightSon().getKey());
        assertEquals(13, tree.getRoot().getRightSon().getLeftSon().getKey());

        assertEquals(1, tree.getRoot().getLeftSon().getLeftSon().getHeight());
        assertEquals(1, tree.getRoot().getRightSon().getRightSon().getHeight());
        assertEquals(1, tree.getRoot().getRightSon().getLeftSon().getHeight());

        assertEquals(0, tree.getRoot().getLeftSon().getLeftSon().getCb());
        assertEquals(0, tree.getRoot().getRightSon().getRightSon().getCb());
        assertEquals(0, tree.getRoot().getRightSon().getLeftSon().getCb());
    }

    @Test
    void testeRebalanceamentoEsquerdaDuploSemFilho() {
        Tree tree = new Tree();
        tree.inserir(8);
        tree.inserir(16);
        tree.inserir(10);

        // root check
        assertNotNull(tree.getRoot());
        assertEquals(10, tree.getRoot().getKey());
        assertEquals(2, tree.getRoot().getHeight());
        assertEquals(0, tree.getRoot().getCb());

        // segundo nivel
        assertNotNull(tree.getRoot().getLeftSon());
        assertNotNull(tree.getRoot().getRightSon());

        assertEquals(tree.getRoot(), tree.getRoot().getRightSon().getDaddy());
        assertEquals(tree.getRoot(), tree.getRoot().getLeftSon().getDaddy());

        assertEquals(16, tree.getRoot().getRightSon().getKey());
        assertEquals(8, tree.getRoot().getLeftSon().getKey());

        assertEquals(1, tree.getRoot().getRightSon().getHeight());
        assertEquals(1, tree.getRoot().getLeftSon().getHeight());

        assertEquals(0, tree.getRoot().getLeftSon().getCb());
        assertEquals(0, tree.getRoot().getRightSon().getCb());
    }

    @Test
    void testeRebalanceamentoEsquerdaDuploComFilho() {
        Tree tree = new Tree();
        tree.inserir(8);
        tree.inserir(11);
        tree.inserir(6);
        tree.inserir(10);
        tree.inserir(16);
        tree.inserir(9);
        System.out.println(Arrays.toString(tree.emLargura()));
        // root check
        assertNotNull(tree.getRoot());
        assertEquals(10, tree.getRoot().getKey());
        assertEquals(3, tree.getRoot().getHeight());
        assertEquals(0, tree.getRoot().getCb());

        // Ve se os filhos que devem existir existem e se os que n devem existir n existem
        assertNotNull(tree.getRoot().getLeftSon());
        assertNotNull(tree.getRoot().getRightSon());

        // Ve se os filhos tem os pais certos
        assertEquals(tree.getRoot(), tree.getRoot().getLeftSon().getDaddy());
        assertEquals(tree.getRoot(), tree.getRoot().getRightSon().getDaddy());

        // Ve se as keys estão certas
        assertEquals(8, tree.getRoot().getLeftSon().getKey());
        assertEquals(11, tree.getRoot().getRightSon().getKey());

        assertEquals(2, tree.getRoot().getLeftSon().getHeight());
        assertEquals(2, tree.getRoot().getRightSon().getHeight());

        assertEquals(0, tree.getRoot().getLeftSon().getCb());
        assertEquals(-1, tree.getRoot().getRightSon().getCb());

        // Ve se os filhos que devem existir existem e se os que n devem existir n existem
        assertNotNull(tree.getRoot().getLeftSon().getLeftSon());
        assertNotNull(tree.getRoot().getLeftSon().getRightSon());
        assertNotNull(tree.getRoot().getRightSon().getRightSon());
        assertNull(tree.getRoot().getRightSon().getLeftSon());

        // Ve se os filhos tem os pais certos
        assertEquals(tree.getRoot().getLeftSon(), tree.getRoot().getLeftSon().getLeftSon().getDaddy());
        assertEquals(tree.getRoot().getRightSon(), tree.getRoot().getRightSon().getRightSon().getDaddy());

        // Ve se as keys estão certas
        assertEquals(6, tree.getRoot().getLeftSon().getLeftSon().getKey());
        assertEquals(9, tree.getRoot().getLeftSon().getRightSon().getKey());
        assertEquals(16, tree.getRoot().getRightSon().getRightSon().getKey());

        assertEquals(1, tree.getRoot().getLeftSon().getLeftSon().getHeight());
        assertEquals(1, tree.getRoot().getLeftSon().getRightSon().getHeight());
        assertEquals(1, tree.getRoot().getRightSon().getRightSon().getHeight());

        assertEquals(0, tree.getRoot().getLeftSon().getLeftSon().getCb());
        assertEquals(0, tree.getRoot().getLeftSon().getRightSon().getCb());
        assertEquals(0, tree.getRoot().getRightSon().getRightSon().getCb());
    }

    @Test
    void exclusaoNoSemFilhos(){
        assertTrue(tree.excluir(25));
        assertNull(tree.pesquisar(25));
        assertTrue(tree.excluir(8));
        assertNull(tree.pesquisar(8));
    }

    @Test
    void tentativaDeExclusaoDeNaoExistente() {
        assertFalse(tree.excluir(56));
    }

    @Test
    void exclusaoDeRootSemFilhos() {
        Tree teste = new Tree(5);
        assertEquals(5, teste.getRoot().getKey());
        assertTrue(teste.excluir(5));
        assertNull(teste.getRoot());
    }

    @Test
    void exclusaoNodeComFilhoEsquerda() {
        Tree tree = new Tree(15);
        tree.inserir(10);
        tree.inserir(16);
        tree.inserir(2);
        assertTrue(tree.excluir(10));
        assertEquals(2, tree.getRoot().getLeftSon().getKey());
    }

    @Test
    void exclusaoNodeComFilhoDireita() {
        Tree tree = new Tree(15);
        tree.inserir(10);
        tree.inserir(16);
        tree.inserir(12);
        assertTrue(tree.excluir(10));
        assertEquals(12, tree.getRoot().getLeftSon().getKey());
    }

    @Test
    void exclusaoNodeComFilhoDoisFilhos() {
        assertTrue(tree.excluir(10));

        // root check
        assertNotNull(tree.getRoot());
        assertNull(tree.getRoot().getDaddy());
        assertEquals(15, tree.getRoot().getKey());
        assertEquals(3, tree.getRoot().getHeight());
        assertEquals(0, tree.getRoot().getCb());

        // verificando os filhos

        assertNotNull(tree.getRoot().getRightSon());
        assertEquals(20, tree.getRoot().getRightSon().getKey());
        assertEquals(2, tree.getRoot().getRightSon().getHeight());
        assertEquals(0, tree.getRoot().getRightSon().getCb());

        assertNotNull(tree.getRoot().getLeftSon());
        assertEquals(8, tree.getRoot().getLeftSon().getKey());
        assertEquals(2, tree.getRoot().getLeftSon().getHeight());
        assertEquals(-1, tree.getRoot().getLeftSon().getCb());

        assertEquals(tree.getRoot(), tree.getRoot().getLeftSon().getDaddy());
        assertEquals(tree.getRoot(), tree.getRoot().getRightSon().getDaddy());

        // verifica netos
        assertNull(tree.getRoot().getLeftSon().getLeftSon());

        assertNotNull(tree.getRoot().getLeftSon().getRightSon());
        assertEquals(12, tree.getRoot().getLeftSon().getRightSon().getKey());
        assertEquals(1, tree.getRoot().getLeftSon().getRightSon().getHeight());
        assertEquals(0, tree.getRoot().getLeftSon().getRightSon().getCb());

        assertNotNull(tree.getRoot().getRightSon().getLeftSon());
        assertEquals(16, tree.getRoot().getRightSon().getLeftSon().getKey());
        assertEquals(1, tree.getRoot().getRightSon().getLeftSon().getHeight());
        assertEquals(0, tree.getRoot().getRightSon().getLeftSon().getCb());

        assertNotNull(tree.getRoot().getRightSon().getRightSon());
        assertEquals(25, tree.getRoot().getRightSon().getRightSon().getKey());
        assertEquals(1, tree.getRoot().getRightSon().getRightSon().getHeight());
        assertEquals(0, tree.getRoot().getRightSon().getRightSon().getCb());

        assertEquals(tree.getRoot().getLeftSon(), tree.getRoot().getLeftSon().getRightSon().getDaddy());
        assertEquals(tree.getRoot().getRightSon(), tree.getRoot().getRightSon().getLeftSon().getDaddy());
        assertEquals(tree.getRoot().getRightSon(), tree.getRoot().getRightSon().getRightSon().getDaddy());
    }

    @Test
    void exclusaoNodeComFilhoDoisFilhosRaiz() {
        assertTrue(tree.excluir(15));

        // root check
        assertNotNull(tree.getRoot());
        assertNull(tree.getRoot().getDaddy());
        assertEquals(12, tree.getRoot().getKey());
        assertEquals(3, tree.getRoot().getHeight());
        assertEquals(0, tree.getRoot().getCb());

        // verificando os filhos
        assertNotNull(tree.getRoot().getRightSon());
        assertEquals(20, tree.getRoot().getRightSon().getKey());
        assertEquals(2, tree.getRoot().getRightSon().getHeight());
        assertEquals(0, tree.getRoot().getRightSon().getCb());

        assertNotNull(tree.getRoot().getLeftSon());
        assertEquals(10, tree.getRoot().getLeftSon().getKey());
        assertEquals(2, tree.getRoot().getLeftSon().getHeight());
        assertEquals(1, tree.getRoot().getLeftSon().getCb());

        assertEquals(tree.getRoot(), tree.getRoot().getLeftSon().getDaddy());
        assertEquals(tree.getRoot(), tree.getRoot().getRightSon().getDaddy());

        // verifica netos
        assertNotNull(tree.getRoot().getLeftSon().getLeftSon());
        assertEquals(8, tree.getRoot().getLeftSon().getLeftSon().getKey());
        assertEquals(1, tree.getRoot().getLeftSon().getLeftSon().getHeight());
        assertEquals(0, tree.getRoot().getLeftSon().getLeftSon().getCb());

        assertNull(tree.getRoot().getLeftSon().getRightSon());

        assertNotNull(tree.getRoot().getRightSon().getLeftSon());
        assertEquals(16, tree.getRoot().getRightSon().getLeftSon().getKey());
        assertEquals(1, tree.getRoot().getRightSon().getLeftSon().getHeight());
        assertEquals(0, tree.getRoot().getRightSon().getLeftSon().getCb());

        assertNotNull(tree.getRoot().getRightSon().getRightSon());
        assertEquals(25, tree.getRoot().getRightSon().getRightSon().getKey());
        assertEquals(1, tree.getRoot().getRightSon().getRightSon().getHeight());
        assertEquals(0, tree.getRoot().getRightSon().getRightSon().getCb());

        assertEquals(tree.getRoot().getLeftSon(), tree.getRoot().getLeftSon().getLeftSon().getDaddy());
        assertEquals(tree.getRoot().getRightSon(), tree.getRoot().getRightSon().getLeftSon().getDaddy());
        assertEquals(tree.getRoot().getRightSon(), tree.getRoot().getRightSon().getRightSon().getDaddy());
    }
    @Test
    void exclusaoNodeSemFilhoComRebalanceamento() {
        Tree tree = new Tree(15);
        tree.inserir(10);
        tree.inserir(16);
        tree.inserir(8);
        assertTrue(tree.excluir(16));

        // root check
        assertNotNull(tree.getRoot());
        assertNull(tree.getRoot().getDaddy());
        assertEquals(10, tree.getRoot().getKey());
        assertEquals(2, tree.getRoot().getHeight());
        assertEquals(0, tree.getRoot().getCb());

        // verificando os filhos
        assertNotNull(tree.getRoot().getRightSon());
        assertEquals(15, tree.getRoot().getRightSon().getKey());
        assertEquals(1, tree.getRoot().getRightSon().getHeight());
        assertEquals(0, tree.getRoot().getRightSon().getCb());

        assertNotNull(tree.getRoot().getLeftSon());
        assertEquals(8, tree.getRoot().getLeftSon().getKey());
        assertEquals(1, tree.getRoot().getLeftSon().getHeight());
        assertEquals(0, tree.getRoot().getLeftSon().getCb());

        assertEquals(tree.getRoot(), tree.getRoot().getLeftSon().getDaddy());
        assertEquals(tree.getRoot(), tree.getRoot().getRightSon().getDaddy());

    }

    @Test
    void exclusaoNodeComFilhoUmDireitaComRebalanceamento() {
        Tree tree = new Tree(15);
        tree.inserir(10);
        tree.inserir(16);
        tree.inserir(8);
        tree.inserir(11);
        tree.inserir(20);
        tree.inserir(4);

        assertTrue(tree.excluir(16));

        // root check
        assertNotNull(tree.getRoot());
        assertNull(tree.getRoot().getDaddy());
        assertEquals(10, tree.getRoot().getKey());
        assertEquals(3, tree.getRoot().getHeight());
        assertEquals(0, tree.getRoot().getCb());

        // verificando os filhos
        assertNotNull(tree.getRoot().getRightSon());
        assertEquals(15, tree.getRoot().getRightSon().getKey());
        assertEquals(2, tree.getRoot().getRightSon().getHeight());
        assertEquals(0, tree.getRoot().getRightSon().getCb());

        assertNotNull(tree.getRoot().getLeftSon());
        assertEquals(8, tree.getRoot().getLeftSon().getKey());
        assertEquals(2, tree.getRoot().getLeftSon().getHeight());
        assertEquals(1, tree.getRoot().getLeftSon().getCb());

        assertEquals(tree.getRoot(), tree.getRoot().getLeftSon().getDaddy());
        assertEquals(tree.getRoot(), tree.getRoot().getRightSon().getDaddy());

        // verifica netos
        assertNotNull(tree.getRoot().getLeftSon().getLeftSon());
        assertEquals(4, tree.getRoot().getLeftSon().getLeftSon().getKey());
        assertEquals(1, tree.getRoot().getLeftSon().getLeftSon().getHeight());
        assertEquals(0, tree.getRoot().getLeftSon().getLeftSon().getCb());

        assertNull(tree.getRoot().getLeftSon().getRightSon());

        assertNotNull(tree.getRoot().getRightSon().getLeftSon());
        assertEquals(11, tree.getRoot().getRightSon().getLeftSon().getKey());
        assertEquals(1, tree.getRoot().getRightSon().getLeftSon().getHeight());
        assertEquals(0, tree.getRoot().getRightSon().getLeftSon().getCb());

        assertNotNull(tree.getRoot().getRightSon().getRightSon());
        assertEquals(20, tree.getRoot().getRightSon().getRightSon().getKey());
        assertEquals(1, tree.getRoot().getRightSon().getRightSon().getHeight());
        assertEquals(0, tree.getRoot().getRightSon().getRightSon().getCb());

        assertEquals(tree.getRoot().getLeftSon(), tree.getRoot().getLeftSon().getLeftSon().getDaddy());
        assertEquals(tree.getRoot().getRightSon(), tree.getRoot().getRightSon().getLeftSon().getDaddy());
        System.out.println(tree.getRoot().getRightSon());
        System.out.println(tree.getRoot().getRightSon().getRightSon());
        assertEquals(tree.getRoot().getRightSon(), tree.getRoot().getRightSon().getRightSon().getDaddy());
    }

    @Test
    void testePreOrdem() {
        ArrayList<Node> list = tree.preOrdem();
        assertEquals(15, list.remove(0).getKey());
        assertEquals(10, list.remove(0).getKey());
        assertEquals(8, list.remove(0).getKey());
        assertEquals(12, list.remove(0).getKey());
        assertEquals(20, list.remove(0).getKey());
        assertEquals(16, list.remove(0).getKey());
        assertEquals(25, list.remove(0).getKey());
    }

    @Test
    void testePosOrdem() {
        ArrayList<Node> list = tree.posOrdem();
        assertEquals(8, list.remove(0).getKey());
        assertEquals(12, list.remove(0).getKey());
        assertEquals(10, list.remove(0).getKey());
        assertEquals(16, list.remove(0).getKey());
        assertEquals(25, list.remove(0).getKey());
        assertEquals(20, list.remove(0).getKey());
        assertEquals(15, list.remove(0).getKey());
    }

    @Test
    void testeEmOrdem() {
        ArrayList<Node> list = tree.emOrdem();
        assertEquals(8, list.remove(0).getKey());
        assertEquals(10, list.remove(0).getKey());
        assertEquals(12, list.remove(0).getKey());
        assertEquals(15, list.remove(0).getKey());
        assertEquals(16, list.remove(0).getKey());
        assertEquals(20, list.remove(0).getKey());
        assertEquals(25, list.remove(0).getKey());
    }
}
