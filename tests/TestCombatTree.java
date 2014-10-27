import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;


import org.whs.Tournament.Fighter.Fighter;
import org.whs.Tournament.Fighter.FighterCollection;
import org.whs.Tournament.Fighter.RandomBot;
import org.whs.Tournament.Structure.CombatTree;

import java.util.ArrayList;

public class TestCombatTree {
    @Test
    public void testTournament() {
        FighterCollection participants = new FighterCollection();
        for(int i=0;i<11;i++) {
            Fighter f = new RandomBot(i);
            participants.add(f);
        }

        CombatTree ct = new CombatTree(participants);
        ct.resolve();
        Fighter winner = ct.getWinner();

        assertThat(winner, instanceOf(RandomBot.class));
    }
}