package alura.challenge.forohub.application.service;

import alura.challenge.forohub.application.exceptions.DuplicatedTopicException;
import alura.challenge.forohub.application.exceptions.NotFoundException;
import alura.challenge.forohub.application.exceptions.UnauthorizedUserException;
import alura.challenge.forohub.application.port.out.persistence.CourseRepository;
import alura.challenge.forohub.application.port.out.persistence.TopicRepository;
import alura.challenge.forohub.application.port.in.usecase.CreateNewTopicUseCase;
import alura.challenge.forohub.application.port.out.persistence.UserRepository;
import alura.challenge.forohub.domain.course.CourseId;
import alura.challenge.forohub.domain.topic.Topic;
import alura.challenge.forohub.domain.user.UserId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@RequiredArgsConstructor
@Validated
@Scope( proxyMode = ScopedProxyMode.TARGET_CLASS )
public class CreateTopicService implements CreateNewTopicUseCase {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Override
    public Topic createNewTopic(@NotNull @Valid Topic topic, @NotNull @Valid UserId authorId,@NotNull @Valid CourseId courseId, @NotNull @Valid UserId authenticatedUserId) throws DuplicatedTopicException, NotFoundException, UnauthorizedUserException {
        if(!authorId.equals(authenticatedUserId)) throw new UnauthorizedUserException("The authenticated user ID must match the author ID specified in the Topic creation request.");

        var isTopicDuplicated = topicRepository.isTopicDuplicated(topic.title(), topic.content());
        var topicAuthor = userRepository.findUserById(authorId);
        var topicCourse = courseRepository.findCourseById(courseId);

        if(isTopicDuplicated) throw new DuplicatedTopicException("A topic with the same title and content already exists.");
        if(topicAuthor.isEmpty()) throw  new NotFoundException("The specified User was not found.");
        if(topicCourse.isEmpty()) throw  new NotFoundException("The specified Course was not found.");

        topic.author(topicAuthor.get());
        topic.course(topicCourse.get());

        return topicRepository.saveNew(topic);
    }
}
