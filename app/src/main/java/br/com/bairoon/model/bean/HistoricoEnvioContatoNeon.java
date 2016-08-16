package br.com.bairoon.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katherine Freitas on 15/08/2016.
 */
public class HistoricoEnvioContatoNeon implements Parcelable {
    private List<ContaNeon> contatosNeon;

    public HistoricoEnvioContatoNeon() {
    }

    public List<ContaNeon> getContatosNeon() {
        return contatosNeon;
    }

    public void setContatosNeon(List<ContaNeon> contatosNeon) {
        this.contatosNeon = contatosNeon;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.contatosNeon);
    }

    protected HistoricoEnvioContatoNeon(Parcel in) {
        this.contatosNeon = new ArrayList<ContaNeon>();
        in.readList(this.contatosNeon, ContaNeon.class.getClassLoader());
    }

    public static final Parcelable.Creator<HistoricoEnvioContatoNeon> CREATOR = new Parcelable.Creator<HistoricoEnvioContatoNeon>() {
        @Override
        public HistoricoEnvioContatoNeon createFromParcel(Parcel source) {
            return new HistoricoEnvioContatoNeon(source);
        }

        @Override
        public HistoricoEnvioContatoNeon[] newArray(int size) {
            return new HistoricoEnvioContatoNeon[size];
        }
    };
}
