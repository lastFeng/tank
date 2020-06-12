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
import java.awt.event.KeyAdapter;
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
public class TankFrame extends Frame {
    private static int GAME_WIDTH = 800;
    private static int GAME_HEIGHT = 800;
    private Tank myTank;
    private Tank enemyTank;
    private Image offScreenImage = null;

    public TankFrame(){
        this.myTank = new Tank(100, 100, Direction.R, TankGroup.GOOD);
        this.enemyTank = new Tank(200, 200, Direction.L, TankGroup.BAD);
        this.setTitle("坦克大战");
        this.setLocation(200, 200);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.addKeyListener(new TankKeyListener());
    }

    /**
     * 双缓冲，解决图片闪烁问题
     * 内存没有完全加载到显存中，在显示器中加载显存很快，所以会产生闪烁
     * 在内存中画好需要的内容，一次性加载到显存中，就不会出现闪烁现象
     * @param graphics
     */
    @Override
    public void update(Graphics graphics) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }

        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.WHITE);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        graphics.drawImage(offScreenImage, 0, 0, null);
    }

    /**
     * 绘画
     * @param graphics
     */
    @Override
    public void paint(Graphics graphics) {
        myTank.paint(graphics);
        enemyTank.paint(graphics);
    }

    /**
     * 键盘监听类
     */
    private class TankKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent event) {
            myTank.keyPressed(event);
        }

        @Override
        public void keyReleased(KeyEvent event) {
            myTank.keyReleased(event);
        }
    }
}