package im.hua.diycode.network;

/**
 * Created by hua on 2016/12/10.
 */

public class MyException extends Exception {
    private int mCode;
    private String mMessage;

    public MyException() {
    }

    public MyException(int code, String message) {
        this.mCode = code;
        this.mMessage = message;
    }

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        mCode = code;
    }

    @Override
    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }
}
