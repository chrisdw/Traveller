package uk.org.downesward.traveller.encounters;

import java.io.StringWriter;

import uk.org.downesward.traveller.common.UPP;
import uk.org.downesward.utiliites.Dice;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

//animal v0.2 - a Traveller encounter table generator
//by D.S.Lewis 16/10/2007

//tables are based on LBB3 encounter rules

//todo list:
//herbivore behaviour should be FAS
//create an ecological assessment of each habitat - food chain & quantity

//version history:
//v0.2 - extracted the constant table data to a header file
//		- separated the Gen functions from Print functions, with an array to store critters
//		- scan the array of critters to find related ones across different regions
//v0.1 - basic implementation of the rules - just prints a tableusing System;
public class TableGenerator {
    /* global arrays to store the critters */
    public final int MAX_CRIT = 264;

    int[] cr_size = new int[MAX_CRIT];
    Integer[] cr_type = new Integer[MAX_CRIT];
    int[] cr_weapon = new int[MAX_CRIT];
    int[] cr_armour = new int[MAX_CRIT];
    int[] cr_attrib = new int[MAX_CRIT];
    Integer[] cr_attack = new Integer[MAX_CRIT];
    Integer[] cr_flee = new Integer[MAX_CRIT];
    Integer[] cr_speed = new Integer[MAX_CRIT];
    Integer[] cr_family = new Integer[MAX_CRIT];
    int cr_count = 0; // number of critters in the array

    int _tsize;
    UPP _upp;

    public void Generate(int tsize, UPP upp)
    {
        _tsize = tsize;
        _upp = upp;

        genTable(tsize, upp);
        findFamily(0);
        //prtTable(tsize, upp);
    }

    //---------------------------------------------------------------------------//
    // generate 1- or 2-dice encounter tables
    //---------------------------------------------------------------------------//
    protected void genTable(int dice, UPP upp)
    {
        genRegion(dice, "Clear, Road, Open", upp, 3, 0, 0);
        genRegion(dice, "Prairie, Plain, Steppe", upp, 4, 0, 0);
        genRegion(dice, "Rough, Hills, Foothills", upp, 0, 0, 0);
        genRegion(dice, "Broken, Badlands", upp, -3, -3, 0);
        genRegion(dice, "Mountain, Alpine", upp, 0, 0, 0);
        genRegion(dice, "Forest, Woods", upp, -4, -4, 0);
        genRegion(dice, "Jungle, Rainforest", upp, -3, -2, 0);
        genRegion(dice, "River, Stream, Creek", upp, 1, 1, 3);
        genRegion(dice, "Swamp, Bog", upp, -2, 4, 5);
        genRegion(dice, "Marsh, Wetland", upp, 0, -1, 2);
        genRegion(dice, "Desert, Dunes", upp, 3, -3, 0);
        genRegion(dice, "Beach, Shore, Sea Edge", upp, 3, 2, 1);
        genRegion(dice, "Surface, Ocean, Sea", upp, 2, 3, 4);
        genRegion(dice, "Shallows, Ocean, Sea", upp, 2, 2, 4);
        if (upp.hydro.getValue() > 0)
        {
            genRegion(dice, "Depths, Ocean, Sea", upp, 2, 4, 4);
            genRegion(dice, "Bottom, Ocean, Sea", upp, -4, 0, 4);
        }
        genRegion(dice, "Sea Cave, Sea Cavern", upp, -2, 0, 4);
        genRegion(dice, "Sargasso, Seaweed", upp, -4, -2, 4);
        genRegion(dice, "Ruins, Old City", upp, -3, 0, 0);
        genRegion(dice, "Cave, Cavern", upp, -4, 1, 0);
        genRegion(dice, "Chasm, Crevass. Abyss", upp, -1, -3, 0);
        genRegion(dice, "Crater, Hollow", upp, 0, -1, 0);

    }

