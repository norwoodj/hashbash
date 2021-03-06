package com.johnmalcolmnorwood.hashbash.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rainbow_table")
public class RainbowTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    private String name;
    private String status;

    @Column(name = "numchains")
    private int numChains;

    @Column(name = "chainlength")
    private int chainLength;

    @Column(name = "passwordlength")
    private int passwordLength;

    @Column(name = "chainsgenerated")
    private long chainsGenerated;

    @Column(name = "finalchaincount")
    private long finalChainCount;

    @Column(name = "characterset")
    private String characterSet;

    @Column(name = "hashfunction")
    @Enumerated(EnumType.STRING)
    private HashFunctionName hashFunction;

    @Column(name = "generatestarted")
    private Date generateStarted;

    @Column(name = "generatecompleted")
    private Date generateCompleted;

    @Column(name = "created")
    private Date created;

    @JsonGetter
    public Double getGenerationTime() {
        if (generateCompleted == null) {
            return null;
        }

        return (generateCompleted.getTime() - generateStarted.getTime()) / 1000.;
    }

    @PrePersist
    public void setCreated() {
        created = Date.from(ZonedDateTime.now(ZoneId.of("UTC")).toInstant());
    }
}
