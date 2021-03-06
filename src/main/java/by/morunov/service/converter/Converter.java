package by.morunov.service.converter;

import java.util.List;

/**
 * @author Alex Morunov
 */
public interface Converter<Entity, Dto> {

    Entity toEntity(Dto dto);

    List<Entity> toEntity(List<Dto> dtos);

    Dto toDto(Entity entity);

    List<Dto> toDto(List<Entity> entities);
}