    //---------------------------------------------------------------------------//
    // generate 1- or 2-dice encounter tables for a named region
    //---------------------------------------------------------------------------//
    protected void genRegion(int dice, String name, UPP upp, int typeDM, int sizeDM, int special)
    {
        //printf("\n%-60s UPP %-6s\n",name,upp);
        //printf("Die  Animal              Weight  Hits  Armour   Weapons           Wounds\n");
        if (dice == 1)
        {
            genCritter(1, "S", upp, typeDM, sizeDM, special);
            genCritter(2, "H", upp, typeDM, sizeDM, special);
            genCritter(3, "H", upp, typeDM, sizeDM, special);
            genCritter(4, "H", upp, typeDM, sizeDM, special);
            genCritter(5, "O", upp, typeDM, sizeDM, special);
            genCritter(6, "C", upp, typeDM, sizeDM, special);
        }
        else
        {
            genCritter(2, "S", upp, typeDM, sizeDM, special);
            genCritter(3, "O", upp, typeDM, sizeDM, special);
            genCritter(4, "S", upp, typeDM, sizeDM, special);
            genCritter(5, "O", upp, typeDM, sizeDM, special);
            genCritter(6, "H", upp, typeDM, sizeDM, special);
            genCritter(7, "H", upp, typeDM, sizeDM, special);
            genCritter(8, "H", upp, typeDM, sizeDM, special);
            genCritter(9, "C", upp, typeDM, sizeDM, special);
            genCritter(10, "E", upp, typeDM, sizeDM, special);
            genCritter(11, "C", upp, typeDM, sizeDM, special);
            genCritter(12, "C", upp, typeDM, sizeDM, special);
        }
    }

