package clientPack;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class Client extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Моё окно");
        Button button = new Button("Отправить");
        TextField textField1 = new TextField();
        textField1.setPrefColumnCount(25);
        FlowPane layout = new FlowPane(Orientation.VERTICAL);
        layout.getChildren().add(textField1);
        layout.getChildren().add(button);
        //сокет и потоки в обработчике события
        button.setOnAction(actionEvent -> {
                    Socket clientSocket = new Socket(InetAddress.getByName("127.0.0.1"), 8000);
                    InputStream in = clientSocket.getInputStream();
                    OutputStream out = clientSocket.getOutputStream();
                    boolean flag=true;
                        String s1 = textField1.getText();
                        out.write(s1.getBytes());
                        byte[] bytes = new byte[128];
                        in.read(bytes);
                        String s2;
                        s2 = new String(bytes);
                    System.out.println(s2);
                    in.close();
                    out.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Scene scene = new Scene(layout, 300,300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
