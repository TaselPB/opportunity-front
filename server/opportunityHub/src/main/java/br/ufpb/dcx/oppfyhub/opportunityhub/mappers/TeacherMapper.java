package br.ufpb.dcx.oppfyhub.opportunityhub.mappers;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.UserRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {
    @Autowired
    ModelMapper modelMapper;

    public User updateTeacherFromDTO(UserRequestDTO userRequestDTO, User userToUpdate) {
        modelMapper.map(userRequestDTO, userToUpdate);
        return userToUpdate;
    }
}
