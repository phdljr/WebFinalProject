package kr.ac.kumoh.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class Top10MovieDTO implements Comparable<Top10MovieDTO> {

    private String title;
    private Double rate;
    private Double grade;
    private String mediaRating;

    public void setMediaRating(String mediaRating) {
        this.mediaRating = mediaRating;
    }

    @Override
    public int compareTo(Top10MovieDTO dto) {
        if (this.grade < dto.getGrade())
            return 1;
        else if (this.grade > dto.getGrade())
            return -1;

        return 0;
    }
}
