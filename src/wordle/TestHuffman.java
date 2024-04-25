package wordle;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class TestHuffman {


    @Test
    void testHuffman() {
        String expected = "[f, fb, b, fbn, n, fbne, e, fbnejqxzvgsimp, j, jq, q, jqxz, x, xz, z, jqxzv, v, jqxzvg, g, jqxzvgs, s, jqxzvgsimp, i, imp, m, mp, p, fbnejqxzvgsimpltohdwkyruca, l, lt, t, ltohd, o, ohd, h, hd, d, ltohdwkyruca, w, wk, k, wky, y, wkyr, r, wkyruca, u, uc, c, uca, a]";


        Wordle game = new Wordle();
        Huffman huffman = new Huffman(String.join("", game.dictionary));
        String actual = huffman.huffmantree.inorder().toString();

        //System.out.println(expected);
        assertEquals(expected, actual);
    }
}
