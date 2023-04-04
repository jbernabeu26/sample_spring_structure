package com.waigo.hexagonal.infraestructure.event;

@Repository
public interface JpaEventRepository extends JpaRepository<Event, Long>{}