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
import server.PropertyManager;
import server.object.collides.CollideChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo Weifeng
 * @version: 1.0
 * @create: 2020/6/17 10:24
 */
public class GameModel {
    private PlayerTank myPlayerTank;
    private List<AbstractGameObject> objects;
    private CollideChain collideChain;

    public GameModel() {
        this.collideChain = new CollideChain();
        initGames();
    }

    private void initGames() {
        this.objects = new ArrayList<>();
        this.myPlayerTank = new PlayerTank(400, 700, Direction.U);
        this.objects.add(myPlayerTank);
        int initEnemyTank = Integer.valueOf(PropertyManager.get("initTankCount"));
        for (int i = 0; i < initEnemyTank; i++) {
            this.objects.add(new NpcTank(80 * i, 50, Direction.randDirection()));
        }
    }

    public void paint(Graphics graphics) {
        for (int i = 0; i < objects.size(); i++) {
            AbstractGameObject object = objects.get(i);

            for (int j = i + 1; j < objects.size(); j++) {
                AbstractGameObject gameObject = objects.get(j);
                this.collideChain.collide(object, gameObject);
            }

            if (object.isLive()) {
                object.paint(graphics);
            } else {
                objects.remove(object);
            }
        }
    }

    public void addGameObject(AbstractGameObject object) {
        objects.add(object);
    }

    public PlayerTank getMyPlayerTank() {
        return myPlayerTank;
    }
}