/*
 * Copyright 2001-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package server;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo Weifeng
 * @version: 1.0
 * @create: 2020/6/12 10:36
 */
public class Tank {
    public static final int SPEED = 5;
    private int x;
    private int y;
    private Direction direction;
    private boolean bL;
    private boolean bR;
    private boolean bU;
    private boolean bD;
    private boolean moving;
    private TankGroup tankGroup;

    public Tank(int x, int y, Direction direction, TankGroup tankGroup) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.tankGroup = tankGroup;
    }

    public void paint(Graphics graphics) {

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
        move();
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
        }
        setTankDirection();
    }

    private void move() {
        if (!moving) {
            return;
        }

        switch (direction){
            case L:
                x -= SPEED;
                break;
            case R:
                x += SPEED;
                break;
            case U:
                y -= SPEED;
                break;
            case D:
                y += SPEED;
                break;
        }

    }

    private void setTankDirection() {
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
}