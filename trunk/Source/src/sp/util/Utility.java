/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sp.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.*;

/**
 *
 * @author IT Devil
 */
public class Utility {
    // hàm này có chức năng xóa các item có tên theo định dạng sau trong session
    // "listproduct_"+count với count là biến tăng dần
    // mỗi một attribute như vầy sẽ chứa 1 list các sản phẩm

    // Chuẩn hóa chuỗi ngày tháng truyền vào
    static public String mahoaMD5(String password) {
        byte[] defaultBytes = password.getBytes();
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();


            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }
            String foo = messageDigest.toString();
            System.out.println("sessionid" + hexString.toString());
        } catch (NoSuchAlgorithmException e) {
        }

        return hexString.toString();
    }

    static public String chuanhoachuoinhommon(String s) {
        String slit[] = s.split(";");
        int length = slit.length;
        String result[] = new String[length];
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (slit[i].equals("") == false && slit[i].trim().equals("") == false) {
                result[count] = slit[i].trim();
                count++;
            }
        }

        String sum = "";
        for (int i = 0; i < count; i++) {
            sum = sum + result[i] + "-";
        }
        sum = sum.substring(0, 10);
        return sum;
    }

    static public String chuanhoachuoiNgaythang(String s) {
        String slit[] = s.split("-");
        String result[] = new String[3];
        int count = 0;
        for (int i = 0; i < slit.length; i++) {
            if (slit[i].equals("") == false && slit[i].trim().equals("") == false) {
                result[count] = slit[i].trim();
                count++;
            }
        }
        String sum = "";
        for (int i = 0; i < count; i++) {
            sum = sum + result[i] + "-";
        }
        sum = sum.substring(0, 10);
        return sum;
    }

    static public boolean ValidateIntSerial(String intserial) {
        // kiểm tra giá trị này có phải là số hợp lệ hay không
        char[] chars = intserial.toCharArray();// chuyển sang mảng char

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (Character.isDigit(c) == false) {
                return false;
            }
        }
        return true;
    }

    static public boolean ValidateDouble(String doublerial) {
        try {
            Double.parseDouble(doublerial);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    static public boolean ValidateDate(String birthday) {
        // kiểm tra giá trị này có phải là số hợp lệ hay không
        char[] chars = birthday.toCharArray();// chuyển sang mảng char
        int count = 0;
        String result[] = new String[3];
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            // nếu xuất hiện ký tự chữ hoặc khác ký tự '-' trả về false
            if (Character.isDigit(c) == false && Character.valueOf(c).equals('-') == false && Character.valueOf(c).equals(' ') == false) {
                return false;
            }
        }

        String slit[] = birthday.split("-");
        for (int i = 0; i < slit.length; i++) {
            if (slit[i].equals("") == false && slit[i].trim().equals("") == false) {
                result[count] = slit[i].trim();
                count++;
            }
        }
        // nếu có tới 2 chuỗi số được tách ra lập tức trả về false
        if (count > 3) {
            return false;
        }
        // nếu năm mà khác 4 ký tự lập tức loại
        if (result[0].length() != 4) {
            return false;
        }
        // nếu tháng khác mà khác 2 ký tự lập tức loại
        if (result[1].length() != 2) {
            return false;
        } else {
            // nếu tháng lớn hơn 12 và nhỏ hơn 1 lập tức loại
            if (Integer.parseInt(result[1]) > 12 || Integer.parseInt(result[1]) < 1) {
                return false;
            }
        }
        // nếu ngày khác 2 ký tự lập tức loại
        if (result[2].length() != 2) {
            return false;
        } else {
            int day = Integer.parseInt(result[2]);
            int month = Integer.parseInt(result[1]);
            int year = Integer.parseInt(result[0]);
            // nếu lớn hơn 31 ngày lập tức loại
            if (day > 31 || day < 1) {
                return false;
            }

            if (year % 4 != 0 || (year % 4 == 0 && year % 100 == 0)) {

                if (month == 2 && day > 28) {
                    return false;
                }
                if (month == 4 || month == 6 || month == 9 || month == 11) {
                    if (day > 30) {
                        return false;
                    }
                }
            } else {
                if (month == 2 && day > 29) {
                    return false;
                }
                if (month == 4 || month == 6 || month == 9 || month == 11) {
                    if (day > 30) {
                        return false;
                    }
                }
            }

        }

        return true;
    }

    static public boolean ValidateEmail(String email) {
        email = email.trim();
        String slit[] = email.split("@");
        if (slit.length != 2) {
            return false;
        } else {
            String slit_1[] = slit[1].split(".");
            char[] chars = slit[0].toCharArray();// chuyển sang mảng char
            for (int i = 0; i < chars.length; i++) {
                if ((chars[i] < 'a' || chars[i] > 'z') && (chars[i] < 'A' || chars[i] > 'Z') && (chars[i] < '0' || chars[i] > '9') && chars[i] != '.' && chars[i] != '-') {
                    return false;
                }
            }
            if (slit_1.length >= 2 && slit_1.length <= 3) {
                return false;
            } else {
                if (slit_1.length >= 2) {
                    char[] chars_1 = slit_1[0].toCharArray();
                    for (int i = 0; i < chars_1.length; i++) {
                        if ((chars_1[i] < 'a' || chars_1[i] > 'z') && (chars_1[i] < 'A' || chars_1[i] > 'Z')) {
                            return false;
                        }
                    }

                    char[] chars_2 = slit_1[1].toCharArray();
                    for (int i = 0; i < chars_2.length; i++) {
                        if ((chars_2[i] < 'a' || chars_2[i] > 'z') && (chars_2[i] < 'A' || chars_2[i] > 'Z')) {
                            return false;
                        }
                    }
                }
                if (slit_1.length >= 3) {
                    char[] chars_3 = slit_1[2].toCharArray();
                    for (int i = 0; i < chars_3.length; i++) {
                        if ((chars_3[i] < 'a' || chars_3[i] > 'z') && (chars_3[i] < 'A' || chars_3[i] > 'Z')) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    // so sánh hai ngày với nhau
    //Date nho hon ngay hien tai thi tra về -1
    //Date_1 bang ngay hien tai thi tra ve 0
    //Date_1 lon hon ngay hien tai thi tra ve 1
    static public int CompareDaytoCurrentDay(String date) {

        Date date_1 = Date.valueOf(date);
        Date curentdate = new Date(Calendar.getInstance().getTime().getTime());
        int result = date_1.compareTo(curentdate);
        int flag = 0;
        if (result < 0) {
            flag = -1;
        }
        if (result == 0) {
            flag = 0;
        }
        if (result > 0) {
            flag = 1;
        }

        return flag;
    }

    static public String[] split_chuoi(String chuoi, String regex) {
        String array_1[] = chuoi.split(regex);
        ArrayList<String> result_1 = new ArrayList<String>();
        // String result_1[] = new String[array_1.length];
        for (int i = 0; i < array_1.length; i++) {
            if (!"".equals(array_1[i].trim())) {
                result_1.add(array_1[i].trim());
            }
        }
        return result_1.toArray(array_1);
    }

    static public boolean sosanhchuoi(String chuoi_1, String chuoi_2) {
        boolean flag = false;
        String array_1[] = split_chuoi(chuoi_1, ",");
        String array_2[] = split_chuoi(chuoi_2, ",");

        for (int i = 0; i < array_1.length; i++) {
            for (int j = 0; j < array_2.length; j++) {
                if (array_1[i].equals(array_2[j])) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
    static public int [][] randdom_matran(int lenght) {
        int a[][] = new int[lenght][lenght];
        Random r = new Random();
        for (int i = 0; i < lenght; i++) {
            for (int j = 0; j < lenght; j++) {
                if (i == j) {
                    a[i][j] = 0;
                } else {
                    a[i][j] = r.nextInt(2);
                }
            }
        }

        return a;

    }

   
    static public void addSoLuongPagetoJSonObjectList(int count, JSONObjectList jsonlist) {
        String key_soluong[] = {"SOLUONG"};
        JSONObject soluong = new JSONObject(key_soluong);

        soluong.getObject().put(key_soluong[0], String.valueOf(count));

        jsonlist.getListobject().add(soluong);
    }

//    static public String tachlaycackytudaucuatennguoi(String ten)
//    {
//
//    }
}
