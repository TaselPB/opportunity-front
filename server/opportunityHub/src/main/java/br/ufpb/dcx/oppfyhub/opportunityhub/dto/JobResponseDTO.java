package br.ufpb.dcx.oppfyhub.opportunityhub.dto;

import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Job;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.User;
import br.ufpb.dcx.oppfyhub.opportunityhub.enums.TypeJob;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class JobResponseDTO {
    private long id;
    private Integer numberVacancies;
    private Integer hoursWeek;
    private Double scholarshipValue;
    private LocalDate registrationData;
    private LocalDate openingDate;
    private String benefits;
    private String titleJob;
    private String pdfLink;
    private LocalDate closingDate;
    private TypeJob typeJob;
    private UserDTO userCreator; // Usar UserDTO em vez de User
    private List<UserDTO> interestedUsers; // Usar List<UserDTO> em vez de List<User>
    private String nameProject;
    private String linkJob;
    private Integer interests;

    // Outros campos e métodos

    public static JobResponseDTO from(Job job) {
        List<UserDTO> interestedUserDTOs = job.getInterestedUsers().stream()
                .map(UserDTO::from)
                .collect(Collectors.toList());

        return JobResponseDTO.builder()
                .id(job.getId())
                .numberVacancies(job.getNumberVacancies())
                .hoursWeek(job.getHoursWeek())
                .scholarshipValue(job.getScholarshipValue())
                .openingDate(job.getOpeningDate())
                .registrationData(job.getRegistrationData())
                .benefits(job.getBenefits())
                .titleJob(job.getTitleJob())
                .pdfLink(job.getPdfLink())
                .closingDate(job.getClosingDate())
                .userCreator(UserDTO.from(job.getUserCreator()))
                .interestedUsers(interestedUserDTOs)
                .typeJob(job.getTypeJob())
                .nameProject(job.getNameProject())
                .linkJob(job.getLinkJob())
                .interests(job.getInterests())
                .build();
    }

    public static List<JobResponseDTO> fromAll(List<Job> jobs) {
        return jobs.stream().map(JobResponseDTO::from).toList();
    }
}
