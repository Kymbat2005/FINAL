package kz.narxoz.demo.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_finals")
public class Finals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private Long id;

    @Column(name = "t_name")
    private String name;
    @Column(name = "t_date")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "final_id")
    private  Finals finals;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "sunject_user",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Student> students;

}
