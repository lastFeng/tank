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

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo Weifeng
 * @version: 1.0
 * @create: 2020/6/12 15:03
 */
public class Bullet {
    private int x;
    private int y;
    private Direction direction;
    private TankGroup tankGroup;
    public static final int SPEED = 10;

    public Bullet(int x, int y, Direction direction, TankGroup tankGroup) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.tankGroup = tankGroup;
    }

    public void paint(Graphics graphics) {

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
        int[] move = Direction.move(true, x, y, SPEED, direction);
        if (move != null) {
            x = move[0];
            y = move[1];
        }
    }

}