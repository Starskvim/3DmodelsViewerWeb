package com.example.modelsviewerweb.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table
@RequiredArgsConstructor
@Setter
@Getter
public class PrintModelTagWeb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameTag;

    private Integer countModels = 0;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY,
            mappedBy="modelTags")
    private List<PrintModelWeb> printModels = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrintModelTagWeb modelTag = (PrintModelTagWeb) o;
        return Objects.equals(nameTag, modelTag.nameTag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameTag);
    }
}
