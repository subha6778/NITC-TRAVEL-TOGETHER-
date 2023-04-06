package GUI;


import javax.swing.*;
import java.awt.*;

class ImagePanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;


    public ImagePanel(String img,Dimension dimension) {

        this(new ImageIcon(img).getImage(),dimension);
    }

    public ImagePanel(Image img,Dimension size) {
        this.img = img;

        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }



    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

}
