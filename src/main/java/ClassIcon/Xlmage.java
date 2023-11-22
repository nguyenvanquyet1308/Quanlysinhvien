/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClassIcon;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author nguyenvanquyet
 */
public class Xlmage {

    public static Image getApplcon(){
        URL url = Xlmage.class.getResource("ClassIcon/fpt.png");
        return new ImageIcon(url).getImage();

    }
}
