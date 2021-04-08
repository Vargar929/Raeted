package cf.homeit.rating.DataModel;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.Objects;

public class RatingList implements Parcelable {

    public String rValue;
    public String rTime;
//    public String PageTitle;
    protected RatingList(Parcel in) {
    }

    public RatingList() {

    }

    public static final Parcelable.Creator<RatingList> CREATOR = new Parcelable.Creator<RatingList>() {
        @Override
        public RatingList createFromParcel(Parcel in) {
            return new RatingList(in);
        }

        @Override
        public RatingList[] newArray(int size) {
            return new RatingList[size];
        }
    };

    public String getrValue() {
        return rValue;
    }

    public void setrValue(String rValue) {
        this.rValue = rValue;
    }

    public String getrTime() {
        return rTime;
    }

    public void setrTime(String rTime) {
        this.rTime = rTime;
    }

    public static Parcelable.Creator<RatingList> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RatingList)) return false;
        RatingList that = (RatingList) o;
        return Objects.equals(rValue, that.rValue) &&
                Objects.equals(rTime, that.rTime);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(rValue, rTime);
    }

    @Override
    public String toString() {
        return "ReadingList{" +
                "Value='" + rValue + '\'' +
                ", Time='" + rTime + '\'' +
                '}';
    }
}
