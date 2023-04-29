import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

public class DisplayTree {
    public static String string(Tree t) {
        if (t == null)
            return null;
        StringBuilder out = new StringBuilder("""
                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="UTF-8">
                    <meta http-equiv="X-UA-Compatible" content="IE=edge">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Document</title>
                    <style>
                        html {
                            padding: 0;
                            margin: 0;
                            background-color: black;            color: white;        }

                        body,
                        html,
                        table,
                        tbody {
                            width: 100%;
                            padding: 0%;
                            margin: 0;
                        }

                        tr {
                            display: flex;
                            justify-content: space-around;
                            border-color: aliceblue;
                            padding: 1vh;
                            border-bottom-style: dashed;
                        }

                        th {
                            display: flex;
                            justify-content: space-around;
                            width: 100%;
                            border-color: aliceblue;
                            padding: 1vh;
                            border-right-style: groove;
                        }
                        th:first-child {
                            border-left-style: groove;
                        }
                    </style>
                </head>

                <body>
                    <table>
                        <tbody>
                """);
        Integer[] keys = t.emLargura();
        out.append("            <tr>\n");
        out.append("                <th>\n");
        out.append("                    ");
        out.append(keys[0]);
        out.append("\n                </th>\n");
        out.append("            </tr>\n");
        for (int i = 1; i < t.getHeight(); i++) {
            out.append("            <tr>\n");
            for (int j = 0; j < (1 << i); j++) {
                out.append("                <th>\n");
                out.append("                    ");
                out.append(keys[j+(1<<i)-1]);
                out.append("\n                </th>\n");
            }
            out.append("            </tr>\n");
        }
        out.append("""
                        </tbody>
                    </table>
                </body>

                </html>""");
        return out.toString();
    }

    public static void display(Tree tree) {
        try {
            Path file = Paths.get("index.html");
            Files.write(file, Collections.singleton(string(tree)), StandardCharsets.UTF_8);
            try {
                System.out.println("sa");
                Desktop.getDesktop().browse(file.toUri());
                System.out.println("pe");

            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.inserir(8);
        tree.inserir(4);
        tree.inserir(354);
        tree.inserir(2345);
        tree.inserir(34);
        tree.inserir(2);
        tree.inserir(1);

        display(tree);
    }

}
