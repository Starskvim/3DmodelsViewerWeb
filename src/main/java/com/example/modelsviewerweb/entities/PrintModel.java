package com.example.modelsviewerweb.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "MODELPRINT")
@NoArgsConstructor
@Setter
@Getter
public class PrintModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelName;

    private String modelDerictory;

    private String modelCategory;

//    cascade = CascadeType.ALL,

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn
    private Set<ModelZIP> modelZIPSet = new HashSet<>();


    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn
    private Set<ModelOTH> modelOTHSet = new HashSet<>();


    public PrintModel(String modelName, String modelDerictory, String modelCategory) {
        this.modelName = modelName;
        this.modelDerictory = modelDerictory;
        this.modelCategory = modelCategory;
    }

    public void addModelZIP(ModelZIP modelZIP) {
        modelZIPSet.add(modelZIP);
    }

    public void addModelOTH(ModelOTH modelOTH) {
        modelOTHSet.add(modelOTH);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrintModel that = (PrintModel) o;
        return modelName.equals(that.modelName)
                && modelDerictory.equals(that.modelDerictory)
                && modelCategory.equals(that.modelCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelName, modelDerictory, modelCategory);
    }
}