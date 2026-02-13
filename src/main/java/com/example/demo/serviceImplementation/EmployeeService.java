package com.example.demo.serviceImplementation;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        //Return type EmployeeDTO
        //EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        //return modelMapper.map(employeeEntity, EmployeeDTO.class);

        //Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        //return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1, EmployeeDTO.class));

        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));

    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
//        to check if user is admin
//        logging
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }


    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        isExistsByEmployeeId(employeeId);
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public void isExistsByEmployeeId(Long employeeId) {
       // boolean return employeeRepository.existsById(employeeId);
        boolean exists = employeeRepository.existsById(employeeId);
        if(!exists) throw new ResourceNotFoundException("Employee not found with id: "+employeeId);
    }

    public boolean deleteEmployeeById(Long employeeId) {
//        boolean exists = isExistsByEmployeeId(employeeId);
//        if(!exists) return false;
        isExistsByEmployeeId(employeeId);
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
//        boolean exists = isExistsByEmployeeId(employeeId);
//        if(!exists) return null;
        isExistsByEmployeeId(employeeId);

        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        /*updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });*/

        updates.forEach((field, value) -> {
            BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(employeeEntity);
            wrapper.setPropertyValue(field, value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }

}









