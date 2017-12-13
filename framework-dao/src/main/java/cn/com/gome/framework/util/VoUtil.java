package cn.com.gome.framework.util;

import com.gomeplus.frame.utils.DateFormatUtils;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liuxianzhao
 * @ClassName VoUtil
 * @Description Vo工具类
 * @date 2017年07月27日 14:28
 */
public class VoUtil {

    public static String getDateCollmun(String time) {
        if (time == null || time == "") {
            return "";
        }
        return DateFormatUtils.format(time, "yyyyMMdd", "yyyyMMdd_");
    }

    public static String getDateTimeCollmun(String time) {
        return getDateTimeCollmun(time, "yyyyMMddHHmmss_UI");
    }

    public static String getTimeCollmun(String time) {
        if (time == null || time == "") {
            return "";
        }
        return DateFormatUtils.format(time, "HHmmss", "HHmmss_");
    }

    public static String getDateTimeCollmun(String time, String tgtFormat) {
        if (time == null || time == "") {
            return "";
        }
        return DateFormatUtils.format(time, "yyyyMMddHHmmss", tgtFormat);
    }

    /**
     * @param dateStr        日期字符串
     * @param dateStrPattern 输入的日期字符串 格式  eg. 'MM/dd/yyyy hh:mm:ss'
     * @param outputPattern  需要输出的日期字符串格式   eg. 'yyyyMMddhhmmss'
     * @return
     * @throws ParseException
     */
    public static String formatDateStr(String dateStr, String dateStrPattern, String outputPattern) {
        try {
            if (StringUtils.isEmpty(dateStr)) {
                return "";
            }
            Date date = new SimpleDateFormat(dateStrPattern).parse(dateStr);
            return new SimpleDateFormat(outputPattern).format(date);
        } catch (Exception e) {
            return dateStr;
        }
    }
}
