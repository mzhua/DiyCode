package im.hua.diycode.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hua on 2016/11/17.
 */

public class AbilitiesEntity extends BaseEntity implements Parcelable{
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.update ? (byte) 1 : (byte) 0);
        dest.writeByte(this.destroy ? (byte) 1 : (byte) 0);
    }

    public AbilitiesEntity() {
    }

    protected AbilitiesEntity(Parcel in) {
        this.update = in.readByte() != 0;
        this.destroy = in.readByte() != 0;
    }

    public static final Creator<AbilitiesEntity> CREATOR = new Creator<AbilitiesEntity>() {
        @Override
        public AbilitiesEntity createFromParcel(Parcel source) {
            return new AbilitiesEntity(source);
        }

        @Override
        public AbilitiesEntity[] newArray(int size) {
            return new AbilitiesEntity[size];
        }
    };
}
