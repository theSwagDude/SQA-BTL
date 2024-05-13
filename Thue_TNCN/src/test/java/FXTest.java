/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class FXTest {
    public static class JFXTestHelper extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            // No operation needed here.
        }
    }

    @BeforeClass
    public static void initJFX() {
        Thread t = new Thread("JavaFX Init Thread") {
            @Override
            public void run() {
                Application.launch(JFXTestHelper.class, new String[0]);
            }
        };
        t.setDaemon(true);
        t.start();
    }
    
}
