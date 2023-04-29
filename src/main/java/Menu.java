import java.util.Scanner;
public class Menu {
    Scanner sc1 = new Scanner(System.in);
    public void rosto(){
        title();
        actions();
    }
    public void options(){
        System.out.println("MENU" + "\n" + "1)Criar Árvore" +
                "\n" + "2)Inserir Nó" + "\n" + "3)Deletar Nó" + "\n" + "4)Pesquisar Nó" +
                "\n" + "5)Percursos"+ "\n" +"6)Vizualizar a Árvore" +"\n"+"7)Sair");
    }

    public void percursos(){
        System.out.println("Selecione a opção desejada: " + "\n" + "1)Em ordem" + "\n" + "2)Pós Ordem" + "\n" + "3)Pré Ordem");
    }

    public void actions(){
        System.out.println("Seja bem Vindo!");
        Tree arvore = null;
        int a =1;
        do{
            options();
            System.out.println("Digite a opção desejada");
            a = sc1.nextInt();
            if(arvore == null || a == 1) {
                arvore = new Tree();
                System.out.println("Árvore criada! Selecione a próxima opção!");
            }
            else {
                if (a == 2) {
                    System.out.println("Digite o numeral para inserção: ");
                    int var = Integer.parseInt(sc1.next());
                    try {
                        arvore.inserir(var);
                    } catch(SameKeyException e) {
                        System.out.println(e.getMessage());
                    }
                }
                else if (a==3) {
                    System.out.println("Digite o numeral para deletar: ");
                    int var = Integer.parseInt(sc1.next());
                    boolean testaExcuir = arvore.excluir(var);
                    if(!testaExcuir) System.out.println("Este nó não existe. ");
                    else System.out.println("Nó excluído com sucesso! ");

                }
                else if (a==4){
                    System.out.println("Digite a chave que você quer procurar! ");
                    int var = Integer.parseInt(sc1.next());
                    if(arvore.pesquisar(var) != null) {
                        Node pesquisa = arvore.pesquisar(var);
                        System.out.println("A chave que você procurou está no nó: " + pesquisa.toString());
                    }
                    else
                        System.out.println("A chave que você procurou não está na árvore.");
                }
                else if (a == 5){
                    percursos();
                    int b = sc1.nextInt();
                    if (b ==1 ) System.out.println(arvore.emOrdem());
                    else if(b==2) System.out.println(arvore.posOrdem());
                    else System.out.println(arvore.preOrdem());
                }
                else if (a == 6){
                    DisplayTree.display(arvore);
                }
                else if (a == 7) {
                    System.out.println("Você escolheu sair!");
                    break;
                }
                else {
                    System.out.println("Opção inválida! digite outra!");
                }
            }
        }while(true);
        xavante();
    }

    public void title(){
        System.out.println(" ▄▄▄· ▄▄▄   ▌ ▐·      ▄▄▄  ▄▄▄ .   ▄▄▄·  ▌ ▐·▄▄▌  \n" +
                "▐█ ▀█ ▀▄ █·▪█·█▌ ▄█▀▄ ▀▄ █·▀▄.▀·  ▐█ ▀█ ▪█·█▌██•  \n" +
                "▄█▀▀█ ▐▀▀▄ ▐█▐█•▐█▌.▐▌▐▀▀▄ ▐▀▀▪▄  ▄█▀▀█ ▐█▐█•██ ▪ \n" +
                "▐█▪ ▐▌▐█•█▌ ███ ▐█▌.▐▌▐█•█▌▐█▄▄▌  ▐█▪ ▐▌ ███ ▐█▌ ▄\n" +
                " ▀  ▀ .▀  ▀. ▀   ▀█▄▀▪.▀  ▀ ▀▀▀    ▀  ▀ . ▀  .▀▀▀ \n");
    }

