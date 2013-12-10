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
public class ObjectGround extends ObjectBoilerplate
{

    public ObjectGround(float x, float y, float sx, float sy, Image image)
    {
        this.image = image;
        this.rect = new Rectangle(x, y, sx, sy);
    }

    @Override
    void update()
    {

    }

    
    @Override
    void render(int shiftRight)
    {
        image.draw(rect.getX() - shiftRight, MainApp.SCREEN_HEIGHT - rect.getY() - rect.getHeight(), rect.getWidth(), rect.getHeight());
    }
}
