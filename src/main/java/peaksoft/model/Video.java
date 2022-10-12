package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "videos")
@NoArgsConstructor
@Getter
@Setter

public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "video_seq")
    @SequenceGenerator(name = "video_seq",sequenceName = "video_seq",allocationSize = 1)
     private Long id;
    @Column(name = "video_name")
     private String videoName;
     private String link;

     @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
     private Lesson lesson;

}