    //---------------------------------------------------------------------------//
    // generate a single critter, by type
    //---------------------------------------------------------------------------//
    protected void genCritter(int dnum, String ctype, UPP upp, int typeDM, int sizeDM, int special)
    {
        // other attributes of a critter
        int size = 0;
        int weapon = 0;
        int armour = 0;
        int attrib = 0; // flyer etc
        int att_roll = roll(6) + roll(6);
        int attack;
        int flee;
        int speed;

        // choose a specific type within the overall class
        int type = roll(6) + roll(6);

        // apply DMs
        type += typeDM;
        if (type < 0) type = 0;
        if (type > 13) type = 13;

        // add offset for overall class
        // no offset for herbivores
        if (ctype.equals("O")) type += 14;
        if (ctype.equals("C")) type += 28;
        if (ctype.equals("S")) type += 42;
        if (ctype.equals("E")) type += 56;

        // check for other attributes
        // adjust roll for planet-type
        if (upp.size.getValue() > 8) att_roll -= 1;
        if (upp.size.getValue() < 6) att_roll += 1;
        if (upp.size.getValue() > 4) att_roll += 1; // cumulative with the above for +2
        if (upp.atmosphere.getValue() > 7) att_roll += 1;
        if (upp.atmosphere.getValue() < 6) att_roll -= 1;
        if (att_roll < 2) att_roll = 2;
        if (att_roll > 12) att_roll = 12;

        if (special == 0)
        { // general terrain
            if (att_roll == 10) { attrib = 2; sizeDM = -6; }
            if (att_roll == 11) { attrib = 2; sizeDM = -5; }
            if (att_roll == 12) { attrib = 2; sizeDM = -3; }
        }
        if (special == 1)
        { // beach
            if (att_roll == 2) { attrib = 3; sizeDM += 1; }
            if (att_roll == 3) { attrib = 1; sizeDM += 2; }
            if (att_roll == 4) { attrib = 1; sizeDM += 2; }
            if (att_roll == 11) { attrib = 2; sizeDM = -6; }
            if (att_roll == 12) { attrib = 2; sizeDM = -5; }
        }
        if (special == 2)
        { // marsh
            if (att_roll == 2) { attrib = 3; sizeDM -= 6; }
            if (att_roll == 3) { attrib = 1; sizeDM += 2; }
            if (att_roll == 4) { attrib = 1; sizeDM += 1; }
            if (att_roll == 11) { attrib = 2; sizeDM = -6; }
            if (att_roll == 12) { attrib = 2; sizeDM = -5; }
        }
        if (special == 3)
        { // river
            if (att_roll == 2) { attrib = 3; sizeDM += 1; }
            if (att_roll == 3) { attrib = 1; sizeDM += 1; }
            if (att_roll == 11) { attrib = 2; sizeDM = -6; }
            if (att_roll == 12) { attrib = 2; sizeDM = -5; }
        }
        if (special == 4)
        { // sea
            if (att_roll == 2) { attrib = 3; sizeDM += 2; }
            if (att_roll == 3) { attrib = 3; sizeDM += 2; }
            if (att_roll == 4) { attrib = 3; sizeDM += 2; }
            if (att_roll == 5) { attrib = 1; sizeDM += 2; }
            if (att_roll == 6) { attrib = 1; sizeDM += 0; }
            if (att_roll == 7) { attrib = 3; sizeDM += 1; }
            if (att_roll == 8) { attrib = 3; sizeDM -= 1; }
            if (att_roll == 9) { attrib = 4; sizeDM -= 7; }
            if (att_roll == 10) { attrib = 4; sizeDM -= 6; }
            if (att_roll == 11) { attrib = 2; sizeDM = -6; }
            if (att_roll == 12) { attrib = 2; sizeDM = -5; }
        }
        if (special == 5)
        { // swamp
            if (att_roll == 2) { attrib = 3; sizeDM -= 3; }
            if (att_roll == 3) { attrib = 1; sizeDM += 1; }
            if (att_roll == 4) { attrib = 1; sizeDM += 1; }
            if (att_roll == 11) { attrib = 2; sizeDM = -6; }
            if (att_roll == 12) { attrib = 2; sizeDM = -5; }
        }

        // adjust size for planet
        if (upp.size.getValue() > 7) sizeDM -= 1;
        if (upp.size.getValue() < 5) sizeDM += 1;


        // derive a size
        size = roll(6) + roll(6) + sizeDM;
        if (size < 1) size = 1;
        if (size > 20) size = 20;
        while (TableData.weights[size].startsWith("*"))
        {
            size = roll(6) + roll(6) + sizeDM + 6;
            if (size < 1) size = 1;
            if (size > 20) size = 20;
        }

        if (ctype.equals("E")) size = 0;


        // derive armour
        armour = roll(6) + roll(6);
        if (ctype.equals("C")) armour -= 1;
        if (ctype.equals("S")) armour += 1;
        if (ctype.equals("H")) armour += 2;
        if (armour < 1) armour = 1;
        if (armour > 20) armour = 20;
        while (TableData.armours[armour].startsWith("*"))
        {
            armour = roll(6) + roll(6) + 6;
            if (ctype.equals("C")) armour -= 1;
            if (ctype.equals("S")) armour += 1;
            if (ctype.equals("H")) armour += 2;
            if (armour < 1) armour = 1;
            if (armour > 20) armour = 20;
        }

        if (ctype.equals("E")) armour = 0;

        // derive weapons
        weapon = roll(6) + roll(6);
        if (ctype.equals("O")) weapon += 4;
        if (ctype.equals("C")) weapon += 8;
        if (ctype.equals("H")) weapon -= 3;
        if (weapon < 1) weapon = 1;
        if (weapon > 20) weapon = 20;

        if (ctype.equals("E")) weapon = 0;

        // derive behaviours
        attack = roll(6); flee = roll(6); speed = roll(6); // defaults
        if ((TableData.ctypes[type]).startsWith("F"))
        { // filter
            attack = 0; flee += 2; speed -= 5;
        }
        if ((TableData.ctypes[type]).startsWith("I") && ctype.equals("H"))
        { // intermittent
            attack += 3; flee += 3; speed -= 4;
        }
        if ((TableData.ctypes[type]).startsWith("G") && ctype.equals("H"))
        { // grazer
            attack += 2; flee += 0; speed -= 2;
        }
        if ((TableData.ctypes[type]).startsWith("G") && ctype.equals("O"))
        { // gatherer
            attack += 3; flee += 2; speed -= 3;
        }
        if ((TableData.ctypes[type]).startsWith("H") && ctype.equals("O"))
        { // hunter
            attack += 0; flee += 2; speed -= 4;
        }
        if ((TableData.ctypes[type]).startsWith("E"))
        { // eater
            attack += 0; flee += 3; speed -= 3;
        }
        if ((TableData.ctypes[type]).startsWith("P"))
        { // pouncer
            attack = 0; flee = 0; speed -= 4;
        }
        if ((TableData.ctypes[type]).startsWith("C") && ctype.equals("C"))
        { // chaser
            attack = 0; flee += 3; speed -= 2;
        }
        if ((TableData.ctypes[type]).startsWith("T"))
        { // trapper
            attack = 0; flee += 2; speed -= 5;
        }
        if ((TableData.ctypes[type]).startsWith("S"))
        { // siren
            attack = 0; flee += 3; speed -= 4;
        }
        if ((TableData.ctypes[type]).startsWith("K"))
        { // killer
            attack += 0; flee += 3; speed -= 3;
        }
        if ((TableData.ctypes[type]).startsWith("H") && ctype.equals("S"))
        { // hijacker
            attack += 1; flee += 2; speed -= 4;
        }
        if ((TableData.ctypes[type]).startsWith("I") && ctype.equals("S"))
        { // intimidater
            attack += 2; flee += 1; speed -= 4;
        }
        if ((TableData.ctypes[type]).startsWith("C") && ctype.equals("S"))
        { // carrion-eater
            attack += 3; flee += 2; speed -= 3;
        }
        if ((TableData.ctypes[type]).startsWith("R") && ctype.equals("S"))
        { // reducer
            attack += 3; flee += 2; speed -= 4;
        }

        if (speed < 0) speed = 0;

        // output the critter to the table
        cr_size[cr_count] = size;
        cr_type[cr_count] = type;
        cr_weapon[cr_count] = weapon + TableData.weapon_dups[weapon]; // normalise repeated weapons to a single index
        cr_armour[cr_count] = armour;
        cr_attrib[cr_count] = attrib;
        cr_attack[cr_count] = attack;
        cr_flee[cr_count] = flee;
        cr_speed[cr_count] = speed;
        cr_family[cr_count] = 0;
        if (cr_count < MAX_CRIT) cr_count += 1; // increment counter (if possible)
    }


