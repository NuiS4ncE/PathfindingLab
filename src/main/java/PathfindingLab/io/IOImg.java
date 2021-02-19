package PathfindingLab.io;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.awt.image.AffineTransformOp;

public class IOImg {

    private ImageIO imgIo;
    private BufferedImage buffImg;
    private int red, green, blue;
    private int[][] map;
    private int defaultGroundColor = 229;

    /**
     * Method for getting the colors of the image. Has no use yet.
     * @throws IOException
     */
    public int[][] getMap() throws IOException {
        map = new int[this.buffImg.getHeight()][this.buffImg.getWidth()];
        System.out.println("Img width: " + this.buffImg.getWidth() + " img height: " + this.buffImg.getHeight());
        for (int i = 0; i < this.buffImg.getWidth(); i++) {
            for (int j = 0; j < this.buffImg.getHeight(); j++) {
                Color colorImg = new Color(this.buffImg.getRGB(i,j));
                red = colorImg.getRed();
                green = colorImg.getGreen();
                blue = colorImg.getBlue();
                //System.out.println("Red: " + red + " green: " + green + " blue: "+ blue);
                if(red == defaultGroundColor && green == defaultGroundColor && blue == defaultGroundColor ) {
                    map[i][j] = 1;
                } else {
                    map[i][j] = 0;
                }
            }
        }
        /*for(int k = 0; k < map.length; k++) {
            System.out.println(Arrays.toString(map[k]));
        }*/
        return map;
    }

    /**
     * Getter for getting the BufferedImage from file
     * @return Returns the retrieved BufferedImage
     * @throws IOException
     */
    public BufferedImage getBuffImg() throws IOException {
        return this.buffImg;
    }

    public void setBuffImg(File file, int wantedWidth, int wantedHeight) throws IOException{
        BufferedImage beforeImg = imgIo.read(file);
        BufferedImage resizedImg = Thumbnails.of(beforeImg).size(wantedWidth, wantedHeight).asBufferedImage();
        this.buffImg = resizedImg;
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
