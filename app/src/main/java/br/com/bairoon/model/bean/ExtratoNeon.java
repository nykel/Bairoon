package br.com.bairoon.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ExtratoNeon implements Parcelable {

    private List<DepositoNeon> depositos;

    public List<DepositoNeon> getDepositos() {
        return depositos;
    }

    public void setDepositos(List<DepositoNeon> depositos) {
        this.depositos = depositos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.depositos);
    }

    public ExtratoNeon() {
    }

    protected ExtratoNeon(Parcel in) {
        this.depositos = in.createTypedArrayList(DepositoNeon.CREATOR);
    }

    public static final Parcelable.Creator<ExtratoNeon> CREATOR = new Parcelable.Creator<ExtratoNeon>() {
        @Override
        public ExtratoNeon createFromParcel(Parcel source) {
            return new ExtratoNeon(source);
        }

        @Override
        public ExtratoNeon[] newArray(int size) {
            return new ExtratoNeon[size];
        }
    };
}
