package br.ufpb.dcx.oppfyhub.opportunityhub.controller;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.JobResponseInterestedUsersDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.JobTitleRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.JobRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.JobResponseDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Job;
import br.ufpb.dcx.oppfyhub.opportunityhub.enums.TypeJob;
import br.ufpb.dcx.oppfyhub.opportunityhub.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("v1/api/jobs")
public class JobController {
    @Autowired
    JobService jobService;

    // Gets

    @Operation(
            summary = "Get all Jobs in system",
            description = "Returns a list of system jobs, if it does not find any job it returns an empty list, the endpoint returns all the job information"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return list of jobs",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Job.class)) })})
    @GetMapping // ok
    @ResponseStatus(code= HttpStatus.OK)
    public List<JobResponseDTO> getAllJobs() {
        return jobService.getAllJobs();
    }

    @Operation(
            summary = "Returns all the subjects that the user has shown interest in",
            security = @SecurityRequirement(name = "bearerAuth"),
            description = "Returns all the subjects that the user has shown interest in, the authorization token must be passed in the request so that the user's information can be extracted"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns all the subjects that the user has shown interest in",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Job.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
    })
    @GetMapping("interests")
    @ResponseStatus(code = HttpStatus.OK)
    public List<JobResponseDTO> getAllInterestsFromUser(@Parameter(description = "Bearer token authorization", required = true,hidden = true , schema = @Schema(implementation = String.class)) @RequestHeader("Authorization") String header) {
        return jobService.getAllInterestsFromUser(header);
    }

    @Operation(
            summary = "Get job by id",
            description = "Return the job by id, if there is no job with the specified id, it returns a 404 error"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found job",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Job.class)) }),
            @ApiResponse(responseCode = "404", description = "Job not found",
                    content = @Content),
    })
    @GetMapping("{id}") // ok
    @ResponseStatus(code=HttpStatus.OK)
    public JobResponseDTO getJob(@PathVariable long id) {
        return jobService.getJob(id);
    }

    @Operation(
            summary = "Get list of jobs by title job",
            description = "Return list of jobs by title job, If there is no job that contains the name, you can return an empty list"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of jobs",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Job.class)) })
    })
    @GetMapping("titleJobs") // ok
    @ResponseStatus(code = HttpStatus.OK)
    public List<JobResponseDTO> getJobByTitleJob(@RequestParam String titleJob) {
        return jobService.getJobByTitleJob(titleJob);
    }

    @Operation(
            summary = "Get list of jobs by type Job",
            description = "Return list of jobs by type job, If there is no job associated to a this type, you can return an empty list"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of jobs",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Job.class)) })
    })
    @GetMapping("typeJobs") // ok
    @ResponseStatus(code = HttpStatus.OK)
    public List<JobResponseDTO> getJobsByTypeJob(@RequestParam TypeJob typeJob) {
        return jobService.getJobsByTypeJob(typeJob);
    }

    // Posts

    @Operation(
            summary = "Create job",
            security = @SecurityRequirement(name = "bearerAuth"),
            description = "Create a new job, if this job for successful created, return this job"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Job created successful",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Job.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized user",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
    })
    @PostMapping //ok
    @ResponseStatus(code=HttpStatus.CREATED)
    public JobResponseDTO createJob(@RequestBody @Valid JobRequestDTO jobRequestDTO,@Parameter(description = "Bearer token authorization", required = true,hidden = true , schema = @Schema(implementation = String.class)) @RequestHeader("Authorization") String header) {
        return jobService.createJob(jobRequestDTO, header);
    }

    @Operation(
            summary = "Register interest in job by id",
            security = @SecurityRequirement(name = "bearerAuth"),
            description = "Register interest in exists job, if this job not exists return 404, its required the user authorization header. If this user not is Student return not authorized and if this user not exists return 404 user not found"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List user interest in this jobs",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Job.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized user",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found or job not found",
                    content = @Content),
    })
    @PostMapping("{id}/interests")
    @ResponseStatus(code = HttpStatus.OK)
    public JobResponseInterestedUsersDTO realizeInterest(@PathVariable long id,@Parameter(description = "Bearer token authorization", required = true,hidden = true , schema = @Schema(implementation = String.class))  @RequestHeader("Authorization") String header) {
        return jobService.realizeInterest(id, header);
    }

    // Puts
    @Operation(
            summary = "Alter job info by id",
            security = @SecurityRequirement(name = "bearerAuth"),
            description = "Change the job exists, if this job does not exist it returns 404, the user authorization header is required. If this user is not a teacher or did not create the subject, return unauthorized and if this user does not exist, return 404 user not found"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "changed job",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Job.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized user",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found or job not found",
                    content = @Content),
    })
    @PutMapping("{id}/change") // ok
    @ResponseStatus(code=HttpStatus.OK)
    public JobResponseDTO changeInfoJob(@PathVariable long id, @RequestBody @Valid JobRequestDTO jobRequestDTO,@Parameter(description = "Bearer token authorization", required = true,hidden = true , schema = @Schema(implementation = String.class)) @RequestHeader("Authorization") String header) {
        return jobService.changeInfoJob(id, jobRequestDTO, header);
    }

    // Patches
    @Operation(
            summary = "Alter title job by id",
            security = @SecurityRequirement(name = "bearerAuth"),
            description = "Change title if job exists, if this job does not exist it returns 404, the user authorization header is required. If this user is not a teacher or did not create the subject, return unauthorized and if this user does not exist, return 404 user not found"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "changed job",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Job.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized user",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found or job not found",
                    content = @Content),
    })
    @PatchMapping("{id}/title") // ok
    @ResponseStatus(code=HttpStatus.OK)
    public JobResponseDTO changeTitleJob(@PathVariable long id, @RequestBody @Valid JobTitleRequestDTO jobTitleRequestDTO,@Parameter(description = "Bearer token authorization", required = true,hidden = true , schema = @Schema(implementation = String.class)) @RequestHeader("Authorization") String header) {
        return jobService.changeTitleJob(id, jobTitleRequestDTO, header);
    }


    // Deletes
    @Operation(
            summary = "Delete job by id",
            security = @SecurityRequirement(name = "bearerAuth"),
            description = "Delete job if this job exists, if this job does not exist it returns 404, the user authorization header is required. If this user is not a teacher or did not create the subject, return unauthorized and if this user does not exist, return 404 user not found"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "changed job",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Job.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized user",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found or job not found",
                    content = @Content),
    })
    @DeleteMapping("{id}/delete") // ok
    @ResponseStatus(code = HttpStatus.OK)
    public JobResponseDTO deleteJob(@PathVariable long id,@Parameter(description = "Bearer token authorization", required = true,hidden = true , schema = @Schema(implementation = String.class)) @RequestHeader("Authorization") String header) {
        return jobService.deleteJob(id, header);
    }

}
