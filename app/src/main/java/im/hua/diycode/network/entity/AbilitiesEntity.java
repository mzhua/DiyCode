package im.hua.diycode.network.entity;

/**
 * Created by hua on 2016/11/17.
 */

public class AbilitiesEntity {
    /**
     * update : false
     * destroy : false
     */

    private boolean update;
    private boolean destroy;

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isDestroy() {
        return destroy;
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }
}
