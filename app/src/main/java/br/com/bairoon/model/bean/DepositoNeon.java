package br.com.bairoon.model.bean;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class DepositoNeon implements Parcelable {

    private ContatoNeon contatoNeon;
    private double valor;
    private Date dataOperacao;

    public DepositoNeon(ContatoNeon contatoNeon, double valor, Date dataOperacao) {
        this.contatoNeon = contatoNeon;
        this.valor = valor;
        this.dataOperacao = dataOperacao;
    }

    public ContatoNeon getContatoNeon() {
        return contatoNeon;
    }

    public void setContatoNeon(ContatoNeon contatoNeon) {
        this.contatoNeon = contatoNeon;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(Date dataOperacao) {
        this.dataOperacao = dataOperacao;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.contatoNeon, flags);
        dest.writeDouble(this.valor);
        dest.writeLong(this.dataOperacao != null ? this.dataOperacao.getTime() : -1);
    }

    protected DepositoNeon(Parcel in) {
        this.contatoNeon = in.readParcelable(ContatoNeon.class.getClassLoader());
        this.valor = in.readDouble();
        long tmpDataOperacao = in.readLong();
        this.dataOperacao = tmpDataOperacao == -1 ? null : new Date(tmpDataOperacao);
    }

    public static final Parcelable.Creator<DepositoNeon> CREATOR = new Parcelable.Creator<DepositoNeon>() {
        @Override
        public DepositoNeon createFromParcel(Parcel source) {
            return new DepositoNeon(source);
        }

        @Override
        public DepositoNeon[] newArray(int size) {
            return new DepositoNeon[size];
        }
    };
}
