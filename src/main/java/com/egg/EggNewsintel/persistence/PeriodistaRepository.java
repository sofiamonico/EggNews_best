package com.egg.EggNewsintel.persistence;

import com.egg.EggNewsintel.domain.Journalist;
import com.egg.EggNewsintel.domain.New;
import com.egg.EggNewsintel.domain.repository.JournalistRepository;
import com.egg.EggNewsintel.persistence.crud.PeriodistaCrudRepository;
import com.egg.EggNewsintel.persistence.entity.Periodista;
import com.egg.EggNewsintel.persistence.mapper.JournalistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PeriodistaRepository implements JournalistRepository {
    @Autowired
    private PeriodistaCrudRepository periodistaCrudRepository;
    @Autowired
    private JournalistMapper mapper;


    @Override
    public List<Journalist> getAll() {
        List<Periodista> periodistas = (List<Periodista>) periodistaCrudRepository.findAll();
        return mapper.toJournalists(periodistas);
    }

    @Override
    public Optional<Journalist> getJournalist(int journalistId) {
        return periodistaCrudRepository.findById(journalistId).map(periodista -> mapper.toJournalist(periodista));
    }

    @Override
    public Journalist save(Journalist journalist) {
        Periodista periodista = mapper.toPeriodista(journalist);
        return mapper.toJournalist(periodistaCrudRepository.save(periodista));
    }

    @Override
    public void delete(int journalistId) {

    }
}
