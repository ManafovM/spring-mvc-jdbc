package model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "owner")
public class Car {
    private long id;
    private String model;
    private User owner;
}