    public void xavante(){
        System.out.println("Até Logo!" + "\n" +"MMMMMMMMMMMMMMMMWKxolokNWWWWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWWNOlcllolclllloONWMMMMWMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMWx;cxkx::ONNWNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNWNKl;xXWWWWWMWXOdlco0WWWMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMK;:NMMMXo:oooooooooooooooooooooooooooooooooooo;.xWWMWOccldOXWWKd:l0WWMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMXko..dNMMMWNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNd.oWWMMd..,...:dKWXo;xNMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMWWWO:cdxo,,:::::::ccccccccc:::::::::ccccc:cccc::::::;..cxkx,.;:'    .oXWk;lKMWWMMMMMMMMMMMM\n" +
                "MMMMMMMMMWWNc;KMWWd        .,,,,,,,'        .',,,,,,,,.         .'''';::'      ,OWK::KMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMWWXl.:xxo'        ':::::::,.       .;::::::::,         ,:::::::'       'OMXc;0MMMMMMMMMMMMM\n" +
                "MMMMMMMMMNd:llc,.          ':::::::,        .;::::c:::,        .,:::::::'        :XMX:;KMMMMMMMMMMMM\n" +
                "MMMMMMMMMx'xWMMNKd,        ':::::::,        .;::::::::,         ,:::::::'        .xMMK;:XMMMMMMMMMMM\n" +
                "MMMMMMMMMO,cKXKOKWXd.      ':::::::,        .;::::::::,        .,:::::::'         oWMMO,lNMMMMMMMMMM\n" +
                "MMMMMMMMMWOllolc:xNWk.     ':::::::,        .;::::::::,         ,:::::::'         cNWMWd,kWMMMMMMMMM\n" +
                "MMMMMMMMMMMMWWWWk,dWWx.    ':::::::,         ',,,,,,,,.         ,:::::::'         cNMWMXc;KMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMN:;XMX:    ':::::::,.   ..,;;;,,,,,,,;;;,'.    .,:::::::'         lWMWMMO'oWMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMX;:NMWx.   ':::::::'..;:c:,...  ':..  ..',::;'..,:::::::'        .xMWMMWNl,0MMMMMMMM\n" +
                "MMMMMMMMMMMMMMMWd,kMMWO.   '::::;,;c:;'.;c.     :o,.     ;:..;;:,,;:::::'        .;c:dNWWO,oWMMMMMMM\n" +
                "MMMMMMMMMMMMMMNd,oKXWM0'   ':::,,cc;.    :o.    :o,.    :l.    .:c;,;:::'        .',.'kMMWc;KMMMMMMM\n" +
                "MMMMMMMMMMMMWNd,;l;cKMk.   ':;':l,        ;o'   :o,.  .ll.       .c:',::'        .;:,.lWMMx'xWMMMMMM\n" +
                "MMMMMMMMMMMMMWXXN0;cNWl    ','cl:,.        ,o,  :o,. .lc.        .;cc,,:'        .:::.;XMMK;lWMMMMMM\n" +
                "MMMMMMMMMWMMWMMMWo'kWO.    .,lc'cdolc:,'..  .o; :o,..o;    .';ccldd,;c,,'        .:::'.kMMWc;KMMMMMM\n" +
                "MMMMMMMMWW0xdkXXo,dWK;     .lc';:::;;:loolclcdkodklokxcccllllcc;;;::';c,.        .;::,.oMMMo'OMMMMMM\n" +
                "MMMMMMMMXo;odc::lOWK:      ,o,,:::'.....,::ccllccc:::cccc::'  ....,::'::         .;::;.cNMMx'xMMMMMM\n" +
                "MMMMMMMWo'OMWWKXWNx'       lc':::' .,;'.':::::,......';::::' .;;. .::,,c.        .;:::.;KMMk'oMMMMMM\n" +
                "MMMMMMMMx,l0XNXOo;.       .l:':::. ':;,',:::::,..'''',:::::' .....,::,'c'        .;:::.,0MMO'oMMMMMM\n" +
                "MMMMMMMMW0c'';,...        .o:':::. ,:'. .;:::c;...''',:::::. .....'::,'c,        .::::.'OMMO'oMMMMMM\n" +
                "MMMMMMMMWOc:x00Od'        .lc':::' .;;'..;::::,..','',;::::' .::,..;c,,c.        .::::.'OWWO'oMMMMMM\n" +
                "MMMMMMMWk;oNMMWWMx.        :o';:::'......;::::;'''''',;:;::' .... '::';c.        .::::.'0WWk'dMMMMMM\n" +
                "MMMMMMMO,lNMWNWW0;         .o:'::::;'';codooolxxxxooxdlooddl;,''',::,'c;.        .::::.;KMMd'kMMMMMM\n" +
                "MMMMMMN:;KWMk;::,.         .;o;':lodoll:;,...:o':o,.:d,..';:cloddlc;':;'.        .;::;.cWMWl,KMMMMMM\n" +
                "MMMMMMk'dMMWl.,;:;.        '';l;;l:,.       :l. :o,. ;o'      ..;c:,c;':'        .:::'.xMMX;cNMMMMMM\n" +
                "MMMMMMo'OMMX;.:::;.        ':,,l:.        .cl.  :o,.  ,o,         'c;';:'        .::;.cXWWk'dMMMMMMM\n" +
                "MMMMMWl,0WW0,.:::;.        '::;,:l;.     .ll.   :o'.   'o;      .::,,:::'        .:;.,0WMWc,0MMMMMMM\n" +
                "MMMMMWl,0WWO'':::;.        '::::,;cc:.  .l:.    :o'.    .o:  .,::,,:::::'        .,.;OWMMO,lWMMMMMMM\n" +
                "MMMMMWo,OWWk..:::;.        '::::::,',:::dl.     ;l'.     ,dl;;,.':::::::'         'lXMMMNl,0MMMMMMMM\n" +
                "MMMMMMx'xMMO'.:::;.        ':::::::,. ..,;;;;;,,::;;,,,,,,,'.  .,:::::::'        .dWMMMMk,dWMMMMMMMM\n" +
                "MMMMMMK;cNMK,.:::,.        ':::::::,        .;::ccc::;'.       .,:::::::'        .kWMMMK;cNMMMMMMMMM\n" +
                "MMMMMMWo,OMNc.;::;.        ':::::::,.       .;::::::::,        .,:::::::'        .OWWMX:;KMMMMMMMMMM\n" +
                "MMMMMMMK;cNMO'.::;.        ':::::::,        .;::::::::'         ,:::::::'        ;XMMNl,OMMMMMMMMMMM\n" +
                "MMMMMMMMx,xWWx..:;.        ':::::::,        .;::::::::'         ,:::::::'       .kWMNl,OWMMMMMMMMMMM\n" +
                "MMMMMMMMNo,kWWOc'.         ':::::::,        .;::::::::'         ,:::::::'      ,kWWXc;OWMMMMMMMMMMMM\n" +
                "MMMMMMMMMNl,kWMW0;         ':::::::,        .;::::::::'         ,:::::::'   .;dXMW0::KMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMNo,xNWW0,        ':::::::,        .;::::::::'         ,::::::;.'okKWMMNx;lXMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMWk;cKWW0:.      .:::::::,.       .;::::::::'         ,::::::.,OWWWWW0c:kWMMWMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMWWKo;oKWN0occ:c:.'::::::,.       .;::::::::'         ,::::;.,OWWMMXd;oXWMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMW0o:lONWMMWMNo.';::::'        .;::::::::'         .,,'''lKWMMXd:c0WMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMWXxcco0NWMMNOl:;,;;.        .;::::::::'        .;clokKWWW0o:lOWMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMN0occoONWWWNXKXNO;        .,::::::,.''.  .'cONWWWMWKxc:oKWMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMWWMN0dlcldONWMMWMXxc,'',cdd:,''.',:xXN0OO0NWWMWN0dlcokXWWWWWWMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMWXkoclloxOXWWWWNNWWMWNKOOkOKNWWMWWMMNKkdllloONMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMWN0xollolloxkO0KKXXXXXXXK0OkxollllldOXWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWKkoc;,'..'',,,,,'...';cdOXWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
    }
}
