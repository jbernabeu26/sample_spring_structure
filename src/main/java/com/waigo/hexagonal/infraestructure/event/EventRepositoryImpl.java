package com.waigo.hexagonal.infraestructure.event;

@Repository
public class EventRepositoryImpl implements EventRepository {
    private final JpaEventRepository jpaEventRepository;

    public EventRepositoryImpl(JpaEventRepository jpaEventRepository) {
        this.jpaEventRepository = jpaEventRepository;
    }

    @Override
    public Event save(Event event) {
        return jpaEventRepository.save(event);
    }

    @Override
    public void deleteById(Long eventId) {
        jpaEventRepository.deleteById(eventId);
    }

    @Override
    public List<Event> findByCategory(Category category) {
        return jpaEventRepository.findByCategory(category);
    }

    @Override
    public Event findById(Long eventId) {
        return jpaEventRepository.findById(eventId).orElseThrow(() -> new EntityNotFoundException("Event not found"));
    }
}