import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by hongyeah on 2015/2/25.
 */
public class CreateImage {
    public CreateImage(ImageIcon imageIcon,String path,int i)  {
        File file = new File(path);
        if(!file.isDirectory())
         file.mkdirs();
        OutputStream output = null;
        try {
            output = new FileOutputStream(path+"/"+i+".jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = imageIcon.getImage();
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(
                    image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
        }

        if (bimage == null) {
            int type = BufferedImage.TYPE_INT_RGB;
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }
        Graphics g = bimage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        try {
            ImageIO.write(bimage, "JPG", output);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"创建图片IO异常！");
        }finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