    public void WriteStreamAsText(StringWriter sw)
    {
        prtTable(sw, _tsize, _upp);
    }

    public void WriteToXML(Node parent)
    {
        // initialise counter to iterate through the arrays

        cr_count = 0;
        int dice = _tsize;
        UPP upp = _upp;

        xmlRegion(parent, dice, "Clear, Road, Open", upp);
        xmlRegion(parent, dice, "Prairie, Plain, Steppe", upp);
        xmlRegion(parent, dice, "Rough, Hills, Foothills", upp);
        xmlRegion(parent, dice, "Broken, Badlands", upp);
        xmlRegion(parent, dice, "Mountain, Alpine", upp);
        xmlRegion(parent, dice, "Forest, Woods", upp);
        xmlRegion(parent, dice, "Jungle, Rainforest", upp);
        xmlRegion(parent, dice, "River, Stream, Creek", upp);
        xmlRegion(parent, dice, "Swamp, Bog", upp);
        xmlRegion(parent, dice, "Marsh, Wetland", upp);
        xmlRegion(parent, dice, "Desert, Dunes", upp);
        xmlRegion(parent, dice, "Beach, Shore, Sea Edge", upp);
        xmlRegion(parent, dice, "Surface, Ocean, Sea", upp);
        xmlRegion(parent, dice, "Shallows, Ocean, Sea", upp);
        if (upp.hydro.getValue() > 0)
        {
            xmlRegion(parent, dice, "Depths, Ocean, Sea", upp);
            xmlRegion(parent, dice, "Bottom, Ocean, Sea", upp);
        }
        xmlRegion(parent, dice, "Sea Cave, Sea Cavern", upp);
        xmlRegion(parent, dice, "Sargasso, Seaweed", upp);
        xmlRegion(parent, dice, "Ruins, Old City", upp);
        xmlRegion(parent, dice, "Cave, Cavern", upp);
        xmlRegion(parent, dice, "Chasm, Crevass. Abyss", upp);
        xmlRegion(parent, dice, "Crater, Hollow", upp);
    }

