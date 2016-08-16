package br.com.bairoon.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ContaNeon implements Parcelable {

    private static BigDecimal SALDO;
    private BigDecimal saldo;
    private ExtratoNeon extrato;
    private DepositoNeon depositoNeon;

    static {
        SALDO = new BigDecimal(800.00);
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public ExtratoNeon getExtrato() {
        return extrato;
    }

    public void setExtrato(ExtratoNeon extrato) {
        this.extrato = extrato;
    }

    public DepositoNeon getDepositoNeon() {
        return depositoNeon;
    }

    public void setDepositoNeon(DepositoNeon depositoNeon) {
        this.depositoNeon = depositoNeon;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.saldo);
        dest.writeParcelable(this.extrato, flags);
        dest.writeParcelable(this.depositoNeon, flags);
    }

    public ContaNeon() {
    }

    protected ContaNeon(Parcel in) {
        this.saldo = (BigDecimal) in.readSerializable();
        this.extrato = in.readParcelable(ExtratoNeon.class.getClassLoader());
        this.depositoNeon = in.readParcelable(DepositoNeon.class.getClassLoader());
    }

    public static final Creator<ContaNeon> CREATOR = new Creator<ContaNeon>() {
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
