package io.mallinicouture.data.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Advertisement {

    private Long id;
    @SerializedName("text")
    private String title;
    @SerializedName("subText")
    private String information;
    private Image image;

}