    private void xmlRegion(Node parent, int dice, String name, UPP _upp)
    {
        Element region = parent.getOwnerDocument().createElement("Region");
        Element nameEle = parent.getOwnerDocument().createElement("name");
        nameEle.setTextContent(name);
        region.appendChild(nameEle);

        if (dice == 1)
        {
            xmlCritter(region, 1);
            xmlCritter(region, 2);
            xmlCritter(region, 3);
            xmlCritter(region, 4);
            xmlCritter(region, 5);
            xmlCritter(region, 6);
        }
        else
        {
            xmlCritter(region, 2);
            xmlCritter(region, 3);
            xmlCritter(region, 4);
            xmlCritter(region, 5);
            xmlCritter(region, 6);
            xmlCritter(region, 7);
            xmlCritter(region, 8);
            xmlCritter(region, 9);
            xmlCritter(region, 10);
            xmlCritter(region, 11);
            xmlCritter(region, 12);
        }
        parent.appendChild(region);
    }

    private void xmlCritter(Element region, Integer dnum)
    {
        Element critter = region.getOwnerDocument().createElement("critter");

        Attr ctype = region.getOwnerDocument().createAttribute("type");
        ctype.setValue(cr_type[cr_count].toString());
        critter.setAttributeNode(ctype);
        Attr dnumAttr = region.getOwnerDocument().createAttribute("dnum");
        dnumAttr.setValue(dnum.toString());
        critter.setAttributeNode(dnumAttr);

        if (cr_attrib[cr_count] > 0)
        {
        	Attr attribAttr = region.getOwnerDocument().createAttribute("attribute");
        	String attrib = TableData.attribs.substring(cr_attrib[cr_count],cr_attrib[cr_count]+1).toString();
            attribAttr.setValue(attrib);
            critter.setAttributeNode(attribAttr);
        }
        Element ctypeDescr = region.getOwnerDocument().createElement("description");
        ctypeDescr.setTextContent(TableData.ctypes[cr_type[cr_count]].trim());
        critter.appendChild(ctypeDescr);

        if (cr_type[cr_count] <= 55)
        {
            Element ctypeWeight = region.getOwnerDocument().createElement("weight");
            ctypeWeight.setTextContent(TableData.weights[cr_size[cr_count]]);
            critter.appendChild(ctypeWeight);

            Element ctypeArmour = region.getOwnerDocument().createElement("armour");
            ctypeArmour.setTextContent(TableData.armours[cr_armour[cr_count]]);
            critter.appendChild(ctypeArmour);

            Element ctypeWeapons = region.getOwnerDocument().createElement("weapons");
            ctypeWeapons.setTextContent(TableData.weapons[cr_weapon[cr_count]]);
            critter.appendChild(ctypeWeapons);

            if (TableData.wounds[cr_size[cr_count]].trim().length() > 0)
            {
                Element ctypeWounds = region.getOwnerDocument().createElement("wounds");
                ctypeWounds.setTextContent(TableData.wounds[cr_size[cr_count]]);
                critter.appendChild(ctypeWounds);
            }

            Element ctypeAttack = region.getOwnerDocument().createElement("attack");
            ctypeAttack.setTextContent(cr_attack[cr_count].toString());
            critter.appendChild(ctypeAttack);

            Element ctypeFlee = region.getOwnerDocument().createElement("flee");
            ctypeFlee.setTextContent(cr_flee[cr_count].toString());
            critter.appendChild(ctypeFlee);

            Element ctypeSpeed = region.getOwnerDocument().createElement("speed");
            ctypeSpeed.setTextContent(cr_speed[cr_count].toString());
            critter.appendChild(ctypeSpeed);
        }

        if (cr_family[cr_count] > 0)
        { // critter is part of a family
            Attr familyAttr = region.getOwnerDocument().createAttribute("family");
            familyAttr.setTextContent(cr_family[cr_count].toString());
            critter.setAttributeNode(familyAttr);
        }

        region.appendChild(critter);
        if (cr_count < MAX_CRIT) cr_count += 1; // increment counter (if possible)
    }

