import java.util.Scanner;
public class Menu {
    Scanner sc1 = new Scanner(System.in);
    public void rosto(State state){
        title();
        actions(state);
    }
    public void options(){
        System.out.println("""
                MENU
                1) Consultar pelo CPF
                2) Consultar pessoas cujos nomes comecem com uma string
                3) Consultar pessoas cuja data de nascimento está entre um intervalo de tempo
                4) Sair""");
    }

    public void actions(State state){
        System.out.println("Seja bem vindo!");
        int op = 0;
        do{
            options();
            System.out.println("Digite a opção desejada: ");
            op = sc1.nextInt();
            switch (op) {
                case 1:
                    System.out.println("numero bonito");

                default:
                    System.out.println("numero invalido, voce sera bootado");
            }

        }while(op == 6);
        //xavante();
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
