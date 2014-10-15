import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;


import org.whs.Tournament.Fighter.Fighter;
import org.whs.Tournament.Fighter.RandomBot;
import org.whs.Tournament.Structure.League;

import java.util.ArrayList;

public class TestLeague {
    @Test
    public void testTournament() {
        ArrayList<Fighter> participants = new ArrayList<Fighter>();
        for(int i=0;i<20;i++) {
            Fighter f = new RandomBot(i);
            participants.add(f);
        }

        League l = new League(participants);
        l.resolve();
        Fighter winner = l.getWinner();

        assertThat(winner, instanceOf(RandomBot.class));
    }
}