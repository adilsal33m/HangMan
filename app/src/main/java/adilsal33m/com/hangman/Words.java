package adilsal33m.com.hangman;

import java.util.Random;

/**
 * Created by Adil Saleem on 7/4/2017.
 */

public class Words {

    private static String[] words=("unprolonged\n" +
            "dernier\n" +
            "warwick\n" +
            "clientage\n" +
            "indecomposableness\n" +
            "homogeneity\n" +
            "translucidus\n" +
            "pibroch\n" +
            "unemissive\n" +
            "sthenias\n" +
            "cyberneticist\n" +
            "nonremovable\n" +
            "alboin\n" +
            "susurrate\n" +
            "overshoe\n" +
            "sharia\n" +
            "cryptogrammic\n" +
            "xenodiagnostic\n" +
            "benzoylate\n" +
            "heterolysis\n" +
            "foxberry\n" +
            "philosophization\n" +
            "lorestan\n" +
            "balconette\n" +
            "leavening\n" +
            "intoxicated\n" +
            "vitality\n" +
            "epigenesis\n" +
            "presentable\n" +
            "tallulah\n" +
            "adfreeze\n" +
            "leprologist\n" +
            "predestruction\n" +
            "electrometry\n" +
            "proclaim\n" +
            "fluorination\n" +
            "isostatically\n" +
            "unconsoling\n" +
            "nurseries\n" +
            "knitting\n" +
            "reproachably\n" +
            "traditionalist\n" +
            "discuses\n" +
            "eristic\n" +
            "laconic\n" +
            "andizhan\n" +
            "inflicter\n" +
            "noninclusive\n" +
            "millilux\n" +
            "monasticism\n" +
            "kerugma\n" +
            "unluxurious\n" +
            "euphoniousness\n" +
            "aerogenous\n" +
            "uncontributed\n" +
            "superpositive\n" +
            "sunnite\n" +
            "kalmarian\n" +
            "unwidowed\n" +
            "symbolistic\n" +
            "carpetbagged\n" +
            "grenadierial\n" +
            "unpsychotic\n" +
            "reportorial\n" +
            "leatheroid\n" +
            "calentural\n" +
            "unestranged\n" +
            "liliaceous\n" +
            "nonessential\n" +
            "countrypeople\n" +
            "undertone\n" +
            "impanation\n" +
            "binaural\n" +
            "covenant\n" +
            "axillar\n" +
            "outslang\n" +
            "connubially\n" +
            "nonrespectable\n" +
            "protectorate\n" +
            "cyane\n" +
            "adoptively\n" +
            "potawatomi\n" +
            "unmedicable\n" +
            "officiary\n" +
            "boulevard\n" +
            "bulldozer\n" +
            "transmissibility\n" +
            "unsolicitous\n" +
            "tasajillo\n" +
            "chestnut\n" +
            "unflavored\n" +
            "unfluent\n" +
            "miffier\n" +
            "assignably\n" +
            "instituter\n" +
            "seashore\n" +
            "hazardous\n" +
            "enervated\n" +
            "underproposition\n" +
            "repairer").split("\n");

    public static String getWord(){
        return words[new Random().nextInt(words.length)];
    }
}
