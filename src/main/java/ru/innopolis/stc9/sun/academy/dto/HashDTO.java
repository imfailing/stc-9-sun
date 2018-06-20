package ru.innopolis.stc9.sun.academy.dto;

import java.util.Objects;

public class HashDTO {
    private String hash;
    private int userid;
    private int recovered;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    @Override
    public String toString() {
        return "HashDTO{" +
                "hash='" + hash + '\'' +
                ", userid=" + userid +
                ", recovered=" + recovered +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashDTO hashDTO = (HashDTO) o;
        return userid == hashDTO.userid &&
                recovered == hashDTO.recovered &&
                Objects.equals(hash, hashDTO.hash);
    }

    @Override
    public int hashCode() {

        return Objects.hash(hash, userid, recovered);
    }
}
