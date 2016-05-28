package mapeditor_v1;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Martin-610 on 2016-05-22.
 */
public class MapEditorController implements Initializable {

    private static GraphicsContext imageCanvas;

    private Model model;
    private int width;
    private int height;
    private BufferedImage[] tileImages;
    private String[] toolTips;
    private TileLoader tileLoader= new TileLoader();
    private TooltipLoader textLoader= new TooltipLoader();

    @FXML
    private ImageView[] toolIcons = new ImageView[100];
    @FXML
    private Button[] toolButtons = new Button[100];;
    @FXML
    private FlowPane toolPane;
    @FXML
    private Canvas canvas;

    public MapEditorController(int width, int height, Model model){
        this.model = model;
        this.width = width;
        this.height = height;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        tileImages = tileLoader.loadImage("src/main/resources/sprites/tiles/tileGrid.png");
        toolTips= textLoader.loadText("src/main/resources/tooltips/tiles.txt");

        canvas.setHeight(height*32);
        canvas.setWidth(width*32);
        imageCanvas = canvas.getGraphicsContext2D();


        for(int i=0; i< toolIcons.length; i++ ){
            Image image = SwingFXUtils.toFXImage(tileImages[i],null);
            toolIcons[i]= new ImageView();
            toolIcons[i].setImage(image);


            toolButtons[i]= new Button();
            toolButtons[i].setGraphic(toolIcons[i]);
            //toolButtons[i].setTooltip();
            toolButtons[i].setOnAction(toolHandler);
            toolPane.getChildren().add(toolButtons[i]);
        }
        paintCanvas();

    }
    final EventHandler<ActionEvent> toolHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(final ActionEvent event) {
            for(int i=0; i<toolButtons.length; i++){
                if(event.getSource() == toolButtons[i]){
                    //pp.paintImage =  bimages[i];
                    model.setSelectedTile(i);
                }
            }
        }
    };

    @FXML
    public void onMousePressed(MouseEvent e){
        int x = (int)e.getX();
        int y = (int)e.getY();
        if(x > 0 && x < width*32 && y>0 && y < height*32) {
            model.setTile(x, y);

            paintCanvas();
            System.out.println("x: " + x / 32);
            System.out.println("y: " + y / 32);
        }
    }

    @FXML
    public void clearButton(){
        model.clearMap();
        paintCanvas();
    }

    @FXML
    public void saveButton() throws IOException{
        model.saveMap();
    }
    @FXML
    public void fillButton(){
        model.fillMap();
        paintCanvas();
    }

    public void paintCanvas(){
        imageCanvas.clearRect(0,0,width*32, height*32);

        for (int i = 0; i < model.getMap().length; i++)
        {
            for (int j = 0; j < model.getMap()[0].length; j++){
                int id = model.getMap()[i][j];
                imageCanvas.drawImage(toolIcons[id].getImage(), i*32, j*32);
            }

        }
    }

}