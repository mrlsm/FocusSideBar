package com.mrlsm.focussidebar;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Mrlsm
 * @since 2019/9/1
 * description: 获取首字母工具类
 */
public class SpellingUtils {
    private final static int[] SEC_POSITION_VALUE = {
            1601, 1637, 1833, 2078, 2274,
            2302, 2433, 2594, 2787, 3106,
            3212, 3472, 3635, 3722, 3730,
            3858, 4027, 4086, 4390, 4558,
            4684, 4925, 5249, 5590, 5595
    };
    // 存放国标一级汉字不同读音的起始区位码对应读音
    private final static String[] FIRST_LETTER = {
            "A", "B", "C", "D", "E",
            "F", "G", "H", "J", "K",
            "L", "M", "N", "O", "P",
            "Q", "R", "S", "T", "W",
            "X", "Y", "Z", "#"
    };

    // 存放所有国标二级汉字读音
    static String SECOND_SEC_TABLE = "cjwgnspgcgne[z[btzzzdxzkzgt[jnnjqmbsgzsczjszz"
            + "[pgkbzgz[zwjkgkljzwkpjqhz[w[dzlsgmrzpzwwcckznkzzgttnjjnzkkzztcjnmczlqlzpzqfqrpzslwbtgkjfzxjwzltbncxjjjjtxdttsqzzcdxxhgck"
            + "[phffss[zbgxlppbzll[hlxs[zm[jhsojnghdzqzklgjhsgqzhxqgkezzwzscscjxzezxadzpmdssmzjzqjzzc[j"
            + "[wqjbzzpxgznzcpwhkxhqkmwfbpbzdtjzzkqhzlzgxfptzjzzzpszlfchmqshgmxxsxj["
            + "[dcsbbqbefsjzhxwgzkpzlqbgldlcctnmazddkssngzcsgxlzzazbnptsdkdzlhgzmzlcxpz"
            + "[jndqjwxqxfzzfjlejpzrxccqwqqsbnkzmgplbmjrqcflnzmzqmsqzrbcjthztqfrxqhxmjjcjlxqgjmshzkbswzemzltxfszdswlzcjqxsjnqbsctzhbftdczzdjwz"
            + "ghqfrxwckqkxebptlpxjzsrmebwhjlbjslzzsmdxlclqkxlhxjrzjmfqhxhwzwsbhtrxxglhqhfnm[zkldzxzpzlgg[mtcfpajjzzljtzanjgbjplqgdzzqz"
            + "axbkzsecjsznslzzhsxlzcghpxzhznztdsbcjkdlzazfmzdlebbgqzzkxgldndnzskjshdlzxbcghxzpkdjmmzngmmclgwzszxzjfznmlzzthcszdbdllscdd"
            + "nlkjzkjszcjlkwhqasdknhcsganhdaashtcplcpqzbsdmpjlpzjoqlcdhjjzsprchn[nnlhlzzqzhwzptczgwwmzffjqqqqzxaclbhkdjxdgmmzdjxzllszgx"
            + "gkjrzwzwzclzmssjzldbzd[fcxzhlxchzzjq[[qagmnzxpfrkssbjlzxzszglnscmhzwwmnzjjlxxhchsz[[ttxrzczxbzhcsmxjsznpwgpxxtazbgajcxlz"
            + "[dccwzocwkccsbnhcpdzznfczztzckxkzbsqkkztqqxfcwchczkelzqbsqzjqcclmthszwhmktlkjlzcxwheqqhtqh[pq"
            + "[qscfzmndmgbwhwlgsllzsdlmlxpthmjhwljzzhzjxhtxjlhxrswlwzjcbxmhzqxsdzpmgfcsglsxzmjshxpjxwmzqksmzplrthbxftpmhzxlchlhlzz"
            + "lxgsssstclsldclrpbhzhxzzfhb[gdmzcnqqwlqhjj[zwjzzejjdhpblqxtqkwhlchqxagtlxljxmsl[htzkzjecxjcjnmfbz[sfzwzbjzgnzsdzsqzrslj"
            + "pclpwxsdwejbjcbcnaztwgmpapclzqpclzxsbnmsggfnzjjbzsfzzndxhplqkzczwalsbccjx[zzgwkzpsgxfzfcdkhjgxdlqfsgdslqwzkxtmhsbgzmjzrglzj"
            + "bpmlmsxlzjqqhzzjczzdjwbmzklddpmjegxzhzlxhlqzqhkzcwcjmzzxnatjhzccxzpcqlbzwwztwbqcmlpmzrjcccxfpznzzljplxxzztzlgdldcklzrzzgqtg"
            + "jhhgjljaxfgfjzslcfdqzlclgjdjcsnzlljpjqdcclcjxmzzftsxgcgsbrzxjqqctzhgzqtjqqlzxjzlzlbczamcstzlpdjbzregklzzzhlzszqlznwczcllwjq"
            + "jjjkdgjzolbbzppglghtgzxzghzmzcnqszczhbhgxkamtxzxnbskzzzgjzlqjdfcjxdzgjqjjpmgwgjjjpkqsbgbmmcjssclpqpdxcdzzkz[cjddzzgzwrhjrtgz"
            + "nzqldkljszzgzqzjgdzkshpzmtlcpwnjafzzdjcnmwesczglbtzcgmssllzxqsxsbsjsbbsgghfjlzpmzjnlzzwdqshzxtzzwhmzzhzwdbxbtlmszzzfsxjc[dxx"
            + "lhjhf[sxzqhfzmzcztqcxzxrttdjhnnzzqqmnqdmmg[zdxmjgdhcdzzbffallztdltfxmxqzdngwqdbdczjdxbzgsqqddjcmbkzffxmkdmdszzszcmljdsznsbrs"
            + "kmkmpcklgdbqtfzswtfgglzplljzhgj[gzpzltcsmcnbtjbqfkthbzzgkpbbzmtdssxtbnpdklezcjnzddzkzddhqhsdzsctarlltkzlgecllkjlqjaqnbdkkghp"
            + "jtzqksecshalqfmmgjnlzjbbtmlzzxdcjpldlpcqdhzzcbzsczbzmsljflkrzjsnfrgjhxpdhzjzbzgdlqcsezgxlblgzxtwmabchecmwzjzzlljjzhlg[djlslz"
            + "gkdzpzxjzzzlwcxszfgwzzdlzhcljscmbjhblzzlzcblzdpdqzsxqzbztdkzxjz[cnrjmpdjgklcljbctbjddbblblczqrppxjcjlzcshltoljnmdddlngkaqhqh"
            + "jgzkheznmshrp[qqjchgmfprxhjgdzchghlzrzqlczqjnzsqtkqjzmszswlcfqqqxzfggzptqwlmcrnfkkfszzlqbmqammmzxctpshcptxxzzsmphpshmclmldqf"
            + "zqxszzzdzjzzhqpdszglstjbckbxzqzjsgpsxqzqzrqtbdkzxzkhhgflbcsmdldgdzdblzzzcxnncszbzbfglzzxswmsccmqnjqsbdqsjtxxmbltxzclzshzcxrq"
            + "jgjzlxzfjphzmzqqzdfqjjlzznzjcdgzzgctxmzzsctlkphtxhtlbjxjlxscdqxcbbtjfqzfsltjbtkqbxxjjljchczdbzjdczjdcprnpqcjpfczlclzxzdmxmph"
            + "jsgzgszzqlzlwtjpfszasmcjbtzkzcwmztcsjjljcqlwzmalbxzfbpnlsfhtgjwejjxxglljstgshjqlzfkcgnnnszfdeqfhbsaqtgzlbxmmzgszldzdqmjjrgbj"
            + "tkgdhgkblqkbdmbzlxwcxzttzbkmrtjzxqjbhlmhmjjzmqasldczxzqdlqcafzwzxqhz";

