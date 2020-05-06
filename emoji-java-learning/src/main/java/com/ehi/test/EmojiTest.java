package com.ehi.test;

import com.vdurmont.emoji.EmojiParser;

/**
 * ClassName: EmojiTest
 *
 * @Author: WangYiHai
 * @Date: 2020/5/6 18:53
 * @Description: TODO
 */
public class EmojiTest {
    public static void main(String[] args) {
        String str="\uD83E\uDD17\uD83D\uDE0F\uD83D\uDE36\uD83D\uDE10\uD83D\uDE44";
        String result= EmojiParser.parseToAliases(str);
        System.out.println(result);

        String res=EmojiParser.parseToUnicode(result);
        System.out.println(res);
    }

}