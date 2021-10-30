import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageToAscii {

    static final String fileName = "meFinal2.jpg";
    static final char[] ascii = {' ', '.', ',', ':', '+', '=', '*', '#', '&', '%', '$', '@'};

    static int resX = 180;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Enter a file path for the first argument and a width in characters for the second argument (Must be less than the width in pixels of the original image).");
            return;
        }
        resX = Integer.parseInt(args[1]);
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("./" + args[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int inc = image.getWidth() / resX / 2;

        for (int i = 0; i < image.getHeight(); i += inc * 5) {
            for (int j = 0; j < image.getWidth(); j += inc * 2) {
                int colors = image.getRGB(j, i);

                int red = (colors & 0x00ff0000) >> 16;
                int green = (colors & 0x0000ff00) >> 8;
                int blue = (colors & 0x000000ff);
                int darkness = (red + green + blue) / 3;
                int normalizedDarkness = (darkness * (ascii.length - 1)) / 0xff;
                //int darkness = (int) ((((colors & 0x00ff0000) >> 16) + ((colors & 0x0000ff00) >> 8) + colors & 0x000000ff) / 3 * (22.0 / 0xff));
                if (normalizedDarkness > ascii.length - 1)
                    System.out.print(normalizedDarkness);
                else
                    System.out.print(ascii[normalizedDarkness]);
            }
            System.out.println();
        }
    }
}
