package ru.innopolis.stc9.sun.academy.dto.mapper;

import ru.innopolis.stc9.sun.academy.dto.HashDTO;
import ru.innopolis.stc9.sun.academy.entity.Hash;

public class HashMapper {
    public HashMapper() {
    }

    public static HashDTO toDto (Hash hash)
    {
        if (hash == null) return null;
        HashDTO hashDTO = new HashDTO();
        hashDTO.setHash(hash.getHash());
        hashDTO.setRecovered(hash.getRecovered());
        hashDTO.setUserid(hash.getUserid());
        return hashDTO;
    }

    public static Hash toEntity (HashDTO hashDTO)
    {
        if (hashDTO == null) return null;
        return new Hash(hashDTO.getHash(),hashDTO.getUserid(),hashDTO.getRecovered());
    }
}
