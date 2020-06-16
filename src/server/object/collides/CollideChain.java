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

import server.PropertyManager;
import server.object.AbstractGameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo Weifeng
 * @version: 1.0
 * @create: 2020/6/16 14:47
 */
public class CollideChain implements Collides{
    private List<Collides> collides;

    public CollideChain() {
        initCollides();
    }

    @Override
    public boolean collide(AbstractGameObject one, AbstractGameObject two){
        for (Collides collide : collides) {
            if (!collide.collide(one, two)) {
                return false;
            }
        }
        return true;
    }

    private void initCollides() {
        this.collides = new ArrayList<>();
        String[] collides = PropertyManager.get("collides").split(",");
        Class clazz = null;
        Collides collide = null;
        for (String sub : collides) {
            try {
                clazz = Class.forName("server.object.collides." + sub);
                collide = (Collides)clazz.getDeclaredConstructor().newInstance();
                this.collides.add(collide);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}