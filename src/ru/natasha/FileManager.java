package ru.natasha;

import java.io.*;

public class FileManager {
    public static void write(String fileName, String text){
        File file = new File(fileName);

        try {
            if (!file.exists()){
                file.createNewFile();
            }

            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                out.print(text);
            } finally {
                out.close();
            }

        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    public static String read(String fileName) throws FileNotFoundException{
        StringBuilder sb = new StringBuilder();
        File file = new File(fileName);
        exists(fileName);

        try {
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }

    public static String readLine(String fileName, int line) throws FileNotFoundException{
        StringBuilder sb = new StringBuilder();
        File file = new File(fileName);
        exists(fileName);
        int current_line = 1;

        try {
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    if (current_line == line) {
                        sb.append(s);
                    }
                    current_line++;
                }
            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();

    }

    public static void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()){
            throw new FileNotFoundException(file.getName());
        }
    }

    public static void update(String fileName, String newText) throws FileNotFoundException {
        exists(fileName);
        StringBuilder sb = new StringBuilder();
        String oldFile = read(fileName);
        sb.append(oldFile);
        sb.append(newText);
        write(fileName, sb.toString());
    }

    public static void delete(String fileName) throws FileNotFoundException{
        exists(fileName);
        new File(fileName).delete();
    }

}
