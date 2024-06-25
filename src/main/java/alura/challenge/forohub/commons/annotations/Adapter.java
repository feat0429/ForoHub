package alura.challenge.forohub.commons.annotations;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface Adapter {

    @AliasFor(annotation = Component.class)
    String value() default "";
}
