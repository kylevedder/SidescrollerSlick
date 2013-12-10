/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sidescrollerslick;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Kyle
 */
public class ObjectPlayerBetter extends ObjectBoilerplate
{

    final private float ACCELERATION_PER_TICK = 0.5f;
    final private float JUMP_HEIGHT = 12;
    final private float TERMINAL_VELOCITY = -15;
    final private float MOVE_SPEED = 3;
    final private int ANIM_SPEED = 200;
    private DirectionEnum direction = DirectionEnum.RIGHT;
    private Animation animLeft;
    private Animation animRight;
    private SpriteSheet sheet;

    float velocityY = 0.0f;
    boolean inAir = false;
    boolean jumping = false;

    // <editor-fold defaultstate="collapsed" desc="Init Code">
    public ObjectPlayerBetter(float x, float y, float sx, float sy, SpriteSheet sheet)
    {
        this.sheet = sheet;
        SpriteSheet tempSheet = new SpriteSheet(this.sheet.getSubImage(0, 0, (this.sheet.getWidth() / 4), this.sheet.getHeight()), this.sheet.getHeight(), this.sheet.getHeight());
        animRight = new Animation(tempSheet, ANIM_SPEED);
        tempSheet = new SpriteSheet(this.sheet.getSubImage((this.sheet.getWidth() / 4), 0, (this.sheet.getWidth() / 4), this.sheet.getHeight()), this.sheet.getHeight(), this.sheet.getHeight());
        animLeft = new Animation(tempSheet, ANIM_SPEED);
        this.rect = new Rectangle(x, y, sx, sy);
    }

    /**
     * starts the animations
     */
    public void init()
    {
        animRight.start();
        animLeft.start();
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Update Code">   
    Rectangle rectOld;
    boolean flagCallUpdate = false;

    /**
     * Master update loop called by GameEngine
     */
    @Override
    void update()
    {
        rectOld = rect;
        //wile in air
        if (!Physics.checkCollidesWithGround(rect))
        {
            gravity();
            //chech if now ground hit
            if (Physics.checkCollidesWithGround(rect))
            {
                System.out.println("Call");
                
                flagCallUpdate = false;
                this.rect = Physics.updateForCollision(rect, rectOld);
            }

        }
        //on ground
        else
        {
        }

//        gravity();
//        Physics.updateForCollision(rect, rectOld);
    }

    private void gravity()
    {
        inAir = true;
        velocityY -= ACCELERATION_PER_TICK;
        //if falling faster than terminal velocity
        if (velocityY < TERMINAL_VELOCITY)
        {
            velocityY = TERMINAL_VELOCITY;
        }
        this.rect.setY(rect.getY() + velocityY);
    }

    // <editor-fold defaultstate="collapsed" desc="Externally called update functions"> 
    /**
     * Moves the player left and right
     */
    public void move(DirectionEnum dir)
    {

    }

    /**
     * Called externally to get player to jump
     */
    public void jump()
    {
        jumping = true;
        if (!inAir)
        {
            velocityY = JUMP_HEIGHT;
            this.rect.setY(rect.getY() + velocityY);
            inAir = true;
        }
    }
    // </editor-fold>

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Render Code">   
    /**
     * Draws the player, including the shift right on the screen
     *
     * @param shiftRight
     */
    @Override
    public void render(int shiftRight)
    {
        if (direction == DirectionEnum.LEFT)
        {
            this.animLeft.draw(rect.getX() - shiftRight, MainApp.SCREEN_HEIGHT - rect.getY() - rect.getHeight(), rect.getWidth(), rect.getHeight());
        }
        else if (direction == DirectionEnum.RIGHT)
        {
            this.animRight.draw(rect.getX() - shiftRight, MainApp.SCREEN_HEIGHT - rect.getY() - rect.getHeight(), rect.getWidth(), rect.getHeight());
        }
    }

    // </editor-fold>
}
