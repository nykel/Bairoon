package br.com.bairoon.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;

public class ContatoNeon implements Parcelable {

    private int id;
    private String nome;
    private String foto;
    private String telefone;

    public ContatoNeon(int id, String nome, String foto, String telefone) {
        this.id = id;
        this.nome = nome;
        this.foto = foto;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nome);
        dest.writeString(this.foto);
        dest.writeString(this.telefone);
    }

    protected ContatoNeon(Parcel in) {
        this.id = in.readInt();
        this.nome = in.readString();
        this.foto = in.readString();
        this.telefone = in.readString();
    }

    public static final Parcelable.Creator<ContatoNeon> CREATOR = new Parcelable.Creator<ContatoNeon>() {
        @Override
        public ContatoNeon createFromParcel(Parcel source) {
            return new ContatoNeon(source);
        }

        @Override
        public ContatoNeon[] newArray(int size) {
            return new ContatoNeon[size];
        }
    };
}
