package com.example.modelsviewerweb.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY,
            mappedBy="modelTags")
    private List<PrintModel> printModels = new ArrayList<>();
}
