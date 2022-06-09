package kr.ac.kumoh.backend.dto;

import lombok.Getter;


@Getter
public class PersonDTO {

    private String name;
    private String occupation;

    public PersonDTO(String name, String occupation) {
        this.name = name;
        this.occupation = occupation;
    }
}
