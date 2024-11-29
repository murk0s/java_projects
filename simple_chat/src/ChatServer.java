import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class ChatServer {
    Set<Client> clients;
    ServerSocket serverSoket;

    public ChatServer() throws IOException {
        this.serverSoket = new ServerSocket(1234);
        clients = new HashSet<Client>();
    }

    public void run() {
        while (true) {
            System.out.println("Waiting...");
            // ждем клиента
            try {
                new Client(serverSoket.accept());
                System.out.println("count of clients is: " + clients.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendAll(String message, Client clientFrom) {
        for (Client client : clients) {
            if (client != clientFrom) client.recieveMessage(message);
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatServer().run();
    }

    class Client implements Runnable {
        Socket socket;
        String name="";
        Scanner in;
        PrintStream out;

        public Client(Socket socket) {
            clients.add(this);
            this.socket = socket;
            new Thread(this).start();
            System.out.println("Client connected!");
        }

        @Override
        public void run() {
            try {
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();

                // создаем удобные средства ввода и вывода
                in = new Scanner(is);
                out = new PrintStream(os);
                // читаем из сети и пишем в сеть
                out.println("Welcome to chat!");
                while (name == "") {
                    out.println("What is your name?");
                    name = in.nextLine();
                }
                sendAll(name + " come in", this);
                String input = in.nextLine();
                while (!input.equals("bye")) {
                    if (!input.equals("")) sendAll(name + " : " + input, this);
                    input = in.nextLine();
                }
                sendAll(name + " quite", this);
                socket.close();
                clients.remove(this);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        public void recieveMessage(String message) {
            try {
                out.println(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}