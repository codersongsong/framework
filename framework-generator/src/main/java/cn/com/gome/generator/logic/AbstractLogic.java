package cn.com.gome.generator.logic;

import com.google.common.base.CaseFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractLogic {

    protected String getCodeGenerateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时:mm分:ss秒");
        return sdf.format(new Date());
    }

    /**
     * 类描述
     */
    protected String getClassDescription(String className, String classDesc) {
        StringBuilder sb = new StringBuilder();
        sb.append("/**\n");
        sb.append(" * <Description> ");
        sb.append(classDesc);
        sb.append(" </Description>\n");
        sb.append(" * <ClassName> ");
        sb.append(className);
        sb.append(" </ClassName>\n");
        sb.append(" *\n");
        sb.append(" * @Author generator\n");
        sb.append(" * @Date ");
        sb.append(getCodeGenerateTime());
        sb.append("\n");
        sb.append(" */\n");
        return sb.toString();
    }

    /**
     * 首字母小写:aaBbCc -> AaBbCc
     *
     * @param src
     * @return
     */
    protected  String lowerToUpper(String src) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, src);
    }

    /**
     * 首字母小写:AaBbCc -> aaBbCc
     *
     * @param src
     * @return
     */
    protected  String upperToLower(String src) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, src);
    }


    /**
     * 表名、字段名首字母小写:AA_BB_CC -> aaBbCc
     *
     * @param column
     * @return
     */
    protected  String columnToStringL(String column) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, column);
    }

    /**
     * 表名、字段名首字母大写:AA_BB_CC  -> AaBbCc
     *
     * @param column
     * @return
     */
    protected  String columnToStringU(String column) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, column);
    }

    public static void main(String[] args) {
        System.out.println();
//        System.out.println(columnToStringU("AA_BB_CC"));//AaBbCc
//        System.out.println(columnToStringL("AA_BB_CC"));//aaBbCc
//        System.out.println(lowerToUpper("aaBbCc"));//AaBbCc
//        System.out.println(upperToLower("AaBbCc"));//aaBbCc
//        System.out.println(getClassDescription("AdminDataDictionaryController", "数据字典获取控制器"));
    }

}
