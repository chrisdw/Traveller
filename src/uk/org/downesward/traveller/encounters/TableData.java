package uk.org.downesward.traveller.encounters;
//animal v0.2 - a Traveller encounter table generator
//by D.S.Lewis 16/10/2007

//---------------------------------------------------------------------------//
//global tables
//---------------------------------------------------------------------------//
//constant table of critter types
public class TableData {
    public static String[] ctypes = {
        "Filter (1D)       ",
        "Filter            ",
        "Filter            ",
        "Intermittent      ",
        "Intermittent      ",
        "Intermittent (1D) ",
        "Intermittent      ",
        "Grazer            ",
        "Grazer (1D)       ",
        "Grazer (2D)       ",
        "Grazer (3D)       ",
        "Grazer (2D)       ",
        "Grazer (4D)       ",
        "Grazer (5D)       ",
        "Gatherer          ",
        "Gatherer          ",
        "Eater             ",
        "Gatherer          ",
        "Eater (2D)        ",
        "Gatherer          ",
        "Hunter            ",
        "Hunter (1D)       ",
        "Hunter            ",
        "Gatherer          ",
        "Eater (1D)        ",
        "Hunter (1D)       ",
        "Gatherer          ",
        "Gatherer          ",
        "Siren             ",
        "Pouncer           ",
        "Siren             ",
        "Pouncer           ",
        "Killer (1D)       ",
        "Trapper           ",
        "Pouncer           ",
        "Chaser            ",
        "Chaser (3D)       ",
        "Chaser            ",
        "Killer            ",
        "Chaser (2D)       ",
        "Siren             ",
        "Chaser (1D)       ",
        "Carrion-eater (1D)",
        "Carrion-eater (2D)",
        "Reducer (1D)      ",
        "Hijacker (1D)     ",
        "Carrion-eater (2D)",
        "Intimidator (1D)  ",
        "Reducer           ",
        "Carrion-eater (1D)",
        "Reducer (3D)      ",
        "Hijacker          ",
        "Intimidator (2D)  ",
        "Reducer (1D)      ",
        "Hijacker          ",
        "Intimidator (1D)  ",
        "Event 0           ",
        "Event 1           ",
        "Event 2           ",
        "Event 3           ",
        "Event 4           ",
        "Event 5           ",
        "Event 6           ",
        "Event 7           ",
        "Event 8           ",
        "Event 9           ",
        "Event 10          ",
        "Event 11          ",
        "Event 12          ",
        "Event 13          "
    };

    // constant table of weights, by size (starting at 0)
    public static String[] weights ={
        "       ",
        "    1Kg  1D/0 ",
        "    3Kg  1D/1D",
        "    6Kg  1D/2D",
        "   12Kg  2D/2D",
        "   25Kg  3D/2D",
        "   50Kg  4D/2D",
        "  100Kg  5D/2D",
        "  200Kg  5D/3D",
        "  400Kg  6D/3D",
        "  800Kg  7D/3D",
        " 1600Kg  8D/3D",
        " 3200Kg  8D/4D",
        "*  (+6)       ",
        " 6000Kg  9D/4D",
        "12000Kg 10D/5D",
        "24000Kg 12D/6D",
        "30000Kg 14D/7D",
        "36000Kg 15D/7D",
        "40000Kg 16D/8D",
        "44000Kg 17D/9D"
    };

    // constant table of armour names, by armour type (starting at 0)
    public static String[] armours ={
        "        ",
        "* (+6)  ",
        "        ",
        "        ",
        "jack    ",
        "        ",
        "        ",
        "        ",
        "        ",
        "        ",
        "jack    ",
        "        ",
        "* (+6)  ",
        "mesh+1  ",
        "cloth+1 ",
        "mesh    ",
        "cloth   ",
        "battle+4",
        "reflec  ",
        "ablat   ",
        "battle  "
    };

    // constant table of wound mods, by size (starting at 0)
    public static String[] wounds ={
        "   ",
        "-2D",
        "-2D",
        "-1D",
        "   ",
        "   ",
        "   ",
        "   ",
        "+1D",
        "+2D",
        "+3D",
        "+4D",
        "+5D",
        "*+6",
        "x2 ",
        "x2 ",
        "x3 ",
        "x4 ",
        "x4 ",
        "x5 ",
        "x6 "
    };

    // constant table of weapon names & damage, by weapon type (starting at 0)
    public static String[] weapons ={
        "                      ",
        "hooves & horns    4D",
        "horns             2D",
        "hooves & teeth    4D",
        "hooves            2D",
        "horns & teeth     4D",
        "thrasher          2D",
        "claws & teeth     3D",
        "teeth             2D",
        "claws             1D",
        "claws             1D",
        "thrasher          2D",
        "claws & teeth     3D",
        "claws+1         1D+1",
        "stinger           3D",
        "claws+1 teeth+1 4D+2",
        "teeth+1         2D+1",
        "as blade          2D",
        "as pike           3D",
        "as broadsword     4D",
        "as body pistol    3D"
    };

    // offset adjustments for duplicated weapon values
    // add these to the weapon index - it makes no difference to the result,
    // but ensures that identical results have identical indices
    public static int[] weapon_dups ={ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, -5, 0, 0, 0, 0, 0, 0, 0, 0 };

    public static String attribs = " AFST";
}
