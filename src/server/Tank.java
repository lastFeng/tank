package server;

import java.awt.*;
import java.awt.event.KeyEvent;

import static server.Constant.*;
import static server.Constant.BULLET_IMAGE_HEIGHT;
import static server.Direction.outOfBoundChecked;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2020/6/14 16:01
 * @description:
 */
public class Tank {

    protected int x;
    protected int y;
    protected Direction direction;
    protected boolean bL;
    protected boolean bR;
    protected boolean bU;
    protected boolean bD;
    protected boolean moving;
    protected TankGroup tankGroup;
    protected boolean live;
    protected int oldX;
    protected int oldY;

    public boolean isLive() {
        return live;
    }

    public Tank(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.live = true;
        this.tankGroup = TankGroup.BAD;
        this.oldX = x;
        this.oldY = y;
    }

    public void paint(Graphics graphics){
        if (this.tankGroup == TankGroup.GOOD) {
            switch (direction) {
                case L:
                    graphics.drawImage(ResourceManager.goodTankL, x, y, null);
                    break;
                case R:
                    graphics.drawImage(ResourceManager.goodTankR, x, y, null);
                    break;
                case U:
                    graphics.drawImage(ResourceManager.goodTankU, x, y, null);
                    break;
                case D:
                    graphics.drawImage(ResourceManager.goodTankD, x, y, null);
                    break;
            }
        }

        if (this.tankGroup == TankGroup.BAD) {
            switch (direction) {
                case L:
                    graphics.drawImage(ResourceManager.badTankL, x, y, null);
                    break;
                case R:
                    graphics.drawImage(ResourceManager.badTankR, x, y, null);
                    break;
                case U:
                    graphics.drawImage(ResourceManager.badTankU, x, y, null);
                    break;
                case D:
                    graphics.drawImage(ResourceManager.badTankD, x, y, null);
                    break;
            }
        }


        int[] move = Direction.move(this.moving, x, y, TANK_MOVE_SPEED, direction);

        if (move != null) {
            boolean boundChecked = outOfBoundChecked(move[0], move[1], TankFrame.GAME_WIDTH - TANK_IMAGE_WITH, TankFrame.GAME_HEIGHT - TANK_IMAGE_HEIGHT);

            if (!boundChecked) {
                x = move[0];
                y = move[1];
            }

        }
    }

    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                bL = true;
                break;
            case KeyEvent.VK_RIGHT:
                bR = true;
                break;
            case KeyEvent.VK_UP:
                bU = true;
                break;
            case KeyEvent.VK_DOWN:
                bD = true;
                break;
        }
        setTankDirection();
    }

    public void keyReleased(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                bL = false;
                break;
            case KeyEvent.VK_RIGHT:
                bR = false;
                break;
            case KeyEvent.VK_UP:
                bU = false;
                break;
            case KeyEvent.VK_DOWN:
                bD = false;
                break;
            case KeyEvent.VK_CONTROL:
                fire();
                break;
        }
        setTankDirection();
    }

    protected void setTankDirection() {
        if (!bL && !bR && !bU && !bD) {
            moving = false;
        } else {
            moving = true;
            if (bL && !bR && !bU && !bD) {
                direction = Direction.L;
            }
            if (!bL && bR && !bU && !bD){
                direction = Direction.R;
            }
            if (!bL && !bR && bU && !bD) {
                direction = Direction.U;
            }
            if (!bL && !bR && !bU && bD) {
                direction = Direction.D;
            }
        }
    }

    public void fire() {
        int bulletX = x + TANK_IMAGE_WITH / 2 - BULLET_IMAGE_WITH / 2;
        int bulletY = y + TANK_IMAGE_HEIGHT / 2 - BULLET_IMAGE_HEIGHT / 2;
        TankFrame.INSTANCE.addBullet(new Bullet(bulletX, bulletY, direction, tankGroup));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void die() {
        this.live = false;
    }
}
