
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class writePesquisa {
    public static void writeToFile(ArrayList<String> outputLines, String fileName) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Output\\" + fileName + ".txt"));
            for (String line : outputLines) {
                writer.write(line);
                writer.write("\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Erro ao criar arquivo");
        }
    }
}
