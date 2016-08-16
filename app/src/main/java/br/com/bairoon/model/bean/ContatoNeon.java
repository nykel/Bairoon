package br.com.bairoon.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Nykel Andersow on 15/08/2016.
 */
public class ContatoNeon implements Parcelable {

    private String nome;
    private String foto;
    private String telefone;
    private BigDecimal valorRecebido;
    private Date dataRecebimento;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nome);
        dest.writeString(this.foto);
        dest.writeString(this.telefone);
        dest.writeSerializable(this.valorRecebido);
        dest.writeLong(this.dataRecebimento != null ? this.dataRecebimento.getTime() : -1);
    }

    public ContatoNeon() {
    }

    protected ContatoNeon(Parcel in) {
        this.nome = in.readString();
        this.foto = in.readString();
        this.telefone = in.readString();
        this.valorRecebido = (BigDecimal) in.readSerializable();
        long tmpDataRecebimento = in.readLong();
        this.dataRecebimento = tmpDataRecebimento == -1 ? null : new Date(tmpDataRecebimento);
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

    public BigDecimal getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(BigDecimal valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    public Date getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(Date dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }


}
