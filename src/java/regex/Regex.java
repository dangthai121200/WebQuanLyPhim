/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author gaone
 */
public class Regex {
    private final static Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
    private final static Pattern VALID_FULLNAME_REGEX =Pattern.compile("^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]*$");
    
    private final static Pattern VALID_PASSWORD_REGEX=Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]\\.:;',?/*~$^+=<>]).{6,20}$");
    
//Kiểm trả có nhập đúng gmail
    public static boolean checkGmail(String gmail){    
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(gmail);
        return matcher.find();
    }
     
    //Kiểm tra có nhập đúng tên
    public static boolean checkFullName(String fullName){
        Matcher matcher=VALID_FULLNAME_REGEX.matcher(fullName);
        return matcher.find();
    }
    //Kiểm tra mật khẩu
//    Mật khẩu phải chứa ít nhất một chữ số [0-9].
//    Mật khẩu phải chứa ít nhất một ký tự Latinh viết thường [az].
//    Mật khẩu phải chứa ít nhất một ký tự Latinh viết hoa [AZ].
//    Mật khẩu phải chứa ít nhất một ký tự đặc biệt như ! @ # & ( ).
//    Mật khẩu phải có độ dài ít nhất 8 ký tự và tối đa 20 ký tự.
    public static boolean checkPassWord(String password){
         Matcher matcher=VALID_PASSWORD_REGEX.matcher(password);
        return matcher.find();
    }
    public static void main(String[] args) {
        System.out.println(checkFullName("ĐẶNG HỒNG BẢO THÁI"));
    }
}
