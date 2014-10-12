import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.whs.Tournament.Fighter.Bot;
import org.whs.Tournament.Fighter.Fighter;
import org.whs.Tournament.Fighter.HumanFighter;
import org.whs.Tournament.Fighter.RandomBot;

public class TestFighter {
    @Test
    public void testClass() {
        Fighter f = new Fighter();
        assertThat(f, instanceOf(Fighter.class));
    }

    @Test
    public void testName() {
        String name = "El Perro";
        Fighter f = new HumanFighter(name);
        assertEquals(name,f.getName());
    }

    @Test
    public void testType() {
        String name = "El Fuerte";
        Fighter f = new HumanFighter(name);
        assertEquals(Fighter.FIGHTER_TYPE_HUMAN,f.getType());

        f = new Fighter();
        assertEquals(Fighter.FIGHTER_TYPE_EMPTY,f.getType());

        f = new Bot(1,1);
        assertEquals(Fighter.FIGHTER_TYPE_BOT,f.getType());

        f = new RandomBot(1);
        assertEquals(Fighter.FIGHTER_TYPE_BOT,f.getType());
    }

    @Test
    public void testDifficulty() {
        String name = "Yoshimitsu";
        Fighter f = new HumanFighter(name);
        assertEquals(Fighter.DEFAULT_DIFFICULTY, f.getDifficulty());

        for(int i=0;i<5;i++) {
            f = new Bot(i,i);
            assertTrue(f.getDifficulty() >= (i*20 - 10));
            assertTrue(f.getDifficulty() <= (i*20 + 10));
        }
    }

    @Test
    public void testWins() {
        String name = "Gordon Freeman";
        Fighter f = new HumanFighter(name);
        f.addWin();
        assertEquals(f.getWins(),1);
    }

    @Test
    public void testLoses() {
        String name = "Yamscha";
        Fighter f = new HumanFighter(name);
        f.addLose();
        assertEquals(f.getLoses(),1);
    }
}
