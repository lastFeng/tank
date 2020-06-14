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

import static server.Constant.*;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo Weifeng
 * @version: 1.0
 * @create: 2020/6/12 10:36
 */
public class NpcTank extends Tank{

    public NpcTank(int x, int y, Direction direction) {
        super(x, y, direction);
        this.tankGroup = TankGroup.BAD;
    }

    @Override
    public void paint(Graphics graphics) {

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

        int[] move = Direction.move(this.moving, x, y, TANK_MOVE_SPEED, direction);
        if (move != null) {
            x = move[0];
            y = move[1];
        }
    }

}