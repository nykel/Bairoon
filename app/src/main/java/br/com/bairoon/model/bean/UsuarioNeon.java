package br.com.bairoon.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class UsuarioNeon implements Parcelable {

    private int id;
    private String nome;
    private String email;
    private String token;
    private List<ContatoNeon> constatos;
    private ContaNeon contaNeon;

    public UsuarioNeon(String nome, String email) {
        this.id = 1;
        this.nome = nome;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<ContatoNeon> getConstatos() {
        return constatos;
    }

    public void setConstatos(List<ContatoNeon> constatos) {
        this.constatos = constatos;
    }

    public ContaNeon getContaNeon() {
        return contaNeon;
    }

    public void setContaNeon(ContaNeon contaNeon) {
        this.contaNeon = contaNeon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nome);
        dest.writeString(this.email);
        dest.writeString(this.token);
        dest.writeList(this.constatos);
        dest.writeParcelable(this.contaNeon, flags);
    }

    protected UsuarioNeon(Parcel in) {
        this.id = in.readInt();
        this.nome = in.readString();
        this.email = in.readString();
        this.token = in.readString();
        this.constatos = new ArrayList<ContatoNeon>();
        in.readList(this.constatos, ContatoNeon.class.getClassLoader());
        this.contaNeon = in.readParcelable(ContaNeon.class.getClassLoader());
    }

    public static final Parcelable.Creator<UsuarioNeon> CREATOR = new Parcelable.Creator<UsuarioNeon>() {
        @Override
        public UsuarioNeon createFromParcel(Parcel source) {
            return new UsuarioNeon(source);
        }

        @Override
        public UsuarioNeon[] newArray(int size) {
            return new UsuarioNeon[size];
        }
    };
}
