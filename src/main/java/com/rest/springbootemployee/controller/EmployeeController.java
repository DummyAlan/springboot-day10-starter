package com.rest.springbootemployee.controller;

import com.rest.springbootemployee.controller.dto.EmployeeRequest;
import com.rest.springbootemployee.controller.dto.EmployeeResponse;
import com.rest.springbootemployee.controller.mapper.EmployeeMapper;
import com.rest.springbootemployee.entity.Employee;
import com.rest.springbootemployee.exception.IdInvalidException;
import com.rest.springbootemployee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<EmployeeResponse> getAll() {
        return employeeMapper.toResponse(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public EmployeeResponse getById(@PathVariable String id) {
        if(!id.isEmpty()){
            return employeeMapper.toResponse(employeeService.findById(id));
        }
        throw new IdInvalidException();
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getByGender(@RequestParam String gender) {
        return employeeService.findByGender(gender);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse add(@RequestBody EmployeeRequest employeeRequest) {
        return employeeMapper.toResponse(employeeService.create(employeeMapper.toEntity(employeeRequest)));
    }
    @PutMapping("/{id}")
    public EmployeeResponse update(@PathVariable String id, @RequestBody EmployeeRequest employeeRequest) {
        if (!id.isEmpty()){
            return employeeMapper.toResponse(employeeService.update(id, employeeMapper.toEntity(employeeRequest)));
        }
        throw new IdInvalidException();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        if (!id.isEmpty() && id.trim().length() > 0){
            employeeService.delete(id);
        }
        else {
            throw new IdInvalidException();
        }
    }


    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> getByPage(int page, int pageSize) {
        return employeeService.findByPage(page, pageSize);
    }

}
