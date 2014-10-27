import org.junit.Test;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.whs.Tournament.Fighter.Fighter;
import org.whs.Tournament.Fighter.HumanFighter;
import org.whs.Tournament.Fighter.NullFighter;
import org.whs.Tournament.Fighter.RandomBot;
import org.whs.Tournament.Structure.Combat.Combat;


public class TestCombat {
    @Test
    public void testCombatNull() {
        NullFighter nf1 = new NullFighter();
        NullFighter nf2 = new NullFighter();
        HumanFighter hf = new HumanFighter("Dimitri");
        RandomBot rbf1 = new RandomBot(1);
        RandomBot rbf2 = new RandomBot(2);

        Combat c;
        Fighter winner;

        c = new Combat(nf1,nf2);
        winner = c.resolve();
        assertThat(winner, instanceOf(NullFighter.class));

        c = new Combat(nf1,hf);
        winner = c.resolve();
        assertThat(winner, instanceOf(HumanFighter.class));

        c = new Combat(rbf1,nf1);
        winner = c.resolve();
        assertThat(winner, instanceOf(RandomBot.class));

        c = new Combat(rbf1,rbf2);
        winner = c.resolve();
        assertThat(winner, instanceOf(RandomBot.class));
    }
}