package br.com.bairoon.model.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.math.BigDecimal;

public class ContaNeon implements Parcelable {
    private BigDecimal saldo;


    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.saldo);
    }

    public ContaNeon() {
    }

    protected ContaNeon(Parcel in) {
        this.saldo = (BigDecimal) in.readSerializable();
    }

    public static final Parcelable.Creator<ContaNeon> CREATOR = new Parcelable.Creator<ContaNeon>() {
        @Override
        public ContaNeon createFromParcel(Parcel source) {
            return new ContaNeon(source);
        }

        @Override
        public ContaNeon[] newArray(int size) {
            return new ContaNeon[size];
        }
    };
}
