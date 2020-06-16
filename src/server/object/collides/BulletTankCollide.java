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
package server.object.collides;

import server.object.AbstractGameObject;
import server.object.Bullet;
import server.object.Tank;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo Weifeng
 * @version: 1.0
 * @create: 2020/6/16 14:00
 */
public class BulletTankCollide implements Collides {
    @Override
    public void collide(AbstractGameObject one, AbstractGameObject two) {
        if (one instanceof Bullet && two instanceof Tank) {
            Bullet bullet = (Bullet) one;
            Tank tank = (Tank) two;
            bullet.collideWithTank(tank);
        } else if (one instanceof Tank && two instanceof Bullet) {
            collide(two, one);
        }
    }
}