package server.object.collides;

import server.TankFrame;
import server.object.AbstractGameObject;
import server.object.Explode;
import server.object.Tank;

import java.awt.*;

import static server.Constant.TANK_IMAGE_HEIGHT;
import static server.Constant.TANK_IMAGE_WITH;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2020/6/16 20:41
 * @description:
 */
public class TankTankCollide implements Collides{
    @Override
    public boolean collide(AbstractGameObject one, AbstractGameObject two) {

        if (one instanceof Tank && two instanceof Tank) {
            Tank tankOne = (Tank) one;
            Tank tankTwo = (Tank) two;

            if (one.isLive() && two.isLive()) {

                if (tankOne.getRectangle().intersects(tankTwo.getRectangle())) {
                    tankOne.collide();
                    tankOne.collide();
                }
            }
        }

        return true;
    }
}
