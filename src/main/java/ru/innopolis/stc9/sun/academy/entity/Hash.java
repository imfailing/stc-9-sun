package ru.innopolis.stc9.sun.academy.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;


@Data
@Entity
@Table(name = "\"recover\"")
public class Hash {

    @Id
    private String hash;

    private int userid;

    private int recovered;

    public Hash(String hash, int userid, int recovered) {
        this.hash = hash;
        this.userid = userid;
        this.recovered = recovered;
    }

    public String getHash() {
        return hash;
    }

    public int getUserid() {
        return userid;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hash hash1 = (Hash) o;
        return userid == hash1.userid &&
                recovered == hash1.recovered &&
                Objects.equals(hash, hash1.hash);
    }

    @Override
    public int hashCode() {

        return Objects.hash(hash, userid, recovered);
    }
}