    //---------------------------------------------------------------------------//
    // output 1- or 2-dice encounter tables
    //---------------------------------------------------------------------------//
    protected void prtTable(StringWriter sw, int dice, UPP upp)
    {
        // initialise counter to iterate through the arrays

        cr_count = 0;

        prtRegion(sw, dice, "Clear, Road, Open", upp);
        prtRegion(sw, dice, "Prairie, Plain, Steppe", upp);
        prtRegion(sw, dice, "Rough, Hills, Foothills", upp);
        prtRegion(sw, dice, "Broken, Badlands", upp);
        prtRegion(sw, dice, "Mountain, Alpine", upp);
        prtRegion(sw, dice, "Forest, Woods", upp);
        prtRegion(sw, dice, "Jungle, Rainforest", upp);
        prtRegion(sw, dice, "River, Stream, Creek", upp);
        prtRegion(sw, dice, "Swamp, Bog", upp);
        prtRegion(sw, dice, "Marsh, Wetland", upp);
        prtRegion(sw, dice, "Desert, Dunes", upp);
        prtRegion(sw, dice, "Beach, Shore, Sea Edge", upp);
        prtRegion(sw, dice, "Surface, Ocean, Sea", upp);
        prtRegion(sw, dice, "Shallows, Ocean, Sea", upp);
        if (upp.hydro.getValue() > 0)
        {
            prtRegion(sw, dice, "Depths, Ocean, Sea", upp);
            prtRegion(sw, dice, "Bottom, Ocean, Sea", upp);
        }
        prtRegion(sw, dice, "Sea Cave, Sea Cavern", upp);
        prtRegion(sw, dice, "Sargasso, Seaweed", upp);
        prtRegion(sw, dice, "Ruins, Old City", upp);
        prtRegion(sw, dice, "Cave, Cavern", upp);
        prtRegion(sw, dice, "Chasm, Crevass. Abyss", upp);
        prtRegion(sw, dice, "Crater, Hollow", upp);

    }

    //---------------------------------------------------------------------------//
    // output 1- or 2-dice encounter tables for a named region
    //---------------------------------------------------------------------------//
    protected void prtRegion(StringWriter sw, int dice, String name, UPP upp)
    {
        sw.write("\n");
        sw.write(String.format("%-65s UPP %-6s\n", name, upp.physicalUPP()));
        sw.write("Die  Animal              Weight  Hits  Armour   Weapons           Wounds\n");
        if (dice == 1)
        {
            prtCritter(sw, 1);
            prtCritter(sw, 2);
            prtCritter(sw, 3);
            prtCritter(sw, 4);
            prtCritter(sw, 5);
            prtCritter(sw, 6);
        }
        else
        {
            prtCritter(sw, 2);
            prtCritter(sw, 3);
            prtCritter(sw, 4);
            prtCritter(sw, 5);
            prtCritter(sw, 6);
            prtCritter(sw, 7);
            prtCritter(sw, 8);
            prtCritter(sw, 9);
            prtCritter(sw, 10);
            prtCritter(sw, 11);
            prtCritter(sw, 12);
        }
    }

