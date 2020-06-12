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
package images;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import server.Tank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo Weifeng
 * @version: 1.0
 * @create: 2020/6/12 11:05
 */
public class ImagesTest {
    @Test
    public void testLoadImage() {
        try {
            BufferedImage badTank = ImageIO.read(new File("images/BadTank1.png"));
            Assertions.assertNotNull(badTank);

            BufferedImage goodTank = ImageIO.read(new File("images/GoodTank1.png"));
            Assertions.assertNotNull(goodTank);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRotateImage() {
        try {
            BufferedImage tank = ImageIO.read(Tank.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            Assertions.assertNotNull(tank);
            tank = rotateImage(tank, 90);
            Assertions.assertNotNull(tank);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage rotateImage(final BufferedImage tank, final int degree) {
        int width = tank.getWidth();
        int height = tank.getHeight();
        int type = tank.getColorModel().getTransparency();
        BufferedImage image;
        Graphics2D graphics2D;
        graphics2D = (image = new BufferedImage(width, height, type)).createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2D.rotate(Math.toRadians(degree), width / 2, height / 2);
        graphics2D.drawImage(tank, 0, 0, null);
        graphics2D.dispose();
        return image;
    }
}