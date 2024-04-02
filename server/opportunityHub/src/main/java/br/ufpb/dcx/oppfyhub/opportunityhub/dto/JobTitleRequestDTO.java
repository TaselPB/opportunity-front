package br.ufpb.dcx.oppfyhub.opportunityhub.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobTitleRequestDTO {
    @JsonProperty("title_job")
    @NotNull(message = "Title of Job project not be a null")
    @NotBlank
    private String titleJob;
}
