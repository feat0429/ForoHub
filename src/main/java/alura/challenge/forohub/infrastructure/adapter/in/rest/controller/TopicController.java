package alura.challenge.forohub.infrastructure.adapter.in.rest.controller;

import alura.challenge.forohub.application.exceptions.DuplicatedResponseException;
import alura.challenge.forohub.application.exceptions.DuplicatedTopicException;
import alura.challenge.forohub.application.exceptions.NotFoundException;
import alura.challenge.forohub.application.exceptions.UnauthorizedUserException;
import alura.challenge.forohub.application.service.*;
import alura.challenge.forohub.domain.course.CourseId;
import alura.challenge.forohub.domain.exceptions.BlankContentException;
import alura.challenge.forohub.domain.exceptions.BlankTitleException;
import alura.challenge.forohub.domain.exceptions.ClosedTopicException;
import alura.challenge.forohub.domain.response.ResponseId;
import alura.challenge.forohub.domain.topic.TopicId;
import alura.challenge.forohub.domain.user.User;
import alura.challenge.forohub.domain.user.UserId;
import alura.challenge.forohub.infrastructure.adapter.in.rest.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/topic")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class TopicController {

    private final FindTopicService findTopicService;
    private final CreateTopicService createTopicService;
    private final AddResponseService addResponseService;
    private final UpdateResponseService updateResponseService;
    private final FindResponseService findResponseService;
    private final UpdateTopicService updateTopicService;
    private final DeleteTopicService deleteTopicService;

    @GetMapping
    @Operation(summary = "Get all the stored topics. The result is paginated.")
    public ResponseEntity<Page<GetTopicWithResponseCountDto>> getAllTopics(Pageable pagination){
        var topics = findTopicService.findAllTopics(pagination);

        return ResponseEntity.ok(topics
                .map(GetTopicWithResponseCountDto::fromDomainModel));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a stored topic given an ID.")
    public ResponseEntity<GetTopicWithResponseCountDto> getTopicById(@PathVariable Long id ) throws NotFoundException {
        var topic = findTopicService.findTopicById(new TopicId(id));

        return ResponseEntity.ok(GetTopicWithResponseCountDto.fromDomainModel(topic));
    }

    @PostMapping
    @Operation(summary = "Creates a new topic given provided data.")
    public ResponseEntity<CreatedTopicDto> createNewTopic(@RequestBody CreateTopicDto createTopicDto) throws DuplicatedTopicException, NotFoundException, UnauthorizedUserException {
        var authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var createdTopic = createTopicService.createNewTopic(
                createTopicDto.toDomainModel(),
                new UserId(createTopicDto.authorId()),
                new CourseId(createTopicDto.courseId()),
                authenticatedUser.id()
        );
        URI url = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTopic.id().value())
                .toUri();

        return ResponseEntity
                .created(url)
                .body(CreatedTopicDto.fromDomainModel(createdTopic));
    }

    @PutMapping
    @Operation(summary = "Edits the title and/or content of an existing topic.")
    public ResponseEntity<UpdatedTopicDto> editTopic(@RequestBody EditTopicDto editTopicDto) throws DuplicatedTopicException, NotFoundException, ClosedTopicException, BlankContentException, BlankTitleException, UnauthorizedUserException {
        var authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var editedTopic = updateTopicService.editTopic(
            new TopicId(editTopicDto.topicId()),
            editTopicDto.title(),
            editTopicDto.content(),
            authenticatedUser.id()
        );

        return ResponseEntity
                .ok(UpdatedTopicDto.fromDomainModel(editedTopic));
    }

    @PutMapping("/{topicId}")
    @Operation(summary = "Closes an existing topic.")
    public ResponseEntity closeTopic(@PathVariable Long topicId) throws NotFoundException, ClosedTopicException {
        updateTopicService.closeTopic(new TopicId(topicId));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{topicId}")
    @Operation(summary = "Deletes an existing topic.")
    public ResponseEntity deleteTopic(@PathVariable Long topicId) throws NotFoundException {
        deleteTopicService.deleteTopic(new TopicId(topicId));

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{topicId}/response")
    @Operation(summary = "Get all the responses of an existing topic.")
    public ResponseEntity<List<GetResponseDto>> getResponsesByTopic(@PathVariable Long topicId) throws NotFoundException {
        var responses = findResponseService.findResponsesByTopic(new TopicId(topicId));

        return ResponseEntity
                .ok(responses.stream().map(GetResponseDto::fromDomainModel).toList());
    }

    @PostMapping("/{topicId}/response")
    @Operation(summary = "Adds a new responses to an existing OPEN topic.")
    public ResponseEntity<AddedResponseDto> addResponse(@PathVariable Long topicId, @RequestBody AddResponseDto addResponseDto) throws NotFoundException, ClosedTopicException, DuplicatedResponseException, UnauthorizedUserException {
        var authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var createdResponse = addResponseService.addNewResponseToTopic(
                addResponseDto.message(),
                new UserId(addResponseDto.authorId()),
                new TopicId(topicId),
                authenticatedUser.id()
        );


        return ResponseEntity
                .ok(AddedResponseDto.fromDomainModel(createdResponse));
    }

    @PutMapping("/{topicId}/response")
    @Operation(summary = "Edits an existing response of an OPEN topic.")
    public ResponseEntity<UpdatedResponseDto> editResponse(@PathVariable Long topicId, @RequestBody EditResponseDto editResponseDto) throws NotFoundException, ClosedTopicException, DuplicatedResponseException, UnauthorizedUserException {
        var authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var editedResponse = updateResponseService.editResponseById(
                new ResponseId(editResponseDto.responseId()),
                editResponseDto.message(),
                authenticatedUser.id()
        );

        return ResponseEntity
                .ok(UpdatedResponseDto.fromDomainModel(editedResponse));
    }

}
