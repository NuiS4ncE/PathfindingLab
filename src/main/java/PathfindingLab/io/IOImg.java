package PathfindingLab.io;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class IOImg {

    private ImageIO imgIo;
    private BufferedImage buffImg;
    private int red, green, blue;

    public void getColors() throws IOException {
        buffImg = imgIo.read(new File("/maps/arena.png"));
        for (int x = 0; x < buffImg.getWidth(); x++) {
            for (int y = 0; y < buffImg.getHeight(); y++) {
                Color colorImg = new Color(buffImg.getRGB(x,y));
                red = colorImg.getRed();
                green = colorImg.getGreen();
                blue = colorImg.getBlue();
            }
        }
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
