package model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {

    private int id;
    private String name;
    private String surname;
    private String email;
    private int age;
}
