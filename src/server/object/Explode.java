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

import server.Audio;
import server.ResourceManager;

import java.awt.*;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo Weifeng
 * @version: 1.0
 * @create: 2020/6/15 13:46
 */
public class Explode extends AbstractGameObject{

    private int x;
    private int y;
    private int step;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        this.step = 0;
        this.live = true;
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    @Override
    public void paint(Graphics graphics) {
        if (step >= ResourceManager.explodes.length) {
            this.live = false;
            return;
        }
        graphics.drawImage(ResourceManager.explodes[step], x, y, null);
        step++;
    }

    public boolean isFinished() {
        return !this.live;
    }
}