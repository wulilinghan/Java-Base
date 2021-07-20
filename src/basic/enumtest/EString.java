package basic.enumtest;

public enum EString {

    NX("NX","设置不存在"),
    PX("XX","Only set the key if it already exist");

    private EString(String code,String msg) {
        this.code = code;
        this.msg = msg;
    }
    private String code;
    private String msg;
}
