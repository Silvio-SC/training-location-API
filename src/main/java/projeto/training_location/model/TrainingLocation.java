package projeto.training_location.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TrainingLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
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


    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<String> getFight_tags() {
        return fight_tags;
    }
    public void setFight_tags(List<String> fight_tags) {
        this.fight_tags = fight_tags;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<String> getPhotos() {
        return photos;
    }
    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
    public BigDecimal getEvaluation_average() {
        return evaluation_average;
    }
    public void setEvaluation_average() {
        Double list = this.assessments.stream().mapToDouble(Assenssment::getScore).average().orElse(0.0);
        BigDecimal num = BigDecimal.valueOf(list);
        this.evaluation_average = num;
    }
    public String getResponsible() {
        return responsible;
    }
    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }


    public enum fight_tags {
        Boxing, Muay_Thai, Jiu_Jitsu
    }

}