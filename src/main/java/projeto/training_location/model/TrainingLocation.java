package projeto.training_location.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TrainingLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @Embedded
    private Address address;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @ElementCollection(targetClass = fight_tags.class)
    @Enumerated(EnumType.STRING)
    private List<String> fight_tags;

    @Column(nullable = false)
    private String description;

    @Column()
    private List<String> photos;

    @Column(precision = 3, scale = 2)
    private BigDecimal evaluation_average;

    @Column()
    private String responsible;

    @Column(nullable = false)
    private String price;

    @OneToMany
    private List<Assenssment> assessments;

    @OneToMany
    private List<DayActivity> days_activities;


    public enum fight_tags {
        Boxing, Muay_Thai, Jiu_Jitsu, MMA, Karate, Judo, Taekwondo, Kung_Fu
    }

}