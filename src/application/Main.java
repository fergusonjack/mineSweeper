package application;

import java.awt.Color;

import controller.Grid;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Scale;

public class Main extends Application {
	static Button[][] buttons;
	static int buttonsizex = 55;
	static int buttonsizey = 40;
	static int arraySizex = 20;
	static int arraySizey = 20;
	int spacing = 2;
	static Image flag = new Image("img/flag.png");
	static Image mine = new Image("img/mine.png");
	protected static Grid grid;

	@Override
	public void start(Stage primaryStage) {
		grid = new Grid();
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, buttonsizex * arraySizex, buttonsizey * arraySizey);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			root.setCenter(addbuttons());
			primaryStage.setTitle("Minesweeper");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public GridPane addbuttons() {
		buttons = new Button[arraySizex][arraySizey];
		GridPane grid = new GridPane();
		grid.setVgap(spacing);
		grid.setHgap(spacing);
		for (int y = 0; y < arraySizey; y++) {
			for (int x = 0; x < arraySizey; x++) {
				buttons[x][y] = new Button();
				buttons[x][y].setId(x + "," + y);
				buttons[x][y].getStyleClass().removeAll("addBobOk, focus");
				buttons[x][y].getStyleClass().add("gray");
				buttons[x][y].setOnMouseClicked(new ButtonPress());
				buttons[x][y].setPrefSize(buttonsizex, buttonsizey);
				grid.add(buttons[x][y], x, y);
			}
		}
		return grid;
	}

	public static void addFlag(int x, int y) {
		ImageView flagV = new ImageView(flag);
		flagV.setFitHeight(buttonsizey - 15);
		flagV.setFitWidth(buttonsizex - 25);
		buttons[x][y].setGraphic(flagV);
	}

	public static void removeFlag(int x, int y) {
		buttons[x][y].setGraphic(null);
	}

	public Image scale(Image source, int targetWidth, int targetHeight, boolean preserveRatio) {
		ImageView imageView = new ImageView(source);
		imageView.setPreserveRatio(preserveRatio);
		imageView.setFitWidth(targetWidth);
		imageView.setFitHeight(targetHeight);
		return imageView.snapshot(null, null);
	}

	public static void endUnCover() {
		for (int y = 0; y < arraySizey; y++) {
			for (int x = 0; x < arraySizey; x++) {
				if ((grid.getTiles()[x][y]).isMine()){
					ImageView mineV = new ImageView(mine);
					mineV.setFitHeight(buttonsizey - 15);
					mineV.setFitWidth(buttonsizex - 15);
					buttons[x][y].setGraphic(mineV);
					buttons[x][y].getStyleClass().clear();
					buttons[x][y].getStyleClass().add("Lgray");
				} else {
					buttons[x][y].getStyleClass().add("Lgray");
				}
			}
		}
	}
}
