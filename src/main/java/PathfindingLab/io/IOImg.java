package PathfindingLab.io;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;


public class IOImg {

    private ImageIO imgIo;
    private BufferedImage buffImg;
    private int red, green, blue;
    private int[][] pixelMap;

    /**
     * Method for getting the colors of the image. Has no use yet.
     * @throws IOException
     */
    public int[][] getPixelMap(Image inputImg) throws IOException {
        BufferedImage buffImg = SwingFXUtils.fromFXImage(inputImg, null);
        pixelMap = new int[buffImg.getHeight()][buffImg.getWidth()];
        for (int i = 0; i < buffImg.getWidth(); i++) {
            for (int j = 0; j < buffImg.getHeight(); j++) {
                Color colorImg = new Color(buffImg.getRGB(i,j));
                red = colorImg.getRed();
                green = colorImg.getGreen();
                blue = colorImg.getBlue();
                if(red == 255 && green == 255 && blue == 255 ) {
                    pixelMap[i][j] = 1;
                } else {
                    pixelMap[i][j] = 0;
                }
            }
        }
        System.out.println(Arrays.toString(pixelMap[0]));
        System.out.println(Arrays.toString(pixelMap[1]));
        return pixelMap;
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