    /**
     * 取得给定汉字串的首字母串,即声母串
     *
     * @param str 给定汉字串
     * @return 声母串
     */
    public static String getAllFirstLetter(String str) {
        if (str == null || str.trim().length() == 0) {
            return "";
        }

        String _str = "";
        for (int i = 0; i < str.length(); i++) {
            _str = _str + getFirstLetter(str.substring(i, i + 1));
        }

        return _str;
    }

    /**
     * 取得给定汉字的首字母,即声母
     *
     * @param chinese 给定的汉字
     * @return 给定汉字的声母
     */
    public static String getFirstLetter(String chinese) {
        if (chinese == null || chinese.trim().length() == 0) {
            return "";
        }
        // 二级字库偏移量
        int ioffset = 0;

        chinese = conversionStr(chinese, "GB2312", "ISO8859-1");

        if (chinese.length() > 1) // 判断是不是汉字
        {
            int li_SectorCode = (int) chinese.charAt(0); // 汉字区码
            int li_PositionCode = (int) chinese.charAt(1); // 汉字位码
            li_SectorCode = li_SectorCode - 160;
            li_PositionCode = li_PositionCode - 160;
            int li_SecPosCode = li_SectorCode * 100 + li_PositionCode; // 汉字区位码

            // 汉字编码范围在1601-1694,1701-1794,....一直到8701-8794范围以内
            if (li_SecPosCode > 1600 && li_SecPosCode < 5590
                    && li_SecPosCode % 100 < 95) {
                for (int i = 0; i < 23; i++) {
                    if (li_SecPosCode >= SEC_POSITION_VALUE[i]
                            && li_SecPosCode < SEC_POSITION_VALUE[i + 1]) {
                        chinese = FIRST_LETTER[i];
                        break;
                    }
                }
            } else if (li_SecPosCode > 5590 && li_SecPosCode < 8795
                    && li_SecPosCode % 100 < 95) {
                ioffset = (li_SectorCode - 56) * 94 + li_PositionCode - 1;
                if (ioffset >= 0 && ioffset <= 3007) {
                    char mChar = (char) (SECOND_SEC_TABLE.substring(ioffset, ioffset + 1).charAt(0) - 32);
                    chinese = String.valueOf(mChar);
                }
            } else {
                chinese = conversionStr(chinese, "ISO8859-1", "GB2312");
                chinese = chinese.substring(0, 1);
            }
        } else {
            chinese = "#" + chinese;
        }

        return chinese;
    }

    /**
     * 字符串编码转换
     *
     * @param str           要转换编码的字符串
     * @param charsetName   原来的编码
     * @param toCharsetName 转换后的编码
     * @return 经过编码转换后的字符串
     */
    private static String conversionStr(String str, String charsetName, String toCharsetName) {
        try {
            str = new String(str.getBytes(charsetName), toCharsetName);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return str;
    }

    public static List<String> stringSort(List<String> list) {
        if (list == null || list.size() < 2) {
            return list;
        }
        List<String> result = new ArrayList<>(list);
        Collections.sort(result, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String s1 = getFirstLetter(o1.substring(0, 1));
                String s2 = getFirstLetter(o2.substring(0, 1));
                if (s1.startsWith("#") && !s2.startsWith("#")) {
                    return 1;
                } else if (!s1.startsWith("#") && s2.startsWith("#")) {
                    return -1;
                } else {
                    return s1.compareTo(s2);
                }
            }
        });
        return result;
    }
}
