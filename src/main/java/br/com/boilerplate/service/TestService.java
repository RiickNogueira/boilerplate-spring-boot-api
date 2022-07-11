package br.com.boilerplate.service;

import br.com.boilerplate.model.Test;
import br.com.boilerplate.model.dto.TestFormDTO;
import br.com.boilerplate.repository.TestRepository;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Test> findAll() {
        return testRepository.findAllByOrderByDescription();
    }

    public Test findById(Long id){
        Optional<Test> result = testRepository.findById(id);

        return result.orElseThrow(() -> new ObjectNotFoundException(id, this.getClass().getName()));
    }

    public Test save(Test obj) {
        if(obj.getId() == null){
            return create(obj);
        }
        else{
            return update(obj);
        }
    }

    private Test create(Test obj) {
        return testRepository.save(obj);
    }

    private Test update(Test updatedObj) {
        Test obj = findById(updatedObj.getId());

        obj.setDescription(updatedObj.getDescription());

        return testRepository.save(obj);
    }

    public void delete(Long id){
        Test obj = findById(id);

        testRepository.delete(obj);
    }

    public Test convertToModel(TestFormDTO dto) {
        return modelMapper.map(dto, Test.class);
    }
}
