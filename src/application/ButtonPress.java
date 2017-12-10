package application;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ButtonPress implements EventHandler<MouseEvent> {

	@Override
	public void handle(MouseEvent event) {
		if (event.getButton().toString().equals("PRIMARY")) {
			String coords = ((Button) event.getSource()).getId();
			String[] splitCoords = coords.split(",");

			int xval = Integer.valueOf(splitCoords[0]);
			int yval = Integer.valueOf(splitCoords[1]);

			if (Main.grid.clickMine(xval, yval)) {
				System.out.println("end game");
				Main.endUnCover();
				return;
			}

			Main.grid.click(xval, yval);

			for (int y = 0; y < Main.arraySizey; y++) {
				for (int x = 0; x < Main.arraySizex; x++) {
					if (Main.grid.getTiles()[x][y].getNumNextTo() == 0) {
						Main.buttons[x][y].getStyleClass().add("gray");
					} else if (Main.grid.getTiles()[x][y].getNumNextTo() == -1) {
						Main.buttons[x][y].getStyleClass().add("Lgray");
					} else {
						Main.buttons[x][y].getStyleClass().add("fringe");
						Main.buttons[x][y].setText("" + Main.grid.getTiles()[x][y].getNumNextTo());
					}
				}
			}
		} else {
			String coords = ((Button) event.getSource()).getId();
			String[] splitCoords = coords.split(",");
			
			int xval = Integer.valueOf(splitCoords[0]);
			int yval = Integer.valueOf(splitCoords[1]);

			
			if (!(Main.grid.isFlag(xval , yval))){
				Main.grid.addFlag(xval, yval);
				Main.addFlag(xval, yval);
			} else {
				Main.grid.removeFlag(xval, yval);
				Main.removeFlag(xval, yval);
			}
		}
	}

}
