import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class DisplayTree {
    public static String display(Tree<Integer, String> t) {
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
        Integer[] keys = t.emLargura().toArray(new Integer[0]);
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


    public static void main(String[] args) {
        Tree<Integer, String> tree = new Tree<>();
        tree.inserir(8, "8");
        tree.inserir(4, "4");
        tree.inserir(354, "354");
        tree.inserir(2345, "2345");
        tree.inserir(34, "34");
        tree.inserir(2, "2");
        tree.inserir(1, "1");
        try {
            Path file = Paths.get("index.html");
            Files.write(file, Collections.singleton(display(tree)), StandardCharsets.UTF_8);
            try {
                Desktop.getDesktop().browse(file.toUri());

            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
