package br.ufpb.dcx.oppfyhub.opportunityhub.mappers;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.JobRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Job;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {
    @Autowired
    ModelMapper modelMapper;

    public Job updateJobFromDto(JobRequestDTO jobRequestDTO, Job jobToUpdate) {
        modelMapper.map(jobRequestDTO, jobToUpdate);
        return jobToUpdate;
    }
}
