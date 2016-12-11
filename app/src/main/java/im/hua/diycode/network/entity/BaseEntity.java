package im.hua.diycode.network.entity;

/**
 * Created by hua on 2016/12/9.
 */

public class BaseEntity {

    /**
     * error : Error message
     */

    private String error;
    /**
     * error_description : 资源所有者认证无效或没有所有者
     */

    private String error_description;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }
}