    //---------------------------------------------------------------------------//
    // output a single critter, by array index
    // the critter to output is indexed by the global int cr_count
    // and this function increments the index ready for the next call
    //---------------------------------------------------------------------------//
    protected void prtCritter(StringWriter sw, int dnum)
    {

        // print the critter to the output stream
        if (cr_type[cr_count] < 14)
        { // special treatment for herbivores - FAS behaviour
            sw.write(String.format("%-2s %s %s %s %s %s%s F%dA%dS%d",
                dnum, TableData.attribs.substring(cr_attrib[cr_count], cr_attrib[cr_count]+1), TableData.ctypes[cr_type[cr_count]],
                TableData.weights[cr_size[cr_count]], TableData.armours[cr_armour[cr_count]],
                TableData.weapons[cr_weapon[cr_count]], TableData.wounds[cr_size[cr_count]],
                cr_flee[cr_count], cr_attack[cr_count], cr_speed[cr_count]));
        }
        else if (cr_type[cr_count] > 55)
        {
            // Event
            sw.write(String.format("%-2s %s %s\n",
                dnum, TableData.attribs.substring(cr_attrib[cr_count], cr_attrib[cr_count]), TableData.ctypes[cr_type[cr_count]]));
        }
        else
        {
            sw.write(String.format("%-2s %s %s %s %s %s%s A%sF%sS%s",
                dnum, TableData.attribs.substring(cr_attrib[cr_count], cr_attrib[cr_count]+1), TableData.ctypes[cr_type[cr_count]],
                TableData.weights[cr_size[cr_count]], TableData.armours[cr_armour[cr_count]],
                TableData.weapons[cr_weapon[cr_count]], TableData.wounds[cr_size[cr_count]],
                cr_attack[cr_count], cr_flee[cr_count], cr_speed[cr_count]));
        }

        if (cr_family[cr_count] > 0)
        { // critter is part of a family
            sw.write(String.format(" *%-2d", cr_family[cr_count]));
        }

        sw.write("\n");

        if (cr_count < MAX_CRIT) cr_count += 1; // increment counter (if possible)

    }


    //---------------------------------------------------------------------------//
    // roll a die of the specified shape
    //---------------------------------------------------------------------------//
    int roll(int sides)
    {
        Dice die = new Dice(sides);

        int dieRoll = die.roll();
        return (dieRoll);
    }

    //---------------------------------------------------------------------------//
    // find similar critters in the arrays and set their family indicators
    //---------------------------------------------------------------------------//
    protected void findFamily(int cr_num)
    {
        int fam_count = 0; // used to set next family indicator
        int cr_xref = 0;   // 2nd array index to iterate critters to compare with cr_num

        // for each critter in the array
        for (cr_num = 0; cr_num < cr_count; cr_num++)
        {
            for (cr_xref = cr_num + 1; cr_xref <= cr_count; cr_xref++)
            {
                if ((cr_type[cr_num] < 56) && // not applicable to events (only critters)
                   (cr_type[cr_num] == cr_type[cr_xref]) && // same behaviour
                   (cr_weapon[cr_num] == cr_weapon[cr_xref]) && // same weapon
                   (cr_attrib[cr_num] == cr_attrib[cr_xref]) && // same locomotion
                   (Math.abs(cr_size[cr_num] - cr_size[cr_xref]) < 2))
                { // similar size
                    if (cr_family[cr_num] == 0)
                    { // not part of a family yet
                        fam_count++; // assign next family number
                        cr_family[cr_num] = fam_count;
                    }
                    cr_family[cr_xref] = cr_family[cr_num]; // join xref to same family
                }
            }
        }

    }

}
