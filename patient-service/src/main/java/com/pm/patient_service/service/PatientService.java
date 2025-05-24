package com.pm.patient_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pm.patient_service.repository.PatientRepository;
import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.exception.EmailAlreadyExistsException;
import com.pm.patient_service.exception.PatientNotFoundException;



@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository=patientRepository;
    }

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients=patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("A patient with this email"+"already exists"+patientRequestDTO.getEmail());
        }

        Patient newPatient=patientRepository.save(
            PatientMapper.toPatient(patientRequestDTO)
        );
        return PatientMapper.toDTO(newPatient);

    }

    public PatientResponseDTO updatePatient(UUID id,PatientRequestDTO patientRequestDTO ){
        Patient patient=patientRepository.findById(id).orElseThrow(()->new PatientNotFoundException("Patient Not Found with ID: " + id));
    
        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)){
            throw new EmailAlreadyExistsException("A patient with this email"+"already exists"+patientRequestDTO.getEmail());
        }
        
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateofBirth(LocalDate.parse(patientRequestDTO.getDateofBirth()));

        Patient updatedPatient=patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);


    }

    public void deletePatient(UUID id){
        patientRepository.deleteById(id);
    }


    
}
