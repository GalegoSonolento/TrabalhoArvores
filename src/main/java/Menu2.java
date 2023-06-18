import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class Menu2 implements ActionListener {

    private JLabel label;
    private JFrame frame;
    private JButton button;
    private JPanel panel;
    Scanner sc1 = new Scanner(System.in);

    public Menu2(){
        frame = new JFrame();

        button = new JButton("Click me");
        button.addActionListener(this);


        label = new JLabel("NUmber of clicks: 0");


        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Our GUI");
        frame.pack();
        frame.setVisible(true);
    }

    public void rosto(State state){
        title();
        actions(state);
    }
    public void options(){
        System.out.println("""
                MENU\s
                1) Consultar pelo CPF
                2) Consultar pessoas cujos nomes comecem com uma string
                3) Consultar pessoas cuja data de nascimento está entre um intervalo de tempo
                4) Sair""");
    }

    public void actions(State state){
        System.out.println("Seja bem vindo!");
        int op = 0;
        do {
            options();
            System.out.println("Digite a opção desejada: ");
            op = sc1.nextInt();
            switch (op) {
                case 1 -> {
                    System.out.println("Digite o CPF da pessoas que deseja procurar!");
                    long cpf = sc1.nextLong();
                    Pessoa daddy = state.pesquisaPorCPF(cpf);
                    if (daddy == null)
                        System.out.println("Pessoa não encontrada");
                    else
                        System.out.println(daddy);
                }
                case 2 -> {
                    System.out.println("Digite o nome da Pessoa que você deseja procurar");
                    String nome = sc1.next();
                    ArrayList<Pessoa> mommy = state.pesquisaPorNomeParcial(nome);
                    if (mommy.isEmpty())
                        System.out.println("Pessoa não encontrada");
                    else
                        System.out.println(mommy);
                }
                case 3 -> {
                    System.out.println("Digite a data inicial (dd/mm/yyyy)");
                    String moxe = sc1.next();
                    LocalDate data = LocalDate.parse(moxe, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    System.out.println("Digite a data final (dd/mm/yyyy)");
                    String schula = sc1.next();
                    LocalDate dataf = LocalDate.parse(schula, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    ArrayList<Pessoa> marcio = state.pesquisaPorDataIntervalo(data, dataf);
                    if (marcio.isEmpty())
                        System.out.println("Pessoa não encontrada");
                    else
                        System.out.println(marcio);
                }
                case 4 -> {
                    System.out.println("Obrigado por usar nosso sistema!");
                    xavante();
                }
                default -> {
                    System.out.println("Valor inválido, digite novamente...");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception ignored) {
                    }
                }
            }

        } while (op!=4);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void title(){
        System.out.println(" ▄▄▄· ▄▄▄   ▌ ▐·      ▄▄▄  ▄▄▄ .   ▄▄▄·  ▌ ▐·▄▄▌  \n" +
                "▐█ ▀█ ▀▄ █·▪█·█▌ ▄█▀▄ ▀▄ █·▀▄.▀·  ▐█ ▀█ ▪█·█▌██•  \n" +
                "▄█▀▀█ ▐▀▀▄ ▐█▐█•▐█▌.▐▌▐▀▀▄ ▐▀▀▪▄  ▄█▀▀█ ▐█▐█•██ ▪ \n" +
                "▐█▪ ▐▌▐█•█▌ ███ ▐█▌.▐▌▐█•█▌▐█▄▄▌  ▐█▪ ▐▌ ███ ▐█▌ ▄\n" +
                " ▀  ▀ .▀  ▀. ▀   ▀█▄▀▪.▀  ▀ ▀▀▀    ▀  ▀ . ▀  .▀▀▀ \n");
    }

    public void xavante(){
        System.out.println("""
                Até Logo!
                MMMMMMMMMMMMMMMMWKxolokNWWWWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWWNOlcllolclllloONWMMMMWMMMMMMMMMMMMMMMM
                MMMMMMMMMMMMMMMWx;cxkx::ONNWNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNWNKl;xXWWWWWMWXOdlco0WWWMMMMMMMMMMMMMMMMM
                MMMMMMMMMMMMMMMK;:NMMMXo:oooooooooooooooooooooooooooooooooooo;.xWWMWOccldOXWWKd:l0WWMMMMMMMMMMMMMMMM
                MMMMMMMMMMMMMXko..dNMMMWNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNd.oWWMMd..,...:dKWXo;xNMMMMMMMMMMMMMMMM
                MMMMMMMMMWWWO:cdxo,,:::::::ccccccccc:::::::::ccccc:cccc::::::;..cxkx,.;:'    .oXWk;lKMWWMMMMMMMMMMMM
                MMMMMMMMMWWNc;KMWWd        .,,,,,,,'        .',,,,,,,,.         .'''';::'      ,OWK::KMMMMMMMMMMMMMM
                MMMMMMMMMWWXl.:xxo'        ':::::::,.       .;::::::::,         ,:::::::'       'OMXc;0MMMMMMMMMMMMM
                MMMMMMMMMNd:llc,.          ':::::::,        .;::::c:::,        .,:::::::'        :XMX:;KMMMMMMMMMMMM
                MMMMMMMMMx'xWMMNKd,        ':::::::,        .;::::::::,         ,:::::::'        .xMMK;:XMMMMMMMMMMM
                MMMMMMMMMO,cKXKOKWXd.      ':::::::,        .;::::::::,        .,:::::::'         oWMMO,lNMMMMMMMMMM
                MMMMMMMMMWOllolc:xNWk.     ':::::::,        .;::::::::,         ,:::::::'         cNWMWd,kWMMMMMMMMM
                MMMMMMMMMMMMWWWWk,dWWx.    ':::::::,         ',,,,,,,,.         ,:::::::'         cNMWMXc;KMMMMMMMMM
                MMMMMMMMMMMMMMMMN:;XMX:    ':::::::,.   ..,;;;,,,,,,,;;;,'.    .,:::::::'         lWMWMMO'oWMMMMMMMM
                MMMMMMMMMMMMMMMMX;:NMWx.   ':::::::'..;:c:,...  ':..  ..',::;'..,:::::::'        .xMWMMWNl,0MMMMMMMM
                MMMMMMMMMMMMMMMWd,kMMWO.   '::::;,;c:;'.;c.     :o,.     ;:..;;:,,;:::::'        .;c:dNWWO,oWMMMMMMM
                MMMMMMMMMMMMMMNd,oKXWM0'   ':::,,cc;.    :o.    :o,.    :l.    .:c;,;:::'        .',.'kMMWc;KMMMMMMM
                MMMMMMMMMMMMWNd,;l;cKMk.   ':;':l,        ;o'   :o,.  .ll.       .c:',::'        .;:,.lWMMx'xWMMMMMM
                MMMMMMMMMMMMMWXXN0;cNWl    ','cl:,.        ,o,  :o,. .lc.        .;cc,,:'        .:::.;XMMK;lWMMMMMM
                MMMMMMMMMWMMWMMMWo'kWO.    .,lc'cdolc:,'..  .o; :o,..o;    .';ccldd,;c,,'        .:::'.kMMWc;KMMMMMM
                MMMMMMMMWW0xdkXXo,dWK;     .lc';:::;;:loolclcdkodklokxcccllllcc;;;::';c,.        .;::,.oMMMo'OMMMMMM
                MMMMMMMMXo;odc::lOWK:      ,o,,:::'.....,::ccllccc:::cccc::'  ....,::'::         .;::;.cNMMx'xMMMMMM
                MMMMMMMWo'OMWWKXWNx'       lc':::' .,;'.':::::,......';::::' .;;. .::,,c.        .;:::.;KMMk'oMMMMMM
                MMMMMMMMx,l0XNXOo;.       .l:':::. ':;,',:::::,..'''',:::::' .....,::,'c'        .;:::.,0MMO'oMMMMMM
                MMMMMMMMW0c'';,...        .o:':::. ,:'. .;:::c;...''',:::::. .....'::,'c,        .::::.'OMMO'oMMMMMM
                MMMMMMMMWOc:x00Od'        .lc':::' .;;'..;::::,..','',;::::' .::,..;c,,c.        .::::.'OWWO'oMMMMMM
                MMMMMMMWk;oNMMWWMx.        :o';:::'......;::::;'''''',;:;::' .... '::';c.        .::::.'0WWk'dMMMMMM
                MMMMMMMO,lNMWNWW0;         .o:'::::;'';codooolxxxxooxdlooddl;,''',::,'c;.        .::::.;KMMd'kMMMMMM
                MMMMMMN:;KWMk;::,.         .;o;':lodoll:;,...:o':o,.:d,..';:cloddlc;':;'.        .;::;.cWMWl,KMMMMMM
                MMMMMMk'dMMWl.,;:;.        '';l;;l:,.       :l. :o,. ;o'      ..;c:,c;':'        .:::'.xMMX;cNMMMMMM
                MMMMMMo'OMMX;.:::;.        ':,,l:.        .cl.  :o,.  ,o,         'c;';:'        .::;.cXWWk'dMMMMMMM
                MMMMMWl,0WW0,.:::;.        '::;,:l;.     .ll.   :o'.   'o;      .::,,:::'        .:;.,0WMWc,0MMMMMMM
                MMMMMWl,0WWO'':::;.        '::::,;cc:.  .l:.    :o'.    .o:  .,::,,:::::'        .,.;OWMMO,lWMMMMMMM
                MMMMMWo,OWWk..:::;.        '::::::,',:::dl.     ;l'.     ,dl;;,.':::::::'         'lXMMMNl,0MMMMMMMM
                MMMMMMx'xMMO'.:::;.        ':::::::,. ..,;;;;;,,::;;,,,,,,,'.  .,:::::::'        .dWMMMMk,dWMMMMMMMM
                MMMMMMK;cNMK,.:::,.        ':::::::,        .;::ccc::;'.       .,:::::::'        .kWMMMK;cNMMMMMMMMM
                MMMMMMWo,OMNc.;::;.        ':::::::,.       .;::::::::,        .,:::::::'        .OWWMX:;KMMMMMMMMMM
                MMMMMMMK;cNMO'.::;.        ':::::::,        .;::::::::'         ,:::::::'        ;XMMNl,OMMMMMMMMMMM
                MMMMMMMMx,xWWx..:;.        ':::::::,        .;::::::::'         ,:::::::'       .kWMNl,OWMMMMMMMMMMM
                MMMMMMMMNo,kWWOc'.         ':::::::,        .;::::::::'         ,:::::::'      ,kWWXc;OWMMMMMMMMMMMM
                MMMMMMMMMNl,kWMW0;         ':::::::,        .;::::::::'         ,:::::::'   .;dXMW0::KMMMMMMMMMMMMMM
                MMMMMMMMMMNo,xNWW0,        ':::::::,        .;::::::::'         ,::::::;.'okKWMMNx;lXMMMMMMMMMMMMMMM
                MMMMMMMMMMMWk;cKWW0:.      .:::::::,.       .;::::::::'         ,::::::.,OWWWWW0c:kWMMWMMMMMMMMMMMMM
                MMMMMMMMMMMWWKo;oKWN0occ:c:.'::::::,.       .;::::::::'         ,::::;.,OWWMMXd;oXWMMMMMMMMMMMMMMMMM
                MMMMMMMMMMMMMMW0o:lONWMMWMNo.';::::'        .;::::::::'         .,,'''lKWMMXd:c0WMMMMMMMMMMMMMMMMMMM
                MMMMMMMMMMMMMMMMWXxcco0NWMMNOl:;,;;.        .;::::::::'        .;clokKWWW0o:lOWMMMMMMMMMMMMMMMMMMMMM
                MMMMMMMMMMMMMMMMMMMN0occoONWWWNXKXNO;        .,::::::,.''.  .'cONWWWMWKxc:oKWMMMMMMMMMMMMMMMMMMMMMMM
                MMMMMMMMMMMMMMMMMMMWWMN0dlcldONWMMWMXxc,'',cdd:,''.',:xXN0OO0NWWMWN0dlcokXWWWWWWMMMMMMMMMMMMMMMMMMMM
                MMMMMMMMMMMMMMMMMMMMMMMMMWXkoclloxOXWWWWNNWWMWNKOOkOKNWWMWWMMNKkdllloONMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
                MMMMMMMMMMMMMMMMMMMMMMMMMMMMMWN0xollolloxkO0KKXXXXXXXK0OkxollllldOXWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
                MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWKkoc;,'..'',,,,,'...';cdOXWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM""");
    }
}
