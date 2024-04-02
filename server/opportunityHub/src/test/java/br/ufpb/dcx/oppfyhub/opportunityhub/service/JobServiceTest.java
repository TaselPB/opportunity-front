package br.ufpb.dcx.oppfyhub.opportunityhub.service;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.JobRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.JobResponseDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Job;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.User;
import br.ufpb.dcx.oppfyhub.opportunityhub.enums.TypeJob;
import br.ufpb.dcx.oppfyhub.opportunityhub.execption.NotFoundJobException;
import br.ufpb.dcx.oppfyhub.opportunityhub.execption.NotFoundUserException;
import br.ufpb.dcx.oppfyhub.opportunityhub.mappers.JobMapper;
import br.ufpb.dcx.oppfyhub.opportunityhub.repository.JobRepository;
import br.ufpb.dcx.oppfyhub.opportunityhub.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class JobServiceTest {
    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobService jobService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JobMapper jobMapper;

//    @Test
//    public void getAllJobsTest() {
//        User user1 = new User();
//        user1.setId(1L);
//        user1.setName("Professor 1");
//
//        TypeJob typeJob = TypeJob.PROJETO_DE_EXTENSAO;
//        Job newJob = new Job(30, 30, 300.00, LocalDate.now(), "Nenhum", "Vaga phoebus teste 1",
//                "Teste", LocalDate.of(2023, 8, 5), user1, typeJob,
//                "Ayty", "Teste");
//        newJob.setId(1L);
//
//        TypeJob typeJob2 = TypeJob.PROJETO_DE_EXTENSAO;
//
//        Job newJob2 = new Job(30, 30, 300.00, LocalDate.now(), "Nenhum", "Vaga phoebus",
//                "Teste", LocalDate.of(2023, 8, 5), user1, typeJob2,
//                "Ayty", "Teste");
//        newJob2.setId(2L);
//
//        List<Job> listJobs = Arrays.asList(newJob, newJob2);
//
//        when(jobRepository.findAll()).thenReturn(listJobs);
//        List<JobResponseDTO> responseDTOS = jobService.getAllJobs();
//
//        assertNotNull(responseDTOS);
//        assertEquals(2, responseDTOS.size());
//
//        JobResponseDTO responseDTO1 = responseDTOS.get(0);
//        assertEquals(1L, responseDTO1.getId());
//        assertEquals("Ayty", responseDTO1.getNameProject());
//        assertEquals(30, responseDTO1.getNumberVacancies());
//        assertEquals(30, responseDTO1.getHoursWeek());
//        assertEquals(300.00, responseDTO1.getScholarshipValue());
//        assertEquals("Nenhum", responseDTO1.getBenefits());
//        assertEquals("Vaga phoebus teste 1", responseDTO1.getTitleJob());
//
//
//        JobResponseDTO responseDTO2 = responseDTOS.get(1);
//        assertEquals(2L, responseDTO2.getId());
//        assertEquals("Ayty", responseDTO2.getNameProject());
//        assertEquals(30, responseDTO2.getNumberVacancies());
//        assertEquals(30, responseDTO2.getHoursWeek());
//        assertEquals(300.00, responseDTO2.getScholarshipValue());
//        assertEquals("Nenhum", responseDTO2.getBenefits());
//        assertEquals("Vaga phoebus", responseDTO2.getTitleJob());
//
//        verify(jobRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void testCreateJobSuccess() {
//        // Mocking Teacher and JobRequestDTO
//        User user = new User();
//        JobRequestDTO jobRequestDTO = new JobRequestDTO();
//        jobRequestDTO.setTeacherID(1L);
//        jobRequestDTO.setNumberVacancies(10);
//        // Set other properties of jobRequestDTO
//
//        // Mocking the behavior of teacherRepository
//        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//
//        // Mocking the behavior of jobRepository
//        Job savedJob = new Job(); // create a mock saved Job object
//        when(jobRepository.save(any(Job.class))).thenReturn(savedJob);
//
//        // Call the method under test
//        JobResponseDTO result = jobService.createJob(jobRequestDTO);
//
//        // Assertions
//        assertNotNull(result);
//        // You can add more assertions based on the expected behavior
//
//        // Verify interactions
//        verify(userRepository, times(1)).findById(1L);
//        verify(jobRepository, times(1)).save(any(Job.class));
//    }
//
//    @Test
//    public void testCreateJobTeacherNotFound() {
//        // Mocking JobRequestDTO
//        JobRequestDTO jobRequestDTO = new JobRequestDTO();
//        jobRequestDTO.setTeacherID(1L);
//        jobRequestDTO.setNumberVacancies(10);
//        // Set other properties of jobRequestDTO
//
//        // Mocking the behavior of teacherRepository
//        when(userRepository.findById(1L)).thenReturn(Optional.empty());
//
//        // Call the method under test and expect an exception
//        assertThrows(NotFoundUserException.class, () -> jobService.createJob(jobRequestDTO));
//
//        // Verify interactions
//        verify(userRepository, times(1)).findById(1L);
//        verify(jobRepository, never()).save(any(Job.class));
//    }
//
//    @Test
//    public void getJobTest() {
//        long jobId = 1L;
//
//        Job job = new Job();
//        job.setId(jobId);
//        job.setTitleJob("Vaga do ayty");
//
//        when(jobRepository.findById(job.getId())).thenReturn(Optional.of(job));
//
//        JobResponseDTO jobResponseDTO = jobService.getJob(job.getId());
//
//        assertNotNull(jobResponseDTO);
//        assertEquals(jobId, jobResponseDTO.getId());
//        assertEquals("Vaga do ayty", jobResponseDTO.getTitleJob());
//
//        verify(jobRepository, times(1)).findById(jobId);
//    }
//
//    @Test
//    public void getJobTestNotFound() {
//        long jobId = 2L;
//
//        when(jobRepository.findById(jobId)).thenReturn(Optional.empty());
//
//        assertThrows(NotFoundJobException.class, () -> jobService.getJob(jobId));
//
//        verify(jobRepository, times(1)).findById(jobId);
//        verify(jobRepository, never()).save(any(Job.class));
//    }
//
//    @Test
//    public void testChangeInfoJob() {
//        long jobId = 1L;
//        long teacherId = 1L;
//        JobRequestDTO jobRequestDTO = new JobRequestDTO();
//        jobRequestDTO.setTeacherID(teacherId);
//
//        Job existingJob = new Job();
//        existingJob.setId(jobId);
//
//        User user = new User();
//        user.setId(teacherId);
//
//        Job updatedJob = new Job();
//        updatedJob.setId(jobId);
//        updatedJob.setUserCreator(user);
//
//        when(jobRepository.findById(jobId)).thenReturn(Optional.of(existingJob));
//        when(userRepository.findById(teacherId)).thenReturn(Optional.of(user));
//        when(jobMapper.updateJobFromDto(jobRequestDTO, existingJob)).thenReturn(updatedJob);
//
//        JobResponseDTO result = jobService.changeInfoJob(jobId, jobRequestDTO);
//
//        verify(jobRepository, times(1)).findById(jobId);
//        verify(userRepository, times(1)).findById(teacherId);
//        verify(jobRepository, times(1)).save(updatedJob);
//
//        assertNotNull(result);
//        assertEquals(jobId, result.getId());
//    }
//
//    @Test
//    public void testChangeInfoJobNotFound() {
//        long jobId = 1L;
//        JobRequestDTO jobRequestDTO = new JobRequestDTO();
//
//        when(jobRepository.findById(jobId)).thenReturn(Optional.empty());
//
//        assertThrows(NotFoundJobException.class, () -> jobService.changeInfoJob(jobId, jobRequestDTO));
//
//        verify(jobRepository, times(1)).findById(jobId);
//    }
//
//    @Test
//    public void testChangeInfoJobTeacherNotFound() {
//        long jobId = 1L;
//        long teacherId = 1L;
//        JobRequestDTO jobRequestDTO = new JobRequestDTO();
//        jobRequestDTO.setTeacherID(1L);
//
//        Job existingJob = new Job();
//        when(jobRepository.findById(jobId)).thenReturn(Optional.of(existingJob));
//        when(userRepository.findById(teacherId)).thenReturn(Optional.empty());
//
//        assertThrows(NotFoundUserException.class, () -> jobService.changeInfoJob(jobId, jobRequestDTO));
//
//        verify(jobRepository, times(1)).findById(jobId);
//        verify(userRepository, times(1)).findById(teacherId);
//    }
//
//    @Test
//    public void testDeleteJobSuccess() {
//        // Mocking the job to be deleted
//        Job jobToDelete = new Job();
//        jobToDelete.setId(1L);
//
//        // Mocking the behavior of jobRepository.findById
//        when(jobRepository.findById(1L)).thenReturn(Optional.of(jobToDelete));
//
//        // Mocking the behavior of jobRepository.delete
//        doNothing().when(jobRepository).delete(jobToDelete);
//
//        // Call the method under test
//        JobResponseDTO result = jobService.deleteJob(1L);
//
//        // Assertions
//        assertNotNull(result);
//        assertEquals(1L, result.getId());
//
//        // Verify interactions
//        verify(jobRepository, times(1)).findById(1L);
//        verify(jobRepository, times(1)).delete(jobToDelete);
//    }
//
//    @Test
//    public void testDeleteJobNotFound() {
//        long jobId = 1L;
//
//        when(jobRepository.findById(jobId)).thenReturn(Optional.empty());
//
//        assertThrows(NotFoundJobException.class, () -> jobService.deleteJob(jobId));
//
//        verify(jobRepository, times(1)).findById(jobId);
//        verify(jobRepository, never()).delete(any(Job.class));
//    }
//
//    @Test
//    public void testGetJobByTitleJob() {
//        String titleJobPrefix = "Job";
//
//        Job newJob = new Job();
//        newJob.setId(1L);
//        newJob.setTitleJob("Job test");
//
//        Job anotherJob = new Job();
//        anotherJob.setTitleJob("Job right");
//
//        List<Job> jobs = new ArrayList<>();
//        jobs.add(newJob);
//        jobs.add(anotherJob);
//
//        // Mock the behavior of jobRepository.findByTitleJobStartingWith
//        when(jobRepository.findByTitleJobStartingWith("Job")).thenReturn(jobs);
//
//        List<JobResponseDTO> expectedResponse = new ArrayList<>();
//        expectedResponse.add(JobResponseDTO.from(newJob));
//        expectedResponse.add(JobResponseDTO.from(anotherJob));
//
//        // Call the method under test
//        List<JobResponseDTO> result = jobService.getJobByTitleJob(titleJobPrefix);
//
//        // Assert the result
//        assertEquals(expectedResponse.size(), result.size());
//
//        // Verify interactions
//        verify(jobRepository, times(1)).findByTitleJobStartingWith(titleJobPrefix);
//    }
}
