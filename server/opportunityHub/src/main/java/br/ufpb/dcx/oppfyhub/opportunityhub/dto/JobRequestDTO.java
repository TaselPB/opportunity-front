package br.ufpb.dcx.oppfyhub.opportunityhub.dto;

import br.ufpb.dcx.oppfyhub.opportunityhub.enums.TypeJob;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class JobRequestDTO {
    @JsonProperty("number_vacancies")
    @NotNull(message = "Number vacancies of project not be a null")
    private Integer numberVacancies;

    @JsonProperty("hours_week")
    @NotNull(message = "Hours week of project not be a null")
    private Integer hoursWeek;

    @JsonProperty("scholarship_value")
    @NotNull(message = "Scholarship value of project not be a null")
    private Double scholarshipValue;

    @JsonProperty("opening_date")
    @NotNull(message = "Opening date of project not be a null")
    private LocalDate openingDate;

    @JsonProperty("benefits")
    @NotNull(message = "Benefits of project not be a null")
    private String benefits;

    @JsonProperty("title_job")
    @NotNull(message = "Title of Job project not be a null")
    private String titleJob;

    @JsonProperty("pdf_link")
    @NotNull(message = "PDF link not be a null")
    private String pdfLink;

    @JsonProperty("closing_date")
    @NotNull(message = "Closing date of project not be a null")
    private LocalDate closingDate;

    @JsonProperty("type_job")
    @NotNull(message = "Type of job not be a null")
    @Enumerated(EnumType.STRING)
    private TypeJob typeJob;

    @JsonProperty("name_project")
    @NotNull(message = "Name of project not be a null")
    private String nameProject;

    @JsonProperty("link_job")
    @NotNull(message = "Link of job not be a null")
    private String linkJob;
}
