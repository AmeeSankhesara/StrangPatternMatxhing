
// Amee Sankhesara


import java.io.*;


public class InputSource
{
    String inputString = "";

    InputSource(String fileName)
    {
        try {
            this.inputString = readFile("PCLA_proteins.txt");

        } catch (Exception e) {
            // Handle it.
            System.out.println(e.getMessage());

        }
    }

    String readFile(String fileName) throws IOException {

//        final Path filePath = Paths.get(fileName);
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder(); String line = br.readLine();
        while (line != null) {
            sb.append(line).append("\n");
            line = br.readLine();
        }

        return sb.toString();

    }

}



