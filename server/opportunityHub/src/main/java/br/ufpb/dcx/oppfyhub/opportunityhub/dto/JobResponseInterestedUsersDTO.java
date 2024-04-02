package br.ufpb.dcx.oppfyhub.opportunityhub.dto;

import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Job;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class JobResponseInterestedUsersDTO {
    private long id;
    private List<UserDTO> interestedUsers;

    public static JobResponseInterestedUsersDTO from(Job job) {
        List<UserDTO> userDTOs = job.getInterestedUsers().stream()
                .map(UserDTO::from)
                .collect(Collectors.toList());

        return JobResponseInterestedUsersDTO.builder()
                .id(job.getId())
                .interestedUsers(userDTOs)
                .build();
    }
}
