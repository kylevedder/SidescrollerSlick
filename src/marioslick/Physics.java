/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marioslick;

import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Kyle
 */
public class Physics
{

    /**
     * Called immeditly after collision We know due to this call that .getMinX()
     * / GameEngine.TILESIZE
     *
     * @param rectNew
     * @param rectOld
     */
    public static Rectangle updateForCollision(Rectangle rectNew, Rectangle rectOld)
    {
        float deltaX = rectNew.getX() - rectOld.getX();
        float deltaY = (rectNew.getMinY() - rectOld.getMinY());
        System.out.println("Delta x: " + deltaX + " Delta Y: " + deltaY);
        float slope = 0;
        
        if (deltaX != 0)
        {
            slope = deltaY / deltaX;//(rectNew.getMinY() - rectOld.getMinY()) / (rectNew.getMaxX() - rectOld.getMaxX());
            System.out.println("Non zero Xd");
        }   
        
        //vertical travel
        else
        {
            
            System.out.println("Zero Xd");
            if (deltaY != 0)
            {
                //travle up, need to be below
                if (deltaY > 0)
                {   System.out.println("Above 0");
                    int blockInside = (int) (rectNew.getMaxY() / GameEngine.TILESIZE);
                    float positionNewY = blockInside * GameEngine.TILESIZE - 1;
                    rectNew.setY(positionNewY - rectNew.getHeight());
                    return rectNew;
                }
                //travle down, need to be above
                else
                {
                    System.out.println("Below 0");
                  int blockInside = (int) (rectNew.getMaxY() / GameEngine.TILESIZE);
                    float positionNewY = blockInside * GameEngine.TILESIZE + 1;
                    rectNew.setY(positionNewY);
                    return rectNew;  
                }
            }
            else
            {
                System.out.println("Not moving");
            }
        }
        int x;
        int y;
        float output;
        //upper right or lower left
        if (slope > 0)
        {
            //bottom left
            if (deltaX > 0)
            {
                x = (int) (rectNew.getMaxX() / GameEngine.TILESIZE);
                y = (int) (rectNew.getMaxY()/ GameEngine.TILESIZE); 
                output = slope * (x - rectNew.getMaxX()) - rectNew.getMaxY();
                if (output > y*GameEngine.TILESIZE)
                {
                    //left side of block
                    //get the block inside
                    int intPosX = (int)(rectNew.getMaxX() / GameEngine.TILESIZE);
                    //set a pos of that block shifted -1
                    float floatPosX = intPosX * GameEngine.TILESIZE - 1;
                    //set it
                    rectNew.setX(floatPosX);
                    return rectNew; 
                    
                }
                else
                {
                    //under block0
                    //get the block inside
                    int intPosY = (int)(rectNew.getMaxY() / GameEngine.TILESIZE);
                    //set a pos of that block shifted -1
                    float floatPosY = intPosY * GameEngine.TILESIZE - 1;
                    //set it
                    rectNew.setY(floatPosY);
                    return rectNew; 
                }
            }
            //top right
            else if (deltaX < 0)
            {
                x = (int) (rectNew.getMinX() / GameEngine.TILESIZE);
                y = (int) (rectNew.getMinY()/ GameEngine.TILESIZE);
                output = slope * (x - rectNew.getMinX()) - rectNew.getMinY();
                if (output > y*GameEngine.TILESIZE)
                {
                    //top
                    //get the block inside
                    int intPosY = (int)(rectNew.getMinY() / GameEngine.TILESIZE);
                    //set a pos of that block shifted -1
                    float floatPosY = intPosY * GameEngine.TILESIZE + GameEngine.TILESIZE + 1 ;
                    //set it
                    rectNew.setY(floatPosY);
                    return rectNew; 
                }
                else
                {
                    //side
                    //get the block inside
                    int intPosX = (int)(rectNew.getMinX() / GameEngine.TILESIZE);
                    //set a pos of that block shifted -1
                    float floatPosX = intPosX * GameEngine.TILESIZE + GameEngine.TILESIZE + 1;
                    //set it
                    rectNew.setX(floatPosX);
                    return rectNew; 
                }
            }
            else
            {
                return rectNew;
            }
        }
        //upper left or lower right
        else if (slope < 0)
        {
            //top left
            if (deltaX > 0)
            {
                x = (int) (rectNew.getMaxX() / GameEngine.TILESIZE);
                y = (int) (rectNew.getMinY()/ GameEngine.TILESIZE);
                output = slope * (x - rectNew.getMaxX()) - rectNew.getMinY();
                if (output > y*GameEngine.TILESIZE)
                {
                    //top
                    //get the block inside
                    int intPosY = (int)(rectNew.getMinY() / GameEngine.TILESIZE);
                    //set a pos of that block shifted -1
                    float floatPosY = intPosY * GameEngine.TILESIZE + GameEngine.TILESIZE + 1 ;
                    //set it
                    rectNew.setY(floatPosY);
                    return rectNew; 
                }
                else
                {
                    //side
                    //get the block inside
                    int intPosX = (int)(rectNew.getMaxX() / GameEngine.TILESIZE);
                    //set a pos of that block shifted -1
                    float floatPosX = intPosX * GameEngine.TILESIZE - 1;
                    //set it
                    rectNew.setX(floatPosX);
                    return rectNew; 
                }
            }
            //bottom right
            else if (deltaX < 0)
            {
                x = (int) (rectNew.getMinX() / GameEngine.TILESIZE);
                y = (int) (rectNew.getMaxY()/ GameEngine.TILESIZE);
                output = slope * (x - rectNew.getMinX()) - rectNew.getMaxY();
                if (output > y*GameEngine.TILESIZE)
                {
                    //side
                    //get the block inside
                    int intPosX = (int)(rectNew.getMinX() / GameEngine.TILESIZE);
                    //set a pos of that block shifted -1
                    float floatPosX = intPosX * GameEngine.TILESIZE  + GameEngine.TILESIZE - 1;
                    //set it
                    rectNew.setX(floatPosX);
                    return rectNew; 
                }
                else
                {
                    //bottom
                    //get the block inside
                    int intPosY = (int)(rectNew.getMaxY() / GameEngine.TILESIZE);
                    //set a pos of that block shifted -1
                    float floatPosY = intPosY * GameEngine.TILESIZE - 1;
                    //set it
                    rectNew.setY(floatPosY);
                    return rectNew; 
                }
            }
            else
            {
                return rectNew;
            }
        }
        //slope horizontal
        else
        {
            if (deltaX != 0)
            {
                //left to right
                if (deltaX > 0)
                {
                    int blockInside = (int) (rectNew.getMaxY() / GameEngine.TILESIZE);
                    float positionNewY = blockInside * GameEngine.TILESIZE - 1;
                    rectNew.setY(positionNewY - rectNew.getHeight());
                    return rectNew;
                }
                //right to left
                else
                {
                  int blockInside = (int) (rectNew.getMaxY() / GameEngine.TILESIZE);
                    float positionNewY = blockInside * GameEngine.TILESIZE + 1;
                    rectNew.setY(positionNewY);
                    return rectNew;  
                }
            }
        }
        return rectNew;
    }

