import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ASCII {
    public static StringBuilder draw(String text, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics _graphics = image.getGraphics();

        _graphics.setFont(new Font("SansSerif", Font.PLAIN, 12));

        Graphics2D graphics = (Graphics2D) _graphics;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString(text, 5, 10);

        StringBuilder sb = new StringBuilder();
        sb.append("<br>");

        for (int y = 0; y < height; y++) {

            Random rand = new Random();
            float r = rand.nextFloat();
            float b = rand.nextFloat();
            float g = rand.nextFloat();

            Color randColor = new Color(r, b, g);

            String hex = "#"+Integer.toHexString(randColor.getRGB()).substring(2);
            sb.append(String.format("<span style='color:%s'>", hex));

            for (int x = 0; x < width; x++) {
                sb.append(image.getRGB(x, y) == -16777216 ? " " : "#");
            }

            if (sb.toString().trim().isEmpty()) {
                continue;
            }

            sb.append("</span><br>");
        }

        return sb;
    }
}
