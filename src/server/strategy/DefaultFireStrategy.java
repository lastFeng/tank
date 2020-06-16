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
package server.strategy;

import server.TankFrame;
import server.object.Bullet;
import server.object.Tank;

import static server.Constant.BULLET_IMAGE_HEIGHT;
import static server.Constant.BULLET_IMAGE_WITH;
import static server.Constant.TANK_IMAGE_HEIGHT;
import static server.Constant.TANK_IMAGE_WITH;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo Weifeng
 * @version: 1.0
 * @create: 2020/6/16 9:49
 */
public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        TankFrame tankFrame = TankFrame.INSTANCE;
        int bulletX = tank.getX() + TANK_IMAGE_WITH / 2 - BULLET_IMAGE_WITH / 2;
        int bulletY = tank.getY() + TANK_IMAGE_HEIGHT / 2 - BULLET_IMAGE_HEIGHT / 2;
        tankFrame.addGameObject(new Bullet(bulletX, bulletY, tank.getDirection(), tank.getTankGroup()));
    }
}