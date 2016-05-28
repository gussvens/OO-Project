package mapeditor_v1;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
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

    private MapModel mapModel;
    private int width;
    private int height;
    private BufferedImage[] tileImages;
    private String[] toolTips;
    private TileLoader tileLoader= new TileLoader();
    private TooltipLoader textLoader= new TooltipLoader();

    @FXML private ImageView[] toolIcons = new ImageView[100];
    @FXML private Button[] toolButtons = new Button[100];;
    @FXML private FlowPane toolPane;
    @FXML private Canvas canvas;


    /**
     * Constructor
     * @param width Width of the canvas
     * @param height Height of the canvas
     * @param mapModel MapModel containing map
     */
    public MapEditorController(int width, int height, MapModel mapModel){
        this.mapModel = mapModel;
        this.width = width;
        this.height = height;
    }

    /**
     * Initialises 100 new buttons, one for every tile type. Sets every buttons icon and tooltip.
     * Also sets canvas to correct size and creates a GraphicsContext for the canvas
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        tileImages = tileLoader.loadImage("/sprites/tiles/tileGrid.png");
        toolTips= textLoader.loadText("src/main/resources/tooltips/tiles.txt");

        canvas.setHeight(height*32);
        canvas.setWidth(width*32);
        imageCanvas = canvas.getGraphicsContext2D();


        for(int i=0; i< toolIcons.length; i++ ){
            Image image = SwingFXUtils.toFXImage(tileImages[i],null);
            toolIcons[i]= new ImageView();
            toolIcons[i].setImage(image);

            Tooltip tooltip = new Tooltip(toolTips[i]);


            toolButtons[i]= new Button();
            toolButtons[i].setGraphic(toolIcons[i]);
            toolButtons[i].setTooltip(tooltip);
            toolButtons[i].setOnAction(toolHandler);
            toolPane.getChildren().add(toolButtons[i]);
        }
        paintCanvas();

    }

    /**
     * Sets mapModels selected ID to button ID
     */
    final EventHandler<ActionEvent> toolHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(final ActionEvent event) {
            for(int i=0; i<toolButtons.length; i++){
                if(event.getSource() == toolButtons[i]){
                    mapModel.setSelectedTile(i);
                }
            }
        }
    };

    /**
     * Sets
     * @param e MouseEvent from canvas Mouseclick or mousedrag
     */
    @FXML
    public void onMousePressed(MouseEvent e){
        int x = (int)e.getX();
        int y = (int)e.getY();
        if(x > 0 && x < width*32 && y>0 && y < height*32) {
            mapModel.setTile(x, y);

            paintCanvas();
        }
    }
    @FXML
    /**
     * Exits current instance of application and starts a new one
     */
    public void newButton(){
        mapModel.clearMap();
        paintCanvas();
    }

    /**
     * Makes mapModel clear and repaints canvas
     */
    @FXML
    public void clearButton(){
        mapModel.clearMap();
        paintCanvas();
    }

    /**
     * Calls on the mapModel to save
     * @throws IOException
     */
    @FXML
    public void saveButton(){
        try {
            mapModel.saveMap();
        }catch (IOException e){
            System.err.println("Caught IOException: " + e.getMessage());
        }

    }

    /**
     * Calls on the mapModel to save and exit after.
     * @throws IOException
     */
    @FXML
    public void saveExitButton(){
       try {
        mapModel.saveMap();
        System.exit(0);
       }catch (IOException e){
           System.err.println("Caught IOException: " + e.getMessage());
       }
    }

    @FXML
    /**
     * Calls on the map to fill itself with selected ID
     * Repaints canvas
     */
    public void fillButton(){
        mapModel.fillMap();
        paintCanvas();
    }

    /**
     * Paints the canvas according to map
     */
    public void paintCanvas(){
        imageCanvas.clearRect(0,0,width*32, height*32);

        for (int i = 0; i < mapModel.getMap().length; i++)
        {
            for (int j = 0; j < mapModel.getMap()[0].length; j++){
                int id = mapModel.getMap()[i][j];
                imageCanvas.drawImage(toolIcons[id].getImage(), i*32, j*32);
            }

        }
    }

}
