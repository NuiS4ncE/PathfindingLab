package PathfindingLab.io;

import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Image;


public class IOImg {

    private ImageIO imgIo;
    private BufferedImage buffImg;
    private int red, green, blue;

    /**
     * Method for getting the colors of the image. Has no use yet.
     * @throws IOException
     */
    public void getColors() throws IOException {
        buffImg = imgIo.read(new File("maps/arena.png"));
        for (int x = 0; x < buffImg.getWidth(); x++) {
            for (int y = 0; y < buffImg.getHeight(); y++) {
                Color colorImg = new Color(buffImg.getRGB(x,y));
                red = colorImg.getRed();
                green = colorImg.getGreen();
                blue = colorImg.getBlue();
            }
        }
    }

    /**
     * Getter for getting the BufferedImage from file
     * @return Returns the retrieved BufferedImage
     * @throws IOException
     */
    public BufferedImage getBuffImg() throws IOException {
        File currentDir = new File(".");
        File parentDir = currentDir.getParentFile();
        //System.out.println("TRYING TO FIND FILE!");
        return buffImg = imgIo.read(new File("maps/arena.png"));
    }

    public int getRedPxl() {
        return this.red;
    }

    public int getGreenPxl() {
        return this.green;
    }

    public int getBluePxl() {
        return this.blue;
    }
}
