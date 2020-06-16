package server.object;

import server.Direction;
import server.PropertyManager;
import server.ResourceManager;
import server.TankFrame;
import server.TankGroup;
import server.strategy.DefaultFireStrategy;
import server.strategy.FireStrategy;

import java.awt.*;
import java.awt.event.KeyEvent;

import static server.Constant.TANK_IMAGE_HEIGHT;
import static server.Constant.TANK_IMAGE_WITH;
import static server.Constant.TANK_MOVE_SPEED;
import static server.Direction.outOfBoundChecked;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2020/6/14 16:01
 * @description:
 */
public class Tank extends AbstractGameObject {

    protected int x;
    protected int y;
    protected Direction direction;
    protected boolean bL;
    protected boolean bR;
    protected boolean bU;
    protected boolean bD;
    protected boolean moving;
    protected TankGroup tankGroup;
    protected int oldX;
    protected int oldY;
    protected FireStrategy fireStrategy;

    public Tank(int x, int y, Direction direction, TankGroup tankGroup) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.tankGroup = tankGroup;
        this.live = true;
        this.oldX = x;
        this.oldY = y;
        this.initFireStrategy();
    }

    private void initFireStrategy() {
        if (this.tankGroup == TankGroup.BAD) {
            this.fireStrategy = new DefaultFireStrategy();
            return;
        }
        String bulletFireStrategy = PropertyManager.get("bulletFireStrategy");
        try {
            Class clazz = Class.forName("server.strategy." + bulletFireStrategy);
            this.fireStrategy = (FireStrategy)clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
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
        fireStrategy.fire(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public TankGroup getTankGroup() {
        return tankGroup;
    }

    public void die() {
        this.live = false;
    }
}
