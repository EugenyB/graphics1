/**
 * Created by eugeny on 10.09.2015.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Canvas canvas;
    double minX = -5;
    double maxX = 5;
    double minY = -5;
    double maxY = 5;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane rootPane = new GridPane();
        canvas = new Canvas(500,500);
        Button drawBtn = new Button("Draw");
        drawBtn.setOnAction(e -> drawGraphic());
        ToolBar toolBar = new ToolBar(drawBtn);
        toolBar.setMinWidth(500);
        toolBar.setMinHeight(50);
        GridPane.setConstraints(toolBar, 0, 0);
        GridPane.setConstraints(canvas, 0, 1);
        rootPane.getChildren().addAll(toolBar, canvas);
        Scene scene = new Scene(rootPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawGraphic() {
        double x1 = -4, y1 = -4, x2 = 4, y2 = 4;
        int xs1 = toScreenX(x1);
        int xs2 = toScreenX(x2);
        int ys1 = toScreenY(y1);
        int ys2 = toScreenY(y2);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.strokeLine(xs1,ys1,xs2,ys2);
    }

    private int toScreenX(double x) {
        return (int) (canvas.getWidth() * (x - minX) / (maxX - minX));
    }
    private int toScreenY(double y) {
        return (int) (canvas.getHeight() * (1 - (y - minY) / (maxY - minY)));
    }
    private double toWorldX(int xs) {
        return 1.0 * xs / canvas.getWidth() * (maxX - minX) + minX;
    }
    private double toWorldY(int ys) {
        return (1.0 * ys - canvas.getHeight()) /
                (-canvas.getHeight()) * (maxY - minY) + minY;
    }
}
