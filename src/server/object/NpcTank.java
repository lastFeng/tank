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
import server.TankGroup;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

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

    private Random random = new Random();

    public NpcTank(int x, int y, Direction direction) {
        super(x, y, direction, TankGroup.BAD);
        this.moving = true;
    }

    @Override
    public void paint(Graphics graphics) {

        super.paint(graphics);
        randomDirection();
        if (random.nextInt(100) > 90) {
            //fire();
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {

    }

    @Override
    public void keyReleased(KeyEvent event) {
    }

    private void randomDirection() {
        if (random.nextInt(100) > 90) {
            direction = Direction.randDirection();
        }
    }

}