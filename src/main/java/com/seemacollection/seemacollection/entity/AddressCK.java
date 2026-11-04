package com.seemacollection.seemacollection.entity;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AddressCK implements Serializable {
    private String tehsil;
    private int plotNo;

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        AddressCK addressCK = (AddressCK) object;
        return plotNo == addressCK.plotNo && Objects.equals(tehsil, addressCK.tehsil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tehsil, plotNo);
    }
}
