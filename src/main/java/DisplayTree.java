public class DisplayTree {
    public static String display(Tree t) {
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


    public static void main(String[] args) {
        Tree tree = perfect(128);
        String html = display(tree);
        System.out.println(html);
    }

    public static Tree perfect(int size) {
        Tree tree = new Tree();
        perfectChild(tree, 0, size);
        return tree;
    }

    public static void perfectChild(Tree t, int init, int end) {
        int size = end - init;
        if (size < 2) {
            return;
        }
        int key = size / 2;
        t.inserir(init+key);
        perfectChild(t, init, init + key);
        perfectChild(t, init + key , end);
    }
}
