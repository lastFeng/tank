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
package server.object;

import server.Direction;
import server.ResourceManager;
import server.TankFrame;
import server.TankGroup;
import server.object.collides.Collides;

import java.awt.*;

import static server.Constant.*;
import static server.Direction.outOfBoundChecked;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo Weifeng
 * @version: 1.0
 * @create: 2020/6/12 15:03
 */
public class Bullet extends AbstractGameObject{
    private int x;
    private int y;
    private Direction direction;
    private TankGroup tankGroup;

    public Bullet(int x, int y, Direction direction, TankGroup tankGroup) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.tankGroup = tankGroup;
        this.live = true;
    }

    @Override
    public void paint(Graphics graphics) {

        if (live) {
            switch (direction) {
                case L:
                    graphics.drawImage(ResourceManager.bulletL, x, y, null);
                    break;
                case R:
                    graphics.drawImage(ResourceManager.bulletR, x, y, null);
                    break;
                case U:
                    graphics.drawImage(ResourceManager.bulletU, x, y, null);
                    break;
                case D:
                    graphics.drawImage(ResourceManager.bulletD, x, y, null);
                    break;
            }
            int[] move = Direction.move(true, x, y, BULLET_MOVE_SPEED, direction);
            if (move != null) {
                x = move[0];
                y = move[1];
            }

            this.live = !outOfBoundChecked(x, y, TankFrame.GAME_WIDTH, TankFrame.GAME_HEIGHT);
        }
    }

    public boolean isOutOfBound() {
        return !live;
    }

    /**
     * 简单的碰撞检测,两个长方形是否相交
     * @param collides
     * @return
     */
    public void collideWithTank(Collides collides) {
        if (!this.live || !collides.isLive()) {
            return;
        }

        if (collides instanceof Tank) {
            Tank tank = (Tank) collides;
            if (this.tankGroup == tank.tankGroup) {
                return;
            }
        }
        Rectangle bulletRect = new Rectangle(x, y, BULLET_IMAGE_WITH, BULLET_IMAGE_HEIGHT);
        Rectangle tankRect = new Rectangle(collides.getX(), collides.getY(), TANK_IMAGE_WITH, TANK_IMAGE_HEIGHT);

        if (bulletRect.intersects(tankRect)) {
            this.die();
            collides.die();
            TankFrame.INSTANCE.addGameObject(new Explode(collides.getX(), collides.getY()));
        }
    }

    private void die() {
        this.live = false;
    }
}