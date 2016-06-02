package gui;

import instances.IndexOutOfBoundGridException;
import instances.UnmergeableGridException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import utils.Couple;

/**
 * Created by mouton on 23/02/16.
 */
public class GridMousePressedHandler implements EventHandler<MouseEvent> {


    private GridDrawer drawer;
    private boolean edit;

    public GridMousePressedHandler(GridDrawer drawer) {
        this.drawer = drawer;
        this.edit = true;
    }


    @Override
    public void handle(MouseEvent mouseEvent) {
        int x = (int) mouseEvent.getX();
        int y = (int) mouseEvent.getY();
        if(this.edit)
            this.handleEdit(x,y);
        else
            this.handlePlay(x,y);

    }

    public void handleEdit(int x, int y){
        Integer line = GridDrawer.getLine(y);
        if (line == null)
            return;

        Integer column = GridDrawer.getColumn(x);
        if (column == null)
            return;

        boolean add = drawer.grid.invertPoint(line, column);
        if (add)
            this.drawer.addPoint(line, column);
        else
            this.drawer.removePoint(line, column);
    }

    public void handlePlay(int x, int y) {
        Couple<Integer, Integer> lineColumn = GridDrawer.getLineColumn(x, y);
        Integer line = lineColumn.first;
        Integer column = lineColumn.second;

        if (line == null && column == null)
            return;
        else if (line == null)
            drawer.mergeColumn(column);
        else if (column == null)
            drawer.mergeLine(line);
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

}
