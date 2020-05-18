package io.mallinicouture.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "mc_image")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Link should not be blank")
    @URL(message = "Link should be a valid url")
    private String link;

    @ManyToOne
    @JoinColumn(name = "mc_dress_id")
    @JsonIgnore
    private Dress dress;

    public Image(String link) {
        this.link = link;
    }

}
