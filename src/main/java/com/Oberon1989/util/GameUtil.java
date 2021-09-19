package com.Oberon1989.util;

import com.Oberon1989.entites.Map;
import com.Oberon1989.entites.Player;
import com.Oberon1989.entites.maps.*;
import com.Oberon1989.entites.playerTransfer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class GameUtil {
    public static CardDeck CreateCardDeck() {
      ArrayList<Map> maps = new ArrayList<>();
        maps.add(new simpleCard(Map.MapColor.red, 13, "Бастион", "+12 к стене", "024.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.red, 1, "Благодатная почва", "+1 к стене,\nиграем снова", "003.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, true));

        maps.add(new bigVein());
        maps.add(new simpleCard(Map.MapColor.red, 3, "Большая стена", "+4 к стене", "009.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.red, 0, "Бракованная руда", "Все игроки теряют\nпо 8 руды", "001.png", 0, 0, -8, -8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.red, 8, "Великая стена", "+8 к стене", "020.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.red, 16, "Величайшая стена", "+15 к стене", "026.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.red, 9, "Галереи", "+5 к стене,\n+1 казарма", "021.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 5, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.red, 7, "Гномы-шахтёры", "+4 к стене,\n+1 шахта", "006.png", 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 4, 0, 0, 0, false));

        maps.add(new groundWater());

        maps.add(new simpleCard(Map.MapColor.red, 0, "Землетрясение", "-1 шахта всех игроков", "015.png", 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, false));

        maps.add(new barracks());

        maps.add(new stealingTechnology());

        maps.add(new simpleCard(Map.MapColor.red, 9, "Магическая гора", "+7 к стене,\n+7 маны", "022.png", 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.red, 6, "Новое оборудование", "+2 к шахте", "018.png", 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.red, 2, "Новшества", "+1 к шахте всех игроков,\nвы получаете 4 маны", "011.png", 4, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.red, 15, "Новые успехи", "+8 к стене,\n+5 к башне", "025.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 8, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.red, 4, "Обвал", "-1 шахта врага", "017.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.red, 0, "Обвал рудника", "-1 шахта,\n+10 к стене,\nвы получаете 5 маны", "019.png", 5, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 10, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.red, 2, "Обычная стена", "+3 к стене", "010.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.red, 11, "Поющий уголь", "+6 к стене,\n+3 к башне", "023.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 6, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.red, 7, "Рабский труд", "+9 к стене,\nвы теряете 5 отрядов", "029.png", 0, 0, 0, 0, -5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.red, 1, "Сад камней", "+1 к стене,\n+1 к башне,\n+2 отряда", "030.png", 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.red, 2, "Сверхурочные", "+5 к стене,\nвы теряте 6 маны", "007.png", -6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, false));

        maps.add(new swapWall());

        maps.add(new simpleCard(Map.MapColor.red, 8, "Секретная пещера", "+1 монастырь,\nиграем снова", "014.png", 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, true));

        maps.add(new simpleCard(Map.MapColor.red, 24, "Сердце дракона", "+20 к стене,\n+8 к башне", "028.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 20, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.red, 18, "Скаломёт", "+6 к стене,\n10 единиц\nурона врагу", "027.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 10, false));

        maps.add(new simpleCard(Map.MapColor.red, 0, "Счастливая монетка", "+2 руды,\n+2 маны,\nиграем снова", "002.png", 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, true));

        maps.add(new simpleCard(Map.MapColor.red, 7, "Толчки", "Все стены\nполучают по 5 урона,\nиграем снова", "013.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -5, -5, 0, 0, true));

        maps.add(new simpleCard(Map.MapColor.red, 14, "Укрепления", "+7 к стене,\n6 урона врагу", "033.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 6, false));

        maps.add(new simpleCard(Map.MapColor.red, 5, "Усиленная стена", "+6 к стене", "016.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, false));

        maps.add(new Foundation());

        maps.add(new simpleCard(Map.MapColor.red, 3, "Шахтёры", "+1 шахта", "004.png", 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 16, "Алмаз", "+15 к башне", "124.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 2, "Аметист", "5 урона собственной башне,\n+2 монастырь", "103.png", 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, -5, 0, 0, 0, 0, 0, false));

        maps.add(new bijouterie());

        maps.add(new simpleCard(Map.MapColor.blue, 3, "Взрыв силы", "+3 к башне", "111.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 5, "Вступление", "+4 к башне,\nвы теряете 3 отряда\n2 урона башне врага", "131.png", 0, 0, 0, 0, -3, 0, 0, 0, 0, 0, 0, 0, 4, -2, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 7, "Гармония", "+1 монастырь,\n+3 к башне,\n+3 к стене", "112.png", 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 3, 0, 3, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 21, "Глаз дракона", "+20 к башне", "127.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 8, "Дробление", "-1 монастырь,\n9 урона башне врага", "116.png", 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, -9, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 2, "Дымчатый кварц", "1 урона башне врага,\n играем снова", "102.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, true));

        maps.add(new simpleCard(Map.MapColor.blue, 9, "Жемчуг мудрости", "+5 к башне,\n+1 монастырь", "115.png", 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 4, "Затмение", "+2 к башне, 2 ед.\nурона башне врага", "106.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, -2, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 1, "Кварц", "+1 к башне,\nиграем снова", "101.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, true));

        maps.add(new simpleCard(Map.MapColor.blue, 12, "Кристальный щит", "+8 к башне,\n +3 к стене", "122.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 3, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 4, "Копье", "5 урона башне врага", "110.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -5, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 6, "Матрица", "+1 монастырь,\n+3 к башне,\n+1 к башне врага", "107.png", 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 3, 1, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 18, "Медитизм", "+13 к башне,\n+6 отрядов,\n+6 руды", "133.png", 0, 0, 6, 0, 6, 0, 0, 0, 0, 0, 0, 0, 13, 0, 0, 0, 0, 0, false));

        maps.add(new lightning());

        maps.add(new simpleCard(Map.MapColor.blue, 15, "Монастырь", "+10 к башне,\n+5 к стене,\nвы получаете 5 отрядов", "125.png", 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 10, 0, 5, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 7, "Мягкий камень", "+5 к башне,\nвраг теряет 6 руды", "117.png", 0, 0, 0, -6, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 13, "Огненный рубин", "+6 к башне,\n4 урона башне врага", "120.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, -4, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 8, "Отвердение", "+11 к башне,\n-6 к стене", "128.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 0, -6, 0, 0, 0, false));

        maps.add(new parity());

        maps.add(new simpleCard(Map.MapColor.blue, 4, "Помощь в работе", "+7 к башне,\nвы теряете 10 руды", "121.png", 0, 0, -10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 0, "Радуга", "+1 к башням всех,\nвы получаете 3 маны", "130.png", 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 5, "Раздоры", "7 урона всем башням,\n-1 монастырь всех игроков", "119.png", 0, 0, 0, 0, 0, 0, -1, -1, 0, 0, 0, 0, -7, -7, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 3, "Рубин", "+5 к башне", "109.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 5, "Рудная жила", "+8 к башне", "105.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 10, "Сапфир", "+11 к башне", "118.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 17, "Сияющий камень", "+12 к башне,\n6 урона врагу", "126.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12, 0, 0, 0, 0, 6, false));

        maps.add(new simpleCard(Map.MapColor.blue, 3, "Ткачи заклинаний", "+1 монастырь", "104.png", 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 2, "Трещина", "3 урона башне врага", "108.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 6, "Эмельральд", "+8 к башне", "114.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.blue, 14, "Эмпатия", "+8 к башне,\n+1 казарма", "123.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 8, 0, 0, 0, 0, 0, false));
        //green
        maps.add(new simpleCard(Map.MapColor.green, 3, "Армия гоблинов", "6 единиц урона.\nВы получаете 3 единицы урона", "205.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 6, false));

        maps.add(new simpleCard(Map.MapColor.green, 4, "Берсерк", "8 урона,\n3 урона вашей башне", "231.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, 0, 0, 0, 0, 8, false));

        maps.add(new simpleCard(Map.MapColor.green, 6, "Бешеная овца", "6 урона,\nвраг теряет 3 отряда", "216.png", 0, 0, 0, 0, 0, -3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, false));

        maps.add(new simpleCard(Map.MapColor.green, 17, "Вампир", "10 урона,\nвраг теряет 5 отрядов,\n-1 к его казарме", "227.png", 0, 0, 0, 0, 0, -5, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 10, false));

        maps.add(new simpleCard(Map.MapColor.green, 13, "Воитель", "13 урона,\nвы теряете 3 маны", "232.png", -3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 13, false));

        maps.add(new simpleCard(Map.MapColor.green, 12, "Вор", "Враг теряет 10 маны 5 руды.\nВы получаете половину от этого", "225.png", 5, -10, 3, -5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.green, 18, "Всадник на пегасе", "12 урона башне врага", "233.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -12, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.green, 5, "Гномы", "4 урона,\n+3 к стене", "209.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 4, false));

        maps.add(new simpleCard(Map.MapColor.green, 1, "Гоблины", "4 единицы урона.\nВы теряете 3 маны", "203.png", -3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, false));

        maps.add(new simpleCard(Map.MapColor.green, 4, "Гоблины-лучники", "3 урона башне врага.\nВы получаете 1 ед. урона", "206.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, 0, 0, 1, 0, false));

        maps.add(new simpleCard(Map.MapColor.green, 8, "Гремлин в башне", "2 урона,\n+4 к стене,\n+2 к башне", "212.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 4, 0, 0, 2, false));

        maps.add(new simpleCard(Map.MapColor.green, 25, "Дракон", "20 урона,\nВраг теряет 10 маны,\n-1 к его казарме", "228.png", 0, -10, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 20, false));

        maps.add(new unicorn());

        maps.add(new acridCloud());

        maps.add(new bug());


        maps.add(new simpleCard(Map.MapColor.green, 15, "Каменный гигант", "10 урона,\n+4 к стене", "226.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 10, false));

        maps.add(new simpleCard(Map.MapColor.green, 11, "Камнееды", "8 урона,\n-1 шахта врага", "224.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 8, false));

        maps.add(new simpleCard(Map.MapColor.green, 2, "Карлик", "3 урона,\n+1 мана", "230.png", 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, false));


        maps.add(new spearMan());

        maps.add(new simpleCard(Map.MapColor.green, 0, "Коровье бешенство", "Все игроки теряют\nпо 6 отрядов", "216.png", 0, 0, 0, 0, -6, -6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.green, 5, "Крушитель", "6 урона", "214.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, false));

        maps.add(new simpleCard(Map.MapColor.green, 6, "Маленькие змейки", "4 урона башне врага", "210.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -4, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.green, 3, "Минотавр", "+1 казарма", "204.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.green, 9, "Оборотень", "9 урона", "219.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, false));

        maps.add(new simpleCard(Map.MapColor.green, 6, "Огр", "7 урона", "215.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, false));

        maps.add(new simpleCard(Map.MapColor.green, 3, "Орк", "5 урона", "208.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, false));

        maps.add(new simpleCard(Map.MapColor.green, 0, "Полнолуние", "+1 казарма всем игрокам,\nвы получаете 3 отряда", "213.png", 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.green, 6, "Призрачная фея", "2 урона башне врага,\nиграем снова", "207.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0, true));

        maps.add(new simpleCard(Map.MapColor.green, 14, "Суккубы", "5 урона башне врага,\nвраг теряет 6 отрядов", "223.png", 0, 0, 0, 0, 0, -6, 0, 0, 0, 0, 0, 0, 0, -5, 0, 0, 0, 0, false));

        maps.add(new simpleCard(Map.MapColor.green, 7, "Тролль-наставник", "+2 к казарме", "211.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, false));


        maps.add(new simpleCard(Map.MapColor.green, 5, "Черт", "6 урона,\nвсе игроки теряют\nпо 5 руды, маны, отрядов", "217.png", -5, -5, -5, -5, -5, -5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, false));

        maps.add(new elfArchers());


        return new CardDeck(maps);
    }

    public static String playerTransferToJsonString(playerTransfer[] players) throws JsonMappingException, IOException {
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(writer, players);
        return writer.toString();
    }

    public static playerTransfer[] createPlayerTransfer(Player player1, Player player2) throws IOException {
        playerTransfer p1 = new playerTransfer(player1.getName(), player1.getMaps(), player1.getMonasteryCount(), player1.getMineCount(), player1.getBarracksCount(), player1.getManaCount(), player1.getOreCount(), player1.getArmyCount(), player1.getTowerCount(), player1.getWallCount(),player1.getTurn());
        playerTransfer p2 = new playerTransfer(player2.getName(), player2.getMaps(), player2.getMonasteryCount(), player2.getMineCount(), player2.getBarracksCount(), player2.getManaCount(), player2.getOreCount(), player2.getArmyCount(), player2.getTowerCount(), player2.getWallCount(),player2.getTurn());
        return new playerTransfer[]{p1, p2};
    }

    public static void Logging(String str)
    {
        try(PrintWriter writer = new PrintWriter(new FileWriter("log.txt")))
        {
            writer.write(str);
           System.out.println(new File(new File(".").getAbsolutePath()+"/log.txt"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void saveTestLog(Player[] players, Map map) {
        try(PrintWriter writer=new PrintWriter(new FileWriter("D:\\log.txt",true)))
        {
            String str = String.format("{\"players\":[%s,%s,%s]}",players[0],players[1],map);
            writer.write(str);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static MessageForFinishGame CheckVin(Player player,Player enemy,int towerCount,int resCount)
    {
        MessageForFinishGame message=null;

        if(player.getTowerCount()>=towerCount)
        {
            String vinMsg ="Вы победили, построив огромную башню";
            String loseMsg=String.format("Поражение! Противник %s построил огромную башню!",player.getName());
            message=new MessageForFinishGame(vinMsg,loseMsg);
        }
        if(enemy.getTowerCount()<=0)
        {
            String vinMsg ="Вы победили, разрушив башню противника";
            String loseMsg=String.format("Поражение! Противник %s разрушил вашу башню!",player.getName());
            message=new MessageForFinishGame(vinMsg,loseMsg);
        }
        if ( player.getManaCount()>=resCount&&player.getOreCount()>=resCount&&player.getArmyCount()>=resCount)
        {
            String vinMsg ="Вы победили, накопив гору ресурсов";
            String loseMsg=String.format("Поражение! Противник %s накопил гору ресурсов!",player.getName());
            message=new MessageForFinishGame(vinMsg,loseMsg);
        }
        return message;
    }





}
