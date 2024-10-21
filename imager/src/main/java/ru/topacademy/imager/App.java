package ru.topacademy.imager;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



/**
 * JavaFX App
 */
public class App extends Application {
	FileChooser fileChooser = new FileChooser();
	Button open_btn = new Button();

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        //var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");

    	open_btn.setText("Open file...");
        open_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Обработка события нажатия на кнопочку
            	File selectedFile = fileChooser.showOpenDialog(stage);
            	// Это мы только что показали созданный ранее диалог выбора файла 
            	if (selectedFile != null) {
            		try (
            			FileInputStream fis = new FileInputStream (selectedFile);
            			Scanner scanner = new Scanner (fis);
            			) {
            				String s = scanner.nextLine();
            				open_btn.setText(s);
            				TimeUnit.MILLISECONDS.sleep (300);
            			} catch (IOException | InterruptedException ex) {
            				System.out.println("Какая-то фигня с файлом");
            				System.out.println(ex);
            			}

                }
            }
        });
        
        // Размещаем кнопочку на приложении
        var scene = new Scene(new StackPane(open_btn), 640, 480);
        stage.setScene(scene);
        stage.show();
        
    }

    public static void main(String[] args) {
        launch();
    }

}