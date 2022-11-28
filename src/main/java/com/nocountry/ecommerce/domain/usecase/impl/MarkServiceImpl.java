package com.nocountry.ecommerce.domain.usecase.impl;

import com.nocountry.ecommerce.common.exception.error.ExistingNameException;
import com.nocountry.ecommerce.common.exception.error.ResourceNotFoundException;
import com.nocountry.ecommerce.domain.model.Mark;
import com.nocountry.ecommerce.domain.repository.MarkRepository;
import com.nocountry.ecommerce.domain.usecase.MarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MarkServiceImpl implements MarkService {


    private static final String NAME = "Mark";

    private final MarkRepository markRepository;

    //===================Find===================//

    @Transactional(readOnly = true)
    public Mark getByIdIfExists(Long id) {
        return markRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(NAME, id));
    }


    @Transactional(readOnly = true)
    public List<Mark> findAllActive() {
        return markRepository.findAllByIsAvailable();
    }


    //===================Update===================//

    @Transactional
    public Long save(Mark request) {
        existsName(request.getName());
        return markRepository.save(request).getId();
    }


    @Transactional
    public void update(Long id, Mark request) {
        Mark mark = getByIdIfExists(id);

        existsName(request.getName());
        mark.setName(request.getName());
    }


    @Transactional
    public void updateAvailable(Long id) {
        Mark mark = getByIdIfExists(id);

        mark.setIsAvailable(true);
        markRepository.save(mark);
    }

    //===================Delete===================//

    @Transactional
    public void deleteById(Long id) {
        Mark mark = getByIdIfExists(id);
        markRepository.delete(mark);
    }

    //===================Util===================//

    private void existsName(String name) {
        if (markRepository.existsByName(name)) throw new ExistingNameException(name);
    }

}
