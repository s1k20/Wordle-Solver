package wordle;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static junit.framework.TestCase.assertEquals;


public class TestWordle {

    @Test
    void testHints() {
        Wordle wordleA = new Wordle();
        String target, guess;
        String [] hints;

        target = "abbey";
        guess = "keeps";
        hints = wordleA.generateHint(guess, target);
        assertEquals("[⬛, 🟨, ⬛, ⬛, ⬛]", Arrays.toString(hints));

        target = "abbey";
        guess = "kebab";
        hints = wordleA.generateHint(guess, target);
        assertEquals( "[⬛, 🟨, 🟩, 🟨, 🟨]", Arrays.toString(hints));

        target = "abbey";
        guess = "babes";
        hints = wordleA.generateHint(guess, target);
        assertEquals( "[🟨, 🟨, 🟩, 🟩, ⬛]", Arrays.toString(hints));

        target = "lobby";
        guess = "table";
        hints = wordleA.generateHint(guess, target);
        assertEquals("[⬛, ⬛, 🟩, 🟨, ⬛]", Arrays.toString(hints));

        target = "ghost";
        guess = "pious";
        hints = wordleA.generateHint(guess, target);
        assertEquals("[⬛, ⬛, 🟩, ⬛, 🟨]", Arrays.toString(hints));

        target = "ghost";
        guess = "slosh";
        hints = wordleA.generateHint(guess, target);
        assertEquals("[⬛, ⬛, 🟩, 🟩, 🟨]", Arrays.toString(hints));


        target = "kayak";
        guess = "aorta";
        hints = wordleA.generateHint(guess, target);
        assertEquals("[🟨, ⬛, ⬛, ⬛, 🟨]", Arrays.toString(hints));

        target = "kayak";
        guess = "kayak";
        hints = wordleA.generateHint(guess, target);
        System.out.println(target + ", " + guess + ", " + hints);
        assertEquals("[🟩, 🟩, 🟩, 🟩, 🟩]", Arrays.toString(hints));

        target = "kayak";
        guess = "fungi";
        hints = wordleA.generateHint(guess, target);
        System.out.println(target + ", " + guess + ", " + hints);
        assertEquals("[⬛, ⬛, ⬛, ⬛, ⬛]", Arrays.toString(hints));
    }
}
