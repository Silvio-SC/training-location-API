package projeto.training_location.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class DayActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    private Day day;

    @OneToMany
    @Column(nullable = false)
    private List<Activity> activities;

    @ManyToOne
    private TrainingLocation trainingLocation;

    

    public Day getDay() {
        return day;
    }



    public void setDay(Day day) {
        this.day = day;
    }



    public List<Activity> getActivities() {
        return activities;
    }



    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }



    public enum Day {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

}
