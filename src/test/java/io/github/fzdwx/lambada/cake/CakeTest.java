package io.github.fzdwx.lambada.cake;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/6/11 10:43
 */
class CakeTest {

    @Test
    void test() {

        final MyCake myCake = new MyCake();
        System.out.println(myCake.empty().length());
        System.out.println(myCake.concat("hello", "world"));
        System.out.println(myCake.concatArray("ni", "hao", "im", "fzdwx"));
        System.out.println(myCake.repeat("hi", 5));
    }

    static class MyCake implements EmptyCake<MyCake>, ConcatCake<MyCake>, ArrayConcatCake<MyCake>, RepeatConcatCake<MyCake> {

        @Override
        public MyCake me() {
            return this;
        }
    }

}