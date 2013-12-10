/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package marioslick;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Kyle
 */
public abstract class ObjectBoilerplate
{

    protected Image image = null;
    protected Rectangle rect = null;
    
    abstract void update();
    abstract void render(int shiftRight);

    public ObjectBoilerplate()
    {               
    }

    public Image getImage()
    {
        return image;
    }
    
    public Rectangle getRectangle()
    {
        return rect;
    }        
}
