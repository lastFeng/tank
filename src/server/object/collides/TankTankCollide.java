package server.object.collides;

import server.object.AbstractGameObject;
import server.object.Tank;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2020/6/16 20:41
 * @description:
 */
public class TankTankCollide implements Collides{
    @Override
    public boolean collide(AbstractGameObject one, AbstractGameObject two) {

        if (one != two && one instanceof Tank && two instanceof Tank) {
            Tank tankOne = (Tank) one;
            Tank tankTwo = (Tank) two;

            if (one.isLive() && two.isLive()) {

                if (tankOne.getRectangle().intersects(tankTwo.getRectangle())) {
                    tankOne.restore();
                    tankTwo.restore();
                }
            }
        }

        return true;
    }
}