    public static boolean checkCollides(Rectangle r1, Rectangle r2)
    {
        return r1.intersects(r2);
    }

    public static boolean checkCollidesWithGround(Rectangle r1)
    {

        boolean collideAtAll = false;
        int xleft = (int) (r1.getMinX() / GameEngine.TILESIZE);
        int xright = (int) (r1.getMaxX() / GameEngine.TILESIZE);
        int ymin = (int) (r1.getMinY() / GameEngine.TILESIZE);
        int ymax = (int) (r1.getMaxY() / GameEngine.TILESIZE);

        if (GameEngine.tileArray[xleft][ymin] != null && checkCollides(r1, GameEngine.tileArray[xleft][ymin].rect))
        {
            collideAtAll = true;
        }
        else if (GameEngine.tileArray[xright][ymin] != null && checkCollides(r1, GameEngine.tileArray[xright][ymin].rect))
        {
            collideAtAll = true;
        }
        else if (GameEngine.tileArray[xleft][ymax] != null && checkCollides(r1, GameEngine.tileArray[xleft][ymax].rect))
        {
            collideAtAll = true;
        }
        else if (GameEngine.tileArray[xright][ymax] != null && checkCollides(r1, GameEngine.tileArray[xright][ymax].rect))
        {
            collideAtAll = true;
        }
        return collideAtAll;
    }

    public static int clampXIntToTileArray(int i)
    {
        if (i < 0)
        {
            i = 0;
        }
        else if (i >= GameEngine.WORLD_LENGTH)
        {
            i = GameEngine.WORLD_LENGTH - 1;
        }
        return i;
    }

    public static int clampYIntToTileArray(int i)
    {
        if (i < 0)
        {
            i = 0;
        }
        else if (i >= GameEngine.WORLD_HEIGHT)
        {
            i = GameEngine.WORLD_HEIGHT - 1;
        }
        return i;
    }

}
