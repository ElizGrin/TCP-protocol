package serverPack;

import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket;
        Socket clientSocket = null;
        BufferedReader inputStream = null;
        BufferedWriter outputStream = null;
        byte[] bytes;
        String s = "";
        String str = null;
        boolean flag;

        try {
            serverSocket = new ServerSocket(8000);
            while (true) {
                clientSocket = serverSocket.accept();
                inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outputStream = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                str = inputStream.readLine();
                char[] word = str.toCharArray();
                char[] reverseWord = new char[str.length()];
                int size = word.length;
                for (int i = 0; i < size; i++) {
                    reverseWord[i] = word[size - 1 - i];
                    s += reverseWord[i];
                    }
                outputStream.write(s);
            }
        } catch (UnsupportedEncodingException ex) {
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            inputStream.close();
            outputStream.close();
            clientSocket.close();
        }
    }
}

