package br.com.squadjoaquina.errorlogger.repository.specs;

import br.com.squadjoaquina.errorlogger.model.Environment;
import br.com.squadjoaquina.errorlogger.model.ErrorAggregate;
import br.com.squadjoaquina.errorlogger.model.Level;
import org.springframework.data.jpa.domain.Specification;

public class ErrorAggregateSpecs {

    private ErrorAggregateSpecs() {
    }

    public static Specification<ErrorAggregate> environmentEquals(
            Environment environment) {
        return (root, query, builder) -> {
            if (environment == null) {
                return builder.isTrue(builder.literal(true));
            }

            return builder.equal(root.get("environment"), environment);
        };
    }

    public static Specification<ErrorAggregate> levelEquals(Level level) {
        return (root, query, builder) -> {

            if (level == null) {
                return builder.isTrue(builder.literal(true));
            }

            return builder.equal(root.get("level"), level);
        };
    }

    public static Specification<ErrorAggregate> originLike(String origin) {
        return (root, query, builder) -> {

            if (origin == null) {
                return builder.isTrue(builder.literal(true));
            }


            return builder.like(builder.upper(root.get("origin")),
                                "%" + origin.toUpperCase() + "%");
        };
    }

    public static Specification<ErrorAggregate> titleLike(String title) {
        return (root, query, builder) -> {

            if (title == null) {
                return builder.isTrue(builder.literal(true));
            }


            return builder.like(builder.upper(root.get("title")),
                                "%" + title.toUpperCase() + "%");
        };
    }
}
