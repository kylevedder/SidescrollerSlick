/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marioslick;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Kyle
 */
public class GameEngine
{
    final int PLAYER_START_X = 40;
    final int PLAYER_START_Y = 400;
    static final int WORLD_LENGTH = 1000;
    static final int WORLD_HEIGHT = 19;
    static final int GROUND_HEIGHT = 3;
    ObjectPlayerBetter player;
    public static ObjectGround[][] tileArray = new ObjectGround[WORLD_LENGTH][WORLD_HEIGHT];

    public static final int TILESIZE = 32;

    public GameEngine()
    {

    }

    public void init(GameContainer gc) throws SlickException
    {
        createGround();
        SpriteSheet image = new SpriteSheet(new Image("images/BallSprites.png"), 16, 16);
        player = new ObjectPlayerBetter(PLAYER_START_X, PLAYER_START_Y, (int) (image.getSubImage(0, 0).getWidth() * 1), (int) (image.getSubImage(0, 0).getHeight() * 1), image);
        player.init();
    }

    Random r = new Random(System.currentTimeMillis());

    private void createGround() throws SlickException
    {
        Image image = new Image("images/NewGround.png");
        ObjectGround ground;
        for (int x = 0; x < WORLD_LENGTH; x++)
        {
            for (int y = 0; y < r.nextInt(GROUND_HEIGHT) + 1; y++)
            {
                ground = new ObjectGround(x * TILESIZE, (y * TILESIZE), TILESIZE, TILESIZE, image);                
                tileArray[x][y] = ground;
            }

        }
    }

    public void update(GameContainer gc, int deltaTime) throws SlickException
    {

        takeInput(gc);
        player.update();
    }

    public static int shiftRight = 0;

    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        //clears
        g.clear();
        //backgrond
        g.setColor(new Color(103, 194, 240));
        g.fillRect(0, 0, MainApp.SCREEN_WIDTH, MainApp.SCREEN_HEIGHT);

        //compute shift right
        shiftRight += computeShiftRight();

        //draws every object
        ObjectBoilerplate object;
        for (int x = 0; x < WORLD_LENGTH; x++)
        {
            for (int y = 0; y < WORLD_HEIGHT; y++)
            {
                object = tileArray[x][y];
                if (object != null)
                {
                    object.render(shiftRight);
                    g.setColor(Color.red);
//                    g.fillRect(object.rect.getX() - shiftRight, MainApp.SCREEN_HEIGHT - object.rect.getY(), object.rect.getWidth(), object.rect.getHeight());
                }
            }
        }
//        g.setColor(Color.red);
//        g.fillRect(player.rect.getX() - shiftRight, MainApp.SCREEN_HEIGHT - player.rect.getY(), player.rect.getWidth(), -player.rect.getHeight());
        player.render(shiftRight);    
    }
    
    Rectangle rtest = new Rectangle(10, 10, 10, 20);
    private int computeShiftRight()
    {
        if ((player.rect.getX() - shiftRight) > (MainApp.SCREEN_WIDTH / 2))
        {
            return (int) (player.rect.getX() - shiftRight - (MainApp.SCREEN_WIDTH / 2));
        }
        else
        {
            return 0;
        }
    }

    private void takeInput(GameContainer gc)
    {
        Input input = gc.getInput();
        //jump
        if (input.isKeyDown(Input.KEY_SPACE) || input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W))
        {
            player.jump();
        }
        if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A))
        {
            player.move(DirectionEnum.LEFT);
        }
        if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D))
        {
            player.move(DirectionEnum.RIGHT);
        }
    }
}
